package com.h.mycourse.controller.aclass;

import com.h.mycourse.model.DoHomework;
import com.h.mycourse.model.Homework;
import com.h.mycourse.model.User;
import com.h.mycourse.service.ClassService;
import com.h.mycourse.service.HomeworkService;
import com.h.mycourse.util.ExcelUtil;
import com.h.mycourse.util.FileDownloadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.Timestamp;
import java.util.Map;

@Controller
public class HomeworkController {
    @Autowired
    ClassService classService;
    @Autowired
    HomeworkService homeworkService;
    //教师作业列表
    @RequestMapping("/homework/teacher")
    public String homeworkListForTeacher(HttpServletRequest request, @RequestParam(name = "classID")long id){
        request.setAttribute("aClass",classService.getByClassId(id));
        return "teacherHomeworkList";
    }

    //教师作业详情
    @RequestMapping("/homework/teacher/detail")
    public String homeworkDetailForTeacher(HttpServletRequest request, @RequestParam(name = "ID")long id){
        request.setAttribute("homework",homeworkService.getHomeworkByHomeworkId(id));
        return "teacherHomeworkDetail";
    }

    //下载学生作业
    @RequestMapping(value = "/homework/download",method = RequestMethod.GET)
    public void homeworkDownload(HttpServletResponse response, @RequestParam(name = "doHomeworkID")long id){
        String path = homeworkService.getDoHomeworkByDoHomeworkId(id).getPath();
        FileDownloadUtil.giveFile(response,path);
    }

    //学生作业列表
    @RequestMapping("/homework/student")
    public String homeworkListForStudent(HttpServletRequest request, @RequestParam(name = "classID")long id){
        request.setAttribute("aClass",classService.getByClassId(id));
        return "studentHomeworkList";
    }

    //学生作业详情
    @RequestMapping("/homework/student/detail")
    public String homeworkDetailForStudent(HttpServletRequest request, HttpSession session, @RequestParam(name = "ID")long id){
        request.setAttribute("homework",homeworkService.getHomeworkByHomeworkId(id));
        request.setAttribute("doHomework",homeworkService.getDoHomeworkByUserIdAndHomeworkId(((User)session.getAttribute("user")).getId(),id));
        return "studentHomeworkDetail";
    }

    //布置新作业
    @RequestMapping("/homework/teacher/addNewHomework")
    public String addNewHomework(HttpServletRequest request, @RequestParam(name = "classID")long id){
        if(request.getParameter("homeworkName").equals("")||request.getParameter("description").equals("")||request.getParameter("ddlDate").equals("")||request.getParameter("ddlTime").equals("")){
            request.setAttribute("msg","填写内容不能为空！<br>" +
                    "<a href=\"/homework/teacher?classID="+id+"\">返回</a>");
            return "message";
        }else{
            String homeworkName = request.getParameter("homeworkName");
            String description = request.getParameter("description");
            Timestamp ddl = Timestamp.valueOf(request.getParameter("ddlDate")+" "+request.getParameter("ddlTime")+":00");
            if(ddl.before(new Timestamp(System.currentTimeMillis()))){
                request.setAttribute("msg","截止日期在当前时间之前！<br>" +
                        "<a href=\"/homework/teacher?classID="+id+"\">返回</a>");
                return "message";
            }
            homeworkService.addNewHomework(homeworkName,ddl,description,id);
            return "redirect:/homework/teacher?classID=" + id;
        }
    }

    //上传作业
    @RequestMapping(value = "/homework/student/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadHomework(HttpSession session, HttpServletRequest request, @RequestParam(name = "homeworkFile")MultipartFile file, @RequestParam(name = "homeworkID")long id)throws IOException{
        User user = (User) session.getAttribute("user");
        Homework homework = homeworkService.getHomeworkByHomeworkId(id);
        File path = new File("E:/receiveFiles/homework/"+homework.getaClass().getCourse().getId()+"/"+homework.getaClass().getClassNumber()+"/"+homework.getId());
        path.mkdirs();
        File convertFile = new File(path+"/"+user.getNumber()+"_"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        homeworkService.uploadNewHomework(user.getId(),convertFile.getAbsolutePath(),homework.getId());
        request.setAttribute("msg","上传成功！<br><a href=\"/homework/student/detail?ID=+"+homework.getId()+"\">查看作业详情</a>");
        return "message";
    }

    //更新作业
    @RequestMapping(value = "/homework/student/update", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateHomework(HttpServletRequest request, @RequestParam(name = "homeworkFile")MultipartFile file, @RequestParam(name = "doHomeworkID")long id)throws IOException{
        DoHomework doHomework = homeworkService.getDoHomeworkByDoHomeworkId(id);
        File originalFile = new File(doHomework.getPath());
        originalFile.delete();
        File path = new File(doHomework.getPath().substring(0, doHomework.getPath().lastIndexOf("\\")));
        path.mkdirs();
        File convertFile = new File(path+"/"+doHomework.getUser().getNumber()+"_"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        homeworkService.updateHomework(doHomework.getId(), convertFile.getAbsolutePath());
        request.setAttribute("msg","上传成功！<br><a href=\"/homework/student/detail?ID=+"+doHomework.getHomework().getId()+"\">查看作业详情</a>");
        return "message";
    }

    //上传作业成绩excel
    @RequestMapping(value = "/homework/teacher/uploadScore", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadScore(HttpServletRequest request, @RequestParam(name = "excel")MultipartFile excel, @RequestParam(name = "homeworkID")long id){
        Map<String, Double> info = ExcelUtil.getExcelInfo(excel);
        homeworkService.releaseScore(info, id);
        request.setAttribute("msg","发布成功！<br><a href=\"/homework/teacher/detail?ID="+id+"\">查看作业详情</a>");
        return "message";
    }
}
