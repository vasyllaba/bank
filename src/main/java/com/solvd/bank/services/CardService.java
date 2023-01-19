package com.solvd.bank.services;

import com.solvd.bank.dao.*;
import com.solvd.bank.dao.mysql.*;
import com.solvd.bank.models.Card;

public class CardService {
    private final ICardDAO cardDAO = new CardDAOImpl();
    private final ICardDetailsDAO cardDetailsDAO = new CardDetailsDAOImpl();
    private final IClientDAO clientDAO = new ClientDAOImpl();
    private final ICreditDAO creditDAO = new CreditDAOImpl();
    private final IDepositDAO depositDAO = new DepositDAOImpl();
    private final IMortgageDAO mortgageDAO = new MortgageDAOImpl();

    public Card getCardWithDetailsById(long id){
        Card card = cardDAO.getById(id);
        card.setDetails(cardDetailsDAO.getById(card.getCardDetailsId()));
        return card;
    }

    Card getFullCardByID(long id){
        Card card = cardDAO.getById(id);
        card.setDetails(cardDetailsDAO.getById(card.getCardDetailsId()));
        card.setOwner(clientDAO.getById(card.getClientId()));
        card.setCredits(creditDAO.getByCardId(card.getId()));
        card.setDeposits(depositDAO.getByCardId(card.getId()));
        card.setMortgages(mortgageDAO.getByCardId(card.getId()));
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
