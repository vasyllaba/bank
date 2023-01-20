package com.solvd.bank.exception;

public class IncorrectEmailException extends RuntimeException {
    public IncorrectEmailException(String message) {
        super(message);
    }
}