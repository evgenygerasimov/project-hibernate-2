package org.example.repository;

import org.example.entity.*;

public class CustomerRepository extends BaseRepository<Customer> {
    public CustomerRepository() {
        super(Customer.class);
    }
}
