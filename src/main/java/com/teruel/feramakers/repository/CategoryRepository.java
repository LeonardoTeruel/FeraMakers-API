package com.teruel.feramakers.repository;

import com.teruel.feramakers.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
