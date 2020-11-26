package com.teruel.feramakers.Mappers;

import com.teruel.feramakers.dto.CommentDTO;
import com.teruel.feramakers.dto.VoteDTO;
import com.teruel.feramakers.model.Comment;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import com.teruel.feramakers.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface VoteMapper {

        /**
         * Converts the VoteDTO to Vote
         * @param voteDTO
         * @param post
         * @param user
         * **/
        @Mapping(target = "voteId", ignore = true)
        @Mapping(target = "post", source = "post")
        @Mapping(target = "user", source = "user")
        Vote mapVoteDTOtoVote (VoteDTO voteDTO, Post post, User user);



}
