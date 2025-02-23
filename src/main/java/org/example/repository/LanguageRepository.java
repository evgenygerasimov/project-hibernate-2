package org.example.repository;

import org.example.entity.Language;

public class LanguageRepository extends BaseRepository<Language> {
    public LanguageRepository() {
        super(Language.class);
    }
}
