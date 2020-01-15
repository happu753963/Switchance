package com.example.switchance_start.register;

import java.io.Serializable;

public class InterestedExperience implements Serializable {

    String interestedExperience;
    String type;

    public InterestedExperience() {

    }

    public InterestedExperience(String interestedExperience) {
        this.interestedExperience = interestedExperience;
    }

    public InterestedExperience(String interestedExperience, String type) {
        this.interestedExperience = interestedExperience;
        this.type = type;
    }

    public String getInterestedExperience() {
        return interestedExperience;
    }

    public void setInterestedExperience(String interestedExperience) {
        this.interestedExperience = interestedExperience;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
