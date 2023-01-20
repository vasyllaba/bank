package com.solvd.bank.dao.factory;

public class DAOFactory {
    public static IDAOFactory getFactory() {
        return new MySqlDAOFactory();
    }
}
