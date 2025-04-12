package com.vyapar.guru.app.mapper;

import com.vyapar.guru.app.dto.InventoryItemDTO;
import com.vyapar.guru.app.entity.InventoryItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryItemMapper {

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    InventoryItemDTO toDTO(InventoryItem inventoryItem);

    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    InventoryItem toEntity(InventoryItemDTO inventoryItemDTO);
}