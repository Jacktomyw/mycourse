package com.h.mycourse.controller.user;

import com.h.mycourse.model.Course;
import com.h.mycourse.model.User;
import com.h.mycourse.service.ClassService;
import com.h.mycourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

@Controller
public class TeacherCourseManageController {
    @Autowired
    CourseService courseService;
    @Autowired
    ClassService classService;

    //查看我的课程
    @RequestMapping("/teacher/myCourses")
    public String myCourses(HttpServletRequest request, HttpSession session){
        request.setAttribute("courses",courseService.getTeacherCourses((User)session.getAttribute("user")));
        return "myCourses";
    }

    //课程详情
    @RequestMapping("/teacher/courseDetail")
    public String courseDetail(HttpServletRequest request, @RequestParam(value = "ID")long id){
        request.setAttribute("course", courseService.getCourseByID(id));
        return "courseDetail";
    }

    //发布课程
    @RequestMapping("/teacher/courseRelease")
    public String courseRelease(HttpServletRequest request, @RequestParam(value = "ID")long id){
        request.setAttribute("course",courseService.getCourseByID(id));
        if(request.getAttribute("msg")==null){
            request.setAttribute("msg","");
        }
        return "courseRelease";
    }

    //发布课程操作
    @RequestMapping("/teacher/courseRelease/check")
    public String checkCourseRelease(HttpServletRequest request){
        if(request.getParameter("cancel")!=null){
            return "redirect:/teacher/courseDetail?ID="+request.getParameter("courseId");
        }else{
            long courseId = Long.parseLong(request.getParameter("courseId"));
            if(request.getParameter("startDate").equals("")||request.getParameter("endDate").equals("")||request.getParameter("classNumber").equals("")||request.getParameter("maxStudent").equals("")){
                request.setAttribute("msg","填写内容不能为空！<br>");
                return "forward:/teacher/courseRelease?ID="+courseId;
            }
            Date startDate = Date.valueOf(request.getParameter("startDate"));
            Date endDate = Date.valueOf(request.getParameter("endDate"));
            int classNumber = Integer.parseInt(request.getParameter("classNumber"));
            int maxStudent = Integer.parseInt(request.getParameter("maxStudent"));
            String msg = classService.classReleaseCheck(courseId, startDate, endDate, classNumber, maxStudent);
            if(msg==null){
                request.setAttribute("msg","发布申请已提交，等待审批中。");
                return "message";
            }else{
                request.setAttribute("msg",msg);
                return "forward:/teacher/courseRelease?ID="+courseId;
            }
        }
    }

    //创建新课程
    @RequestMapping("/teacher/createCourse")
    public String createCourse(){
        return "createCourse";
    }

    //创建新课程操作
    @RequestMapping("/teacher/createCourse/check")
    public String checkCreateCourse(HttpServletRequest request, HttpSession session){
        if(request.getParameter("cancel")!=null){
            return "redirect:/teacher/myCourses";
        }else{
            String courseName = request.getParameter("courseName");
            if(courseName==""){
                request.setAttribute("msg","课程名称不能为空！");
                return "forward:/teacher/createCourse";
            }
            Course course = courseService.createCourseCheck((User)session.getAttribute("user"), courseName);
            if(course==null){
                request.setAttribute("msg","课程名称已存在！");
                return "forward:/teacher/createCourse";
            }else{
                request.setAttribute("msg","课程创建成功，等待审批中。<br><a href=\"/teacher/myCourses\">我的课程</a>");
                return "message";
            }
        }
    }

    //群发邮件编辑
    @RequestMapping("/teacher/email")
    public String editEmail(HttpServletRequest request, @RequestParam(value = "classID")long id){
        request.setAttribute("aClass",classService.getByClassId(id));
        return "emailEdit";
    }

    //群发邮件操作
    @RequestMapping("/teacher/email/check")
    public String sendEmail(HttpServletRequest request){
        long classID = Long.parseLong(request.getParameter("classID"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        content = "该邮件来自MyCourse系统群发。发送者："+classService.getByClassId(classID).getCourse().getOwner().getUsername()+
                System.lineSeparator()+System.lineSeparator()+
                content;
        classService.sendEmail(classID,title,content);
        request.setAttribute("msg","发送成功！<br><a href=\"/teacher/courseDetail?ID="+classService.getByClassId(classID).getCourse().getId()+"\">返回</a>");
        return "message";
    }
}
