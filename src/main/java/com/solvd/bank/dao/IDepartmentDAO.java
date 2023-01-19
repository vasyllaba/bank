package com.solvd.bank.dao;

import com.solvd.bank.models.Department;

import java.util.List;

public interface IDepartmentDAO extends IBaseDAO<Department>{
    List<Department> getAll();
}
