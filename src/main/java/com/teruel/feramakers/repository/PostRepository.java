package com.teruel.feramakers.repository;

import com.teruel.feramakers.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

}
