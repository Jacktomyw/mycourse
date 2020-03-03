package com.h.mycourse.dao;

import com.h.mycourse.model.DoHomework;
import com.h.mycourse.model.Homework;
import com.h.mycourse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoHomeworkDao extends JpaRepository<DoHomework, Long>{
    DoHomework findByUserAndHomework(User user, Homework homework);
    DoHomework findById(long id);
}
