package com.teruel.feramakers.repository;

import com.teruel.feramakers.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional <Category> findByCategoryName (String categoryName);

}
