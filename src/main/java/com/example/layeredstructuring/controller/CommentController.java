package com.example.layeredstructuring.controller;

import com.example.layeredstructuring.dto.CommentDTO;
import com.example.layeredstructuring.dto.PostDTO;
import com.example.layeredstructuring.service.CommentService;
import com.example.layeredstructuring.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CommentController {

    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/{post_id}/comments")
    public String createComment(@PathVariable Integer post_id,
                                @Valid @ModelAttribute("comment") CommentDTO commentDTO,
                                BindingResult result,
                                Model model) {

        PostDTO postDTO = postService.retrievePostByPostId(post_id);
        if(result.hasErrors()) {
            model.addAttribute("post", postDTO);
            model.addAttribute("comment", commentDTO);
            return "guest/view-post";
        }
        commentService.createComment(post_id, commentDTO);
        return "redirect:/posts/" + post_id;
    }
}
