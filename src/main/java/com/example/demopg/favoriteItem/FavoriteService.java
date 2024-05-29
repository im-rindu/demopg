package com.example.demopg.favoriteItem;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demopg.product.ProductRepository;
import com.example.demopg.user.User;
import com.example.demopg.user.UserRepository;

@Service
public class FavoriteService {
  @Autowired
  FavoriteRepository favoriteRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  UserRepository userRepository;

  public ResponseEntity<?> getUserFavorite(Integer userId){
    checkUser(userId);
    Iterable<Favorite> userFavorite = favoriteRepository.findByUserId(userId);
    if(userFavorite.iterator().hasNext()){
      return ResponseEntity.status(HttpStatus.OK).body(userFavorite);
    }
    return ResponseEntity.status(HttpStatus.OK).body("user doesn't have favorite");
  }

  public Favorite changeFavorite(Favorite favorite){
    Optional<Favorite> checkFavorite = favoriteRepository.findByProductId(favorite.getProductId());
    
    checkUser(favorite.getUserId());
    if(!checkFavorite.isPresent()){
      return favoriteRepository.save(favorite);
    }
    Favorite favoriteToRemove = checkFavorite.get();
    favoriteRepository.deleteById(favoriteToRemove.getId());
    return favoriteToRemove;
  }

  private void checkUser(Integer userId){
    Optional<User> checkUser = userRepository.findById(userId);
    if(!checkUser.isPresent()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
  }
}
