package com.example.phonebook.model;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String firstName;

    private String lastName;

    private String phone;

    @Setter(AccessLevel.NONE)
    private final UUID uuid;

    public User() {
        this(UUID.randomUUID());
    }

    public User(UUID uuid) {
        this.uuid = uuid;
    }
}
