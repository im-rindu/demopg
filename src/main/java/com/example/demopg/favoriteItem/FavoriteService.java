package com.example.demopg.favoriteItem;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demopg.CustomResponse;
import com.example.demopg.product.Product;
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

  public CustomResponse<Iterable<Favorite>> getUserFavorite(Integer userId){
    if(!checkUser(userId)){
      return new CustomResponse<Iterable<Favorite>>(HttpStatus.NOT_FOUND, "NOT_FOUND", "User not found", null);
    }
    Iterable<Favorite> userFavorite = favoriteRepository.findByUserId(userId);
    if(userFavorite.iterator().hasNext()){
      return new CustomResponse<Iterable<Favorite>>(HttpStatus.OK, "OK", "Get User Favorite Products", userFavorite);
    }
    return new CustomResponse<Iterable<Favorite>>(HttpStatus.NOT_FOUND, "NOT_FOUND", "user doesn't have favorite", null);
  }

  public CustomResponse<Favorite> changeFavorite(Favorite favorite){
    Optional<Favorite> checkFavorite = favoriteRepository.findByProductId(favorite.getProductId());
    
    if(!checkProduct(favorite.getProductId())){
      return new CustomResponse<Favorite>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found", null);
    }
    if(!checkUser(favorite.getUserId())){
      return new CustomResponse<Favorite>(HttpStatus.NOT_FOUND, "NOT_FOUND", "User not found", null);
    }
    if(!checkFavorite.isPresent()){
      return new CustomResponse<Favorite>(HttpStatus.OK, "OK", "Added to Favorite", favoriteRepository.save(favorite));
    }
    Favorite favoriteToRemove = checkFavorite.get();
    favoriteRepository.deleteById(favoriteToRemove.getId());
    return new CustomResponse<Favorite>(HttpStatus.OK, "OK", "Removed from Favorite", favoriteToRemove);
  }

  private boolean checkUser(Integer userId){
    Optional<User> checkUser = userRepository.findById(userId);
    if(checkUser.isEmpty()){
      return false;
    }
    return true;
  }

  private boolean checkProduct(Integer productId){
    Optional<Product> checkProduct = productRepository.findById(productId);
    if(checkProduct.isEmpty()){
      return false;
    }
    return true;
  }
}
