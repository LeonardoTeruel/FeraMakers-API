package com.teruel.feramakers.service;

import com.teruel.feramakers.Mappers.PostMapper;
import com.teruel.feramakers.dto.PostRequest;
import com.teruel.feramakers.dto.PostResponse;
import com.teruel.feramakers.exceptions.CategoryNotFoundException;
import com.teruel.feramakers.exceptions.PostNotFoundException;
import com.teruel.feramakers.model.Category;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import com.teruel.feramakers.repository.CategoryRepository;
import com.teruel.feramakers.repository.PostRepository;
import com.teruel.feramakers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Autowired
    PostMapper postMapper;

    @Transactional
    public void save(PostRequest postRequest) {

        Category category = categoryRepository.findByCategoryName(postRequest.getCategoryName())
                .orElseThrow( () -> new CategoryNotFoundException(postRequest.getCategoryName()));

        postRepository.save(postMapper.map(postRequest, category, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));

        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {

        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByCategory(Long categoryId) {

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId.toString()));

        List<Post> posts = postRepository.findAllByCategory(category);

        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
