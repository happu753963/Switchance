package com.example.switchance_start.register;

import java.io.Serializable;

public class OwnedItem implements Serializable {

    String ownedItem;

    public OwnedItem() {

    }

    public OwnedItem(String ownedItem) {
        this.ownedItem = ownedItem;
    }

    public String getOwnedItem() {
        return ownedItem;
    }

    public void setOwnedItem(String ownedItem) {
        this.ownedItem = ownedItem;
    }
}
