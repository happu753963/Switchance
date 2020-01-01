package com.example.switchance_start.register;

import java.io.Serializable;

public class InterestedItem implements Serializable {

    String interestedItem;

    public InterestedItem() {

    }

    public InterestedItem(String interestedItem) {
        this.interestedItem = interestedItem;
    }

    public String getInterestedItem() {
        return interestedItem;
    }

    public void setInterestedItem(String interestedItem) {
        this.interestedItem = interestedItem;
    }
}
