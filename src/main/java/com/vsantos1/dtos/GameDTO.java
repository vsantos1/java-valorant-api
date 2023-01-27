package com.vsantos1.dtos;

import jakarta.validation.constraints.NotNull;

public class GameDTO {


    @NotNull
    private String name;

    public GameDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
