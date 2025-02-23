package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Language;
import org.example.repository.LanguageRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final TransactionManager txManager;

    public Language findById(Long id) {
        return txManager.execute(session -> languageRepository.findById(session, id));
    }

    public List<Language> findAll() {
        return txManager.execute(languageRepository::findAll);
    }

    public void delete(Language language) {
        txManager.execute(session -> {
            languageRepository.delete(session, language);
            return null;
        });
    }

    public void save(Language language) {
        txManager.execute(session -> {
            languageRepository.save(session, language);
            return null;
        });
    }

    public void update(Language language) {
        txManager.execute(session -> {
            languageRepository.update(session, language);
            return null;
        });
    }
}
