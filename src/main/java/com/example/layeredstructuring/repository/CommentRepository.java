package com.example.layeredstructuring.repository;

import com.example.layeredstructuring.entity.Comment;
import com.example.layeredstructuring.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c from Comment c JOIN c.post p\n" +
            "WHERE p.posted_by = ?1")
    List<Comment> findCommentsByUserAccount(UserAccount userAccount);
}
