package com.example.demopg.favoriteItem;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity<?> getUserFavorite(@PathVariable Integer id){
    return favoriteService.getUserFavorite(id);
  }

  @PostMapping("/toggle")
  public Favorite setFavorite(@Valid @RequestBody Favorite favorite ){
    return favoriteService.changeFavorite(favorite);
  }
}
