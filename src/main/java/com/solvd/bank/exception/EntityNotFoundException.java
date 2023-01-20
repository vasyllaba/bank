package com.solvd.bank.exception;

import org.apache.log4j.Logger;

public class EntityNotFoundException extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(EntityNotFoundException.class);

    public EntityNotFoundException(Class clazz, String searchField, String searchValue) {
        super(clazz.getSimpleName() + " was not found for parameter " + searchField + " with value " + searchValue);
        LOGGER.error(clazz.getSimpleName() + " was not found for parameter " + searchField + " with value " + searchValue);
    }

    public EntityNotFoundException(String message) {
        super(message);
        LOGGER.error(message);
    }
}
