package com.teruel.feramakers.Mappers;

import com.teruel.feramakers.dto.PostRequest;
import com.teruel.feramakers.dto.PostResponse;
import com.teruel.feramakers.model.Category;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "postContent", source = "postRequest.postContent")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "postVoteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Category category, User user);

    @Mapping(target = "postId", source = "postId")
    @Mapping(target = "categoryName", source = "category.categoryName")
    @Mapping(target = "username", source = "user.username")
    //@Mapping(target = "commentCount", expression = "java(commentCount(post))")
    //@Mapping(target = "duration", expression = "java(getDuration(post))")
    //@Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    //@Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

}