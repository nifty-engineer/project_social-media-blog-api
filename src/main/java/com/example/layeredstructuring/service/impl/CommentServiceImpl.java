package com.example.layeredstructuring.service.impl;

import com.example.layeredstructuring.dto.CommentDTO;
import com.example.layeredstructuring.entity.Comment;
import com.example.layeredstructuring.entity.Post;
import com.example.layeredstructuring.entity.UserAccount;
import com.example.layeredstructuring.mapper.CommentMapper;
import com.example.layeredstructuring.repository.CommentRepository;
import com.example.layeredstructuring.repository.PostRepository;
import com.example.layeredstructuring.repository.UserAccountRepository;
import com.example.layeredstructuring.service.CommentService;
import com.example.layeredstructuring.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserAccountRepository userAccountRepository;

    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserAccountRepository userAccountRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public void createComment(Integer postId, CommentDTO commentDTO) {

        Comment comment = CommentMapper.mapToComment(commentDTO);
        Post post = postRepository.findById(postId).get();
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDTO> retrieveAllComments() {

        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                    .map(CommentMapper::mapToCommentDTO)
                    .collect(Collectors.toList());
    }

    @Override
    public void deleteComment(Integer commentId) {

        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDTO> findCommentsByUserAccount() {

        String email = SecurityUtils.getSignedInUserAccount().getUsername();
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        List<Comment> commentsByUserAccount = commentRepository.findCommentsByUserAccount(userAccount);
        return commentsByUserAccount.stream()
          .map(CommentMapper::mapToCommentDTO)
          .collect(Collectors.toList());
    }
}
