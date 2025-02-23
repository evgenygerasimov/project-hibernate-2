package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.City;
import org.example.repository.CityRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final TransactionManager txManager;

    public City findById(Long id) {
        return txManager.execute(session -> cityRepository.findById(session, id));
    }

    public List<City> findAll() {
        return txManager.execute(cityRepository::findAll);
    }

    public void delete(City city) {
        txManager.execute(session -> {
            cityRepository.delete(session, city);
            return null;
        });
    }

    public void save(City city) {
        txManager.execute(session -> {
            cityRepository.save(session, city);
            return null;
        });
    }

    public void update(City city) {
        txManager.execute(session -> {
            cityRepository.update(session, city);
            return null;
        });
    }
}