package com.vsantos1.repositories.filter;

public class UserQueryFilter {

    private String firstname;

    private String lastname;

    public UserQueryFilter() {
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
}
