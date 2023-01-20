package com.solvd.bank.services;

import com.solvd.bank.dao.IDepartmentDAO;
import com.solvd.bank.dao.mysql.DepartmentDAOImpl;
import com.solvd.bank.models.Department;
import org.apache.log4j.Logger;

import java.util.List;

public class DepartmentService {
    private IDepartmentDAO departmentDAO = new DepartmentDAOImpl();

    private static final Logger LOGGER = Logger.getLogger(DepartmentService.class);

    public List<Department> getAllDepartments(long id){
        LOGGER.info("Enter into getAllDepartments method with id: " + id);

        return departmentDAO.getAll();
    }

    public Department getDepartmentById(long id){
        LOGGER.info("Enter into getDepartmentById method with id: " + id);

        return departmentDAO.getById(id);
    }

    public boolean updateDepartment(Department department){
        LOGGER.info("Enter into updateDepartment method with department: " + department);

        return departmentDAO.update(department);
    }

    public Department createDepartment(Department department){
        LOGGER.info("Enter into createDepartment method with department: " + department);

        return departmentDAO.create(department);
    }

    public boolean removeDepartment(long id){
        LOGGER.info("Enter into removeDepartment method with id: " + id);

        return departmentDAO.remove(id);
    }

}
