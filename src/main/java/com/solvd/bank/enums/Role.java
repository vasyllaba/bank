package com.solvd.bank.enums;

public enum Role {
    EMPLOYEE("Employee"),
    VIP_CLIENT("Vip client"),
    GOLD_CLIENT("Gold client"),
    CLIENT("Client"),
    UNRELIABLE("Unreliable");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
