package com.example.tinyshop.service;


import com.example.tinyshop.model.entity.Collection;
import com.example.tinyshop.model.enums.CollectionCategory;
import com.example.tinyshop.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CollectionService {
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public void initializeCollections() {
        if (collectionRepository.count() == 0) {
            Arrays.stream(CollectionCategory.values()).forEach(currentCollection -> {
                Collection collection = new Collection();
                collection.setType(currentCollection);
                collectionRepository.save(collection);
            });
        }
    }
}
