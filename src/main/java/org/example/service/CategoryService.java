package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final TransactionManager txManager;

    public Category findById(Long id) {
        return txManager.execute(session -> categoryRepository.findById(session, id));
    }

    public List<Category> findAll() {
        return txManager.execute(categoryRepository::findAll);
    }

    public void delete(Category category) {
        txManager.execute(session -> {
            categoryRepository.delete(session, category);
            return null;
        });
    }

    public void save(Category category) {
        txManager.execute(session -> {
            categoryRepository.save(session, category);
            return null;
        });
    }

    public void update(Category category) {
        txManager.execute(session -> {
            categoryRepository.update(session, category);
            return null;
        });
    }
}

