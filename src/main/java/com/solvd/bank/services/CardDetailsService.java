package com.solvd.bank.services;

import com.solvd.bank.dao.ICardDetailsDAO;
import com.solvd.bank.dao.mysql.CardDetailsDAOImpl;
import com.solvd.bank.models.CardDetails;

public class CardDetailsService {
    private final ICardDetailsDAO cardDetailsDAO = new CardDetailsDAOImpl();

    public CardDetails getCardDetailsById(long id){
        return cardDetailsDAO.getById(id);
    }

    public boolean updateCardDetails(CardDetails cardDetails){
        return cardDetailsDAO.update(cardDetails);
    }

    public CardDetails createCardDetails(CardDetails cardDetails){
        return cardDetailsDAO.create(cardDetails);
    }

    public boolean removeCardDetails(long id){
        return cardDetailsDAO.remove(id);
    }
}
