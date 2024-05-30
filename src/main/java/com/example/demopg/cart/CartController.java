package com.example.demopg.cart;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demopg.CustomResponse;

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
  public ResponseEntity<?> getCartItem() { 
    return cartService.getCartItem().toResponseEntity();
  }


  @PostMapping
  public ResponseEntity<?> addToCart(@Valid @RequestBody Cart cart) {
    return cartService.addToCart(cart).toResponseEntity();
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> editItemQuantity(@PathVariable Integer id,@RequestParam(name="quantity", required = false) Integer quantity ){
    return cartService.updateCartItem(id, quantity).toResponseEntity();
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<CustomResponse<Cart>> removeItem(@PathVariable Integer id, @RequestParam(name="quantity", required = false) Integer quantity ){
    return cartService.removeItemFromCart(id, quantity).toResponseEntity();
  }

  @DeleteMapping("/clear")
  public CustomResponse<String> removeAllItem(){
    return new CustomResponse<String>(HttpStatus.OK, "OK", "All Product Removed from Cart", cartService.removeAllItems());
  }
}
