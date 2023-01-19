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
    private IEmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private IClientDAO clientDAO = new ClientDAOImpl();
    private IDepartmentDAO departmentDAO = new DepartmentDAOImpl();

    public List<Employee> getEmployeesByDepartmentID(long id){
        return employeeDAO.getEmployeesByDepartmentId(id);
    }

    public Employee getFullEmployeesByID(long id){
        Employee employee = employeeDAO.getById(id);
        employee.setClient(clientDAO.getById(employee.getClientID()));
        employee.setDepartment(departmentDAO.getById(employee.getDepartmentId()));
        return employee;
    }


}
