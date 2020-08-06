package com.example.projectdidong.Model;

public class SinhVien {
    String ten;
    String maSv;
    String gioiTinh;
    String diaChi;
    String ngaySinh;
    String dienThoai;
    String cmnd;
    String lop;
    String idSinhVien;
    public SinhVien() {

    }

    public SinhVien(String ten, String maSv, String gioiTinh, String diaChi, String ngaySinh, String dienThoai, String cmnd, String lop) {
        this.ten = ten;
        this.maSv = maSv;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.dienThoai = dienThoai;
        this.cmnd = cmnd;
        this.lop = lop;
    }
    public SinhVien(String ten, String maSv, String gioiTinh, String diaChi, String ngaySinh, String dienThoai, String cmnd, String lop, String idSinhVien) {
        this.ten = ten;
        this.maSv = maSv;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.dienThoai = dienThoai;
        this.cmnd = cmnd;
        this.lop = lop;

        this.idSinhVien = idSinhVien;
    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }





    public String getIdSinhVien() {
        return idSinhVien;
    }

    public void setIdSinhVien(String idSinhVien) {
        this.idSinhVien = idSinhVien;
    }
}
