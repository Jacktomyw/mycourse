package com.h.mycourse.service;

import com.h.mycourse.dao.CourseDao;
import com.h.mycourse.dao.PostDao;
import com.h.mycourse.dao.ReplyDao;
import com.h.mycourse.dao.UserDao;
import com.h.mycourse.model.Post;
import com.h.mycourse.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BBSService {
    @Autowired
    UserDao userDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    PostDao postDao;
    @Autowired
    ReplyDao replyDao;
    public Post getPostById(long id){
        return postDao.findById(id);
    }
    public List<Post> getAllPostByCourseId(long courseId){
        return postDao.findAllByCourse(courseDao.findById(courseId));
    }
    public List<Reply> getAllReplyByPostId(long postId){
        return replyDao.findAllByPost(postDao.findById(postId));
    }
    public Post addNewPost(long userId, long courseId, String title, String content){
        Post post = new Post();
        post.setAuthor(userDao.findById(userId).getUsername());
        post.setContent(content);
        post.setTitle(title);
        post.setTime(new Timestamp(System.currentTimeMillis()));
        post.setCourse(courseDao.findById(courseId));
        postDao.save(post);
        return post;
    }
    public void addNewReply(long userId, String content, long postId) {
        Reply reply = new Reply();
        reply.setAuthor(userDao.findById(userId).getUsername());
        reply.setContent(content);
        reply.setTime(new Timestamp(System.currentTimeMillis()));
        reply.setPost(postDao.findById(postId));
        replyDao.save(reply);
    }
}
