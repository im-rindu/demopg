package com.example.demopg.product;

import com.example.demopg.metadata.Metadata;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductDTO {
  private Product product;
  private Metadata metadata = null;

  public ProductDTO(Product product, Metadata metadata) {
    this.product = product;
    this.metadata = metadata;
  }
}
