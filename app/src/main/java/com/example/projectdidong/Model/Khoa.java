package com.example.projectdidong.Model;

public class Khoa {
    private String tenKhoa;
    private String idKhoa;
    private String maSV;

    public Khoa ( ) {

    }


    public Khoa ( String tenKhoa , String maSV , String idKhoa ) {
        this.tenKhoa = tenKhoa;

        this.maSV = maSV;
        this.idKhoa = idKhoa;
    }

    public Khoa ( String tenKhoa ) {
        this.tenKhoa = tenKhoa;
    }

    public Khoa ( String tenKhoa , String maSV ) {
        this.tenKhoa = tenKhoa;
        this.maSV = maSV;
    }

    public String getTenKhoa ( ) {
        return tenKhoa;
    }

    public void setTenKhoa ( String tenKhoa ) {
        this.tenKhoa = tenKhoa;
    }

    public String getIdKhoa ( ) {
        return idKhoa;
    }

    public void setIdKhoa ( String idKhoa ) {
        this.idKhoa = idKhoa;
    }

    public String getMaSV ( ) {
        return maSV;
    }

    public void setMaSV ( String maSV ) {
        this.maSV = maSV;
    }
}
