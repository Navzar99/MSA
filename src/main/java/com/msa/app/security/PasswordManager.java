package com.msa.app.security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordManager {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String EncodePassword(String unHashedPassword){
        return encoder.encode(unHashedPassword);
    }

    public Boolean ComparePasswordHashes(String password_1, String password_2){
        return encoder.matches(password_1, password_2);
    }
}
