package com.example.projectdidong;

public class ClassSinhVien {
    private String tenSV;
    private String maSV;
    private String date;
    private String emailSV;
    private String tenLlop;

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmailSV() {
        return emailSV;
    }

    public void setEmailSV(String emailSV) {
        this.emailSV = emailSV;
    }

    public String getTenLlop() {
        return tenLlop;
    }

    public void setTenLlop(String tenLlop) {
        this.tenLlop = tenLlop;
    }

    public ClassSinhVien(String tenSV, String maSV, String date, String emailSV, String tenLlop) {
        this.tenSV = tenSV;
        this.maSV = maSV;
        this.date = date;
        this.emailSV = emailSV;
        this.tenLlop = tenLlop;
    }

    @Override
    public String toString() {
        return "ClassSinhVien{" +
                "tenSV='" + tenSV + '\'' +
                ", maSV='" + maSV + '\'' +
                ", date='" + date + '\'' +
                ", emailSV='" + emailSV + '\'' +
                ", tenLlop='" + tenLlop + '\'' +
                '}';
    }
}
