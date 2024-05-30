package com.example.demopg.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Cart {
  @Id
  @GeneratedValue
  private Integer id;

  @NotNull(message="Product is required")
  @PositiveOrZero(message = "ProductId must be 0 or more")
  @Column(name = "product_id", nullable = false, unique = true)
  private Integer productId;

  @NotNull(message="Quantity is required")
  @Column(name = "quantity", nullable = false)
  private Integer quantity;
}
