package com.vsantos1.dtos;

import com.vsantos1.models.Game;


public class GameMapDTO {
    private String name;


    private String imageUrl;


    private Game game;

    public GameMapDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
