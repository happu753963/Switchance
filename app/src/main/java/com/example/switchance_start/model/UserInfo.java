package com.example.switchance_start.model;

import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.InterestedItem;
import com.example.switchance_start.register.InterestedSkill;
import com.example.switchance_start.register.OwnedExperience;
import com.example.switchance_start.register.OwnedItem;
import com.example.switchance_start.register.OwnedSkill;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  會員資料
 */
@IgnoreExtraProperties
public class UserInfo implements Serializable {

    /**
     * 帳號
     */
    private String account;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 系所
     */
    private String department;
    /**
     * 性別
     */
    private String gender;
    /**
     * 大頭照
     */
    private int icon;
    /**
     * 識別碼
     */
    private String id;
    /**
     * 感興趣的經歷
     */
    private List<InterestedExperience> interestedExperience;
    /**
     * 感興趣的物件
     */
    private List<InterestedItem> interestedItem;


     /**
     * 感興趣的技能
     */
    private List<InterestedSkill> interestedSkill;

    /**
     * 自我介紹
     */
    private String introduction;

    /**
     * 學校信箱
     */
    private String mail;
    /**
     * 本名 姓名
     */
    private String name;
    /**
     * 擁有的經歷
     */
    private List<OwnedExperience> ownedExperience;
    /**
     * 擁有的物品
     */
    private List<OwnedItem> ownedItem;
    /**
     * 擁有的技能
     */
    private List<OwnedSkill> ownedSkill;
    /**
     * 密碼
     */
    private String password;
    /**
     * 地點
     */
    private String place;
    /**
     * 學校
     */
    private String school;
    /**
     * 時間
     */
    private String time;

    /* 感興趣的物品
     */


    public UserInfo() {
        ownedSkill = new ArrayList<>();
        ownedExperience = new ArrayList<>();
        ownedItem = new ArrayList<>();
        interestedSkill = new ArrayList<>();
        interestedExperience = new ArrayList<>();
        interestedItem = new ArrayList<>();
    }

    public UserInfo(String id, int icon, String gender, String birthday, String school, String department, String mail, String password, String name, String account) {
        this();
        this.id = id;
        this.icon = icon;
        this.gender = gender;
        this.birthday = birthday;
        this.school = school;
        this.department = department;
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.account = account;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

//    public List<OwnedItem> getOwnedItem() {
//        return ownedItem;
//    }
//
//    public void setOwnedItem(List<OwnedItem> ownedItem) {
//        this.ownedItem = ownedItem;
//    }

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

    @Exclude
    public List<String> getOwnedSkillList() {
        List<String> result = new ArrayList<>();
        if(ownedSkill != null) {
            for(OwnedSkill ownedSkill : ownedSkill) {
                result.add(ownedSkill.getOwnedSkill());
            }
        }
        return result;
    }
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<OwnedItem> getOwnedItem() {
        return ownedItem;
    }

    public void setOwnedItem(List<OwnedItem> ownedItem) {
        this.ownedItem = ownedItem;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
