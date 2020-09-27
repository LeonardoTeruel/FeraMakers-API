package com.teruel.feramakers.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class UserStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userStatsId;

    private Long numberOfPosts;

    private Long numberOfComments;

    private Instant lastLogin;

    @NotBlank(message = "User in userStats cannot be null or empty")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userId;

    private Instant createdTime;

    public long getUserStatsId() {
        return userStatsId;
    }

    public void setUserStatsId(long userStatsId) {
        this.userStatsId = userStatsId;
    }

    public long getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(long numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public long getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(long numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public Instant getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Instant lastLogin) {
        this.lastLogin = lastLogin;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }
}
