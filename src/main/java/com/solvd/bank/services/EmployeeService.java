package com.solvd.bank.services;

import com.solvd.bank.dao.IClientDAO;
import com.solvd.bank.dao.IDepartmentDAO;
import com.solvd.bank.dao.IEmployeeDAO;
import com.solvd.bank.dao.mysql.ClientDAOImpl;
import com.solvd.bank.dao.mysql.DepartmentDAOImpl;
import com.solvd.bank.dao.mysql.EmployeeDAOImpl;
import com.solvd.bank.models.Employee;
import org.apache.log4j.Logger;

import java.util.List;

public class EmployeeService {
    private final IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final IClientDAO clientDAO = new ClientDAOImpl();
    private final IDepartmentDAO departmentDAO = new DepartmentDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(EmployeeService.class);

    public List<Employee> getEmployeesByDepartmentID(long id) {
        LOGGER.info("Enter into getEmployeesByDepartmentID method with id: " + id);

        return employeeDAO.getEmployeesByDepartmentId(id);
    }

    public Employee getFullEmployeesByID(long id) {
        LOGGER.info("Enter into getFullEmployeesByID method with id: " + id);

        Employee employee = employeeDAO.getById(id);
        employee.setClient(clientDAO.getById(employee.getClientID()));
        employee.setDepartment(departmentDAO.getById(employee.getDepartmentId()));
        return employee;
    }

    public Employee getEmployeeById(long id) {
        LOGGER.info("Enter into getEmployeeById method with id: " + id);

        return employeeDAO.getById(id);
    }

    public boolean updateEmployee(Employee employee) {
        LOGGER.info("Enter into updateEmployee method with employee: " + employee);

        return employeeDAO.update(employee);
    }

    public Employee createEmployee(Employee employee) {
        LOGGER.info("Enter into getClientById method with employee: " + employee);

        return employeeDAO.create(employee);
    }

    public boolean removeEmployee(long id) {
        LOGGER.info("Enter into removeEmployee method with id: " + id);

        return employeeDAO.remove(id);
    }


}
