package com.example.demopg.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@Validated
@RequestMapping("/api/v1/products")
public class ProductController {
  ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public Iterable<Product> getProducts(
    @RequestParam(name="name", required = false) String name, 
    @RequestParam(name="category", required = false) String category,
    @RequestParam(name="sortBy", required = false) String sortBy, 
    @RequestParam(name="sortDirection", required = false) String sortDirection
  ) {
    return productService.getProducts(name, category, sortBy.toLowerCase(), sortDirection.toLowerCase());
  }

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable Integer id) {
    return productService.getProduct(id);
  }
  
  @PostMapping
  public Product createProduct(@Valid @RequestBody Product product) {
    return productService.createProduct(product);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product) {
    return productService.updateProduct(id, product);
  }

  @DeleteMapping("/{id}")
  public Product deleteProduct(@PathVariable Integer id) {
    return productService.deleteProduct(id);
  }
}
