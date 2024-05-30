package com.example.demopg.product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demopg.CustomResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<CustomResponse<Iterable<Product>>> getProducts(
    @RequestParam(name="name", required = false) String name, 
    @RequestParam(name="category", required = false) String category,
    @RequestParam(name="sortBy", required = false, defaultValue = "id") String sortBy, 
    @RequestParam(name="sortDirection", required = false, defaultValue = "asc") String sortDirection
  ) {
    return new CustomResponse<Iterable<Product>>(HttpStatus.OK, "OK", "Get Products List", productService.getProducts(name, category, sortBy.toLowerCase(), sortDirection.toLowerCase())).toResponseEntity();
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomResponse<Product>> getProduct(@PathVariable Integer id) {
    return new CustomResponse<Product>(HttpStatus.OK, "OK", "Get Product Detail", productService.getProduct(id)).toResponseEntity();
  }
  
  @PostMapping
  public ResponseEntity<CustomResponse<Product>> createProduct(@Valid @RequestBody Product product) {
    return productService.createProduct(product).toResponseEntity();
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomResponse<Product>> updateProduct(@PathVariable Integer id, @Valid @RequestBody Product product) {
    return productService.updateProduct(id, product).toResponseEntity();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CustomResponse<Product>> deleteProduct(@PathVariable Integer id) {
    return new CustomResponse<Product>(HttpStatus.OK, "OK", "Product Removed", productService.deleteProduct(id)).toResponseEntity();
  }
}
