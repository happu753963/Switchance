package com.example.switchance_start.register;

import java.io.Serializable;

public class InterestedSkill implements Serializable {
    String interestedSkill;

    public InterestedSkill() {

    }

    public InterestedSkill(String interestedSkill) {
        this.interestedSkill = interestedSkill;
    }

    public String getInterestedSkill() {
        return interestedSkill;
    }

    public void setInterestedSkill(String interestedSkill) {
        this.interestedSkill = interestedSkill;
    }
}
