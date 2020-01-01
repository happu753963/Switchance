package com.example.switchance_start.register;

import java.io.Serializable;

public class OwnedSkill implements Serializable {

    String ownedSkill;

    public OwnedSkill() {

    }

    public OwnedSkill(String ownedSkill) {
        this.ownedSkill = ownedSkill;
    }

    public String getOwnedSkill() {
        return ownedSkill;
    }

    public void setOwnedSkill(String ownedSkill) {
        this.ownedSkill = ownedSkill;
    }
}
