package com.solvd.bank.models;

import com.solvd.bank.enums.Currency;
import com.solvd.bank.enums.PaymentTimePeriod;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class DepositType {
    private Long id;
    private String name;
    private Boolean isReplenishment;
    private BigDecimal maxReplenishment;
    private PaymentTimePeriod paymentTimePeriod;
    private Integer minTerm;
    private Integer maxTerm;
    private Double noEarlyTerminatedRate;
    private Double earlyTerminatedRate;
    private Currency currency;
    private List<Deposit> deposits;

    private static final Logger LOGGER = Logger.getLogger(DepositType.class);

    public DepositType(Long id, String name, Boolean isReplenishment, BigDecimal maxReplenishment,
                       PaymentTimePeriod paymentTimePeriod, Integer minTerm, Integer maxTerm,
                       Double noEarlyTerminatedRate, Double earlyTerminatedRate, Currency currency,
                       List<Deposit> deposits) {
        LOGGER.info("create new DepositType with id = " + id);
        this.id = id;
        this.name = name;
        this.isReplenishment = isReplenishment;
        this.maxReplenishment = maxReplenishment;
        this.paymentTimePeriod = paymentTimePeriod;
        this.minTerm = minTerm;
        this.maxTerm = maxTerm;
        this.noEarlyTerminatedRate = noEarlyTerminatedRate;
        this.earlyTerminatedRate = earlyTerminatedRate;
        this.currency = currency;
        this.deposits = deposits;
    }

    public DepositType(String name, Boolean isReplenishment, BigDecimal maxReplenishment,
                       PaymentTimePeriod paymentTimePeriod, Integer minTerm, Integer maxTerm,
                       Double noEarlyTerminatedRate, Double earlyTerminatedRate, Currency currency) {
        LOGGER.info("create new DepositType with params");

        this.name = name;
        this.isReplenishment = isReplenishment;
        this.maxReplenishment = maxReplenishment;
        this.paymentTimePeriod = paymentTimePeriod;
        this.minTerm = minTerm;
        this.maxTerm = maxTerm;
        this.noEarlyTerminatedRate = noEarlyTerminatedRate;
        this.earlyTerminatedRate = earlyTerminatedRate;
        this.currency = currency;
    }

    public DepositType() {
        LOGGER.info("create new DepositType without params");

    }

    public Long getId() {
        LOGGER.info("get DepositType id");

        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set DepositType id");

        this.id = id;
    }

    public String getName() {
        LOGGER.info("get DepositType name");

        return name;
    }

    public void setName(String name) {
        LOGGER.info("set DepositType name");

        this.name = name;
    }

    public Boolean getReplenishment() {
        LOGGER.info("get DepositType isReplenishment");

        return isReplenishment;
    }

    public void setReplenishment(Boolean replenishment) {
        LOGGER.info("set DepositType isReplenishment");

        isReplenishment = replenishment;
    }

    public PaymentTimePeriod getPaymentTimePeriod() {
        LOGGER.info("get DepositType paymentTimePeriod");

        return paymentTimePeriod;
    }

    public void setPaymentTimePeriod(PaymentTimePeriod paymentTimePeriod) {
        LOGGER.info("set DepositType paymentTimePeriod");

        this.paymentTimePeriod = paymentTimePeriod;
    }

    public Integer getMinTerm() {
        LOGGER.info("get DepositType minTerm");

        return minTerm;
    }

    public void setMinTerm(Integer minTerm) {
        LOGGER.info("set DepositType minTerm");

        this.minTerm = minTerm;
    }

    public Integer getMaxTerm() {
        LOGGER.info("get DepositType maxTerm");

        return maxTerm;
    }

    public void setMaxTerm(Integer maxTerm) {
        LOGGER.info("set DepositType maxTerm");

        this.maxTerm = maxTerm;
    }

    public Double getNoEarlyTerminatedRate() {
        LOGGER.info("get DepositType noEarlyTerminatedRate");

        return noEarlyTerminatedRate;
    }

    public void setNoEarlyTerminatedRate(Double noEarlyTerminatedRate) {
        LOGGER.info("set DepositType noEarlyTerminatedRate");

        this.noEarlyTerminatedRate = noEarlyTerminatedRate;
    }

    public Double getEarlyTerminatedRate() {
        LOGGER.info("get DepositType earlyTerminatedRate");

        return earlyTerminatedRate;
    }

    public void setEarlyTerminatedRate(Double earlyTerminatedRate) {
        LOGGER.info("set DepositType earlyTerminatedRate");

        this.earlyTerminatedRate = earlyTerminatedRate;
    }

    public Currency getCurrency() {
        LOGGER.info("get DepositType currency");

        return currency;
    }

    public void setCurrency(Currency currency) {
        LOGGER.info("set DepositType currency");

        this.currency = currency;
    }

    public List<Deposit> getDeposits() {
        LOGGER.info("get DepositType deposits");

        return deposits;
    }

    public void setDeposits(List<Deposit> deposits) {
        LOGGER.info("set DepositType deposits");

        this.deposits = deposits;
    }

    public BigDecimal getMaxReplenishment() {
        LOGGER.info("get DepositType maxReplenishment");
        return maxReplenishment;
    }

    public void setMaxReplenishment(BigDecimal maxReplenishment) {
        LOGGER.info("set DepositType maxReplenishment");
        this.maxReplenishment = maxReplenishment;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");

        if (this == o) return true;
        if (!(o instanceof DepositType)) return false;

        DepositType that = (DepositType) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (!Objects.equals(isReplenishment, that.isReplenishment))
            return false;
        if (getPaymentTimePeriod() != that.getPaymentTimePeriod()) return false;
        if (getMinTerm() != null ? !getMinTerm().equals(that.getMinTerm()) : that.getMinTerm() != null) return false;
        if (getMaxTerm() != null ? !getMaxTerm().equals(that.getMaxTerm()) : that.getMaxTerm() != null) return false;
        if (getNoEarlyTerminatedRate() != null ? !getNoEarlyTerminatedRate().equals(that.getNoEarlyTerminatedRate()) : that.getNoEarlyTerminatedRate() != null)
            return false;
        if (getEarlyTerminatedRate() != null ? !getEarlyTerminatedRate().equals(that.getEarlyTerminatedRate()) : that.getEarlyTerminatedRate() != null)
            return false;
        return getCurrency() == that.getCurrency();
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");

        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (isReplenishment != null ? isReplenishment.hashCode() : 0);
        result = 31 * result + (getPaymentTimePeriod() != null ? getPaymentTimePeriod().hashCode() : 0);
        result = 31 * result + (getMinTerm() != null ? getMinTerm().hashCode() : 0);
        result = 31 * result + (getMaxTerm() != null ? getMaxTerm().hashCode() : 0);
        result = 31 * result + (getNoEarlyTerminatedRate() != null ? getNoEarlyTerminatedRate().hashCode() : 0);
        result = 31 * result + (getEarlyTerminatedRate() != null ? getEarlyTerminatedRate().hashCode() : 0);
        result = 31 * result + (getCurrency() != null ? getCurrency().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");

        return "DepositType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isReplenishment=" + isReplenishment +
                ", paymentTimePeriod=" + paymentTimePeriod +
                ", minTerm=" + minTerm +
                ", maxTerm=" + maxTerm +
                ", noEarlyTerminatedRate=" + noEarlyTerminatedRate +
                ", earlyTerminatedRate=" + earlyTerminatedRate +
                ", currency=" + currency +
                ", deposits=" + deposits +
                '}';
    }
}
