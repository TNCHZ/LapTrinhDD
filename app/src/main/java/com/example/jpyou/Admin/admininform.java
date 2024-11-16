package com.example.jpyou.Admin;

public class admininform {
    private String taiKhoan;
    private String matkhau;
    private String chucNang;

    public admininform(String taiKhoan, String matkhau, String chucNang) {
        this.taiKhoan = taiKhoan;
        this.matkhau = matkhau;
        this.chucNang = chucNang;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getChucNang() {
        return chucNang;
    }

    public void setChucNang(String chucNang) {
        this.chucNang = chucNang;
    }
}
