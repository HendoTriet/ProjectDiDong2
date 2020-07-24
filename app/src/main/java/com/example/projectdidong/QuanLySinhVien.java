package com.example.projectdidong;

public class QuanLySinhVien {
    private String sUser;
    private String sPass;

    @Override
    public String toString() {
        return "QuanLySinhVien{" +
                "sUser='" + sUser + '\'' +
                ", sPass='" + sPass + '\'' +
                '}';
    }

    public String getsUser() {
        return sUser;
    }

    public void setsUser(String sUser) {
        this.sUser = sUser;
    }

    public String getsPass() {
        return sPass;
    }

    public void setsPass(String sPass) {
        this.sPass = sPass;
    }

    public QuanLySinhVien(String sUser, String sPass) {
        this.sUser = sUser;
        this.sPass = sPass;
    }
}
