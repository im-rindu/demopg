package com.example.demopg.metadata;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Metadata {
  @Id
  @GeneratedValue
  private Integer id;

  @Column(name = "product_id", nullable = true, unique = true)
  private Integer productId;

  @Column(name = "increment", nullable = true)
  private Integer increment;

  @Column(name = "unit", nullable = true)
  private String unit;

  @Column(name = "weight", nullable = true)
  private Integer weight;

  @Column(name = "calorie", nullable = true)
  private Float calorie;
  
  @Column(name = "proteins", nullable = true)
  private Float proteins;
  
  @Column(name = "fats", nullable = true)
  private Float fats;
  
  @Column(name = "carbs", nullable = true)
  private Float carbs;

}
