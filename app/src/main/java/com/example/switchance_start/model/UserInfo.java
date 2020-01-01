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
    /**
     * 擁有的技能
     */
    private List<OwnedSkill> ownedSkills;
    /**
     * 擁有的經歷
     */
    private List<OwnedExperience> ownedExperiences;
    /**
     * 擁有的物品
     */
    private List<OwnedItem> ownedItems;
    /**
     * 感興趣的技能
     */
    private List<InterestedSkill> interestedSkills;
    /**
     * 感興趣的經歷
     */
    private List<InterestedExperience> interestedExperiences;
    /**
     * 感興趣的物品
     */
    private List<InterestedItem> interestedItems;

    public UserInfo() {
        ownedSkills = new ArrayList<>();
        ownedExperiences = new ArrayList<>();
        ownedItems = new ArrayList<>();
        interestedSkills = new ArrayList<>();
        interestedExperiences = new ArrayList<>();
        interestedItems = new ArrayList<>();
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

    public List<OwnedSkill> getOwnedSkills() {
        return ownedSkills;
    }

    public void setOwnedSkills(List<OwnedSkill> ownedSkills) {
        this.ownedSkills = ownedSkills;
    }

    public List<OwnedExperience> getOwnedExperiences() {
        return ownedExperiences;
    }

    public void setOwnedExperiences(List<OwnedExperience> ownedExperiences) {
        this.ownedExperiences = ownedExperiences;
    }

    public List<OwnedItem> getOwnedItems() {
        return ownedItems;
    }

    public void setOwnedItems(List<OwnedItem> ownedItems) {
        this.ownedItems = ownedItems;
    }

    public List<InterestedSkill> getInterestedSkills() {
        return interestedSkills;
    }

    public void setInterestedSkills(List<InterestedSkill> interestedSkills) {
        this.interestedSkills = interestedSkills;
    }

    public List<InterestedExperience> getInterestedExperiences() {
        return interestedExperiences;
    }

    public void setInterestedExperiences(List<InterestedExperience> interestedExperiences) {
        this.interestedExperiences = interestedExperiences;
    }

    public List<InterestedItem> getInterestedItems() {
        return interestedItems;
    }

    public void setInterestedItems(List<InterestedItem> interestedItems) {
        this.interestedItems = interestedItems;
    }

    @Exclude
    public List<String> getOwnedSkillList() {
        List<String> result = new ArrayList<>();
        if(ownedSkills != null) {
            for(OwnedSkill ownedSkill : ownedSkills) {
                result.add(ownedSkill.getOwnedSkill());
            }
        }
        return result;
    }
}
