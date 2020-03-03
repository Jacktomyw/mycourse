package com.h.mycourse.dao;

import com.h.mycourse.model.AClass;
import com.h.mycourse.model.Grade;
import com.h.mycourse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeDao extends JpaRepository<Grade, Long>{
    List<Grade> findAllByUser(User user);
    Grade findByUserAndAClass(User user, AClass aClass);
}
