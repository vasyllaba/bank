package com.solvd.bank.models;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;

public class Credit {
    private Long id;
    private Long clientId;
    private Long cardId;
    private BigDecimal amount;
    private Integer term;
    private Double interestRate;
    private Date registerDate;
    private Date endDate;
    private Client owner;
    private Card card;

    private static final Logger LOGGER = Logger.getLogger(Credit.class);

    public Credit(Long id, Long clientId, Long cardId, BigDecimal amount, Integer term, Double interestRate,
                  Date registerDate, Date endDate) {
        LOGGER.info("create new Credit with id = " + id);
        this.id = id;
        this.clientId = clientId;
        this.cardId = cardId;
        this.amount = amount;
        this.term = term;
        this.interestRate = interestRate;
        this.registerDate = registerDate;
        this.endDate = endDate;
    }

    public Credit(Long clientId, Long cardId, BigDecimal amount, Integer term, Double interestRate, Date registerDate,
                  Date endDate) {
        LOGGER.info("create new Credit with params");
        this.clientId = clientId;
        this.cardId = cardId;
        this.amount = amount;
        this.term = term;
        this.interestRate = interestRate;
        this.registerDate = registerDate;
        this.endDate = endDate;
    }

    public Credit() {
        LOGGER.info("create new Credit without params");

    }

    public Long getId() {
        LOGGER.info("get Credit id");

        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set Credit id");

        this.id = id;
    }

    public Long getClientId() {
        LOGGER.info("get Credit clientId");

        return clientId;
    }

    public void setClientId(Long clientId) {
        LOGGER.info("set Credit clientId");

        this.clientId = clientId;
    }

    public Long getCardId() {
        LOGGER.info("get Credit cardId");

        return cardId;
    }

    public void setCardId(Long cardId) {
        LOGGER.info("set Credit cardId");

        this.cardId = cardId;
    }

    public BigDecimal getAmount() {
        LOGGER.info("get Credit amount");

        return amount;
    }

    public void setAmount(BigDecimal amount) {
        LOGGER.info("set Credit amount");

        this.amount = amount;
    }

    public Integer getTerm() {
        LOGGER.info("get Credit term");

        return term;
    }

    public void setTerm(Integer term) {
        LOGGER.info("set Credit term");

        this.term = term;
    }

    public Double getInterestRate() {
        LOGGER.info("get Credit interestRate");

        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        LOGGER.info("set Credit interestRate");

        this.interestRate = interestRate;
    }

    public Date getRegisterDate() {
        LOGGER.info("get Credit registerDate");

        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        LOGGER.info("set Credit registerDate");

        this.registerDate = registerDate;
    }

    public Date getEndDate() {
        LOGGER.info("get Credit endDate");

        return endDate;
    }

    public void setEndDate(Date endDate) {
        LOGGER.info("set Credit endDate");

        this.endDate = endDate;
    }

    public Client getOwner() {
        LOGGER.info("get Credit owner");

        return owner;
    }

    public void setOwner(Client owner) {
        LOGGER.info("set Credit owner");

        this.owner = owner;
    }

    public Card getCard() {
        LOGGER.info("get Credit card");

        return card;
    }

    public void setCard(Card card) {
        LOGGER.info("get Credit card");

        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");

        if (this == o) return true;
        if (!(o instanceof Credit)) return false;

        Credit credit = (Credit) o;

        if (getId() != null ? !getId().equals(credit.getId()) : credit.getId() != null) return false;
        if (getClientId() != null ? !getClientId().equals(credit.getClientId()) : credit.getClientId() != null)
            return false;
        if (getCardId() != null ? !getCardId().equals(credit.getCardId()) : credit.getCardId() != null) return false;
        if (getAmount() != null ? !getAmount().equals(credit.getAmount()) : credit.getAmount() != null) return false;
        if (getTerm() != null ? !getTerm().equals(credit.getTerm()) : credit.getTerm() != null) return false;
        if (getInterestRate() != null ? !getInterestRate().equals(credit.getInterestRate()) : credit.getInterestRate() != null)
            return false;
        if (getRegisterDate() != null ? !getRegisterDate().equals(credit.getRegisterDate()) : credit.getRegisterDate() != null)
            return false;
        if (getEndDate() != null ? !getEndDate().equals(credit.getEndDate()) : credit.getEndDate() != null)
            return false;
        if (getOwner() != null ? !getOwner().equals(credit.getOwner()) : credit.getOwner() != null) return false;
        return getCard() != null ? getCard().equals(credit.getCard()) : credit.getCard() == null;
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");

        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getClientId() != null ? getClientId().hashCode() : 0);
        result = 31 * result + (getCardId() != null ? getCardId().hashCode() : 0);
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (getTerm() != null ? getTerm().hashCode() : 0);
        result = 31 * result + (getInterestRate() != null ? getInterestRate().hashCode() : 0);
        result = 31 * result + (getRegisterDate() != null ? getRegisterDate().hashCode() : 0);
        result = 31 * result + (getEndDate() != null ? getEndDate().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + (getCard() != null ? getCard().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");

        return "Credit{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", cardId=" + cardId +
                ", amount=" + amount +
                ", term=" + term +
                ", interestRate=" + interestRate +
                ", registerDate=" + registerDate +
                ", endDate=" + endDate +
                ", owner=" + owner +
                ", card=" + card +
                '}';
    }
}
