package com.solvd.bank;

import com.solvd.bank.models.Card;
import com.solvd.bank.services.CardService;
import com.solvd.bank.services.ClientService;

public class Main {
    public static void main(String[] args) {
        ClientService cs = new ClientService();
        cs.findByEmail("TheBest@emd.com");
    }
}
