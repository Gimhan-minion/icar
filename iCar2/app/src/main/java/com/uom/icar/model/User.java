package com.uom.icar.model;

public class User {
    private String nic;
    private String name;
    private String email;
    private String contactNo;
    private String password;

    public User(String nic, String name, String email, String contactNo, String password) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.password = password;
    }

    public User(){

    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
