package com.example.demopg.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demopg.product.Product;
import com.example.demopg.product.ProductRepository;

@Service
public class CartService {
  @Autowired
  CartRepository cartRepository;

  @Autowired
  ProductRepository productRepository;

  public ResponseEntity<?> getCartItem() {
    Iterable<Cart> cartData = cartRepository.findAll();
    if(cartData.iterator().hasNext()){
      return ResponseEntity.status(HttpStatus.OK).body(cartRepository.findAll());
    }
    return ResponseEntity.status(HttpStatus.OK).body("Cart is empty");
  }

  public Cart addToCart(Cart cart) {
    Cart itemToCart = cart;
    Optional<Product> checkProduct = productRepository.findById(cart.getProductId());
    Optional<Cart> checkProductInCart = cartRepository.findByProductId(cart.getProductId());
    if(!checkProduct.isPresent()){
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
    if(checkProductInCart.isPresent()){
      itemToCart = checkProductInCart.get();
      itemToCart.setQuantity(itemToCart.getQuantity() + cart.getQuantity());
    }
    return cartRepository.save(itemToCart);
  }

  public Cart updateCartItem(Integer id, Cart cart) {
    Cart cartItemToUpdate = cartRepository.findById(id).orElse(null);
    if (cartItemToUpdate == null) {
      return null;
    }
    if (cartItemToUpdate.getQuantity() != null) {
      cartItemToUpdate.setQuantity(cart.getQuantity());
    }
    return cartRepository.save(cartItemToUpdate);
  }

  public Cart removeItemFromCart(Integer id) {
    Cart cartItemToRemove = cartRepository.findById(id).orElse(null);
    if (cartItemToRemove != null) {
      cartRepository.deleteById(id);
    }
    return cartItemToRemove;
  }

  public String removeAllItems() {
    cartRepository.deleteAll();
    return "All product in cart removed";
  }

}
