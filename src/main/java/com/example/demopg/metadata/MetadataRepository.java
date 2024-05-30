package com.example.demopg.metadata;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MetadataRepository extends CrudRepository<Metadata, Integer> {
  Optional<Metadata> findByProductId(Integer productId);
}