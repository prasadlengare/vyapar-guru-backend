package com.vyapar.guru.app.service;

import com.vyapar.guru.app.dto.InventoryItemDTO;
import com.vyapar.guru.app.entity.InventoryItem;
import com.vyapar.guru.app.exception.ResourceNotFoundException;
import com.vyapar.guru.app.mapper.InventoryItemMapper;
import com.vyapar.guru.app.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryService.class);

    private final InventoryRepository inventoryRepository;
    private final InventoryItemMapper inventoryItemMapper;

    public InventoryService(InventoryRepository inventoryRepository, InventoryItemMapper inventoryItemMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryItemMapper = inventoryItemMapper;
    }

    // Save a new inventory item
    public InventoryItemDTO saveItem(InventoryItemDTO itemDTO) {
        InventoryItem inventoryItem = inventoryItemMapper.toEntity(itemDTO);
        logger.debug("Saving inventory item: {}", inventoryItem.getProductName());
        InventoryItem savedItem = inventoryRepository.save(inventoryItem);
        return inventoryItemMapper.toDTO(savedItem);
    }

    // Get an inventory item by ID
    public InventoryItemDTO getItemById(Long id) {
        InventoryItem item = inventoryRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Inventory item not found with ID: {}", id);
                    return new ResourceNotFoundException("Inventory item not found with ID: " + id);
                });
        return inventoryItemMapper.toDTO(item);
    }

    // Get all inventory items
    public List<InventoryItemDTO> getAllItems() {
        List<InventoryItem> items = inventoryRepository.findAll();
        if (items.isEmpty()) {
            logger.warn("No inventory items found");
        } else {
            logger.info("Found {} inventory items", items.size());
        }
        return items.stream()
                .map(inventoryItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Delete an inventory item by ID
    public void deleteItem(Long id) {
        if (!inventoryRepository.existsById(id)) {
            logger.error("Inventory item not found with ID: {}", id);
            throw new ResourceNotFoundException("Inventory item not found with ID: " + id);
        }
        inventoryRepository.deleteById(id);
        logger.info("Deleted inventory item with ID: {}", id);
    }
}