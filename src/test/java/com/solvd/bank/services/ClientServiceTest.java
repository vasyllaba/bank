package com.solvd.bank.services;

import com.solvd.bank.enums.Role;
import com.solvd.bank.exception.IncorrectEmailException;
import com.solvd.bank.models.Client;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClientServiceTest {
    ClientService clientService = new ClientService();
    Client client1;
    Client client2;

    @BeforeMethod
    public void setUp() {
        client1 = new Client(5l,"+38099911101", "testEmail@mail.com", "1111", Role.CLIENT);
        client2 = new Client(6l,"+38099911102", "testEmail2@mail.com", "1112", Role.EMPLOYEE);
    }

    @Test(expectedExceptions = IncorrectEmailException.class)
    public void testCreateClient() {
        client1.setEmail("sameEmail@mail.com");
        client2.setEmail("sameEmail@mail.com");
        clientService.createClient(client1);
        clientService.createClient(client2);
    }

    @AfterMethod
    public void clean(){
        if (client1.getId() != null)
            clientService.removeClient(client1.getId());
        if (client2.getId() != null)
            clientService.removeClient(client2.getId());
    }
}