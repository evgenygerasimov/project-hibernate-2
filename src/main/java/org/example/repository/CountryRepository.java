package org.example.repository;

import org.example.entity.Country;

public class CountryRepository extends BaseRepository<Country> {
    public CountryRepository() {
        super(Country.class);
    }
}
