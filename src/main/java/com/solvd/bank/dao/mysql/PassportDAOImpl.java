package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IPassportDAO;
import com.solvd.bank.models.Passport;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class PassportDAOImpl extends AbstractMySQLRepo implements IPassportDAO {

    private static final String GET_PASSPORT_BY_ID =
            """
                SELECT first_name, last_name, passport_number, date_of_birth, passport_image
                FROM passports WHERE id = ?
            """;
    private static final String UPDATE_PASSPORT =
            "UPDATE passports SET first_name=?, last_name=?, passport_number=?, passport_image=? WHERE id = ?";
    private static final String CREATE_PASSPORT =
            """
                INSERT INTO passports (first_name, last_name, passport_number, date_of_birth, passport_image)
                VALUES (?,?,?,?,?)
            """;
    private static final String REMOVE_PASSPORT = "DELETE FROM passports WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(PassportDAOImpl.class);

    @Override
    public Passport getById(long id) {
        LOGGER.info("Enter into getById method with id: " + id);
        final Connection connection = ConnectionPool.getConnection();
        Passport passport = new Passport();

        try (PreparedStatement ps = connection.prepareStatement(GET_PASSPORT_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                passport.setId(id);
                passport.setFirstName(rs.getString("first_name"));
                passport.setLastName(rs.getString("last_name"));
                passport.setPassportNumber(rs.getString("passport_number"));
                passport.setDate_of_birth(rs.getDate("date_of_birth"));
                passport.setPassportImage(rs.getString("passport_image"));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return passport;
    }

    @Override
    public boolean update(Passport passport) {
        LOGGER.info("Enter into update method with passport: " + passport);

        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PASSPORT)) {
            ps.setString(1, passport.getFirstName());
            ps.setString(2, passport.getLastName());
            ps.setString(3, passport.getPassportNumber());
            ps.setDate(4, Date.valueOf(passport.getDate_of_birth().toString()));
            ps.setString(5, passport.getPassportImage());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Passport create(Passport passport) {
        LOGGER.info("Enter into create method with passport: " + passport);

        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(CREATE_PASSPORT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, passport.getFirstName());
            ps.setString(2, passport.getLastName());
            ps.setString(3, passport.getPassportNumber());
            ps.setDate(4, Date.valueOf(passport.getDate_of_birth().toString()));
            ps.setString(5, passport.getPassportImage());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                passport.setId(rs.getLong(1));
            }
            rs.close();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return passport;
    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Enter into remove method with id: " + id);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PASSPORT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
