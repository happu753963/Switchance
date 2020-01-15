package com.example.switchance_start.register;

import java.io.Serializable;

public class OwnedExperience implements Serializable {

    String ownedExperience;
    String type;

    public OwnedExperience() {

    }

    public OwnedExperience(String ownedExperience) {
        this.ownedExperience = ownedExperience;
    }

    public OwnedExperience(String ownedExperience, String type) {
        this.ownedExperience = ownedExperience;
        this.type = type;
    }

    public String getOwnedExperience() {
        return ownedExperience;
    }

    public void setOwnedExperience(String ownedExperience) {
        this.ownedExperience = ownedExperience;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
