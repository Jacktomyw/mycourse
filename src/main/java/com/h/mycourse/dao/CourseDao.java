package com.h.mycourse.dao;

import com.h.mycourse.model.Course;
import com.h.mycourse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDao extends JpaRepository<Course,Long>{
    Course findByCourseName(String courseName);
    Course findById(long id);
    List<Course> findAllByOwner(User owner);
    List<Course> findAllByAvailableAndRefused(boolean isAvailable, boolean isRefused);
}
