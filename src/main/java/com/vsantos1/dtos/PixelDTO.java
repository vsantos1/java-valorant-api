package com.vsantos1.dtos;

import com.vsantos1.enums.Ability;
import com.vsantos1.enums.TickRate;
import com.vsantos1.enums.Type;
import com.vsantos1.models.GameMap;
import com.vsantos1.models.User;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;

public class PixelDTO {

    private String title;
    private String description;

    @UniqueElements
    private String slug;

    private Type type;

    private Ability ability;

    private TickRate tickRate;

    private User user;

    private GameMap map;

    private String videoUrl;

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

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
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
}
