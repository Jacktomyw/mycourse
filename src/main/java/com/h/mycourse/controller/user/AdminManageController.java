package com.h.mycourse.controller.user;

import com.h.mycourse.model.User;
import com.h.mycourse.service.ClassService;
import com.h.mycourse.service.CourseService;
import com.h.mycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminManageController {
    @Autowired
    ClassService classService;
    @Autowired
    CourseService courseService;
    @Autowired
    UserService userService;

    //待审批列表
    @RequestMapping("/admin/examine")
    public String examine(HttpServletRequest request){
        request.setAttribute("courses", courseService.getWaitingCourses());
        request.setAttribute("classes", classService.getWaitingClasses());
        return "examine";
    }

    //课程通过审批
    @RequestMapping("/admin/examine/course/pass")
    public String passCourse(HttpServletRequest request, @RequestParam(value = "ID")long id){
        courseService.passCourse(id);
        request.setAttribute("msg","操作成功。<br><a href=\"/admin/examine\">继续审批</a>");
        return "message";
    }

    //班级通过审批
    @RequestMapping("/admin/examine/class/pass")
    public String passClass(HttpServletRequest request, @RequestParam(value = "ID")long id){
        classService.passClass(id);
        request.setAttribute("msg","操作成功。<br><a href=\"/admin/examine\">继续审批</a>");
        return "message";
    }

    //课程拒绝审批
    @RequestMapping("/admin/examine/course/refuse")
    public String refuseCourse(HttpServletRequest request, @RequestParam(value = "ID")long id){
        courseService.refuseCourse(id);
        request.setAttribute("msg","操作成功。<br><a href=\"/admin/examine\">继续审批</a>");
        return "message";
    }

    //班级拒绝审批
    @RequestMapping("/admin/examine/class/refuse")
    public String refuseClass(HttpServletRequest request, @RequestParam(value = "ID")long id){
        classService.refuseClass(id);
        request.setAttribute("msg","操作成功。<br><a href=\"/admin/examine\">继续审批</a>");
        return "message";
    }

    //查看统计信息
    @RequestMapping("/admin/stats")
    public String stats(HttpServletRequest request){
        List<User> users = userService.getAllUsers();
        List<User> students = new ArrayList<>();
        List<User> teachers = new ArrayList<>();
        for(User user : users){
            if(user.isStudent()){
                students.add(user);
            }else{
                if(!user.isAdmin()){
                    teachers.add(user);
                }
            }
        }
        request.setAttribute("students",students);
        request.setAttribute("teachers",teachers);
        return "stats";
    }
}
