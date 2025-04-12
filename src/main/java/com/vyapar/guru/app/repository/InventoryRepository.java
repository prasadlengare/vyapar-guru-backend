package com.vyapar.guru.app.repository;

import com.vyapar.guru.app.entity.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, Long> {

    // Custom query to find inventory items by category
    List<InventoryItem> findByProductName(String productName);

    // Custom query to find inventory items by quantity
    List<InventoryItem> findByQuantity(Integer quantity);

    // Custom query to find inventory items by price
    List<InventoryItem> findByPrice(BigDecimal price);
}