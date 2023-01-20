package com.solvd.bank.exception;

public class DeleteDisabledException extends RuntimeException {
    public DeleteDisabledException(Class clazz) {
        super("Unable to delete " + clazz.getSimpleName().toLowerCase());
    }
}
