package com.solvd.bank.enums;

public enum PaymentTimePeriod {
    PER_YEAR("Per year"),
    PER_MONTH("Per month");

    private final String period;

    PaymentTimePeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }
}
