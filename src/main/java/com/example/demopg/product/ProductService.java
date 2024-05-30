package com.example.demopg.product;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demopg.CustomResponse;

@Service
public class ProductService {
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Iterable<Product> getProducts(String name, String category, String sortBy, String sortDirection) {
    if(sortBy == null) {
      sortBy = "";
    }
    if(sortDirection == null) {
      sortDirection = "";
    }
    if(name != null && category != null) {
      if(sortBy.equals("name")){
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByNameDesc(name, category);
        }
        return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByNameAsc(name, category);
      }
      else if(sortBy.equals("price")){
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByPriceDesc(name, category);
        }
        return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByPriceAsc(name, category);
      }
      else if(sortBy.equals("category")){
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByCategoryDesc(name, category);
        }
        return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByCategoryAsc(name, category);
      }
      else if(sortBy.equals("weight")){
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByWeightDesc(name, category);
        }
        return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByWeightAsc(name, category);
      }
      else{
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByIdDesc(name, category);
        }
        return productRepository.findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByIdAsc(name, category);
      }
    } else if(name != null) {
      if(sortBy.equals("name")){
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseOrderByNameDesc(name);
        }
        return productRepository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
      }
      else if(sortBy.equals("price")){
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseOrderByPriceDesc(name);
        }
        return productRepository.findByNameContainingIgnoreCaseOrderByPriceAsc(name);
      }
      else if(sortBy.equals("category")){
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseOrderByCategoryDesc(name);
        }
        return productRepository.findByNameContainingIgnoreCaseOrderByCategoryAsc(name);
      }
      else if(sortBy.equals("weight")){
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseOrderByWeightDesc(name);
        }
        return productRepository.findByNameContainingIgnoreCaseOrderByWeightAsc(name);
      }
      else{
        if(sortDirection.equals("desc")){
          return productRepository.findByNameContainingIgnoreCaseOrderByIdDesc(name);
        }
        return productRepository.findByNameContainingIgnoreCaseOrderByIdAsc(name);
      }
    } else if(category != null) {
      if(sortBy.equals("name")){
        if(sortDirection.equals("desc")){
          return productRepository.findByCategoryIgnoreCaseOrderByNameDesc(category);
        }
        return productRepository.findByCategoryIgnoreCaseOrderByNameAsc(category);
      }
      else if(sortBy.equals("price")){
        if(sortDirection.equals("desc")){
          return productRepository.findByCategoryIgnoreCaseOrderByPriceDesc(category);
        }
        return productRepository.findByCategoryIgnoreCaseOrderByPriceAsc(category);
      }
      else if(sortBy.equals("category")){
        if(sortDirection.equals("desc")){
          return productRepository.findByCategoryIgnoreCaseOrderByCategoryDesc(category);
        }
        return productRepository.findByCategoryIgnoreCaseOrderByCategoryAsc(category);
      }
      else if(sortBy.equals("weight")){
        if(sortDirection.equals("desc")){
          return productRepository.findByCategoryIgnoreCaseOrderByWeightDesc(category);
        }
        return productRepository.findByCategoryIgnoreCaseOrderByWeightAsc(category);
      }
      else{
        if(sortDirection.equals("desc")){
          return productRepository.findByCategoryIgnoreCaseOrderByIdDesc(category);
        }
        return productRepository.findByCategoryIgnoreCaseOrderByIdAsc(category);
      }
    }
    else{
      if(sortBy.equals("name")){
        if(sortDirection.equals("desc")){
          return productRepository.findAllByOrderByNameDesc();
        }
        return productRepository.findAllByOrderByNameAsc();
      }
      else if(sortBy.equals("price")){
        if(sortDirection.equals("desc")){
          return productRepository.findAllByOrderByPriceDesc();
        }
        return productRepository.findAllByOrderByPriceAsc();
      }
      else if(sortBy.equals("category")){
        if(sortDirection.equals("desc")){
          return productRepository.findAllByOrderByCategoryDesc();
        }
        return productRepository.findAllByOrderByCategoryAsc();
      }
      else if(sortBy.equals("weight")){
        if(sortDirection.equals("desc")){
          return productRepository.findAllByOrderByWeightDesc();
        }
        return productRepository.findAllByOrderByWeightAsc();
      }
      else{
        if(sortDirection.equals("desc")){
          return productRepository.findAllByOrderByIdDesc();
        }
        return productRepository.findAllByOrderByIdAsc();
      }
    }
  }

  public Product getProduct(Integer id) {
    return productRepository.findById(id).orElse(null);
  }

  public CustomResponse<Product> createProduct(Product product) {
    if (product == null) {
      return new CustomResponse<Product>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Product can't be empty", null);
    }
    if (product.getName() == null || product.getName() == "") {
      return new CustomResponse<Product>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Name can't be empty", null);
    }
    if (product.getPrice() == null) {
      return new CustomResponse<Product>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Price can't be empty", null);
    }
    if (product.getWeight() == null) {
      return new CustomResponse<Product>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Weight can't be empty", null);
    }
    if (product.getCategory() == null || product.getCategory() == "") {
      return new CustomResponse<Product>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Category can't be empty", null);
    }
    return new CustomResponse<Product>(HttpStatus.OK, "OK", "Product Added", productRepository.save(product));
  }

  public CustomResponse<Product> updateProduct(Integer id, Product product) {
    Product productToUpdate = productRepository.findById(id).orElse(null);
    if (productToUpdate == null) {
      return new CustomResponse<Product>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found", null);
    }
    if (productToUpdate.getName() != null && productToUpdate.getName() != "") {
      productToUpdate.setName(product.getName());
    }
    if (productToUpdate.getPrice() != null) {
      productToUpdate.setPrice(product.getPrice());
    }
    if (productToUpdate.getWeight() != null) {
      productToUpdate.setWeight(product.getWeight());
    }
    if (productToUpdate.getImageUrl() != null) {
      productToUpdate.setImageUrl(product.getImageUrl());
    }
    if (productToUpdate.getCategory() != null && productToUpdate.getCategory() != "") {
      productToUpdate.setCategory(product.getCategory());
    }
    return new CustomResponse<Product>(HttpStatus.OK, "OK", "Get Product Detail", productRepository.save(productToUpdate));
  }

  public Product deleteProduct(Integer id) {
    Product productToRemove = productRepository.findById(id).orElse(null);
    if (productToRemove != null) {
      productRepository.deleteById(id);
    }
    return productToRemove;
  }

  public String deleteAllProducts() {
    productRepository.deleteAll();
    return "All products removed";
  }

}
