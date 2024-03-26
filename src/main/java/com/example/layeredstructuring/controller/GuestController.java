package com.example.layeredstructuring.controller;

import com.example.layeredstructuring.dto.CommentDTO;
import com.example.layeredstructuring.dto.PostDTO;
import com.example.layeredstructuring.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class GuestController {

    private PostService postService;

    public GuestController(PostService postService) {
        this.postService = postService;
    }

    // http://localhost:8080/
    @GetMapping("/")
    public ModelAndView viewBlogPosts() {

        ModelAndView mav = new ModelAndView("guest/view-posts");

        List<PostDTO> postsResponse = postService.retrieveAllPosts();
        mav.addObject("postsResponse", postsResponse);

        return mav;
    }

    @GetMapping("/posts/{post_id}")
    private ModelAndView viewPost(@PathVariable Integer post_id) {

        ModelAndView mav = new ModelAndView("guest/view-post");

        PostDTO postDTO = postService.retrievePostByPostId(post_id);
        CommentDTO commentDTO = new CommentDTO();

        mav.addObject("post", postDTO);
        mav.addObject("comment", commentDTO);

        return mav;
    }

// localhost:8080/posts/search?query=java
//    @GetMapping("/posts/search")
//    public String searchPosts(@RequestParam String query,
//                              Model model) {
//
//        List<PostDTO> postsResponse = postService.searchPosts(query);
//        model.addAttribute("postsResponse", postsResponse);
//        return "guest/view-posts";
//    }

    @GetMapping("/posts/search")
    public ModelAndView searchPosts(@RequestParam String query) {

        ModelAndView mav = new ModelAndView("guest/view-posts");

        List<PostDTO> postsResponse = postService.searchPosts(query);
        mav.addObject("postsResponse", postsResponse);

        return mav;
    }
}
