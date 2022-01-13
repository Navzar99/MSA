package com.msa.app.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.app.entities.User;
import com.msa.app.security.PasswordManager;

public class UserDTO {
    public @JsonProperty String shopName;
    public @JsonProperty String name;
    public @JsonProperty String password;
    public @JsonProperty Boolean isAdmin;
    private final PasswordManager passwordManager = new PasswordManager();

    public User mapToUser() {
        password = passwordManager.encodePassword(password);
        return new User(shopName, name, password, isAdmin);
    }
}
