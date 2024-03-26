package com.example.layeredstructuring.service.impl;

import com.example.layeredstructuring.dto.PostDTO;
import com.example.layeredstructuring.entity.Post;
import com.example.layeredstructuring.entity.UserAccount;
import com.example.layeredstructuring.mapper.PostMapper;
import com.example.layeredstructuring.repository.PostRepository;
import com.example.layeredstructuring.repository.UserAccountRepository;
import com.example.layeredstructuring.service.PostService;
import com.example.layeredstructuring.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserAccountRepository userAccountRepository;

    public PostServiceImpl(PostRepository postRepository,
                           UserAccountRepository userAccountRepository) {
        this.postRepository = postRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<PostDTO> retrieveAllPosts() {

        return postRepository.findAll()
          .stream()
          .map(PostMapper::mapToPostDTO)
          .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> findPostsByPostedBy() {
        String email = SecurityUtils.getSignedInUserAccount().getUsername();
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        List<Post> posts = postRepository.findPostsByPosted_By(userAccount);
        return posts.stream()
          .map(PostMapper::mapToPostDTO)
          .collect(Collectors.toList());
    }

    @Override
    public void createPost(PostDTO postDTO) {

        String email = SecurityUtils.getSignedInUserAccount().getUsername();
        UserAccount userAccount = userAccountRepository.findByEmail(email);

        Post post = PostMapper.mapToPost(postDTO);
        post.setPosted_by(userAccount);
        postRepository.save(post);
    }

    @Override
    public PostDTO retrievePostByPostId(Integer post_id) {

        Post post = postRepository.findById(post_id).get();
        return PostMapper.mapToPostDTO(post);
    }

    @Override
    public void updatePost(PostDTO postDTO) {

        String email = SecurityUtils.getSignedInUserAccount().getUsername();
        UserAccount posted_by = userAccountRepository.findByEmail(email);

        Post post = PostMapper.mapToPost(postDTO);
        post.setPosted_by(posted_by);
        postRepository.save(post);
    }

    @Override
    public void deletePostByPostId(Integer post_id) {

        postRepository.deleteById(post_id);
    }

    @Override
    public List<PostDTO> searchPosts(String query) {

        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream()
          .map(PostMapper::mapToPostDTO)
          .collect(Collectors.toList());
    }
}
