package com.h.mycourse.service;

import com.h.mycourse.dao.ClassDao;
import com.h.mycourse.dao.DoHomeworkDao;
import com.h.mycourse.dao.HomeworkDao;
import com.h.mycourse.dao.UserDao;
import com.h.mycourse.model.DoHomework;
import com.h.mycourse.model.Grade;
import com.h.mycourse.model.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

@Service
public class HomeworkService {
    @Autowired
    HomeworkDao homeworkDao;
    @Autowired
    DoHomeworkDao doHomeworkDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ClassDao classDao;
    public Homework getHomeworkByHomeworkId(long homeworkId){
        return homeworkDao.findById(homeworkId);
    }

    public DoHomework getDoHomeworkByUserIdAndHomeworkId(long userId, long homeworkId) {
        return doHomeworkDao.findByUserAndHomework(userDao.findById(userId),homeworkDao.findById(homeworkId));
    }

    public void addNewHomework(String homeworkName, Timestamp ddl, String description, long aClassId){
        Homework homework = new Homework();
        homework.setHomeworkName(homeworkName);
        homework.setDdl(ddl);
        homework.setDescription(description);
        homework.setaClass(classDao.findById(aClassId));
        homeworkDao.save(homework);
    }

    public void uploadNewHomework(long userId, String path, long homeworkId){
        DoHomework doHomework = new DoHomework();
        doHomework.setUser(userDao.findById(userId));
        doHomework.setPath(path);
        doHomework.setHomework(homeworkDao.findById(homeworkId));
        doHomeworkDao.save(doHomework);
    }

    public void updateHomework(long homeworkId, String newPath){
        DoHomework doHomework = doHomeworkDao.findById(homeworkId);
        doHomework.setPath(newPath);
        doHomeworkDao.save(doHomework);
    }

    public DoHomework getDoHomeworkByDoHomeworkId(long doHomeworkId){
        return doHomeworkDao.findById(doHomeworkId);
    }

    public void releaseScore(Map<String, Double> info, long id) {
        DoHomework doHomework;
        for(String number : info.keySet()){
            doHomework = doHomeworkDao.findByUserAndHomework(userDao.findByNumber(number),homeworkDao.findById(id));
            if(doHomework!=null){
                doHomework.setScore(info.get(number));
                doHomeworkDao.save(doHomework);
            }
        }
    }
}
