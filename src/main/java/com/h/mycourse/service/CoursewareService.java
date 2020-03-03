package com.h.mycourse.service;

import com.h.mycourse.dao.CourseDao;
import com.h.mycourse.dao.CoursewareDao;
import com.h.mycourse.model.Course;
import com.h.mycourse.model.Courseware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CoursewareService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    CoursewareDao coursewareDao;
    public void addNewCourseware(long courseId, String filename, String path){
        Courseware courseware = new Courseware();
        courseware.setCourse(courseDao.findById(courseId));
        courseware.setFileName(filename);
        courseware.setPath(path);
        coursewareDao.save(courseware);
    }

    public void deleteCourseware(long coursewareId){
        File file = new File(coursewareDao.findById(coursewareId).getPath());
        file.delete();
        coursewareDao.deleteById(coursewareId);
    }

    public Courseware getById(long id){
        return coursewareDao.findById(id);
    }
}
