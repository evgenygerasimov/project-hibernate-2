package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.*;
import org.example.repository.FilmRepository;
import org.example.repository.FilmTextRepository;
import org.example.repository.InventoryRepository;
import org.example.utils.TransactionManager;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final InventoryRepository inventoryRepository;
    private final FilmTextRepository filmTextRepository;
    private final TransactionManager txManager;

    public void createNewFilm(Film film, FilmText filmText, List<Actor> actors, Language language, List<Category> categories, Store store) {
        txManager.execute(session -> {

            film.setCategories(categories);
            film.setLanguage(language);
            film.setActors(actors);
            film.setFilmText(filmText);
            film.setTitle(filmText.getTitle());
            film.setDescription(filmText.getDescription());

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventory.setLastUpdate(LocalDateTime.now());

            filmRepository.save(session, film);
            filmText.setFilm(film);
            filmTextRepository.save(session, filmText);
            inventoryRepository.save(session, inventory);
            return null;
        });
    }

    public Film findById(Long id) {
        return txManager.execute(session -> filmRepository.findById(session, id));
    }

    public List<Film> findAll() {
        return txManager.execute(filmRepository::findAll);
    }

    public void delete(Film film) {
        txManager.execute(session -> {
            filmRepository.delete(session, film);
            return null;
        });
    }

    public void save(Film film) {
        txManager.execute(session -> {
            filmRepository.save(session, film);
            return null;
        });
    }

    public void update(Film film) {
        txManager.execute(session -> {
            filmRepository.update(session, film);
            return null;
        });
    }
}
