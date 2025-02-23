package org.example.repository;

import org.example.entity.Address;

public class AddressRepository extends BaseRepository<Address> {
    public AddressRepository() {
        super(Address.class);
    }
}
