package com.example.layeredstructuring.repository;

import com.example.layeredstructuring.entity.Post;
import com.example.layeredstructuring.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("SELECT p FROM Post p WHERE UPPER(p.post_title) LIKE UPPER(CONCAT('%', ?1, '%')) OR " +
                "UPPER(p.post_summary) LIKE UPPER(CONCAT('%', ?1, '%'))")
    List<Post> searchPosts(String query);

    @Query("SELECT p FROM Post p WHERE p.posted_by = ?1")
    List<Post> findPostsByPosted_By(UserAccount userAccount);
}