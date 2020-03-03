package com.h.mycourse.service;

import com.h.mycourse.dao.ClassDao;
import com.h.mycourse.dao.CourseDao;
import com.h.mycourse.dao.GradeDao;
import com.h.mycourse.dao.UserDao;
import com.h.mycourse.model.AClass;
import com.h.mycourse.model.Grade;
import com.h.mycourse.model.User;
import com.h.mycourse.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class ClassService {
    @Autowired
    UserDao userDao;
    @Autowired
    ClassDao classDao;
    @Autowired
    CourseDao courseDao;
    @Autowired
    GradeDao gradeDao;
    public List<AClass> getWaitingClasses(){
        return classDao.findAllByAvailableAndRefused(false, false);
    }
    public List<AClass> getAvailableClasses(){
        return classDao.findAllByAvailableAndRefused(true, false);
    }
    public void passClass(long id){
        AClass aClass = classDao.findById(id);
        if(aClass!=null){
            aClass.setAvailable(true);
            aClass.setRefused(false);
            classDao.save(aClass);
        }
    }
    public void refuseClass(long id){
        AClass aClass = classDao.findById(id);
        if(aClass!=null){
            aClass.setAvailable(false);
            aClass.setRefused(true);
            classDao.save(aClass);
        }
    }
    public String classReleaseCheck(long courseId, Date startDate, Date endDate, int classNumber, int maxStudent){
        if(startDate.after(endDate)){
            return "结束日期必须在开课日期之后！<br>";
        }
        AClass aClass = classDao.findByCourseAndClassNumber(courseDao.findById(courseId),classNumber);
        if(aClass!=null&&!aClass.isRefused()){
            return "班次已存在！<br>";
        }
        if(maxStudent<=0){
            return "限选人数必须大于0！<br>";
        }
        aClass = new AClass();
        aClass.setStartDate(startDate);
        aClass.setEndDate(endDate);
        aClass.setClassNumber(classNumber);
        aClass.setMaxStudent(maxStudent);
        aClass.setCourse(courseDao.findById(courseId));
        classDao.save(aClass);
        return null;
    }
    public List<Grade> getStudentGrades(User user){
        return gradeDao.findAllByUser(user);
    }
    public String checkSelectClass(long id, User user){
        AClass aClass = classDao.findById(id);
        Grade grade = gradeDao.findByUserAndAClass(user,aClass);
        if(grade!=null){
            if(grade.getGrade()!=-2) {
                return "您已选择该课程！";
            }
        }
        if(!aClass.getEndDate().after(new java.util.Date())){
            return "该课程已结束！";
        }
        if(aClass.getMaxStudent()<=aClass.getCurrentStudent()){
            return "该课程已满！";
        }
        if(grade==null) {
            grade = new Grade();
        }
        grade.setGrade(-1);
        grade.setaClass(aClass);
        grade.setUser(userDao.findById(user.getId()));
        gradeDao.save(grade);
        aClass.setCurrentStudent(aClass.getCurrentStudent()+1);
        classDao.save(aClass);
        return null;
    }
    public String quitClass(long classId, long userId){
        AClass aClass = classDao.findById(classId);
        User user = userDao.findById(userId);
        if(aClass!=null&&user!=null){
            Grade grade=gradeDao.findByUserAndAClass(user,aClass);
            if(grade.getaClass().isGradeReleased()){
                return "该课程已出成绩，不可退课！";
            }else{
                grade.setGrade(-2);
                gradeDao.save(grade);
                aClass.setCurrentStudent(aClass.getCurrentStudent()-1);
                classDao.save(aClass);
                return "退课成功！";
            }
        }else {
            return "退课失败，请重试！";
        }
    }
    public Grade getGradeByUserIdAndClassId(long userId, long classId){
        return gradeDao.findByUserAndAClass(userDao.findById(userId),classDao.findById(classId));
    }
    public AClass getByClassId(long classId){
        return classDao.findById(classId);
    }
    public void releaseGrade(Map<String, Double> info, long classID) {
        Grade grade;
        AClass aClass = classDao.findById(classID);
        for(String number : info.keySet()) {
            grade = gradeDao.findByUserAndAClass(userDao.findByNumber(number),aClass);
            if(grade!=null) {
                grade.setGrade(info.get(number));
                gradeDao.save(grade);
            }
        }
        aClass.setGradeReleased(true);
        classDao.save(aClass);
    }
    public void sendEmail(long classID,String title, String content){
        for(Grade grade : classDao.findById(classID).getGrades()){
            MailUtil.sendMail(grade.getUser().getEmail(),title,content);
        }
    }
}
