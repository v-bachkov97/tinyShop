package com.example.tinyshop.repository;

import com.example.tinyshop.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByCategoryIdAndCollectionId(long categoryId, long collectionId);
    Optional<Product> findById(long id);


    List<Product> findTop5ByCategoryIdOrderByAddedOn(long i);
}
