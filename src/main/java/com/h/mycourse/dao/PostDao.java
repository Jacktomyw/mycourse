package com.h.mycourse.dao;

import com.h.mycourse.model.Course;
import com.h.mycourse.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDao extends JpaRepository<Post,Long>{
    List<Post> findAllByCourse(Course course);
    Post findById(long id);
}
