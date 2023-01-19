package com.solvd.bank.enums;

public enum CardType {
    DEBIT("DEBIT"),
    CREDIT("CREDIT"),
    GOLD("GOLD");

    private final String cardType;

    CardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }
}
