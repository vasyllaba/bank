package com.solvd.bank.models;

import com.solvd.bank.enums.PercentDestination;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Deposit {
    private Long id;
    private Long clientId;
    private Long depositTypeId;
    private String name;
    private BigDecimal amount;
    private Boolean isAutoExtension;
    private PercentDestination percentDestination;
    private BigDecimal regularReplacement;
    private Long regularReplacementCardId;
    private Integer term;
    private Double rate;
    private Date registerDate;
    private Date endDate;
    private Client owner;
    private DepositType depositType;
    private Card card;

    private static final Logger LOGGER = Logger.getLogger(Deposit.class);


    public Deposit(Long id, Long clientId, Long depositTypeId, String name, BigDecimal amount, Boolean isAutoExtension,
                   PercentDestination percentDestination, BigDecimal regularReplacement, Long regularReplacementCardId,
                   Integer term, Double rate, Date registerDate, Date endDate) {
        LOGGER.info("create Deposit with id = " + id);
        this.id = id;
        this.clientId = clientId;
        this.depositTypeId = depositTypeId;
        this.name = name;
        this.amount = amount;
        this.isAutoExtension = isAutoExtension;
        this.percentDestination = percentDestination;
        this.regularReplacement = regularReplacement;
        this.regularReplacementCardId = regularReplacementCardId;
        this.term = term;
        this.rate = rate;
        this.registerDate = registerDate;
        this.endDate = endDate;
    }

    public Deposit(Long clientId, Long depositTypeId, String name, BigDecimal amount, Boolean isAutoExtension,
                   PercentDestination percentDestination, BigDecimal regularReplacement, Long regularReplacementCardId,
                   Integer term, Double rate, Date registerDate, Date endDate) {
        LOGGER.info("create Deposit with params");

        this.clientId = clientId;
        this.depositTypeId = depositTypeId;
        this.name = name;
        this.amount = amount;
        this.isAutoExtension = isAutoExtension;
        this.percentDestination = percentDestination;
        this.regularReplacement = regularReplacement;
        this.regularReplacementCardId = regularReplacementCardId;
        this.term = term;
        this.rate = rate;
        this.registerDate = registerDate;
        this.endDate = endDate;
    }

    public Deposit() {
        LOGGER.info("create Deposit without params");

    }

    public Long getId() {
        LOGGER.info("get Deposit id");

        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set Deposit id");

        this.id = id;
    }

    public Long getClientId() {
        LOGGER.info("get Deposit clientId");

        return clientId;
    }

    public void setClientId(Long clientId) {
        LOGGER.info("set Deposit clientId");

        this.clientId = clientId;
    }

    public Long getDepositTypeId() {
        LOGGER.info("get Deposit depositTypeId");

        return depositTypeId;
    }

    public void setDepositTypeId(Long depositTypeId) {
        LOGGER.info("set Deposit depositTypeId");

        this.depositTypeId = depositTypeId;
    }

    public String getName() {
        LOGGER.info("get Deposit name");

        return name;
    }

    public void setName(String name) {
        LOGGER.info("set Deposit name");

        this.name = name;
    }

    public BigDecimal getAmount() {
        LOGGER.info("get Deposit amount");

        return amount;
    }

    public void setAmount(BigDecimal amount) {
        LOGGER.info("set Deposit amount");

        this.amount = amount;
    }

    public Boolean getAutoExtension() {
        LOGGER.info("get Deposit isAutoExtension");

        return isAutoExtension;
    }

    public void setAutoExtension(Boolean autoExtension) {
        LOGGER.info("set Deposit isAutoExtension");

        isAutoExtension = autoExtension;
    }

    public PercentDestination getPercentDestination() {
        LOGGER.info("get Deposit percentDestination");

        return percentDestination;
    }

    public void setPercentDestination(PercentDestination percentDestination) {
        LOGGER.info("set Deposit percentDestination");

        this.percentDestination = percentDestination;
    }

    public BigDecimal getRegularReplacement() {
        LOGGER.info("get Deposit regularReplacement");

        return regularReplacement;
    }

    public void setRegularReplacement(BigDecimal regularReplacement) {
        LOGGER.info("set Deposit regularReplacement");

        this.regularReplacement = regularReplacement;
    }

    public Long getRegularReplacementCardId() {
        LOGGER.info("get Deposit regularReplacementCardId");

        return regularReplacementCardId;
    }

    public void setRegularReplacementCardId(Long regularReplacementCardId) {
        LOGGER.info("set Deposit regularReplacementCardId");

        this.regularReplacementCardId = regularReplacementCardId;
    }

    public Integer getTerm() {
        LOGGER.info("get Deposit term");

        return term;
    }

    public void setTerm(Integer term) {
        LOGGER.info("set Deposit term");

        this.term = term;
    }

    public Double getRate() {
        LOGGER.info("get Deposit rate");

        return rate;
    }

    public void setRate(Double rate) {
        LOGGER.info("set Deposit rate");

        this.rate = rate;
    }

    public Date getRegisterDate() {
        LOGGER.info("get Deposit registerDate");

        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        LOGGER.info("set Deposit registerDate");

        this.registerDate = registerDate;
    }

    public Date getEndDate() {
        LOGGER.info("get Deposit endDate");

        return endDate;
    }

    public void setEndDate(Date endDate) {
        LOGGER.info("set Deposit endDate");

        this.endDate = endDate;
    }

    public Client getOwner() {
        LOGGER.info("get Deposit owner");

        return owner;
    }

    public void setOwner(Client owner) {
        LOGGER.info("set Deposit owner");

        this.owner = owner;
    }

    public DepositType getDepositType() {
        LOGGER.info("get Deposit depositType");

        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        LOGGER.info("set Deposit depositType");

        this.depositType = depositType;
    }

    public Card getCard() {
        LOGGER.info("get Deposit card");

        return card;
    }

    public void setCard(Card card) {
        LOGGER.info("set Deposit card");

        this.card = card;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");

        if (this == o) return true;
        if (!(o instanceof Deposit)) return false;

        Deposit deposit = (Deposit) o;

        if (getName() != null ? !getName().equals(deposit.getName()) : deposit.getName() != null) return false;
        if (getAmount() != null ? !getAmount().equals(deposit.getAmount()) : deposit.getAmount() != null) return false;
        if (!Objects.equals(isAutoExtension, deposit.isAutoExtension)) return false;
        if (getPercentDestination() != deposit.getPercentDestination()) return false;
        if (getRegularReplacement() != null ? !getRegularReplacement().equals(deposit.getRegularReplacement()) : deposit.getRegularReplacement() != null)
            return false;
        if (getTerm() != null ? !getTerm().equals(deposit.getTerm()) : deposit.getTerm() != null) return false;
        if (getRate() != null ? !getRate().equals(deposit.getRate()) : deposit.getRate() != null) return false;
        return getDepositType() != null ? getDepositType().equals(deposit.getDepositType()) : deposit.getDepositType() == null;
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");

        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getAmount() != null ? getAmount().hashCode() : 0);
        result = 31 * result + (isAutoExtension != null ? isAutoExtension.hashCode() : 0);
        result = 31 * result + (getPercentDestination() != null ? getPercentDestination().hashCode() : 0);
        result = 31 * result + (getRegularReplacement() != null ? getRegularReplacement().hashCode() : 0);
        result = 31 * result + (getTerm() != null ? getTerm().hashCode() : 0);
        result = 31 * result + (getRate() != null ? getRate().hashCode() : 0);
        result = 31 * result + (getDepositType() != null ? getDepositType().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");

        return "Deposit{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", depositTypeId=" + depositTypeId +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", isAutoExtension=" + isAutoExtension +
                ", percentDestination=" + percentDestination +
                ", regularReplacement=" + regularReplacement +
                ", regularReplacementCardId=" + regularReplacementCardId +
                ", term=" + term +
                ", rate=" + rate +
                ", registerDate=" + registerDate +
                ", endDate=" + endDate +
                ", owner=" + owner +
                ", depositType=" + depositType +
                ", card=" + card +
                '}';
    }
}
