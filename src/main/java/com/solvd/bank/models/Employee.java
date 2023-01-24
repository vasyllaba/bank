package com.solvd.bank.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

public class Employee {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("clientId")
    private Long clientId;
    @JsonProperty("departmentId")
    private Long departmentId;
    @JsonProperty("jobTitle")
    private String jobTitle;
    @JsonProperty("officeAddress")
    private String officeAddress;
    @JsonProperty("client")
    private Client client;
    @JsonProperty("department")
    private Department department;

    private static final Logger LOGGER = Logger.getLogger(Employee.class);

    public Employee(Long id, Long clientId, Long departmentId, String jobTitle, String officeAddress) {
        LOGGER.info("create new Employee with id = " + id);
        this.id = id;
        this.clientId = clientId;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.officeAddress = officeAddress;
    }

    public Employee(Long clientId, Long departmentId, String jobTitle, String officeAddress) {
        LOGGER.info("create new Employee with params");
        this.clientId = clientId;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.officeAddress = officeAddress;
    }

    public Employee() {
        LOGGER.info("create new Employee without params");
    }

    public Long getId() {
        LOGGER.info("get Employee id");
        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set Employee id");
        this.id = id;
    }

    public Long getClientId() {
        LOGGER.info("get Employee id");
        return clientId;
    }

    public void setClientId(Long clientId) {
        LOGGER.info("set Employee clientID");
        this.clientId = clientId;
    }

    public Long getDepartmentId() {
        LOGGER.info("get Employee departmentId");
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        LOGGER.info("set Employee departmentId");
        this.departmentId = departmentId;
    }

    public String getJobTitle() {
        LOGGER.info("get Employee jobTitle");
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        LOGGER.info("set Employee jobTitle");
        this.jobTitle = jobTitle;
    }

    public String getOfficeAddress() {
        LOGGER.info("get Employee officeAddress");
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        LOGGER.info("set Employee officeAddress");
        this.officeAddress = officeAddress;
    }

    public Client getClient() {
        LOGGER.info("get Employee client");
        return client;
    }

    public void setClient(Client client) {
        LOGGER.info("set Employee client");
        this.client = client;
    }

    public Department getDepartment() {
        LOGGER.info("get Employee department");
        return department;
    }

    public void setDepartment(Department department) {
        LOGGER.info("set Employee department");
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        if (getId() != null ? !getId().equals(employee.getId()) : employee.getId() != null) return false;
        if (getClientId() != null ? !getClientId().equals(employee.getClientId()) : employee.getClientId() != null)
            return false;
        if (getDepartmentId() != null ? !getDepartmentId().equals(employee.getDepartmentId()) : employee.getDepartmentId() != null)
            return false;
        if (getJobTitle() != null ? !getJobTitle().equals(employee.getJobTitle()) : employee.getJobTitle() != null)
            return false;
        return getOfficeAddress() != null ? getOfficeAddress().equals(employee.getOfficeAddress()) : employee.getOfficeAddress() == null;
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getClientId() != null ? getClientId().hashCode() : 0);
        result = 31 * result + (getDepartmentId() != null ? getDepartmentId().hashCode() : 0);
        result = 31 * result + (getJobTitle() != null ? getJobTitle().hashCode() : 0);
        result = 31 * result + (getOfficeAddress() != null ? getOfficeAddress().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");
        return "Employee{" +
                "id=" + id +
                ", clientID=" + clientId +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", officeAddress='" + officeAddress + '\'' +
                '}';
    }
}
