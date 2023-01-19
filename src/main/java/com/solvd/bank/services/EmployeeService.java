package com.solvd.bank.services;

import com.solvd.bank.dao.IClientDAO;
import com.solvd.bank.dao.IDepartmentDAO;
import com.solvd.bank.dao.IEmployeeDAO;
import com.solvd.bank.dao.mysql.ClientDAOImpl;
import com.solvd.bank.dao.mysql.DepartmentDAOImpl;
import com.solvd.bank.dao.mysql.EmployeeDAOImpl;
import com.solvd.bank.models.Employee;

import java.util.List;

public class EmployeeService {
    private final IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private final IClientDAO clientDAO = new ClientDAOImpl();
    private final IDepartmentDAO departmentDAO = new DepartmentDAOImpl();

    public List<Employee> getEmployeesByDepartmentID(long id){
        return employeeDAO.getEmployeesByDepartmentId(id);
    }

    public Employee getFullEmployeesByID(long id){
        Employee employee = employeeDAO.getById(id);
        employee.setClient(clientDAO.getById(employee.getClientID()));
        employee.setDepartment(departmentDAO.getById(employee.getDepartmentId()));
        return employee;
    }

    public Employee getEmployeeById(long id){
        return employeeDAO.getById(id);
    }

    public boolean updateEmployee(Employee employee){
        return employeeDAO.update(employee);
    }

    public Employee createEmployee(Employee employee){
        return employeeDAO.create(employee);
    }

    public boolean removeEmployee(long id){
        return employeeDAO.remove(id);
    }


}
