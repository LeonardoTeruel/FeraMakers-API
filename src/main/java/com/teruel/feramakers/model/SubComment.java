package com.teruel.feramakers.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
public class SubComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long subCommentId;

    @NotBlank
    private String subCommentContent;

    @NotBlank
    @ManyToOne (fetch =FetchType.LAZY)
    @JoinColumn (name = "commentId", referencedColumnName = "commentId")
    private Comment comment;

    @NotBlank
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private Instant createdDate;

    public long getSubCommentId() {
        return subCommentId;
    }

    public void setSubCommentId(long subCommentId) {
        this.subCommentId = subCommentId;
    }

    public String getSubCommentContent() {
        return subCommentContent;
    }

    public void setSubCommentContent(String subCommentContent) {
        this.subCommentContent = subCommentContent;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "SubComment{" +
                "subCommentId=" + subCommentId +
                ", subCommentContent='" + subCommentContent + '\'' +
                ", comment=" + comment +
                ", user=" + user +
                ", createdDate=" + createdDate +
                '}';
    }
}
