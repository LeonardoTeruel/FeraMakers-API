package com.teruel.feramakers.repository;

import com.teruel.feramakers.model.Comment;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
