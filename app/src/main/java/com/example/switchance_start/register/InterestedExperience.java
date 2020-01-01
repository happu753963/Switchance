package com.example.switchance_start.register;

import java.io.Serializable;

public class InterestedExperience implements Serializable {

    String interestedExperience;

    public InterestedExperience() {

    }
    public InterestedExperience(String interestedExperience) {
        this.interestedExperience = interestedExperience;
    }

    public String getInterestedExperience() {
        return interestedExperience;
    }

    public void setInterestedExperience(String interestedExperience) {
        this.interestedExperience = interestedExperience;
    }
}
