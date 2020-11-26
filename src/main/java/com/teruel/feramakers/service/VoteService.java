package com.teruel.feramakers.service;

import com.teruel.feramakers.Mappers.VoteMapper;
import com.teruel.feramakers.dto.VoteDTO;
import com.teruel.feramakers.exceptions.FeraMakersException;
import com.teruel.feramakers.exceptions.PostNotFoundException;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.Vote;
import com.teruel.feramakers.model.VoteType;
import com.teruel.feramakers.repository.PostRepository;
import com.teruel.feramakers.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    AuthService authService;

    @Autowired
    VoteMapper voteMapper;

    @Transactional
    public void vote(VoteDTO voteDTO) {
        Post post = postRepository.findById(voteDTO.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDTO.getPostId()));

        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());

        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDTO.getVoteType())) {
            throw new FeraMakersException("Você já "
                    + voteDTO.getVoteType() + "para esse post");
        }

        if (VoteType.UPVOTE.equals(voteDTO.getVoteType())) {
            post.setPostVoteCount(post.getPostVoteCount() + 1);
        } else {
            post.setPostVoteCount(post.getPostVoteCount() - 1);
        }

        voteRepository.save(voteMapper.mapVoteDTOtoVote(voteDTO, post, authService.getCurrentUser()));
        postRepository.save(post);
    }

}
