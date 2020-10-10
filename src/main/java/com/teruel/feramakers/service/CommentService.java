package com.teruel.feramakers.service;

import com.teruel.feramakers.Mappers.CommentMapper;
import com.teruel.feramakers.dto.CommentDTO;
import com.teruel.feramakers.exceptions.PostNotFoundException;
import com.teruel.feramakers.model.Comment;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import com.teruel.feramakers.repository.CommentRepository;
import com.teruel.feramakers.repository.PostRepository;
import com.teruel.feramakers.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class of the Comments API for the Feramakers Social Network WebSite
 *
 * @author Leonardo Teruel da Silva Cerozi
 *
 * **/

@Service
public class CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthService authService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CommentRepository commentRepository;

    /**
     * Create and save a Comment made in a Post
     * @param commentDTO
     * **/
    public void save (CommentDTO commentDTO) {

       Post post =  postRepository.findById(commentDTO.getPostId())
               .orElseThrow(() -> new PostNotFoundException(commentDTO.getPostId().toString()));

       Comment comment =  commentMapper.mapCommentDTOtoComment(commentDTO, post, authService.getCurrentUser());

       commentRepository.save(comment);

    }

    /**
     * Return all Comments from a Post
     * @param postId
     * **/
    public List<CommentDTO> getAllCommentsForPost(Long postId) {

        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapCommentToCommentDTO).collect(Collectors.toList());
    }

    /**
     * Return all Comments made by an specific User
     * @param userName
     * **/
    public List<CommentDTO> getAllCommentsForUser(String userName) {

        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapCommentToCommentDTO)
                .collect(Collectors.toList());
    }


}
