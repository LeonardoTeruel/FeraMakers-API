package com.teruel.feramakers.dto;

import java.time.Instant;

/**
 * Comment DTO (Data Transfer Object) for the Comment API of the FeraMakers Social Network Website
 *
 * @author Leonardo Teruel da Silva Cerozi
 *
 * **/

public class CommentDTO {

    private Long commentId;

    private Long postId;

    private Instant createdDate;

    private String commentContent;

    private String userName;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
