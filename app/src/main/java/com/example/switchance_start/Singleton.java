package com.example.switchance_start;

import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.InterestedItem;
import com.example.switchance_start.register.InterestedSkill;
import com.example.switchance_start.register.OwnedExperience;
import com.example.switchance_start.register.OwnedItem;
import com.example.switchance_start.register.OwnedSkill;

import java.util.List;

public class Singleton {

    String account;
    String birthday;
    String department;
    String gender;
    int icon;
    String id;

    String introduction="填寫簡單自我介紹\n讓大家認識你吧！";
    String mail;
    String name;
    String password;
    String place="無限制";
    String school;
    String time="無限制";

    int check;
        private List<OwnedSkill> ownedSkill;
        private List<OwnedExperience> ownedExperience;
        private List<OwnedItem> ownedItem;
        private List<InterestedSkill> interestedSkill;
        private List<InterestedExperience> interestedExperience;
        private List<InterestedItem> interestedItem;

        //儲存唯一的物件實體
        private static Singleton instance;

        //建構子設成 private
        private Singleton(){
            // 這裡面跑很了多code，建立物件需要花費很多資源
        }

        //自訂之 getInstance 方法
        public static synchronized Singleton getInstance(){
            //檢查物件實體是否建立過
            if(instance == null){
                //若未建立則呼叫建構子建立
                instance = new Singleton();
            }
            return instance;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public List<OwnedSkill> getOwnedSkill() {
        return ownedSkill;
    }

    public void setOwnedSkill(List<OwnedSkill> ownedSkill) {
        this.ownedSkill = ownedSkill;
    }

    public List<OwnedExperience> getOwnedExperience() {
        return ownedExperience;
    }

    public void setOwnedExperience(List<OwnedExperience> ownedExperience) {
        this.ownedExperience = ownedExperience;
    }

    public List<InterestedSkill> getInterestedSkill() {
        return interestedSkill;
    }

    public void setInterestedSkill(List<InterestedSkill> interestedSkill) {
        this.interestedSkill = interestedSkill;
    }

    public List<InterestedExperience> getInterestedExperience() {
        return interestedExperience;
    }

    public void setInterestedExperience(List<InterestedExperience> interestedExperience) {
        this.interestedExperience = interestedExperience;
    }

    public List<InterestedItem> getInterestedItem() {
        return interestedItem;
    }

    public void setInterestedItem(List<InterestedItem> interestedItem) {
        this.interestedItem = interestedItem;
    }

    public List<OwnedItem> getOwnedItem() {
        return ownedItem;
    }

    public void setOwnedItem(List<OwnedItem> ownedItem) {
        this.ownedItem = ownedItem;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}

