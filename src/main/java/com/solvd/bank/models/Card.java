package com.solvd.bank.models;

import com.solvd.bank.enums.CardType;
import com.solvd.bank.enums.Currency;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;

public class Card {
    private Long id;
    private Long clientId;
    private Long cardDetailsId;
    private CardType cardType;
    private Currency cardCurrency;
    private String cardNumber;
    private Date expireDate;
    private String cvv;
    private BigDecimal moneyCount;
    private BigDecimal creditLimit;
    private Integer pin;
    private Client owner;
    private CardDetails details;

    private static final Logger LOGGER = Logger.getLogger(Card.class);

    public Card(Long id, Long clientId, Long cardDetailsId, CardType cardType, Currency cardCurrency, String cardNumber,
                Date expireDate, String cvv, BigDecimal moneyCount, BigDecimal creditLimit) {
        LOGGER.info("create new Card with id = " + id);
        this.id = id;
        this.clientId = clientId;
        this.cardDetailsId = cardDetailsId;
        this.cardType = cardType;
        this.cardCurrency = cardCurrency;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.moneyCount = moneyCount;
        this.creditLimit = creditLimit;
    }

    public Card(Long clientId, Long cardDetailsId, CardType cardType, Currency cardCurrency, String cardNumber,
                Date expireDate, String cvv, BigDecimal moneyCount, BigDecimal creditLimit) {
        LOGGER.info("create new Card with params");
        this.clientId = clientId;
        this.cardDetailsId = cardDetailsId;
        this.cardType = cardType;
        this.cardCurrency = cardCurrency;
        this.cardNumber = cardNumber;
        this.expireDate = expireDate;
        this.cvv = cvv;
        this.moneyCount = moneyCount;
        this.creditLimit = creditLimit;
    }

    public Card() {
        LOGGER.info("create new Card without params");
    }

    public Long getId() {
        LOGGER.info("get Card id");
        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set Card id");
        this.id = id;
    }

    public Long getClientId() {
        LOGGER.info("get Card clientId");
        return clientId;
    }

    public void setClientId(Long clientId) {
        LOGGER.info("set Card clientId");
        this.clientId = clientId;
    }

    public Long getCardDetailsId() {
        LOGGER.info("get Card cardDetailsId");
        return cardDetailsId;
    }

    public void setCardDetailsId(Long cardDetailsId) {
        LOGGER.info("set Card cardDetailsId");
        this.cardDetailsId = cardDetailsId;
    }

    public CardType getCardType() {
        LOGGER.info("get Card cardType");
        return cardType;
    }

    public void setCardType(CardType cardType) {
        LOGGER.info("set Card cardType");
        this.cardType = cardType;
    }

    public Currency getCardCurrency() {
        LOGGER.info("get Card cardCurrency");
        return cardCurrency;
    }

    public void setCardCurrency(Currency cardCurrency) {
        LOGGER.info("set Card cardCurrency");
        this.cardCurrency = cardCurrency;
    }

    public String getCardNumber() {
        LOGGER.info("get Card cardNumber");
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        LOGGER.info("set Card cardNumber");
        this.cardNumber = cardNumber;
    }

    public Date getExpireDate() {
        LOGGER.info("get Card expireDate");
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        LOGGER.info("set Card expireDate");
        this.expireDate = expireDate;
    }

    public String getCvv() {
        LOGGER.info("get Card cvv");
        return cvv;
    }

    public void setCvv(String cvv) {
        LOGGER.info("set Card cvv");
        this.cvv = cvv;
    }

    public BigDecimal getMoneyCount() {
        LOGGER.info("get Card moneyCount");
        return moneyCount;
    }

    public void setMoneyCount(BigDecimal moneyCount) {
        LOGGER.info("set Card moneyCount");
        this.moneyCount = moneyCount;
    }

    public BigDecimal getCreditLimit() {
        LOGGER.info("get Card creditLimit");
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        LOGGER.info("set Card creditLimit");
        this.creditLimit = creditLimit;
    }

    public Client getOwner() {
        LOGGER.info("get Card owner");
        return owner;
    }

    public void setOwner(Client owner) {
        LOGGER.info("set Card owner");

        this.owner = owner;
    }

    public CardDetails getDetails() {
        LOGGER.info("get Card details");

        return details;
    }

    public void setDetails(CardDetails details) {
        LOGGER.info("set Card details");

        this.details = details;
    }

    public Integer getPin() {
        LOGGER.info("get Card pin");
        return pin;
    }

    public void setPin(Integer pin) {
        LOGGER.info("set Card pin");
        this.pin = pin;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");

        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (getCardType() != card.getCardType()) return false;
        if (getCardCurrency() != card.getCardCurrency()) return false;
        if (getCardNumber() != null ? !getCardNumber().equals(card.getCardNumber()) : card.getCardNumber() != null)
            return false;
        if (getExpireDate() != null ? !getExpireDate().equals(card.getExpireDate()) : card.getExpireDate() != null)
            return false;
        if (getCvv() != null ? !getCvv().equals(card.getCvv()) : card.getCvv() != null) return false;
        if (getOwner() != null ? !getOwner().equals(card.getOwner()) : card.getOwner() != null) return false;
        return getDetails() != null ? getDetails().equals(card.getDetails()) : card.getDetails() == null;
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");

        int result = getCardType() != null ? getCardType().hashCode() : 0;
        result = 31 * result + (getCardCurrency() != null ? getCardCurrency().hashCode() : 0);
        result = 31 * result + (getCardNumber() != null ? getCardNumber().hashCode() : 0);
        result = 31 * result + (getExpireDate() != null ? getExpireDate().hashCode() : 0);
        result = 31 * result + (getCvv() != null ? getCvv().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + (getDetails() != null ? getDetails().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");
        return "Card{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", cardDetailsId=" + cardDetailsId +
                ", cardType=" + cardType +
                ", cardCurrency=" + cardCurrency +
                ", cardNumber='" + cardNumber + '\'' +
                ", expireDate=" + expireDate +
                ", cvv='" + cvv + '\'' +
                ", moneyCount=" + moneyCount +
                ", creditLimit=" + creditLimit +
                ", pin=" + pin +
                ", owner=" + owner +
                ", details=" + details +
                '}';
    }
}
