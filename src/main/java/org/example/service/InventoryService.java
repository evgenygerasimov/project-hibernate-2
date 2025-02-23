package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Inventory;
import org.example.repository.InventoryRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final TransactionManager txManager;

    public Inventory findById(Long id) {
        return txManager.execute(session -> inventoryRepository.findById(session, id));
    }

    public List<Inventory> findAll() {
        return txManager.execute(inventoryRepository::findAll);
    }

    public void delete(Inventory inventory) {
        txManager.execute(session -> {
            inventoryRepository.delete(session, inventory);
            return null;
        });
    }

    public void save(Inventory inventory) {
        txManager.execute(session -> {
            inventoryRepository.save(session, inventory);
            return null;
        });
    }

    public void update(Inventory inventory) {
        txManager.execute(session -> {
            inventoryRepository.update(session, inventory);
            return null;
        });
    }
}
