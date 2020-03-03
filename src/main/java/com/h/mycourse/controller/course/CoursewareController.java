package com.h.mycourse.controller.course;

import com.h.mycourse.model.Course;
import com.h.mycourse.service.ClassService;
import com.h.mycourse.service.CourseService;
import com.h.mycourse.service.CoursewareService;
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
import java.io.*;

@Controller
public class CoursewareController {
    @Autowired
    CourseService courseService;
    @Autowired
    ClassService classService;
    @Autowired
    CoursewareService coursewareService;

    //教师查看课件
    @RequestMapping("/teacher/courseware")
    public String coursewareTeacher(HttpServletRequest request, @RequestParam(name = "courseID")long id){
        request.setAttribute("course",courseService.getCourseByID(id));
        return "coursewareTeacher";
    }

    //学生查看课件
    @RequestMapping("/student/courseware")
    public String coursewareStudent(HttpServletRequest request, @RequestParam(name = "aClassID")long id){
        request.setAttribute("aClass",classService.getByClassId(id));
        return "coursewareStudent";
    }

    //下载课件
    @RequestMapping(value = "/courseware/download", method = RequestMethod.GET)
    public void coursewareDownload(HttpServletResponse response, @RequestParam(name = "coursewareID")long coursewareID){
        String path = coursewareService.getById(coursewareID).getPath();
        FileDownloadUtil.giveFile(response,path);
    }

    //教师上传课件
    @RequestMapping(value = "/teacher/courseware/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String coursewareUpload(HttpServletRequest request, @RequestParam(name = "file")MultipartFile file, @RequestParam(name = "courseID")long id)throws IOException{
        Course course = courseService.getCourseByID(id);
        File path = new File("E:/receiveFiles/courseware/"+course.getId());
        path.mkdirs();
        File convertFile = new File(path+"/"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        coursewareService.addNewCourseware(course.getId(),convertFile.getName(),convertFile.getAbsolutePath());
        request.setAttribute("msg","上传成功！<br><a href=\"/teacher/courseware?courseID=+"+id+"\">返回课件列表</a>");
        return "message";
    }

    //教师删除课件
    @RequestMapping("/teacher/courseware/delete")
    public String coursewareDelete(HttpServletRequest request, @RequestParam(name = "courseID")long id, @RequestParam(name = "coursewareID")long coursewareID){
        coursewareService.deleteCourseware(coursewareID);
        request.setAttribute("msg","删除成功！<br><a href=\"/teacher/courseware?courseID=+"+id+"\">返回课件列表</a>");
        return "message";
    }
}
