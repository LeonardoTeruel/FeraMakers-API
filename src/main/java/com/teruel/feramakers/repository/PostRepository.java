package com.teruel.feramakers.repository;

import com.teruel.feramakers.model.Category;
import com.teruel.feramakers.model.Post;
import com.teruel.feramakers.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByCategory(Category category);

    List<Post> findByUser(User user);

}
