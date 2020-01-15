package com.example.switchance_start.Datas;

public class ChatData {
    String id;
    String messenge;
    int icon;

    public ChatData(String id, String messenge, int icon) {
        this.id = id;
        this.messenge = messenge;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessenge() {
        return messenge;
    }

    public void setMessenge(String messenge) {
        this.messenge = messenge;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
