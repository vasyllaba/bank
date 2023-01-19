package com.solvd.bank.services;

import com.solvd.bank.dao.IDepartmentDAO;
import com.solvd.bank.dao.mysql.DepartmentDAOImpl;
import com.solvd.bank.models.Department;
import java.util.List;

public class DepartmentService {
    private IDepartmentDAO departmentDAO = new DepartmentDAOImpl();

    public List<Department> getAllDepartments(long id){
        return departmentDAO.getAll();
    }

    public Department getDepartmentById(long id){
        return departmentDAO.getById(id);
    }

    public boolean updateDepartment(Department department){
        return departmentDAO.update(department);
    }

    public Department createDepartment(Department department){
        return departmentDAO.create(department);
    }

    public boolean removeDepartment(long id){
        return departmentDAO.remove(id);
    }

}
