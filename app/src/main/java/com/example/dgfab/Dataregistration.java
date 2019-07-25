package com.example.dgfab;

public class Dataregistration {
    String country,state, email ,firstname , lastname,password,pincode,comp_name,sub_bus_type;
    long buss_type;

    public Dataregistration(String country, String state, String email, String firstname, String lastname, String password, String pincode, String comp_name, long buss_type) {
        this.country = country;
        this.state = state;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.pincode = pincode;
        this.comp_name = comp_name;
        this.buss_type = buss_type;
        this.sub_bus_type = sub_bus_type;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getComp_name() {
        return comp_name;
    }

    public void setComp_name(String comp_name) {
        this.comp_name = comp_name;
    }

    public Long getBuss_type() {
        return buss_type;
    }

    public void setBuss_type(Long buss_type) {
        this.buss_type = buss_type;
    }

    public String getSub_bus_type() {
        return sub_bus_type;
    }

    public void setSub_bus_type(String sub_bus_type) {
        this.sub_bus_type = sub_bus_type;
    }
}
