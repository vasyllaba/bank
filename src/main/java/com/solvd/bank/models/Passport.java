package com.solvd.bank.models;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.log4j.Logger;

public class Passport {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("passportNumber")
    private String passportNumber;
    @JsonFormat(pattern = "DD-mm-yyyy")
    private Date date_of_birth;
    @JsonProperty("passportImage")
    private String passportImage;
    @JsonIgnore
    private Client client;

    private static final Logger LOGGER = Logger.getLogger(Passport.class);

    public Passport(Long id, String firstName, String lastName, String passportNumber, Date date_of_birth,
                    String passportImage) {
        LOGGER.info("create new Passport with id = " + id);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.date_of_birth = date_of_birth;
        this.passportImage = passportImage;
    }

    public Passport(String firstName, String lastName, String passportNumber, Date date_of_birth, String passportImage) {
        LOGGER.info("create new Passport with params");
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.date_of_birth = date_of_birth;
        this.passportImage = passportImage;
    }

    public Passport() {
        LOGGER.info("create new Passport without params");
    }

    public Long getId() {
        LOGGER.info("get Passport id");
        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set Passport id");
        this.id = id;
    }

    public String getFirstName() {
        LOGGER.info("get Passport firstName");
        return firstName;
    }

    public void setFirstName(String firstName) {
        LOGGER.info("set Passport firstName");
        this.firstName = firstName;
    }

    public String getLastName() {
        LOGGER.info("get Passport lastname");
        return lastName;
    }

    public void setLastName(String lastName) {
        LOGGER.info("set Passport lastname");
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        LOGGER.info("set Passport passportNumber");
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        LOGGER.info("set Passport passportNumber");
        this.passportNumber = passportNumber;
    }

    public Date getDate_of_birth() {
        LOGGER.info("get Passport date_of_birth");
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        LOGGER.info("set Passport date_of_birth");
        this.date_of_birth = date_of_birth;
    }

    public String getPassportImage() {
        LOGGER.info("get Passport passportImage");
        return passportImage;
    }

    public void setPassportImage(String passportImage) {
        LOGGER.info("set Passport passportImage");
        this.passportImage = passportImage;
    }

    public Client getClient() {
        LOGGER.info("get Passport client");
        return client;
    }

    public void setClient(Client client) {
        LOGGER.info("set Passport client");
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;

        Passport passport = (Passport) o;

        if (getId() != null ? !getId().equals(passport.getId()) : passport.getId() != null) return false;
        if (getFirstName() != null ? !getFirstName().equals(passport.getFirstName()) : passport.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(passport.getLastName()) : passport.getLastName() != null)
            return false;
        if (getPassportNumber() != null ? !getPassportNumber().equals(passport.getPassportNumber()) : passport.getPassportNumber() != null)
            return false;
        if (getPassportImage() != null ? !getPassportImage().equals(passport.getPassportImage()) : passport.getPassportImage() != null)
            return false;
        return getClient() != null ? getClient().equals(passport.getClient()) : passport.getClient() == null;
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getPassportNumber() != null ? getPassportNumber().hashCode() : 0);
        result = 31 * result + (getPassportImage() != null ? getPassportImage().hashCode() : 0);
        result = 31 * result + (getClient() != null ? getClient().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");
        return "Passport{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", passportImage='" + passportImage + '\'' +
                ", client=" + client +
                '}';
    }
}

