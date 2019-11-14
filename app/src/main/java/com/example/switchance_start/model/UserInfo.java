package com.example.switchance_start.model;

import com.example.switchance_start.register.InterestedExperience;
import com.example.switchance_start.register.InterestedItem;
import com.example.switchance_start.register.InterestedSkill;
import com.example.switchance_start.register.OwnedExperience;
import com.example.switchance_start.register.OwnedItem;
import com.example.switchance_start.register.OwnedSkill;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 *  會員資料
 */
@IgnoreExtraProperties
public class UserInfo {
    /**
     * 識別碼
     */
    private String id;
    /**
     * 大頭照
     */
    private int icon;
    /**
     * 性別
     */
    private String gender;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 學校
     */
    private String school;
    /**
     * 系所
     */
    private String department;
    /**
     * 學校信箱
     */
    private String mail;
    /**
     * 密碼
     */
    private String password;
    /**
     * 本名 姓名
     */
    private String name;
    /**
     * 帳號
     */
    private String account;

    private ArrayList<OwnedSkill> ownedSkills;

    private ArrayList<OwnedItem> ownedItems;

    private ArrayList<OwnedExperience> ownedExperiences;

    private ArrayList<InterestedSkill> interestedSkills;

    private ArrayList<InterestedItem> interestedItems;

    private ArrayList<InterestedExperience> InterestedExperiences;

    public UserInfo() {
    }

    public UserInfo(String id, int icon, String gender, String birthday, String school, String department, String mail, String password, String name, String account) {
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

    public int getIcon() {
        return icon;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSchool() {
        return school;
    }

    public String getDepartment() {
        return department;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public ArrayList<OwnedSkill> getOwnedSkills() {
        return ownedSkills;
    }

    public ArrayList<OwnedItem> getOwnedItems() {
        return ownedItems;
    }

    public ArrayList<OwnedExperience> getOwnedExperiences() {
        return ownedExperiences;
    }

    public ArrayList<InterestedSkill> getInterestedSkills() {
        return interestedSkills;
    }

    public ArrayList<InterestedItem> getInterestedItems() {
        return interestedItems;
    }

    public ArrayList<InterestedExperience> getInterestedExperiences() {
        return InterestedExperiences;
    }
}
