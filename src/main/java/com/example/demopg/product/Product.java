package com.example.demopg.product;

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
public class Product {
  @Id
  @GeneratedValue
  private Integer id;
  
  @NotBlank(message="Name is required")
  @Column(name = "name", nullable = false)
  private String name;

  @NotNull(message="Price is required")
  @Column(name = "price", nullable = false)
  @PositiveOrZero(message = "Price must be zero or more")
  private Double price;

  @Column(name = "weight", nullable = true)
  @PositiveOrZero(message = "Weight must be zero or more")
  private Integer weight;

  @Column(name = "image_url", nullable = true)
  private String imageUrl;

  @Column(name = "category", nullable = false)
  @NotBlank(message = "Category is required")
  private String category;
}
