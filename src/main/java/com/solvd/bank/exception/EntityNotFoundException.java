package com.solvd.bank.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class clazz, String searchField, String searchValue) {
        super(clazz.getSimpleName() + " was not found for parameter " + searchField + " with value " + searchValue);
    }
}
