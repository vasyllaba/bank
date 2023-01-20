package com.solvd.bank.services;

import com.solvd.bank.dao.IClientDAO;
import com.solvd.bank.dao.factory.DAOFactory;
import com.solvd.bank.exception.EntityNotFoundException;
import com.solvd.bank.exception.IncorrectEmailException;
import com.solvd.bank.models.Client;
import org.apache.log4j.Logger;

public class ClientService {
    private IClientDAO clientDAO = DAOFactory.getFactory().getClientDAOImpl();


    private static final Logger LOGGER = Logger.getLogger(ClientService.class);

    public Client findByEmail(String email){
        LOGGER.info("Enter into findByEmail method with email: " + email);
        return clientDAO.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(Client.class, "email", email)
        );
    }

    public Client getClientById(long id){
        LOGGER.info("Enter into getClientById method with id: " + id);
        return clientDAO.getById(id).orElseThrow(
                () -> new EntityNotFoundException(Client.class, "id", String.valueOf(id))
        );
    }

    public boolean updateClient(Client client){
        LOGGER.info("Enter into updateClient method with client: " + client);
        return clientDAO.update(client);
    }

    public Client createClient(Client client){
        LOGGER.info("Enter into createClient method with client: " + client);
        if (emailExists(client.getEmail())) {
            throw new IncorrectEmailException("Email " + client.getEmail() + " already exist");
        }
        return clientDAO.create(client).orElseThrow(
                () -> new EntityNotFoundException("Failed entity creat")
        );
    }

    public boolean removeClient(long id){
        LOGGER.info("Enter into removeClient method with id: " + id);
        return clientDAO.remove(id);
    }

    /**
     * method for checking email in database
     *
     * @param email
     * @return true if email already exist else false
     */
    private boolean emailExists(String email) {
        LOGGER.info("Enter into emailExists method with email: " + email);
        return clientDAO.findByEmail(email).isPresent();
    }
}
