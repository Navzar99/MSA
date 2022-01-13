package com.msa.app.security;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class PasswordManager {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encodePassword(String unHashedPassword){
        return encoder.encode(unHashedPassword);
    }

    public Boolean comparePasswordHashes(String password_1, String password_2){
        return encoder.matches(password_1, password_2);
    }
}
