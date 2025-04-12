package com.vyapar.guru.app.controller;

import com.vyapar.guru.app.dto.InventoryItemDTO;
import com.vyapar.guru.app.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // Endpoint to add a new inventory item
    @PostMapping
    public ResponseEntity<InventoryItemDTO> addInventoryItem(@RequestBody @Valid InventoryItemDTO inventoryItemDTO) {
        InventoryItemDTO savedItem = inventoryService.saveItem(inventoryItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    // Endpoint to get an inventory item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> getInventoryItem(@PathVariable Long id) {
        InventoryItemDTO item = inventoryService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    // Endpoint to get all inventory items
    @GetMapping
    public ResponseEntity<List<InventoryItemDTO>> getAllItems() {
        List<InventoryItemDTO> items = inventoryService.getAllItems();
        if (items.isEmpty()) {
            return ResponseEntity.noContent().build();  // If no items, return 204 No Content
        }
        return ResponseEntity.ok(items);  // Otherwise, return 200 OK with items
    }

    // Endpoint to delete an inventory item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}