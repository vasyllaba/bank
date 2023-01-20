package com.solvd.bank.services;

import com.solvd.bank.dao.*;
import com.solvd.bank.dao.factory.DAOFactory;
import com.solvd.bank.dao.mysql.*;
import com.solvd.bank.exception.EntityNotFoundException;
import com.solvd.bank.models.Card;
import com.solvd.bank.models.CardDetails;
import org.apache.log4j.Logger;

public class CardService {
    private final ICardDAO cardDAO = DAOFactory.getFactory().getCardDAOImpl();
    private final ICardDetailsDAO cardDetailsDAO = new CardDetailsDAOImpl();
    private final IClientDAO clientDAO = new ClientDAOImpl();
    private final ICreditDAO creditDAO = new CreditDAOImpl();
    private final IDepositDAO depositDAO = new DepositDAOImpl();
    private final IMortgageDAO mortgageDAO = new MortgageDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(CardService.class);

    public Card getCardWithDetailsById(long id){
        LOGGER.info("Enter into getCardWithDetailsById method with id: " + id);

        Card card = cardDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
        card.setDetails(cardDetailsDAO.getById(card.getCardDetailsId()).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(card.getCardDetailsId())))
        );
        return card;
    }

    Card getFullCardByID(long id){
        LOGGER.info("Enter into getFullCardByID method with id: " + id);

        Card card = cardDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
        card.setDetails(cardDetailsDAO.getById(card.getCardDetailsId()).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(card.getCardDetailsId())))
        );
        card.setOwner(clientDAO.getById(card.getClientId()).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(card.getClientId())))
        );
        card.setCredits(creditDAO.getCreditsByCardId(card.getId()));
        card.setDeposits(depositDAO.getByCardId(card.getId()));
        card.setMortgages(mortgageDAO.getByCardId(card.getId()));
        return card;
    }


    public Card getCardById(long id){
        LOGGER.info("Enter into getCardById method with id: " + id);

        return cardDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(CardDetails.class, "id", String.valueOf(id))
        );
    }

    public boolean updateCard(Card card){
        LOGGER.info("Enter into updateCard method with card: " + card);

        return cardDAO.update(card);
    }

    public Card createCard(Card card){
        LOGGER.info("Enter into createCard method with card: " + card);

        return cardDAO.create(card).orElseThrow(
                () -> new EntityNotFoundException("Failed entity creat")
        );
    }

    public boolean removeCard(long id){
        LOGGER.info("Enter into removeCard method with id: " + id);

        return cardDAO.remove(id);
    }
}
