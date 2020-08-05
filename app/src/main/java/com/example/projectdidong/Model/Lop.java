package com.example.projectdidong.Model;

public class Lop {
    private String tenLop;
    private String idLop;

    public Lop ( ) {
    }

    public Lop ( String tenLop ) {
    }

    public Lop ( String tenLop , String idLop ) {
        this.tenLop = tenLop;
        this.idLop = idLop;
    }

    public String getTenLop ( ) {
        return tenLop;
    }

    public void setTenLop ( String tenLop ) {
        this.tenLop = tenLop;
    }

    public String getIdLop ( ) {
        return idLop;
    }

    public void setIdLop ( String idLop ) {
        this.idLop = idLop;
    }
}
