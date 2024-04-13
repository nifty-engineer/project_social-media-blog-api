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

    @GetMapping("/admin/posts")
    public String posts(Model model) {

        String role = SecurityUtils.getRole().toUpperCase();

        List<PostDTO> posts = null;
        if (ROLE.ADMIN.name().equals(role)) {
            posts = postService.retrieveAllPosts();
        }
        else {
            posts = postService.findPostsByPostedBy();
        }
        model.addAttribute("posts", posts);

        return "/admin/posts";
    }

    @GetMapping("/admin/posts/comments")
    public String allComments(Model model) {

        String role = SecurityUtils.getRole().toUpperCase();
        List<CommentDTO> comments = null;

        if (ROLE.ADMIN.name().equals(role)) {
            comments = commentService.retrieveAllComments();
        }
        else {
            comments = commentService.findCommentsByUserAccount();
        }

        model.addAttribute("comments", comments);
        return "admin/comments";
    }

    @GetMapping("/admin/posts/comments/{comment_id}")
    public String deleteComment(@PathVariable Integer comment_id) {

        commentService.deleteComment(comment_id);
        return "redirect:/admin/posts/comments";
    }

    @GetMapping("admin/posts/newpost")
    public String newPostForm(Model model) { // change to createPostForm
        PostDTO postDTO = new PostDTO();
        model.addAttribute("post", postDTO);
        return "admin/create-post-form";
    }

    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDTO postDTO,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postDTO);
            return "admin/create-post-form";
        }

        postService.createPost(postDTO);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{post_id}/edit")
    public String updatePostForm(@PathVariable Integer post_id,
                                 Model model) {
        PostDTO postDTO = postService.retrievePostByPostId(post_id);
        model.addAttribute("post", postDTO);
        return "admin/update-post-form";
    }

    @PostMapping("/admin/posts/{post_id}")
    public String updatePost(@PathVariable Integer post_id,
                             @Valid @ModelAttribute("post") PostDTO post,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("post", post);
            return "admin/update-post-form";
        }

        post.setPost_id(post_id);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{post_id}/delete")
    public String deletePostByPostId(@PathVariable Integer post_id) {
        postService.deletePostByPostId(post_id);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{post_id}/view")
    public String viewPost(@PathVariable Integer post_id,
                           Model model) {
        PostDTO postDTO = postService.retrievePostByPostId(post_id);
        model.addAttribute("post", postDTO);
        return "admin/view-post";
    }

    // localhost:8080/admin/posts/search?query=java
    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam String query,
                              Model model) {

        List<PostDTO> posts = postService.searchPosts(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
    }
}
