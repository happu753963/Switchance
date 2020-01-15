package com.example.switchance_start.Datas;

import com.example.switchance_start.R;

import java.io.Serializable;

public class ChatData implements Serializable {
    String id;
    String message;
    String receiver;
    String sender;
    int icon;

    public ChatData(){}

    public ChatData(String id, int icon) {
        this.id = id;
        this.icon = icon;
    }

    public ChatData(String id, String message, int icon) {
        this.id = id;
        this.message = message;
        this.icon = icon;
    }

    public ChatData(String message, String receiver, String sender) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
