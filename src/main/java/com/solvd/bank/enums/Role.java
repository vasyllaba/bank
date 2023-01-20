package com.solvd.bank.enums;

public enum Role {
    EMPLOYEE("EMPLOYEE"),
    VIP_CLIENT("VIP_CLIENT"),
    GOLD_CLIENT("GOLD_CLIENT"),
    CLIENT("CLIENT"),
    UNRELIABLE("UNRELIABLE");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
