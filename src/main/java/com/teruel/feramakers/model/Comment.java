package com.teruel.feramakers.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @NotBlank(message = "Comment Content cannot be null or empty")
    private String commentContent;

    @NotBlank(message = "Post cannot be null or empty")
    @ManyToOne (fetch =FetchType.LAZY)
    @JoinColumn (name = "postId", referencedColumnName = "postId")
    private Post post;

    @NotBlank(message = "User cannot be null or empty")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    private Instant createdDate;

    @OneToMany(fetch = FetchType.LAZY)
    private List<SubComment> subComments;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    public List<SubComment> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<SubComment> subComments) {
        this.subComments = subComments;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", commentContent='" + commentContent + '\'' +
                ", post=" + post +
                ", user=" + user +
                ", createdDate=" + createdDate +
                '}';
    }
}
