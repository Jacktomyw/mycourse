package com.h.mycourse.controller.user;

import com.h.mycourse.model.User;
import com.h.mycourse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    //个人信息更新
    @RequestMapping("/updateInfo")
    public String updateInfo(){
        return "updateInfo";
    }

    //更新操作
    @RequestMapping("/updateInfo/update")
    public String update(HttpServletRequest request, HttpSession session){
        if(request.getParameter("update")!=null) {
            User user = (User) session.getAttribute("user");
            user.setEmail(request.getParameter("email"));
            user.setUsername(request.getParameter("username"));
            if(!request.getParameter("password").equals("")){
                user.setPassword(request.getParameter("password"));
            }
            if(request.getParameter("number")!=null){
                user.setNumber(request.getParameter("number"));
            }
            userService.updateInfo(user);
        }
        return "redirect:/main";
    }

    //主界面
    @RequestMapping("/main")
    public String main(HttpSession session, HttpServletRequest request) {
        if(session.getAttribute("user")!=null) {
            if(((User) session.getAttribute("user")).getCode()==null) {
                if (((User) session.getAttribute("user")).isAdmin()) {
                    return "adminMain";
                } else {
                    if (((User) session.getAttribute("user")).isStudent()) {
                        return "studentMain";
                    } else {
                        return "teacherMain";
                    }
                }
            }else{
                request.setAttribute("msg","账户未激活，请先激活！<br><a href=\"/logout\">退出</a>");
                return "message";
            }
        }else{
            return "redirect:index";
        }
    }

    //退出登录
    @RequestMapping(value={"/logout"})
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/index";
    }
}
