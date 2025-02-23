package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Store;
import org.example.repository.StoreRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final TransactionManager txManager;

    public Store findById(Long id) {
        return txManager.execute(session -> storeRepository.findById(session, id));
    }

    public List<Store> findAll() {
        return txManager.execute(storeRepository::findAll);
    }

    public void delete(Store store) {
        txManager.execute(session -> {
            storeRepository.delete(session, store);
            return null;
        });
    }

    public void save(Store store) {
        txManager.execute(session -> {
            storeRepository.save(session, store);
            return null;
        });
    }

    public void update(Store store) {
        txManager.execute(session -> {
            storeRepository.update(session, store);
            return null;
        });
    }
}
