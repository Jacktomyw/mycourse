package com.h.mycourse.controller.course;

import com.h.mycourse.model.Post;
import com.h.mycourse.model.User;
import com.h.mycourse.service.BBSService;
import com.h.mycourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BBSController {
    @Autowired
    CourseService courseService;
    @Autowired
    BBSService bbsService;

    //课程论坛首页
    @RequestMapping("/bbs")
    public String bbsMain(HttpSession session, HttpServletRequest request, @RequestParam(name = "courseID")long courseID){
        request.setAttribute("course",courseService.getCourseByID(courseID));
        request.setAttribute("posts",bbsService.getAllPostByCourseId(courseID));
        User user = (User) session.getAttribute("user");
        if(user.isStudent()){
            request.setAttribute("url","/student/classDetail?ID="+session.getAttribute("classID"));
            request.setAttribute("my","/student/myClasses");
        }else{
            request.setAttribute("url","/teacher/courseDetail?ID="+courseID);
            request.setAttribute("my","/teacher/myCourses");
        }
        return "bbsMain";
    }

    //帖子
    @RequestMapping("/bbs/post")
    public String bbsPost(HttpServletRequest request, @RequestParam(name = "ID")long id){
        request.setAttribute("post", bbsService.getPostById(id));
        request.setAttribute("replies", bbsService.getAllReplyByPostId(id));
        return "bbsPost";
    }

    //发布新帖
    @RequestMapping("/bbs/addPost")
    public String bbsPostAdd(HttpServletRequest request, HttpSession session){
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        Post post = bbsService.addNewPost(((User)session.getAttribute("user")).getId(),Long.parseLong(request.getParameter("courseID")),title,content);
        return "redirect:/bbs/post?ID="+post.getId();
    }

    //发布回复
    @RequestMapping("/bbs/post/addReply")
    public String bbsReplyAdd(HttpServletRequest request, HttpSession session){
        String content = request.getParameter("content");
        String postId = request.getParameter("postID");
        bbsService.addNewReply(((User)session.getAttribute("user")).getId(), content, Long.parseLong(postId));
        return "redirect:/bbs/post?ID="+postId;
    }
}
