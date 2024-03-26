package com.example.layeredstructuring.mapper;

import com.example.layeredstructuring.dto.CommentDTO;
import com.example.layeredstructuring.entity.Comment;

public class CommentMapper {

    public static CommentDTO mapToCommentDTO(Comment comment) {

        return CommentDTO.builder()
                  .comment_id(comment.getComment_id())
                  .guest_name(comment.getGuest_name())
                  .guest_email(comment.getGuest_email())
                  .comment_text(comment.getComment_text())
                  .time_posted_epoch(comment.getTime_posted_epoch())
                  .time_updated_epoch(comment.getTime_updated_epoch())
                  .build();
    }

    public static Comment mapToComment(CommentDTO commentDTO) {

        return Comment.builder()
          .comment_id(commentDTO.getComment_id())
          .guest_name(commentDTO.getGuest_name())
          .guest_email(commentDTO.getGuest_email())
          .comment_text(commentDTO.getComment_text())
          .time_posted_epoch(commentDTO.getTime_posted_epoch())
          .time_updated_epoch(commentDTO.getTime_updated_epoch())
          .build();
    }
}
