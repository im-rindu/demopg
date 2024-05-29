package com.example.demopg.cart;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {
  Optional<Cart> findByProductId(Integer productId);
}
