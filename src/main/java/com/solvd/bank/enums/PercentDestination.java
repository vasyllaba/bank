package com.solvd.bank.enums;

public enum PercentDestination {
    ON_CARD("On card"),
    ADD_TO_DEPOSIT("Add to deposit");

    private final String destination;

    PercentDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }
}
