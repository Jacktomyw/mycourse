package com.h.mycourse.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="courses")
public class Course implements Serializable{
    private long id;
    private String courseName;
    private boolean isAvailable = false;
    private boolean isRefused = false;
    private User owner;
    //课件
    //论坛
    private Set<Post> posts = new HashSet<>();
    private Set<AClass> classes = new HashSet<>();
    private Set<Courseware> coursewares = new HashSet<>();

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true, nullable=false)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    @ManyToOne(optional = false)
    @JoinColumn(name="owner")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @OrderBy(value = "id ASC")
    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @OneToMany(mappedBy="course", cascade=CascadeType.ALL,
            fetch=FetchType.LAZY)
    @OrderBy(value="id ASC")
    public Set<AClass> getClasses() {
        return classes;
    }

    public void setClasses(Set<AClass> classes) {
        this.classes = classes;
    }

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @OrderBy(value = "id ASC")
    public Set<Courseware> getCoursewares() {
        return coursewares;
    }

    public void setCoursewares(Set<Courseware> coursewares) {
        this.coursewares = coursewares;
    }
}
