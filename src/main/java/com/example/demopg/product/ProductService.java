package com.example.demopg.product;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demopg.CustomResponse;
import com.example.demopg.metadata.Metadata;
import com.example.demopg.metadata.MetadataRepository;

@Service
public class ProductService {
  private ProductRepository productRepository;
  private MetadataRepository metadataRepository;

  public ProductService(ProductRepository productRepository, MetadataRepository metadataRepository) {
    this.productRepository = productRepository;
    this.metadataRepository = metadataRepository;
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

  public CustomResponse<ProductDTO> getProduct(Integer id) {
    Product product = productRepository.findById(id).orElse(null);
    if(product == null){
      return new CustomResponse<ProductDTO>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found", null);
      
    }
    Metadata metadata = metadataRepository.findByProductId(id).orElse(null);
    return new CustomResponse<ProductDTO>(HttpStatus.OK, "OK", "Get Product Detail of " + product.getName(), new ProductDTO(product, metadata));
  }

  public CustomResponse<ProductDTO> createProduct(ProductDTO product) {
    if(product.getMetadata() == null){
      return new CustomResponse<ProductDTO>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Metadata can't be empty", null);
    } 
    if (product.getProduct() == null) {
      return new CustomResponse<ProductDTO>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Product can't be empty", null);
    }

    Product productToCreate = product.getProduct();
    Metadata metadataToCreate = product.getMetadata();

    if (productToCreate.getName() == null || productToCreate.getName() == "") {
      return new CustomResponse<ProductDTO>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Name can't be empty", null);
    }
    if (productToCreate.getPrice() == null) {
      return new CustomResponse<ProductDTO>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Price can't be empty", null);
    }
    if (productToCreate.getWeight() == null) {
      return new CustomResponse<ProductDTO>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Weight can't be empty", null);
    }
    if (productToCreate.getCategory() == null || productToCreate.getCategory() == "") {
      return new CustomResponse<ProductDTO>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Category can't be empty", null);
    }

    Product createdProduct = productRepository.save(productToCreate);
    metadataToCreate.setProductId(createdProduct.getId());
    metadataRepository.save(metadataToCreate);

    return new CustomResponse<ProductDTO>(HttpStatus.OK, "OK", "Product Added", product);
  }

  public CustomResponse<ProductDTO> updateProduct(Integer id, ProductDTO productDto) {

    Product productToUpdate = productRepository.findById(id).orElse(null);
    Metadata metadataToUpdate = metadataRepository.findByProductId(id).orElse(null);

    if (productDto.getMetadata() == null) {
      return new CustomResponse<ProductDTO>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Metadata can't be empty", null);
    }
    if (productDto.getProduct() == null) {
      return new CustomResponse<ProductDTO>(HttpStatus.BAD_REQUEST, "BAD_REQUEST", "Product can't be empty", null);
    }

    Product productChange = productDto.getProduct();
    Metadata metadataChange = productDto.getMetadata();

    if (productToUpdate == null) {
      return new CustomResponse<ProductDTO>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product not found", null);
    }

    if (metadataToUpdate == null) {
      metadataToUpdate = new Metadata();
      metadataToUpdate.setProductId(id);
    }

    if (productChange.getName() != null && productChange.getName() != "") {
      productToUpdate.setName(productChange.getName());
    }
    if (productChange.getPrice() != null) {
      productToUpdate.setPrice(productChange.getPrice());
    }
    if (productChange.getWeight() != null) {
      productToUpdate.setWeight(productChange.getWeight());
    }
    if (productChange.getImageUrl() != null) {
      productToUpdate.setImageUrl(productChange.getImageUrl());
    }
    if (productChange.getCategory() != null && productChange.getCategory() != "") {
      productToUpdate.setCategory(productChange.getCategory());
    }

    if (metadataChange.getIncrement() != null) {
      metadataToUpdate.setIncrement(metadataChange.getIncrement());
    }
    if (metadataChange.getUnit() != null && metadataChange.getUnit() != "") {
      metadataToUpdate.setUnit(metadataChange.getUnit());
    }
    if (metadataChange.getWeight() != null) {
      metadataToUpdate.setWeight(metadataChange.getWeight());
    }
    if (metadataChange.getCalorie() != null) {
      metadataToUpdate.setCalorie(metadataChange.getCalorie());
    }
    if (metadataChange.getProteins() != null) {
      metadataToUpdate.setProteins(metadataChange.getProteins());
    }
    if (metadataChange.getFats() != null) {
      metadataToUpdate.setFats(metadataChange.getFats());
    }
    if (metadataChange.getCarbs() != null) {
      metadataToUpdate.setCarbs(metadataChange.getCarbs());
    }
    
    Product updatedProduct = productRepository.save(productToUpdate);
    Metadata updatedMetadata = metadataRepository.save(metadataToUpdate);

    return new CustomResponse<ProductDTO>(HttpStatus.OK, "OK", "Get Product Detail", new ProductDTO(updatedProduct, updatedMetadata));
  }

  public CustomResponse<Product> deleteProduct(Integer id) {
    Product productToRemove = productRepository.findById(id).orElse(null);
    if (productToRemove != null) {
      productRepository.deleteById(id);
    }else{
      return new CustomResponse<Product>(HttpStatus.NOT_FOUND, "NOT_FOUND", "Product Not Found", null);
    }
    return new CustomResponse<Product>(HttpStatus.OK, "OK", "Product Deleted", productToRemove);
  }

  public String deleteAllProducts() {
    productRepository.deleteAll();
    return "All products removed";
  }

}
