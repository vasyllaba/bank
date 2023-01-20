package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IClientDAO;
import com.solvd.bank.enums.Role;
import com.solvd.bank.models.Client;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Optional;

public class ClientDAOImpl extends AbstractMySQLRepo implements IClientDAO {
    private static final ClientDAOImpl INSTANCE = new ClientDAOImpl();
    private static final String GET_CLIENT_BY_ID =
            "SELECT id, passport_id, mobile, email, password, role FROM clients WHERE id = ?";
    private static final String GET_CLIENT_BY_EMAIL =
            "SELECT id, passport_id, mobile, email, password, role FROM clients WHERE email = ?";
    private static final String UPDATE_CLIENT =
            "UPDATE clients SET passport_id = ?, mobile =?, email =?, password =?, role =? WHERE id = ?";
    private static final String CRATE_CLIENT =
            "INSERT INTO clients (passport_id, mobile, email, password, role) VALUES (?, ?, ?, ?, ?)";
    private static final String REMOVE_CLIENT =
            "DELETE FROM clients WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(ClientDAOImpl.class);

    public static ClientDAOImpl getInstance() {
        return INSTANCE;
    }

    /**
     * The method used for getting Client by email from db
     *
     * @param email
     * @return Client
     */
    @Override
    public Optional<Client> findByEmail(String email) {
        LOGGER.info("Enter into findByEmail method with email: " + email);
        final Connection connection = ConnectionPool.getConnection();
        Client client = new Client();

        try (PreparedStatement ps = connection.prepareStatement(GET_CLIENT_BY_EMAIL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                client.setId(rs.getLong("id"));
                client.setPassportId(rs.getLong("passport_id"));
                client.setMobile(rs.getString("mobile"));
                client.setEmail(rs.getString("email"));
                client.setPassword(rs.getString("password"));
                client.setRole(Role.valueOf(rs.getString("role")));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        }

        if (client.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(client);
    }

    @Override
    public Optional<Client> getById(long id) {
        LOGGER.info("Enter into getById method with id: " + id);

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
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        }

        if (client.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(client);
    }

    @Override
    public boolean update(Client client) {
        LOGGER.info("Enter into update method with client: " + client);
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
    public Optional<Client> create(Client client) {
        LOGGER.info("Enter into create method with client: " + client);
        final Connection connection = ConnectionPool.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CRATE_CLIENT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, client.getPassportId());
            ps.setString(2, client.getMobile());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPassword());
            ps.setString(5, client.getRole().getRole());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                client.setId(rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return Optional.empty();
        }

        if (client.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(client);
    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Enter into remove method with id: " + id);
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

