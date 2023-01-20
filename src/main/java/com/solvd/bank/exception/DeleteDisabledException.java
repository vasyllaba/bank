package com.solvd.bank.exception;

import com.solvd.bank.services.CardDetailsService;
import org.apache.log4j.Logger;

public class DeleteDisabledException extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(DeleteDisabledException.class);

    public DeleteDisabledException(Class clazz) {
        super("Unable to delete " + clazz.getSimpleName().toLowerCase());
        LOGGER.error("Unable to delete " + clazz.getSimpleName().toLowerCase());
    }
}
