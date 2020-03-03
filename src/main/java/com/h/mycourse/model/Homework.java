package com.h.mycourse.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "homework")
public class Homework {
    private long id;
    private String homeworkName;
    private String description;
    private Timestamp ddl;

    private AClass aClass;
    private Set<DoHomework> doHomework;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public Timestamp getDdl() {
        return ddl;
    }

    public void setDdl(Timestamp ddl) {
        this.ddl = ddl;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "aClass")
    public AClass getaClass() {
        return aClass;
    }

    public void setaClass(AClass aClass) {
        this.aClass = aClass;
    }

    @OneToMany(mappedBy = "homework", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @OrderBy(value = "id ASC")
    public Set<DoHomework> getDoHomework() {
        return doHomework;
    }

    public void setDoHomework(Set<DoHomework> doHomework) {
        this.doHomework = doHomework;
    }
}
