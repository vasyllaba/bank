package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IClientDAO;
import com.solvd.bank.enums.Role;
import com.solvd.bank.models.Client;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class ClientDAOImpl extends AbstractMySQLRepo implements IClientDAO {

    private static final String GET_CLIENT_BY_ID = "SELECT * FROM clients WHERE id = ?";
    private static final String UPDATE_CLIENT =
            "UPDATE clients SET passport_id = ?, mobile =?, email =?, password =?, role =? WHERE id = ?";
    private static final String CRATE_CLIENT = "INSERT INTO clients (passport_id, mobile, email, password, role) VALUES (?, ?, ?, ?, ?)";
    private static final String REMOVE_CLIENT = "DELETE FROM clients WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(ClientDAOImpl.class);

    @Override
    public Client getById(long id) {
        final Connection connection = ConnectionPool.getConnection();
        Client client = new Client();

        try (PreparedStatement ps = connection.prepareStatement(GET_CLIENT_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                client.setId(rs.getLong("id"));
                client.setPassportId(rs.getLong("passport_id"));
                client.setMobile(rs.getString("mobile"));
                client.setEmail(rs.getString("email"));
                client.setPassword(rs.getString("password"));
                client.setRole(Role.valueOf(rs.getString("role")));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return client;
    }

    @Override
    public boolean update(Client client) {
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_CLIENT)) {
            ps.setLong(1, client.getPassportId());
            ps.setString(2, client.getMobile());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPassword());
            ps.setString(5, client.getRole().getRole());
            ps.setLong(6, client.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Client create(Client client) {
        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CRATE_CLIENT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, client.getPassportId());
            ps.setString(2, client.getMobile());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPassword());
            ps.setString(5, client.getRole().getRole());
            ps.execute();

            ResultSet key = ps.getGeneratedKeys();
            if (key.next()) {
                client.setId(key.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return client;
    }

    @Override
    public boolean remove(long id) {
        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_CLIENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
    }
}

