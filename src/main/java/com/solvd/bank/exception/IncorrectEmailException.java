package com.solvd.bank.exception;

import com.solvd.bank.services.CardDetailsService;
import org.apache.log4j.Logger;

public class IncorrectEmailException extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(IncorrectEmailException.class);

    public IncorrectEmailException(String message) {
        super(message);
        LOGGER.error(message);
    }
}