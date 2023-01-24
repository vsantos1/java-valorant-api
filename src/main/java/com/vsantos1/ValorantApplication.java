package com.vsantos1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ValorantApplication {

    public static void main(String[] args) {
        // TODO: Remove this line
        String pass = new BCryptPasswordEncoder().encode("123456");
        System.out.println(pass);
        SpringApplication.run(ValorantApplication.class, args);
    }

}
