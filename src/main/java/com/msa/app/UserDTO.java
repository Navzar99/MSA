package com.msa.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.app.entities.User;

public class UserDTO {
    public @JsonProperty String name;
    public @JsonProperty String email;

    public User mapToUser() {
        return new User(name, email);
    }
}
