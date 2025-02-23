package org.example.repository;

import org.example.entity.FilmText;

public class FilmTextRepository extends BaseRepository<FilmText> {
    public FilmTextRepository() {
        super(FilmText.class);
    }
}
