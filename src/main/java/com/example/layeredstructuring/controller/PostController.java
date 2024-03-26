package com.example.layeredstructuring.controller;

import com.example.layeredstructuring.dto.CommentDTO;
import com.example.layeredstructuring.dto.PostDTO;
import com.example.layeredstructuring.service.CommentService;
import com.example.layeredstructuring.service.PostService;
import com.example.layeredstructuring.util.ROLE;
import com.example.layeredstructuring.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping("/owner/posts")
    public String posts(Model model) {

        String role = SecurityUtils.getRole().toUpperCase();

        List<PostDTO> posts = null;
        if (ROLE.OWNER.name().equals(role)) {
            posts = postService.retrieveAllPosts();
        }
        else {
            posts = postService.findPostsByPostedBy();
        }
        model.addAttribute("posts", posts);

        return "/owner/posts";
    }

    @GetMapping("/owner/posts/comments")
    public String allComments(Model model) {

        String role = SecurityUtils.getRole().toUpperCase();
        List<CommentDTO> comments = null;

        if (ROLE.OWNER.name().equals(role)) {
            comments = commentService.retrieveAllComments();
        }
        else {
            comments = commentService.findCommentsByUserAccount();
        }

        model.addAttribute("comments", comments);
        return "owner/comments";
    }

    @GetMapping("/owner/posts/comments/{comment_id}")
    public String deleteComment(@PathVariable Integer comment_id) {

        commentService.deleteComment(comment_id);
        return "redirect:/owner/posts/comments";
    }

    @GetMapping("owner/posts/newpost")
    public String newPostForm(Model model) { // change to createPostForm
        PostDTO postDTO = new PostDTO();
        model.addAttribute("post", postDTO);
        return "owner/create-post-form";
    }

    @PostMapping("/owner/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDTO postDTO,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postDTO);
            return "owner/create-post-form";
        }

        postService.createPost(postDTO);
        return "redirect:/owner/posts";
    }

    @GetMapping("/owner/posts/{post_id}/edit")
    public String updatePostForm(@PathVariable Integer post_id,
                                 Model model) {
        PostDTO postDTO = postService.retrievePostByPostId(post_id);
        model.addAttribute("post", postDTO);
        return "owner/update-post-form";
    }

    @PostMapping("/owner/posts/{post_id}")
    public String updatePost(@PathVariable Integer post_id,
                             @Valid @ModelAttribute("post") PostDTO post,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "owner/update-post-form";
        }

        post.setPost_id(post_id);
        postService.updatePost(post);
        return "redirect:/owner/posts";
    }

    @GetMapping("/owner/posts/{post_id}/delete")
    public String deletePostByPostId(@PathVariable Integer post_id) {
        postService.deletePostByPostId(post_id);
        return "redirect:/owner/posts";
    }

    @GetMapping("/owner/posts/{post_id}/view")
    public String viewPost(@PathVariable Integer post_id,
                           Model model) {
        PostDTO postDTO = postService.retrievePostByPostId(post_id);
        model.addAttribute("post", postDTO);
        return "owner/view-post";
    }

    // localhost:8080/owner/posts/search?query=java
    @GetMapping("/owner/posts/search")
    public String searchPosts(@RequestParam String query,
                              Model model) {

        List<PostDTO> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "owner/posts";
    }
}
