package com.vsantos1.dtos;

import com.vsantos1.enums.Ability;
import com.vsantos1.enums.TickRate;
import com.vsantos1.enums.Type;
import com.vsantos1.models.Agent;
import com.vsantos1.models.GameMap;
import com.vsantos1.models.User;

import java.util.Date;

public class PixelDTO {

    private String title;
    private String description;

    private String slug;

    private Type type;

    private Ability ability;

    private TickRate tickRate;

    private User user;

    private GameMap gameMap;

    private String videoUrl;

    private Agent agent;

    private Boolean isVerified;
    private Boolean showUsername;

    private Date createdAt;

    private Date updatedAt;


    public PixelDTO() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public TickRate getTickRate() {
        return tickRate;
    }

    public void setTickRate(TickRate tickRate) {
        this.tickRate = tickRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public Boolean getShowUsername() {
        return showUsername;
    }

    public void setShowUsername(Boolean showUsername) {
        this.showUsername = showUsername;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }
}
