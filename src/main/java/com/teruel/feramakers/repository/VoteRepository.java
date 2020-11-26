package com.teruel.feramakers.repository;

import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import com.teruel.feramakers.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);

}
