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
  
  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "price", nullable = false)
  @PositiveOrZero(message = "Price must be zero or more")
  private Double price;

  @Column(name = "weight", nullable = true)
  @PositiveOrZero(message = "Weight must be zero or more")
  private Integer weight;

  @Column(name = "image_url", nullable = true)
  private String imageUrl;

  @Column(name = "category", nullable = false)
  private String category;
}
