package com.h.mycourse.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="grades")
public class Grade implements Serializable{
    private long id;
    private double grade = -1;
    private User user;
    private AClass aClass;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name="userid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    @ManyToOne(optional = false)
    @JoinColumn(name="classid")
    public AClass getaClass() {
        return aClass;
    }

    public void setaClass(AClass aClass) {
        this.aClass = aClass;
    }
}
