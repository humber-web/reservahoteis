package com.example.hotelreservations.exception;

public class EntityNotFoundException extends RuntimeException {
    private final String entity;
    private final Object id;

    public EntityNotFoundException(String entity, Object id) {
        super(entity + " with id " + id + " was not found.");
        this.entity = entity;
        this.id = id;
    }

    public String getEntity() {
        return entity;
    }

    public Object getId() {
        return id;
    }
}
