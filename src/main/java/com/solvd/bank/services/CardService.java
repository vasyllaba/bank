package com.solvd.bank.services;

import com.solvd.bank.dao.ICardDAO;
import com.solvd.bank.dao.ICardDetailsDAO;
import com.solvd.bank.dao.mysql.CardDAOImpl;
import com.solvd.bank.dao.mysql.CardDetailsDAOImpl;
import com.solvd.bank.models.Card;

public class CardService {
    private final ICardDAO cardDAO = new CardDAOImpl();
    private final ICardDetailsDAO cardDetailsDAO = new CardDetailsDAOImpl();

    public Card getCardWithDetailsById(long id){
        Card card = cardDAO.getById(id);
        card.setDetails(cardDetailsDAO.getById(card.getCardDetailsId()));
        return card;
    }

    public Card getCardById(long id){
        return cardDAO.getById(id);
    }

    public boolean updateCard(Card card){
        return cardDAO.update(card);
    }

    public Card createCard(Card card){
        return cardDAO.create(card);
    }

    public boolean removeCard(long id){
        return cardDAO.remove(id);
    }
}
