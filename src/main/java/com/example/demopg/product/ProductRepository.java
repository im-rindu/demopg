package com.example.demopg.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

  // sort by Id
  Iterable<Product> findAllByOrderByIdAsc();
  Iterable<Product> findAllByOrderByIdDesc();
  Iterable<Product> findByCategoryIgnoreCaseOrderByIdAsc(String category);
  Iterable<Product> findByCategoryIgnoreCaseOrderByIdDesc(String category);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByIdAsc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByIdDesc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByIdAsc(String name, String category);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByIdDesc(String name, String category);

  // sort by Name
  Iterable<Product> findAllByOrderByNameAsc();
  Iterable<Product> findAllByOrderByNameDesc();
  Iterable<Product> findByCategoryIgnoreCaseOrderByNameAsc(String category);
  Iterable<Product> findByCategoryIgnoreCaseOrderByNameDesc(String category);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByNameDesc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByNameAsc(String name, String category);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByNameDesc(String name, String category);

  // sort by Price
  Iterable<Product> findAllByOrderByPriceAsc();
  Iterable<Product> findAllByOrderByPriceDesc();
  Iterable<Product> findByCategoryIgnoreCaseOrderByPriceAsc(String category);
  Iterable<Product> findByCategoryIgnoreCaseOrderByPriceDesc(String category);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByPriceAsc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByPriceDesc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByPriceAsc(String name, String category);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByPriceDesc(String name, String category);

  // sort by Weight
  Iterable<Product> findAllByOrderByWeightAsc();
  Iterable<Product> findAllByOrderByWeightDesc();
  Iterable<Product> findByCategoryIgnoreCaseOrderByWeightAsc(String category);
  Iterable<Product> findByCategoryIgnoreCaseOrderByWeightDesc(String category);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByWeightAsc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByWeightDesc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByWeightAsc(String name, String category);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByWeightDesc(String name, String category);
  
  // sort by Category
  Iterable<Product> findAllByOrderByCategoryAsc();
  Iterable<Product> findAllByOrderByCategoryDesc();
  Iterable<Product> findByCategoryIgnoreCaseOrderByCategoryAsc(String category);
  Iterable<Product> findByCategoryIgnoreCaseOrderByCategoryDesc(String category);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByCategoryAsc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseOrderByCategoryDesc(String name);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByCategoryAsc(String name, String category);
  Iterable<Product> findByNameContainingIgnoreCaseAndCategoryIgnoreCaseOrderByCategoryDesc(String name, String category);
}
