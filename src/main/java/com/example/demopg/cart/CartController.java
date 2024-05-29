package com.example.demopg.cart;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@Validated
@RequestMapping("/api/v1/cart")
public class CartController {
  CartService cartService;

  public CartController (CartService cartService){
    this.cartService = cartService;
  }

  @GetMapping
  public ResponseEntity<?> getCartItems() {
    return cartService.getCartItem();
  }

  @PostMapping
  public Cart addToCart(@Valid @RequestBody Cart cart) {
    return cartService.addToCart(cart);
  }

  @PutMapping("/{id}")
  public Cart editItemQuantity(@PathVariable Integer id, @Valid @RequestBody Cart cart){
    return cartService.updateCartItem(id, cart);
  }
  
  @DeleteMapping("/{id}")
  public Cart removeItem(@PathVariable Integer id){
    return cartService.removeItemFromCart(id);
  }

  @DeleteMapping("/clear")
  public String removeAllItem(){
    return cartService.removeAllItems();
  }
}
