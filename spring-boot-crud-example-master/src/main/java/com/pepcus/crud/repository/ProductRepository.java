package com.pepcus.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepcus.crud.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
  Product findByName(String name);
}
