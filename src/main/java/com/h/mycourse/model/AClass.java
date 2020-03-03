package com.h.mycourse.model;

import org.springframework.core.annotation.Order;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="classes")
public class AClass implements Serializable{
    private long id;
    //时间
    private Date startDate;
    private Date endDate;
    //班次
    private int classNumber;
    //人数
    private int maxStudent;
    private int currentStudent = 0;
    //作业
    private boolean isAvailable = false;
    private boolean isRefused = false;
    private boolean gradeReleased = false;
    private Course course;
    private Set<Grade> grades = new HashSet<>();
    private Set<Homework> homework = new HashSet<>();

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(nullable = false)
    public int getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(int classNumber) {
        this.classNumber = classNumber;
    }

    @Column(nullable = false)
    public int getMaxStudent() {
        return maxStudent;
    }

    public void setMaxStudent(int maxStudent) {
        this.maxStudent = maxStudent;
    }

    @Column(nullable = false)
    public int getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(int currentStudent) {
        this.currentStudent = currentStudent;
    }

    @Column(nullable = false)
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Column(nullable = false)
    public boolean isRefused() {
        return isRefused;
    }

    public void setRefused(boolean refused) {
        isRefused = refused;
    }

    @Column(nullable = false)
    public boolean isGradeReleased() {
        return gradeReleased;
    }

    public void setGradeReleased(boolean gradeReleased) {
        this.gradeReleased = gradeReleased;
    }

    @ManyToOne(optional=false)
    @JoinColumn(name="course")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @OneToMany(mappedBy="aClass", cascade=CascadeType.ALL,
            fetch=FetchType.LAZY)
    @OrderBy(value="id ASC")
    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL,
            fetch=FetchType.LAZY)
    @OrderBy(value = "id ASC")
    public Set<Homework> getHomework() {
        return homework;
    }

    public void setHomework(Set<Homework> homework) {
        this.homework = homework;
    }
}
