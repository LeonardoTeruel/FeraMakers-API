package com.teruel.feramakers.repository;

import com.teruel.feramakers.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
