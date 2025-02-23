package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.FilmText;
import org.example.repository.FilmTextRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class FilmTextService {

    private final FilmTextRepository filmTextRepository;
    private final TransactionManager txManager;

    public FilmText findById(Long id) {
        return txManager.execute(session -> filmTextRepository.findById(session, id));
    }

    public List<FilmText> findAll() {
        return txManager.execute(filmTextRepository::findAll);
    }

    public void delete(FilmText filmText) {
        txManager.execute(session -> {
            filmTextRepository.delete(session, filmText);
            return null;
        });
    }

    public void save(FilmText filmText) {
        txManager.execute(session -> {
            filmTextRepository.save(session, filmText);
            return null;
        });
    }

    public void update(FilmText filmText) {
        txManager.execute(session -> {
            filmTextRepository.save(session, filmText);
            return null;
        });
    }
}


