package org.example.repository;

import org.example.entity.Film;

public class FilmRepository extends BaseRepository<Film> {
    public FilmRepository() {
        super(Film.class);
    }
}
