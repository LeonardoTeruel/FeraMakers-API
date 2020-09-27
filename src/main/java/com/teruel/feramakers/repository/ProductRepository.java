package com.teruel.feramakers.repository;

import com.teruel.feramakers.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
