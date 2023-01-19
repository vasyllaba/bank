package com.solvd.bank.models;

import com.solvd.bank.enums.Currency;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class MortgageType {
    private Long id;
    private BigDecimal maxAmount;
    private Integer maxTerm;
    private Integer minTerm;
    private Double rate;
    private Currency currency;
    private List<Mortgage> mortgageList;

    private static final Logger LOGGER = Logger.getLogger(MortgageType.class);

    public MortgageType(Long id, BigDecimal maxAmount, Integer maxTerm, Integer minTerm, Double rate,
                        Currency currency) {
        LOGGER.info("create new MortgageType with id = " + id);
        this.id = id;
        this.maxAmount = maxAmount;
        this.maxTerm = maxTerm;
        this.minTerm = minTerm;
        this.rate = rate;
        this.currency = currency;
    }

    public MortgageType(BigDecimal maxAmount, Integer maxTerm, Integer minTerm, Double rate, Currency currency) {
        LOGGER.info("create new MortgageType with params");
        this.maxAmount = maxAmount;
        this.maxTerm = maxTerm;
        this.minTerm = minTerm;
        this.rate = rate;
        this.currency = currency;
    }

    public MortgageType() {
        LOGGER.info("create new MortgageType without params");

    }

    public Long getId() {
        LOGGER.info("get MortgageType id");

        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set MortgageType id");

        this.id = id;
    }

    public BigDecimal getMaxAmount() {
        LOGGER.info("get MortgageType maxAmount");

        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        LOGGER.info("set MortgageType maxAmount");

        this.maxAmount = maxAmount;
    }

    public Integer getMaxTerm() {
        LOGGER.info("get MortgageType maxTerm");

        return maxTerm;
    }

    public void setMaxTerm(Integer maxTerm) {
        LOGGER.info("set MortgageType maxTerm");

        this.maxTerm = maxTerm;
    }

    public Integer getMinTerm() {
        LOGGER.info("get MortgageType minTerm");

        return minTerm;
    }

    public void setMinTerm(Integer minTerm) {
        LOGGER.info("set MortgageType minTerm");

        this.minTerm = minTerm;
    }

    public Double getRate() {
        LOGGER.info("get MortgageType rate");

        return rate;
    }

    public void setRate(Double rate) {
        LOGGER.info("set MortgageType rate");

        this.rate = rate;
    }

    public Currency getCurrency() {
        LOGGER.info("get MortgageType currency");

        return currency;
    }

    public void setCurrency(Currency currency) {
        LOGGER.info("set MortgageType currency");

        this.currency = currency;
    }

    public List<Mortgage> getMortgageList() {
        LOGGER.info("get MortgageType mortgageList");

        return mortgageList;
    }

    public void setMortgageList(List<Mortgage> mortgageList) {
        LOGGER.info("set MortgageType mortgageList");

        this.mortgageList = mortgageList;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");
        if (this == o) return true;
        if (!(o instanceof MortgageType)) return false;

        MortgageType that = (MortgageType) o;

        if (getMaxAmount() != null ? !getMaxAmount().equals(that.getMaxAmount()) : that.getMaxAmount() != null)
            return false;
        if (getMaxTerm() != null ? !getMaxTerm().equals(that.getMaxTerm()) : that.getMaxTerm() != null) return false;
        if (getMinTerm() != null ? !getMinTerm().equals(that.getMinTerm()) : that.getMinTerm() != null) return false;
        if (getRate() != null ? !getRate().equals(that.getRate()) : that.getRate() != null) return false;
        return getCurrency() == that.getCurrency();
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");

        int result = getMaxAmount() != null ? getMaxAmount().hashCode() : 0;
        result = 31 * result + (getMaxTerm() != null ? getMaxTerm().hashCode() : 0);
        result = 31 * result + (getMinTerm() != null ? getMinTerm().hashCode() : 0);
        result = 31 * result + (getRate() != null ? getRate().hashCode() : 0);
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");

        return "MortgageTypes{" +
                "id=" + id +
                ", maxAmount=" + maxAmount +
                ", maxTerm=" + maxTerm +
                ", minTerm=" + minTerm +
                ", rate=" + rate +
                ", currency=" + currency +
                ", mortgageList=" + mortgageList +
                '}';
    }
}
