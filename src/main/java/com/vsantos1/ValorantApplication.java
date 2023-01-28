package com.vsantos1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class ValorantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ValorantApplication.class, args);
    }

}
