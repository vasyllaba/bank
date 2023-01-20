package com.solvd.bank;

import com.solvd.bank.models.Card;
import com.solvd.bank.services.CardService;
import com.solvd.bank.services.ClientService;

public class Main {
    public static void main(String[] args) {
//        CardService cs = new CardService();
//
//        Card card = cs.getCardWithDetailsById(5);
//        System.out.println(card);
        ClientService cs = new ClientService();
        System.out.println(cs.findByEmail("TheBest@emd.com"));

    }
}
