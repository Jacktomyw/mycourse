package com.h.mycourse.service;

import com.h.mycourse.dao.CourseDao;
import com.h.mycourse.model.Course;
import com.h.mycourse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseDao dao;
    public Course createCourseCheck(User user, String courseName){
        Course course = dao.findByCourseName(courseName);
        if(course!=null){
            return null;
        }else{
            course = new Course();
            course.setCourseName(courseName);
            course.setOwner(user);
            dao.save(course);
            return course;
        }
    }
    public List<Course> getTeacherCourses(User user){
        return dao.findAllByOwner(user);
    }
    public Course getCourseByID(long ID){
        return dao.findById(ID);
    }
    public List<Course> getWaitingCourses(){
        return dao.findAllByAvailableAndRefused(false, false);
    }
    public void passCourse(long id){
        Course course = dao.findById(id);
        if(course!=null){
            course.setAvailable(true);
            course.setRefused(false);
            dao.save(course);
        }
    }
    public void refuseCourse(long id){
        Course course = dao.findById(id);
        if(course!=null){
            course.setAvailable(false);
            course.setRefused(true);
            dao.save(course);
        }
    }
}
