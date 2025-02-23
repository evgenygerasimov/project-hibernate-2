package org.example.repository;

import org.example.entity.Inventory;

public class InventoryRepository extends BaseRepository<Inventory> {
    public InventoryRepository() {
        super(Inventory.class);
    }
}
