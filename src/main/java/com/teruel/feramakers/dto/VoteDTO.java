package com.teruel.feramakers.dto;

import com.teruel.feramakers.model.VoteType;

/**
 * Comment DTO (Data Transfer Object) for the Comment API of the FeraMakers Social Network Website
 *
 * @author Leonardo Teruel da Silva Cerozi
 *
 * **/

public class VoteDTO {

    private VoteType voteType;
    private Long postId;

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
