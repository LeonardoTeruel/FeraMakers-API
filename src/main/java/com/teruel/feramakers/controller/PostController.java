package com.teruel.feramakers.controller;

import com.teruel.feramakers.dto.PostRequest;
import com.teruel.feramakers.dto.PostResponse;
import com.teruel.feramakers.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping ("/api/post")
public class PostController {

     @Autowired
     PostService postService;

     @PostMapping
     public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest) {
          postService.save(postRequest);
          return new ResponseEntity<>("Post Successfully Created!",
                  HttpStatus.OK);
     }

     @GetMapping
     public ResponseEntity<List<PostResponse>> getAllPosts() {
          return status(HttpStatus.OK).body(postService.getAllPosts());
     }

     @GetMapping("/{postId}")
     public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
          return status(HttpStatus.OK).body(postService.getPost(postId));
     }

     @GetMapping("bycategory/{categoryId}")
     public ResponseEntity<List<PostResponse>> getPostsByCategory(@PathVariable Long categoryId) {
          return status(HttpStatus.OK).body(postService.getPostsByCategory(categoryId));
     }

     @GetMapping("byuser/{username}")
     public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String username) {
          return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
     }

}
