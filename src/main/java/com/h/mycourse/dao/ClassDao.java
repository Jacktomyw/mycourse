package com.h.mycourse.dao;

import com.h.mycourse.model.AClass;
import com.h.mycourse.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassDao extends JpaRepository<AClass,Long> {
    AClass findById(long id);
    List<AClass> findAllByAvailableAndRefused(Boolean isAvailable, Boolean isRefused);
    AClass findByCourseAndClassNumber(Course course, int ClassNumber);
}
