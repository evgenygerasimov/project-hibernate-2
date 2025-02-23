package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.*;
import org.example.repository.AddressRepository;
import org.example.repository.CustomerRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final TransactionManager txManager;

    public void saveWithDependencies(Customer customer) {
        txManager.execute(session -> {
            if (customer.getAddress() == null || customer.getAddress().getCity() == null ||
                    customer.getAddress().getCity().getCountry() == null) {
                throw new IllegalArgumentException("Address, City, and Country must be provided!");
            }

            if (customer.getStore() == null || customer.getStore().getStaff() == null) {
                throw new IllegalArgumentException("Store and Store Manager must be provided!");
            }
            addressRepository.save(session, customer.getAddress());
            customerRepository.save(session, customer);
            return null;
        });
    }

    public Customer findById(Long id) {
        return txManager.execute(session -> customerRepository.findById(session, id));
    }

    public List<Customer> findAll() {
        return txManager.execute(customerRepository::findAll);
    }

    public void delete(Customer customer) {
        txManager.execute(session -> {
            customerRepository.delete(session, customer);
            return null;
        });
    }

    public void save(Customer customer) {
        txManager.execute(session -> {
            customerRepository.save(session, customer);
            return null;
        });
    }

    public void update(Customer customer) {
        txManager.execute(session -> {
            customerRepository.update(session, customer);
            return null;
        });
    }
}
