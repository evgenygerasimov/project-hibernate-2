package org.example.repository;

import org.example.entity.Rental;

public class RentalRepository extends BaseRepository<Rental> {
    public RentalRepository() {
        super(Rental.class);
    }
}
