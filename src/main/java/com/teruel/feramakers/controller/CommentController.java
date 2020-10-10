package com.teruel.feramakers.controller;

import com.teruel.feramakers.Mappers.CommentMapper;
import com.teruel.feramakers.dto.CommentDTO;
import com.teruel.feramakers.exceptions.PostNotFoundException;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import com.teruel.feramakers.repository.CommentRepository;
import com.teruel.feramakers.repository.PostRepository;
import com.teruel.feramakers.repository.UserRepository;
import com.teruel.feramakers.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
/**
 * Controller class of the Comments API for the Feramakers Social Network WebSite
 *
 * @author Leonardo Teruel da Silva Cerozi
 *
 * **/

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    /**
     * Create and save a Comment made in a Post
     * @param commentDTO
     * **/
    @PostMapping
    public ResponseEntity<String> createComment(@RequestBody CommentDTO commentDTO) {

        commentService.save(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Comment Successfuly created");
    }

    /**
     * Return all Comments from a Post
     * @param postId
     * **/
    @GetMapping("/byPost/{postId}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForPost(postId));
    }

    /**
     * Return all Comments made by an specific User
     * @param userName
     * **/
    @GetMapping("/byUser/{userName}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsForUser(@PathVariable String userName){
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForUser(userName));
    }

}
