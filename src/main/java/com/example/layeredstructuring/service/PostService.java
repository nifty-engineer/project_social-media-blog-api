package com.example.layeredstructuring.service;

import com.example.layeredstructuring.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> retrieveAllPosts();

    List<PostDTO> findPostsByPostedBy();

    void createPost(PostDTO postDTO);

    PostDTO retrievePostByPostId(Integer post_id);

    void updatePost(PostDTO postDTO);

    void deletePostByPostId(Integer post_id);

    List<PostDTO> searchPosts(String query);
}