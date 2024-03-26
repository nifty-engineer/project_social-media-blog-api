package com.example.layeredstructuring.service;

import com.example.layeredstructuring.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    void createComment(Integer post_id, CommentDTO commentDTO);

    List<CommentDTO> retrieveAllComments();

    void deleteComment(Integer commentId);

    List<CommentDTO> findCommentsByUserAccount();
}
