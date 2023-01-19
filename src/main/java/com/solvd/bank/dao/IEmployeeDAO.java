package com.solvd.bank.dao;

import com.solvd.bank.models.Employee;

import java.util.List;

public interface IEmployeeDAO extends IBaseDAO<Employee>{
    List<Employee> getEmployeesByDepartmentId(long id);
}
