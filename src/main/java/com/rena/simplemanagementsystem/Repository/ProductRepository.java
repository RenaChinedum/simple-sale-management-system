package com.rena.simplemanagementsystem.Repository;

import com.rena.simplemanagementsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
