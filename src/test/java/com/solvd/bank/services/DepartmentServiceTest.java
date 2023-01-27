package com.solvd.bank.services;

import com.solvd.bank.models.Department;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class DepartmentServiceTest {
    DepartmentService departmentService = new DepartmentService();
    Department newDep;

    @Test(description = "check saving department in db by checking id")
    public void testCreateDepartment() {
        newDep = new Department("MyTest");
        newDep = departmentService.createDepartment(newDep);
        Assert.assertNotNull(newDep.getId());
    }

    @Test(description = "check deleting department from db by method return value")
    public void testRemoveDepartment() {
        newDep = new Department("MyTest");
        newDep = departmentService.createDepartment(newDep);
        Boolean result = departmentService.removeDepartment(newDep.getId());
        Assert.assertTrue(result);
    }

    @AfterMethod
    public void clean(){
        if (newDep.getId() != null)
            departmentService.removeDepartment(newDep.getId());
    }
}