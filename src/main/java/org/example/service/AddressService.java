package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Address;
import org.example.repository.AddressRepository;
import org.example.utils.TransactionManager;

import java.util.List;
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TransactionManager txManager;

    public Address findById(Long id) {
        return txManager.execute(session -> addressRepository.findById(session, id));
    }

    public List<Address> findAll() {
        return txManager.execute(addressRepository::findAll);
    }

    public void delete(Address address) {
        txManager.execute(session -> {
            addressRepository.delete(session, address);
            return null;
        });
    }

    public void save(Address address) {
        txManager.execute(session -> {
            addressRepository.save(session, address);
            return null;
        });
    }

    public void update(Address address) {
        txManager.execute(session -> {
            addressRepository.update(session, address);
            return null;
        });
    }
}
