package com.solvd.bank.models;


import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;

public class Mortgage {
    private Long id;
    private Long mortgageTypeId;
    private Long clientId;
    private Long cardId;
    private BigDecimal amount;
    private Integer term;
    private Double rate;
    private Date registerDate;
    private Date endDate;
    private MortgageType mortgageType;
    private Client owner;
    private Card card;

    private static final Logger LOGGER = Logger.getLogger(Mortgage.class);

    public Mortgage(Long id, Long mortgageTypeId, Long clientId, Long cardId, BigDecimal amount, Integer term,
                    Double rate, Date registerDate, Date endDate) {
        LOGGER.info("create new Mortgage with id = " + id);
        this.id = id;
        this.mortgageTypeId = mortgageTypeId;
        this.clientId = clientId;
        this.cardId = cardId;
        this.amount = amount;
        this.term = term;
        this.rate = rate;
        this.registerDate = registerDate;
        this.endDate = endDate;
    }

    public Mortgage(Long mortgageTypeId, Long clientId, Long cardId, BigDecimal amount, Integer term, Double rate,
                    Date registerDate, Date endDate) {
        LOGGER.info("create new Mortgage with params");

        this.mortgageTypeId = mortgageTypeId;
        this.clientId = clientId;
        this.cardId = cardId;
        this.amount = amount;
        this.term = term;
        this.rate = rate;
        this.registerDate = registerDate;
        this.endDate = endDate;
    }

    public Mortgage() {
        LOGGER.info("create new Mortgage without params");

    }

    public Long getId() {
        LOGGER.info("get Mortgage id");

        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set Mortgage id");

        this.id = id;
    }

    public Long getMortgageTypeId() {
        LOGGER.info("get Mortgage mortgageTypeId");

        return mortgageTypeId;
    }

    public void setMortgageTypeId(Long mortgageTypeId) {
        LOGGER.info("set Mortgage mortgageTypeId");

        this.mortgageTypeId = mortgageTypeId;
    }

    public Long getClientId() {
        LOGGER.info("get Mortgage clientId");

        return clientId;
    }

    public void setClientId(Long clientId) {
        LOGGER.info("set Mortgage clientId");

        this.clientId = clientId;
    }

    public Long getCardId() {
        LOGGER.info("get Mortgage cardId");

        return cardId;
    }

    public void setCardId(Long cardId) {
        LOGGER.info("set Mortgage cardId");

        this.cardId = cardId;
    }

    public BigDecimal getAmount() {
        LOGGER.info("get Mortgage amount");

        return amount;
    }

    public void setAmount(BigDecimal amount) {
        LOGGER.info("set Mortgage amount");

        this.amount = amount;
    }

    public Integer getTerm() {
        LOGGER.info("get Mortgage term");

        return term;
    }

    public void setTerm(Integer term) {
        LOGGER.info("set Mortgage term");

        this.term = term;
    }

    public Double getRate() {
        LOGGER.info("get Mortgage rate");

        return rate;
    }

    public void setRate(Double rate) {
        LOGGER.info("set Mortgage rate");

        this.rate = rate;
    }

    public Date getRegisterDate() {
        LOGGER.info("get Mortgage registerDate");

        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        LOGGER.info("set Mortgage registerDate");

        this.registerDate = registerDate;
    }

    public Date getEndDate() {
        LOGGER.info("get Mortgage endDate");

        return endDate;
    }

    public void setEndDate(Date endDate) {
        LOGGER.info("set Mortgage endDate");

        this.endDate = endDate;
    }

    public MortgageType getMortgageType() {
        LOGGER.info("get Mortgage mortgageType");

        return mortgageType;
    }

    public void setMortgageType(MortgageType mortgageType) {
        LOGGER.info("set Mortgage mortgageType");

        this.mortgageType = mortgageType;
    }

    public Client getOwner() {
        LOGGER.info("get Mortgage owner");

        return owner;
    }

    public void setOwner(Client owner) {
        LOGGER.info("set Mortgage owner");

        this.owner = owner;
    }

    public Card getCard() {
        LOGGER.info("get Mortgage card");

        return card;
    }

    public void setCard(Card card) {
        LOGGER.info("set Mortgage card");

        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");

        if (this == o) return true;
        if (!(o instanceof Mortgage)) return false;

        Mortgage mortgage = (Mortgage) o;

        if (getAmount() != null ? !getAmount().equals(mortgage.getAmount()) : mortgage.getAmount() != null)
            return false;
        if (getTerm() != null ? !getTerm().equals(mortgage.getTerm()) : mortgage.getTerm() != null) return false;
        if (getRate() != null ? !getRate().equals(mortgage.getRate()) : mortgage.getRate() != null) return false;
        if (getRegisterDate() != null ? !getRegisterDate().equals(mortgage.getRegisterDate()) : mortgage.getRegisterDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(mortgage.getEndDate()) : mortgage.getEndDate() != null)
            return false;
        if (getMortgageType() != null ? !getMortgageType().equals(mortgage.getMortgageType()) : mortgage.getMortgageType() != null)
            return false;
        if (getOwner() != null ? !getOwner().equals(mortgage.getOwner()) : mortgage.getOwner() != null) return false;
        return getCard() != null ? getCard().equals(mortgage.getCard()) : mortgage.getCard() == null;
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");

        int result = getAmount() != null ? getAmount().hashCode() : 0;
        result = 31 * result + (getTerm() != null ? getTerm().hashCode() : 0);
        result = 31 * result + (getRate() != null ? getRate().hashCode() : 0);
        result = 31 * result + (getRegisterDate() != null ? getRegisterDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = 31 * result + (getMortgageType() != null ? getMortgageType().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + (getCard() != null ? getCard().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");

        return "Mortgage{" +
                "id=" + id +
                ", mortgageTypeId=" + mortgageTypeId +
                ", clientId=" + clientId +
                ", cardId=" + cardId +
                ", amount=" + amount +
                ", term=" + term +
                ", rate=" + rate +
                ", registerDate=" + registerDate +
                ", endDate=" + endDate +
                ", mortgageType=" + mortgageType +
                ", owner=" + owner +
                ", card=" + card +
                '}';
    }
}
