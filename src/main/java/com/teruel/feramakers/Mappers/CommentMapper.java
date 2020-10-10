package com.teruel.feramakers.Mappers;

import com.teruel.feramakers.dto.CommentDTO;
import com.teruel.feramakers.model.Comment;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper class of the Comments
 * Converts the Comment Data Transfer Object (DTO) to Comment Object and vice-versa
 *
 * @author Leonardo Teruel da Silva Cerozi
 *
 * **/

@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * Converts the CommentDTO to Comment
     * @param commentDTO
     * @param post
     * @param user
     * **/
    @Mapping(target = "commentId", ignore = true)
    @Mapping(target = "commentContent", source = "commentDTO.commentContent")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment mapCommentDTOtoComment (CommentDTO commentDTO, Post post, User user);

    /**
     * Converts the Comment to CommentDTO
     * @param comment
     * **/
    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDTO mapCommentToCommentDTO (Comment comment);
}
