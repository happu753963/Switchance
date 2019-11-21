package com.example.switchance_start.register;

import java.io.Serializable;

public class OwnedExperience implements Serializable {

    String ownedExperience;

    public OwnedExperience() {

    }

    public OwnedExperience(String ownedExperience) {
        this.ownedExperience = ownedExperience;
    }

    public String getOwnedExperience() {
        return ownedExperience;
    }

    public void setOwnedExperience(String ownedExperience) {
        this.ownedExperience = ownedExperience;
    }
}
