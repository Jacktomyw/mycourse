package com.h.mycourse.controller.user;

import com.h.mycourse.model.AClass;
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

@Controller
public class StudentClassManageController {
    @Autowired
    ClassService classService;
    @Autowired
    CourseService courseService;

    //学生班级列表
    @RequestMapping("/student/myClasses")
    public String myClasses(HttpServletRequest request, HttpSession session){
        request.setAttribute("grades",classService.getStudentGrades((User)session.getAttribute("user")));
        return "myClasses";
    }

    //学生选课列表
    @RequestMapping("/student/myClasses/selectClass")
    public String selectClass(HttpServletRequest request){
        request.setAttribute("classes",classService.getAvailableClasses());
        return "selectClass";
    }

    //选课操作
    @RequestMapping("/student/myClasses/selectClass/check")
    public String selectClassCheck(HttpServletRequest request, HttpSession session, @RequestParam(value="ID")long id){
        String msg = classService.checkSelectClass(id, (User)session.getAttribute("user"));
        if(msg==null){
            msg="选课成功！";
        }
        msg = msg + "<br><a href=\"/student/myClasses\">我的课程</a>" +
                "<br><a href=\"/student/myClasses/selectClass\">继续选课</a>";
        request.setAttribute("msg",msg);
        return "message";
    }

    //退课操作
    @RequestMapping("/student/myClasses/quitClass")
    public String quitClass(HttpServletRequest request, HttpSession session, @RequestParam(value="ID")long id){
        String msg = classService.quitClass(id, ((User)session.getAttribute("user")).getId());
        msg = msg + "<br><a href=\"/student/myClasses\">我的课程</a>";
        request.setAttribute("msg",msg);
        return "message";
    }

    //班级详情
    @RequestMapping("/student/classDetail")
    public String classDetail(HttpServletRequest request,HttpSession session, @RequestParam(value = "ID")long id){
        session.setAttribute("classID",id);
        request.setAttribute("grade",classService.getGradeByUserIdAndClassId(((User)session.getAttribute("user")).getId(),id));
        return "classDetail";
    }
}
