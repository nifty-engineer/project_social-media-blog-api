package com.example.layeredstructuring.mapper;

import com.example.layeredstructuring.dto.PostDTO;
import com.example.layeredstructuring.entity.Post;

import java.util.stream.Collectors;

public class PostMapper {

    public static PostDTO mapToPostDTO(Post post) {

        return PostDTO.builder()
          .post_id(post.getPost_id())
          .posted_by(post.getPosted_by())
          .post_title(post.getPost_title())
          .post_text(post.getPost_text())
          .post_summary(post.getPost_summary())
          .time_posted_epoch(post.getTime_posted_epoch())
          .time_updated_epoch(post.getTime_updated_epoch())
          .comments(post.getComments().stream()
                        .map(CommentMapper::mapToCommentDTO)
                        .collect(Collectors.toSet()))
          .build();
    }

    public static Post mapToPost(PostDTO postDTO) {

        return Post.builder()
          .post_id(postDTO.getPost_id())
          .posted_by(postDTO.getPosted_by())
          .post_title(postDTO.getPost_title())
          .post_text(postDTO.getPost_text())
          .post_summary(postDTO.getPost_summary())
          .time_posted_epoch(postDTO.getTime_posted_epoch())
          .time_updated_epoch(postDTO.getTime_updated_epoch())
          .build();
    }
}