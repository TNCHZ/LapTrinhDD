package com.example.jpyou.User;

import androidx.annotation.NonNull;

public class user {
    private String taiKhoanID, hoVaTen, gioiTinh, namSinh, diaChi, CCCD, soDT, Email, chuyenKhoa, chucVu;


    public user(String taiKhoanID, String hoVaTen, String gioiTinh, String namSinh, String diaChi, String CCCD, String soDT, String email, String chuyenKhoa, String chucVu) {
        this.taiKhoanID = taiKhoanID;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.namSinh = namSinh;
        this.diaChi = diaChi;
        this.CCCD = CCCD;
        this.soDT = soDT;
        Email = email;
        this.chuyenKhoa = chuyenKhoa;
        this.chucVu = chucVu;
    }

    public user(String taiKhoanID) {
        this.taiKhoanID = taiKhoanID;

    }

    public String getTaiKhoanID() {
        return taiKhoanID;
    }

    public String getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    @NonNull
    @Override
    public String toString() {
        return this.taiKhoanID + " " + this.hoVaTen + " " + this.soDT;
    }
}
