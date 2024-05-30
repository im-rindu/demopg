package com.example.demopg.metadata;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MetadataService {

  private MetadataRepository metadataRepository;

  public MetadataService(MetadataRepository metadataRepository) {
    this.metadataRepository = metadataRepository;
  }

  public Iterable<Metadata> getMetadata() {
    return metadataRepository.findAll();
  }

  public Metadata createMetadata(Metadata metadata) {
    return metadataRepository.save(metadata);
  }

  public Metadata updateMetadata(Integer id, Metadata metadata) {
    Optional<Metadata> metadataToUpdate = metadataRepository.findByProductId(id);
    if (metadataToUpdate.isEmpty()) {
      return null;
    }
    Metadata updatedMetada = metadataToUpdate.get();
    if (updatedMetada.getIncrement() != null) {
      updatedMetada.setIncrement(metadata.getIncrement());
    }
    if (updatedMetada.getUnit() != null && updatedMetada.getUnit() != "") {
      updatedMetada.setUnit(metadata.getUnit());
    }
    if (updatedMetada.getWeight() != null) {
      updatedMetada.setWeight(metadata.getWeight());
    }
    if (updatedMetada.getCalorie() != null) {
      updatedMetada.setCalorie(metadata.getCalorie());
    }
    if (updatedMetada.getProteins() != null) {
      updatedMetada.setProteins(metadata.getProteins());
    }
    if (updatedMetada.getFats() != null) {
      updatedMetada.setFats(metadata.getFats());
    }
    if (updatedMetada.getCarbs() != null) {
      updatedMetada.setCarbs(metadata.getCarbs());
    }
    return metadataRepository.save(updatedMetada);
  }
}
