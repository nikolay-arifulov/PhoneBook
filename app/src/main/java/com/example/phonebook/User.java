package com.example.phonebook;

import java.util.UUID;

public class User {
    private String firstName;
    private String lastName;
    private String phone;
    private UUID uuid;

    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UUID getUuid() {
        return uuid;
    }
}
