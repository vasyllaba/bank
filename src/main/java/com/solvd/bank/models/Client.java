package com.solvd.bank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.bank.enums.Role;
import com.solvd.bank.exception.IncorrectEmailException;
import org.apache.log4j.Logger;

import java.util.List;

public class Client {
    @JsonProperty
    private Long id;
    @JsonProperty
    private Long passportId;
    @JsonProperty
    private String mobile;
    @JsonProperty
    private String email;
    @JsonProperty
    private String password;
    @JsonProperty
    private Role role;
    @JsonProperty
    private Passport passport;
    @JsonIgnore
    private List<Card> cards;
    @JsonIgnore
    private List<Mortgage> mortgages;
    @JsonIgnore
    private List<Deposit> deposits;
    @JsonIgnore
    private List<Credit> credits;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    //to do

    private static final Logger LOGGER = Logger.getLogger(Client.class);

    public Client(Long id, Long passportId, String mobile, String email, String password, Role role) {
        LOGGER.info("create new Client with id = " + id);
        this.id = id;
        this.passportId = passportId;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Client(Long passportId, String mobile, String email, String password, Role role) {
        LOGGER.info("create new Client with id = " + id);
        this.passportId = passportId;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Client() {
        LOGGER.info("create new Client without params");
    }

    public Long getId() {
        LOGGER.info("get Client id");
        return id;
    }

    public void setId(Long id) {
        LOGGER.info("set Client id");
        this.id = id;
    }

    public Long getPassportId() {
        LOGGER.info("get Client passportId");
        return passportId;
    }

    public void setPassportId(Long passportId) {
        LOGGER.info("set Client passportId");
        this.passportId = passportId;
    }

    public String getMobile() {
        LOGGER.info("get Client mobile");
        return mobile;
    }

    public void setMobile(String mobile) {
        LOGGER.info("set Client mobile");
        this.mobile = mobile;
    }

    public String getPassword() {
        LOGGER.info("get Client password");
        return password;
    }

    public void setPassword(String password) {
        LOGGER.info("set Client password");
        if (!email.matches(EMAIL_PATTERN)){
            throw new IncorrectEmailException("Incorrect email.");
        }
        this.password = password;
    }

    public Role getRole() {
        LOGGER.info("get Client role");
        return role;
    }

    public void setRole(Role role) {
        LOGGER.info("set Client role");
        this.role = role;
    }

    public String getEmail() {
        LOGGER.info("get Client email");
        return email;
    }

    public void setEmail(String email) {
        LOGGER.info("set Client email");
        this.email = email;
    }

    public Passport getPassport() {
        LOGGER.info("get Client passport");
        return passport;
    }

    public void setPassport(Passport passport) {
        LOGGER.info("set Client passport");
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("call equals method");
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (getId() != null ? !getId().equals(client.getId()) : client.getId() != null) return false;
        if (getPassportId() != null ? !getPassportId().equals(client.getPassportId()) : client.getPassportId() != null)
            return false;
        if (getMobile() != null ? !getMobile().equals(client.getMobile()) : client.getMobile() != null) return false;
        if (getEmail() != null ? !getEmail().equals(client.getEmail()) : client.getEmail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(client.getPassword()) : client.getPassword() != null)
            return false;
        return getRole() == client.getRole();
    }

    @Override
    public int hashCode() {
        LOGGER.info("call hashCode method");
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getPassportId() != null ? getPassportId().hashCode() : 0);
        result = 31 * result + (getMobile() != null ? getMobile().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        LOGGER.info("call toString method");
        return "Client{" +
                "id=" + id +
                ", passportId=" + passportId +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
