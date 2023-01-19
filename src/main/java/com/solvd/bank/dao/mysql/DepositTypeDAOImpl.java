package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IDepositTypeDAO;
import com.solvd.bank.enums.Currency;
import com.solvd.bank.enums.PaymentTimePeriod;
import com.solvd.bank.models.DepositType;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;

public class DepositTypeDAOImpl extends AbstractMySQLRepo implements IDepositTypeDAO {
    private static final String GET_DEPOSIT_TYPE_BY_ID =
            """
                SELECT (id, name, is_replenishment, max_replenishment, payment_per_time, min_term, max_term,
                    no_early_terminate_percent_rate, early_terminate_percent_rate, currency)
                FROM deposits_types WHERE id = ?
            """;
    private static final String UPDATE_DEPOSIT_TYPE =
            "UPDATE deposits_types SET name = ?, is_replenishment=?, max_replenishment=? WHERE id = ?";
    private static final String CREATE_DEPOSIT_TYPE =
            """
                INSERT INTO deposits_types (name, is_replenishment, max_replenishment, payment_per_time, min_term,
                    max_term, no_early_terminate_percent_rate, early_terminate_percent_rate, currency)
                VALUES (?,?,?,?,?,?,?,?,?)
            """;
    private static final String REMOVE_DEPOSIT_TYPE = "DELETE FROM deposits_types WHERE Id=?";

    private static final Logger LOGGER = Logger.getLogger(DepositTypeDAOImpl.class);

    @Override
    public DepositType getById(long id) {
        final Connection connection = ConnectionPool.getConnection();
        DepositType depositType = new DepositType();

        try (PreparedStatement ps = connection.prepareStatement(GET_DEPOSIT_TYPE_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                depositType.setId(rs.getLong("id"));
                depositType.setName(rs.getString("name"));
                depositType.setReplenishment(rs.getBoolean("is_replenishment"));
                depositType.setMaxReplenishment(rs.getBigDecimal("max_replenishment"));
                depositType.setPaymentTimePeriod(PaymentTimePeriod.valueOf(rs.getString("payment_per_time")));
                depositType.setMinTerm(rs.getInt("min_term"));
                depositType.setMaxTerm(rs.getInt("max_term"));
                depositType.setNoEarlyTerminatedRate(rs.getDouble("no_early_terminate_percent_rate"));
                depositType.setEarlyTerminatedRate(rs.getDouble("early_terminate_percent_rate"));
                depositType.setCurrency(Currency.valueOf(rs.getString("currency")));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return depositType;
    }

    @Override
    public boolean update(DepositType depositType) {
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_DEPOSIT_TYPE)) {
            ps.setString(1, depositType.getName());
            ps.setBoolean(2, depositType.getReplenishment());
            ps.setBigDecimal(3, depositType.getMaxReplenishment());
            ps.setLong(4, depositType.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public DepositType create(DepositType depositType) {
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(CREATE_DEPOSIT_TYPE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, depositType.getName());
            ps.setBoolean(2, depositType.getReplenishment());
            ps.setBigDecimal(3, depositType.getMaxReplenishment());
            ps.setString(4, depositType.getPaymentTimePeriod().getPeriod());
            ps.setInt(5, depositType.getMinTerm());
            ps.setInt(6, depositType.getMaxTerm());
            ps.setDouble(7, depositType.getNoEarlyTerminatedRate());
            ps.setDouble(8, depositType.getEarlyTerminatedRate());
            ps.setString(8, depositType.getCurrency().getCurrency());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                depositType.setId(rs.getLong(1));
            }
            rs.close();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return depositType;
    }

    @Override
    public boolean remove(long id) {
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_DEPOSIT_TYPE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
