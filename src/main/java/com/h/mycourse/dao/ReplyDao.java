package com.h.mycourse.dao;

import com.h.mycourse.model.Post;
import com.h.mycourse.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyDao extends JpaRepository<Reply,Long>{
    List<Reply> findAllByPost(Post post);
}
