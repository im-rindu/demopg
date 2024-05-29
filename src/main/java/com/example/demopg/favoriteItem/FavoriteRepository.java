package com.example.demopg.favoriteItem;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends CrudRepository<Favorite, Integer>{
  Iterable<Favorite> findByUserId(Integer id);
  Optional<Favorite> findByProductId(Integer id);
}
