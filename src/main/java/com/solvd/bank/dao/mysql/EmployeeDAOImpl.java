package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IEmployeeDAO;
import com.solvd.bank.models.Employee;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOImpl extends AbstractMySQLRepo implements IEmployeeDAO {
    private static final EmployeeDAOImpl INSTANCE = new EmployeeDAOImpl();

    private static final String GET_EMPLOYEE_BY_ID =
            """
                SELECT client_id, department_id, job_title, office_address
                FROM employees WHERE id = ?
            """;
    private static final String UPDATE_EMPLOYEE =
            "UPDATE employees SET client_id = ?, department_id=?, job_title=?, office_address=? WHERE id = ?";
    private static final String CREATE_EMPLOYEE =
            """
                INSERT INTO employees (client_id, department_id, job_title, office_address)
                VALUES (?,?,?,?)
            """;
    private static final String REMOVE_EMPLOYEE = "DELETE FROM employees WHERE Id=?";

    private static final String GET_EMPLOYEES_BY_DEPARTMENT_ID =
            """
                SELECT client_id, department_id, job_title, office_address
                FROM employees WHERE department_id = ?
            """;

    private static final Logger LOGGER = Logger.getLogger(EmployeeDAOImpl.class);

    public static EmployeeDAOImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Employee> getEmployeesByDepartmentId(long id) {
        LOGGER.info("Enter into getEmployeesByDepartmentId method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        List<Employee> employees = new LinkedList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEES_BY_DEPARTMENT_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(id);
                employee.setClientID(rs.getLong("client_id"));
                employee.setDepartmentId(rs.getLong("department_id"));
                employee.setJobTitle(rs.getString("job_title"));
                employee.setOfficeAddress(rs.getString("office_address"));
                employees.add(employee);
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return employees;
    }

    @Override
    public Optional<Employee> getById(long id) {
        LOGGER.info("Enter into getById method with id: " + id);

        final Connection connection = ConnectionPool.getConnection();
        Employee employee = new Employee();

        try (PreparedStatement ps = connection.prepareStatement(GET_EMPLOYEE_BY_ID);) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employee.setId(id);
                employee.setClientID(rs.getLong("client_id"));
                employee.setDepartmentId(rs.getLong("department_id"));
                employee.setJobTitle(rs.getString("job_title"));
                employee.setOfficeAddress(rs.getString("office_address"));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (employee.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(employee);
    }

    @Override
    public boolean update(Employee employee) {
        LOGGER.info("Enter into update method with employee: " + employee);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_EMPLOYEE)) {
            ps.setLong(1, employee.getClientID());
            ps.setLong(2, employee.getDepartmentId());
            ps.setString(3, employee.getJobTitle());
            ps.setString(4, employee.getOfficeAddress());
            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    @Override
    public Optional<Employee> create(Employee employee) {
        LOGGER.info("Enter into create method with employee: " + employee);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(CREATE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, employee.getClientID());
            ps.setLong(2, employee.getDepartmentId());
            ps.setString(3, employee.getJobTitle());
            ps.setString(4, employee.getOfficeAddress());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                employee.setId(rs.getLong(1));
            }
            rs.close();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        if (employee.getId() == null) {
            return Optional.empty();
        }
        return Optional.of(employee);
    }

    @Override
    public boolean remove(long id) {
        LOGGER.info("Enter into remove method with id: " + id);
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_EMPLOYEE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}