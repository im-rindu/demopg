package com.example.demopg.cart;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demopg.CustomResponse;
import com.example.demopg.product.Product;
import com.example.demopg.product.ProductRepository;

@Service
public class CartService {
  @Autowired
  CartRepository cartRepository;

  @Autowired
  ProductRepository productRepository;

  public CustomResponse<?> getCartItem() {
    Iterable<Cart> cartData = cartRepository.findAll();
    if(cartData.iterator().hasNext()){
      return new CustomResponse<Iterable<Cart>>(HttpStatus.OK, "OK", "Get All Product from Cart",cartRepository.findAll());
    }
    return new CustomResponse<String>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Cart is Empty", null);
  }

  public CustomResponse<?> addToCart(Cart cart) {
    Cart itemToCart = cart;
    String message = "Item added to cart";
    Optional<Product> checkProduct = productRepository.findById(cart.getProductId());
    Optional<Cart> checkProductInCart = cartRepository.findByProductId(cart.getProductId());
    if(!checkProduct.isPresent()){
      return new CustomResponse<String>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found", null);
    }
    if (cart.getQuantity() <= 0){
      return new CustomResponse<String>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Quantity can't be 0 or less", null);
    }
    if(checkProductInCart.isPresent()){
      itemToCart = checkProductInCart.get();
      itemToCart.setQuantity(itemToCart.getQuantity() + cart.getQuantity());
      message = "Item in cart updated";
    }
    return new CustomResponse<Cart>(HttpStatus.OK, "OK", message, cartRepository.save(itemToCart));
  }

  public CustomResponse<?> updateCartItem(Integer id, Integer quantity) {
    Cart cartItemToUpdate = cartRepository.findByProductId(id).orElse(null);
    if (cartItemToUpdate == null) {
      return new CustomResponse<String>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found in cart", null);
    }
    if (cartItemToUpdate.getQuantity() != null) {
      cartItemToUpdate.setQuantity(cartItemToUpdate.getQuantity() + quantity);
      if(cartItemToUpdate.getQuantity() <= 0){
        cartItemToUpdate.setQuantity(0);
        return removeItemFromCart(id, null);
      }
      return new CustomResponse<Cart>(HttpStatus.OK, "OK", "Product updated", cartRepository.save(cartItemToUpdate));
    }
    return new CustomResponse<Cart>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Quantity required", cartRepository.save(cartItemToUpdate));
  }

  public CustomResponse<Cart> removeItemFromCart(Integer id, Integer quantity) {
    Cart cartItemToRemove = cartRepository.findByProductId(id).orElse(null);
    if (cartItemToRemove == null) {
      return new CustomResponse<Cart>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found in cart", null);
    }
    if (quantity != null) {
      if (cartItemToRemove.getQuantity() < quantity) {
        return new CustomResponse<Cart>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Quantity to remove can't more than quantity in cart", null);
      }
      if (cartItemToRemove.getQuantity() > quantity) {
        cartItemToRemove.setQuantity(cartItemToRemove.getQuantity() - quantity);
        return new CustomResponse<Cart>(HttpStatus.OK, "OK", "Product reduced " + quantity, cartRepository.save(cartItemToRemove));
      }
    }
    cartRepository.deleteById(cartItemToRemove.getId()); 
    return new CustomResponse<Cart>(HttpStatus.OK, "OK", "Product removed from cart", cartItemToRemove);
  }

  public String removeAllItems() {
    cartRepository.deleteAll();
    return "All product in cart removed";
  }

}
