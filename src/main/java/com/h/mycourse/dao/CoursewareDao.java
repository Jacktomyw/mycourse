package com.h.mycourse.dao;

import com.h.mycourse.model.Courseware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursewareDao extends JpaRepository<Courseware,Long>{
    Courseware findById(long id);
}
