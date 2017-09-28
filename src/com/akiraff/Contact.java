package com.akiraff;

public class Contact {
    private String name;
    private Integer phone;
    private String email;

    public Contact(String name, Integer phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getPhone() {
        return this.phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String setEmail() {
        return this.email;
    }
}