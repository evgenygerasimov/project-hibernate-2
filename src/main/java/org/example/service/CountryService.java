package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Country;
import org.example.repository.CountryRepository;
import org.example.utils.TransactionManager;
import org.hibernate.Hibernate;

import java.util.List;

@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;
    private final TransactionManager txManager;

    public Country findById(Long id) {
        return txManager.execute(session -> {
            Country country = session.get(Country.class, id);
            Hibernate.initialize(country.getCities());
            return country;
        });
    }

    public List<Country> findAll() {
        return txManager.execute(countryRepository::findAll);
    }

    public void delete(Country country) {
        txManager.execute(session -> {
            countryRepository.delete(session, country);
            return null;
        });
    }

    public void save(Country country) {
        txManager.execute(session -> {
            countryRepository.save(session, country);
            return null;
        });
    }

    public void update(Country country) {
        txManager.execute(session -> {
            countryRepository.update(session, country);
            return null;
        });
    }
}
