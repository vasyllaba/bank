package com.solvd.bank.dao.mysql;

import com.solvd.bank.dao.IDepartmentDAO;
import com.solvd.bank.models.Department;
import com.solvd.bank.utils.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DepartmentDAOImpl extends AbstractMySQLRepo implements IDepartmentDAO  {

    private static final String GET_DEPARTMENT_BY_ID = "SELECT (id, name) FROM departments WHERE id = ?";
    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET name = ? WHERE id = ?";
    private static final String CREATE_DEPARTMENT = "INSERT INTO departments (name) VALUES (?)";
    private static final String REMOVE_DEPARTMENT = "DELETE FROM departments WHERE Id=?";

    private static final String GET_ALL_DEPARTMENTS = "SELECT (id, name) FROM departments";

    private static final Logger LOGGER = Logger.getLogger(DepartmentDAOImpl.class);

    @Override
    public List<Department> getAll() {
        final Connection connection = ConnectionPool.getConnection();
        List<Department> departments = new LinkedList<>();

        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_DEPARTMENTS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getLong("id"));
                department.setName(rs.getString("name"));
                departments.add(department);
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return departments;
    }

    @Override
    public Department getById(long id) {
        final Connection connection = ConnectionPool.getConnection();
        Department department = new Department();

        try (PreparedStatement ps = connection.prepareStatement(GET_DEPARTMENT_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                department.setId(rs.getLong("id"));
                department.setName(rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return department;
    }

    @Override
    public boolean update(Department department) {
        final Connection connection = ConnectionPool.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(UPDATE_DEPARTMENT)){
            ps.setString(1, department.getName());
            ps.setLong(2, department.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Department create(Department department) {
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement ps = connection.prepareStatement(CREATE_DEPARTMENT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, department.getName());
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                department.setId(rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return department;
    }

    @Override
    public boolean remove(long id) {
        final Connection connection = ConnectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_DEPARTMENT)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }
}
