package com.example.switchance_start;

public class Singleton {

        String mID;
        String mName;
        String mSchoolMail;
        String mPassword;
        String mAccount;
        String mGender;
        String mSchool;
        String mDepartment;
        int icon;
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

        public String getName() {
            return mName;
        }

        public void setName(String name) {
            this.mName = name;
        }

        public String getID() {
            return mID;
        }

        public void setID(String id) {
            this.mID = id;
        }

        public String getmID() {
        return mID;
    }

    public void setmID(String mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSchoolMail() {
        return mSchoolMail;
    }

    public void setmSchoolMail(String mSchoolMail) {
        this.mSchoolMail = mSchoolMail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmAccount() {
        return mAccount;
    }

    public void setmAccount(String mAccount) {
        this.mAccount = mAccount;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmSchool() {
        return mSchool;
    }

    public void setmSchool(String mSchool) {
        this.mSchool = mSchool;
    }

    public String getmDepartment() {
        return mDepartment;
    }

    public void setmDepartment(String mDepartment) {
        this.mDepartment = mDepartment;
    }
    
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}

