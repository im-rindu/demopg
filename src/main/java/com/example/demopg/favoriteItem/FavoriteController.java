package com.example.demopg.favoriteItem;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demopg.CustomResponse;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/v1/favorites")
public class FavoriteController {
  FavoriteService favoriteService;

  public FavoriteController (FavoriteService favoriteService){
    this.favoriteService = favoriteService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomResponse<Iterable<Favorite>>> getUserFavorite(@PathVariable Integer id){
    CustomResponse<Iterable<Favorite>> response = favoriteService.getUserFavorite(id);
    return response.toResponseEntity();
  }

  @PostMapping("/toggle")
  public ResponseEntity<CustomResponse<Favorite>> setFavorite(@Valid @RequestBody Favorite favorite ){
    CustomResponse<Favorite> response = favoriteService.changeFavorite(favorite);
    return response.toResponseEntity();
  }
}
