package com.solvd.bank.models;


import org.apache.log4j.Logger;

import java.util.List;

public class Department {
    private Long id;
    private String name;
    private List<Employee> employees;

    private static final Logger LOGGER = Logger.getLogger(Department.class);

    public Department(Long id, String name) {
        LOGGER.info("create new Department with id = " + id);
        this.id = id;
        this.name = name;
    }

    public Department(String name) {
        LOGGER.info("create new Department");
        this.name = name;
    }

    public Department() {
        LOGGER.info("create new Department without params");
    }



    public Long getId() {
        LOGGER.info("get Department id");
        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set Department id");
        this.id = id;
    }

    public String getName() {
        LOGGER.info("get Department name");
        return name;
    }

    public void setName(String name) {
        LOGGER.info("set Department name");
        this.name = name;
    }

    public List<Employee> getEmployees() {
        LOGGER.info("get Department employees");
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        LOGGER.info("set Department employees");
        this.employees = employees;
    }


    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");
        if (this == o) return true;
        if (!(o instanceof Department)) return false;

        Department that = (Department) o;

        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getEmployees() != null ? getEmployees().equals(that.getEmployees()) : that.getEmployees() == null;
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getEmployees() != null ? getEmployees().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
