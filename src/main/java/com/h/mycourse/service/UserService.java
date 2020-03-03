package com.h.mycourse.service;

import com.h.mycourse.dao.UserDao;
import com.h.mycourse.model.User;
import com.h.mycourse.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao dao;
    public void updateInfo(User user){
        dao.save(user);
    }
    public User loginCheck(String email, String password){
        User user = dao.findByEmail(email);
        if(user==null){
            return null;
        }
        if(password.equals(user.getPassword())&&!user.isSignedDown()){
            return user;
        }else{
            return null;
        }
    }
    public User registerCheck(String email, String username, String password, String number, boolean isStudent){
        User user = dao.findByEmail(email);
        if(user!=null&&!user.isSignedDown()){
            return null;
        }else{
            if(user==null) {
                user = new User();
            }
            if(user.isSignedDown()){
                user.setSignedDown(false);
            }
            user.setEmail(email);
            user.setUsername(username);
            user.setNumber(number);
            user.setPassword(password);
            user.setStudent(isStudent);
            user.setCode(MailUtil.sendMail(user.getEmail()));
            dao.save(user);
            return user;
        }
    }
    public void signDown(User user) {
        user.setSignedDown(true);
        dao.save(user);
    }
    public List<User> getAllUsers(){
        return dao.findAll();
    }
    public String activate(HttpSession session, String code){
        User user = dao.findByCode(code);
        if (user != null) {
            if (user.getCode() != null) {
                user.setCode(null);
                dao.save(user);
                session.setAttribute("user",user);
                return "账户激活成功！";
            } else {
                return "账户已被激活！";
            }
        }else{
            return "不存在的验证码！";
        }
    }
}
