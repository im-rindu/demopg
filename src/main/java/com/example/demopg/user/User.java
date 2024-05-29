package com.example.demopg.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "\"user\"") 
public class User {
  @Id
  @GeneratedValue
  private Integer id;

  @NotBlank(message="Name is required")
  @Column(name = "name", nullable = false)
  private String name;
}
