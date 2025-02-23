package org.example.repository;

import org.example.entity.Actor;

public class ActorRepository extends BaseRepository<Actor> {
    public ActorRepository() {
        super(Actor.class);
    }
}
