package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Actor;
import org.example.repository.ActorRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;
    private final TransactionManager txManager;

    public Actor findById(Long id) {
        return txManager.execute(session -> actorRepository.findById(session, id));
    }

    public List<Actor> findAll() {
        return txManager.execute(actorRepository::findAll);
    }

    public void delete(Actor actor) {
        txManager.execute(session -> {
            actorRepository.delete(session, actor);
            return null;
        });
    }

    public void save(Actor actor) {
        txManager.execute(session -> {
            actorRepository.save(session, actor);
            return null;
        });
    }

    public void update(Actor actor) {
        txManager.execute(session -> {
            actorRepository.update(session, actor);
            return null;
        });
    }
}
