package com.h.mycourse.dao;

import com.h.mycourse.model.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkDao extends JpaRepository<Homework,Long>{
    Homework findById(long id);
}
