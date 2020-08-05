package com.example.projectdidong.Model;

public class Diem {
    private String sDiem;
    private String sTenMH;

    public Diem ( String tenmh , String diem ) {
    }

    public Diem ( String s , String getsDiem , String idcu ) {
        this.sDiem = sDiem;
        this.sTenMH = sTenMH;
    }

    public Diem ( ) {

    }

    public String getsDiem ( ) {
        return sDiem;
    }

    public void setsDiem ( String sDiem ) {
        this.sDiem = sDiem;
    }

    public String getsTenMH ( ) {
        return sTenMH;
    }

    public void setsTenMH ( String sTenMH ) {
        this.sTenMH = sTenMH;
    }

}
