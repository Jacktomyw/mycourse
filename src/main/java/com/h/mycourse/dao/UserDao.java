package com.h.mycourse.dao;

import com.h.mycourse.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
    User findByEmail(String email);
    User findById(long id);
    User findByNumber(String number);
    User findByCode(String code);
}
