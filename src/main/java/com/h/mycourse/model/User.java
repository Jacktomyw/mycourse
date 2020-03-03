package com.h.mycourse.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User implements Serializable{
    private long id;
    private String username;
    private String email;
    private String number;
    private String password;
    private String code;
    private boolean isStudent;
    private boolean isAdmin = false;
    private boolean isSignedDown = false;
    private Set<Course> courses = new HashSet<>();
    private Set<Grade> grades = new HashSet<>();
    private Set<DoHomework> doHomework = new HashSet<>();

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(unique=true, nullable=false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable=false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(unique=true, nullable=false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(unique=true)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(nullable = false)
    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    @Column(nullable = false)
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Column(nullable = false)
    public boolean isSignedDown() {
        return isSignedDown;
    }

    public void setSignedDown(boolean signedDown) {
        isSignedDown = signedDown;
    }
    @OneToMany(mappedBy = "owner", cascade=CascadeType.ALL,
            fetch=FetchType.LAZY)
    @OrderBy(value = "id ASC")
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @OrderBy(value = "id ASC")
    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @OrderBy(value = "id ASC")
    public Set<DoHomework> getDoHomework() {
        return doHomework;
    }

    public void setDoHomework(Set<DoHomework> doHomework) {
        this.doHomework = doHomework;
    }
}
