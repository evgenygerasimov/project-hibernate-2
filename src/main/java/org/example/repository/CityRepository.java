package org.example.repository;

import org.example.entity.City;

public class CityRepository extends BaseRepository<City> {
    public CityRepository() {
        super(City.class);
    }
}
