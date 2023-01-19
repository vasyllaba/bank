package com.solvd.bank.models;

import org.apache.log4j.Logger;

public class CardDetails {
    private Long id;
    private String iban;
    private Card card;

    private static final Logger LOGGER = Logger.getLogger(CardDetails.class);

    public CardDetails(Long id, String iban) {
        LOGGER.info("create new CardDetails with id = " + id);
        this.id = id;
        this.iban = iban;
    }

    public CardDetails(String iban) {
        LOGGER.info("create new CardDetails with params");
        this.iban = iban;
    }

    public CardDetails() {
        LOGGER.info("create new CardDetails without params");
    }

    public Long getId() {
        LOGGER.info("get CardDetails id");
        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set CardDetails id");
        this.id = id;
    }

    public String getIban() {
        LOGGER.info("get CardDetails iban");
        return iban;
    }

    public void setIban(String iban) {
        LOGGER.info("set CardDetails iban");
        this.iban = iban;
    }

    public Card getCard() {
        LOGGER.info("get CardDetails card");
        return card;
    }

    public void setCard(Card card) {
        LOGGER.info("set CardDetails card");
        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");

        if (this == o) return true;
        if (!(o instanceof CardDetails)) return false;

        CardDetails that = (CardDetails) o;

        if (getIban() != null ? !getIban().equals(that.getIban()) : that.getIban() != null) return false;
        return getCard() != null ? getCard().equals(that.getCard()) : that.getCard() == null;
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");

        int result = getIban() != null ? getIban().hashCode() : 0;
        result = 31 * result + (getCard() != null ? getCard().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");
        return "CardDetails{" +
                "id=" + id +
                ", iban=" + iban +
                '}';
    }
}
