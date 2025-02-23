package org.example.repository;

import org.example.entity.Store;

public class StoreRepository extends BaseRepository<Store> {
    public StoreRepository() {
        super(Store.class);
    }
}
