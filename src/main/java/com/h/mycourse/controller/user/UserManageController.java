package com.h.mycourse.controller.user;

import com.h.mycourse.model.User;
import com.h.mycourse.service.UserService;
import com.h.mycourse.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserManageController {
    @Autowired
    UserService userService;

    //注销操作
    @RequestMapping("/signDown")
    public String signDown(HttpServletRequest request, HttpSession session){
        userService.signDown((User)session.getAttribute("user"));
        session.invalidate();
        request.setAttribute("msg", "操作成功！账号已注销。");
        return "/message";
    }

    //登录页面
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        if(request.getAttribute("msg")==null){
            request.setAttribute("msg","");
        }
        return "login";
    }

    //登录操作
    @RequestMapping("/login/check")
    public String checkLogin(HttpServletRequest request, HttpSession session){
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        User user = userService.loginCheck(email, password);
        if(user!=null) {
            session.setAttribute("user", user);
            return "redirect:/main";
        }else {
            String msg="邮箱或密码错误！";
            msg=msg+System.lineSeparator();
            request.setAttribute("msg",msg);
            return "forward:/login";
        }
    }

    //注册页面
    @RequestMapping("/register")
    public String register(HttpServletRequest request) {
        if(request.getAttribute("msg")==null){
            request.setAttribute("msg","");
        }
        return "register";
    }

    //注册操作
    @RequestMapping("/register/check")
    public String checkRegister(HttpServletRequest request, HttpSession session){
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String username=request.getParameter("username");
        String number=request.getParameter("number");
        String type=request.getParameter("type");
        boolean isStudent=false;
        if(type.equals("student")){
            isStudent=true;
        }else{
            number=null;
        }
        if(email.equals("")||password.equals("")||username.equals("")){
            String msg="邮箱、密码和姓名不能为空！";
            msg=msg+System.lineSeparator();
            request.setAttribute("msg",msg);
            return "forward:/register";
        }else if(!email.contains("@")||!email.endsWith("nju.edu.cn")){
            String msg="邮箱格式错误！";
            msg=msg+System.lineSeparator();
            request.setAttribute("msg",msg);
            return "forward:/register";
        }
        else {
            User user = userService.registerCheck(email, username, password, number, isStudent);
            if (user != null) {
                session.setAttribute("user", user);
                return "redirect:/main";
            } else {
                String msg = "已注册的邮箱！";
                msg = msg + System.lineSeparator();
                request.setAttribute("msg", msg);
                return "forward:/register";
            }
        }
    }

    //激活操作
    @RequestMapping("/userActivate")
    public String activate(HttpServletRequest request, HttpSession session, @RequestParam(name = "code")String code){
        String msg = userService.activate(session, code);
        request.setAttribute("msg", msg);
        return "message";
    }
}
