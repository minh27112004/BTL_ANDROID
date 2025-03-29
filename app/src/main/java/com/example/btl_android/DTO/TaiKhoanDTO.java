package com.example.btl_android.DTO;

public class TaiKhoanDTO {

    public int idTaiKhoan;
    public int idKhachHang;
    public String tenDangNhap;
    public String matKhau;
    public String tenUser;
    public String email;
    public String soDienThoai;
    public String gioiTinh;
    public String ngaySinh;


    public TaiKhoanDTO() {
    }

    public TaiKhoanDTO(int idTaiKhoan, String tenUser, String email, String soDienThoai, String gioiTinh, String ngaySinh) {
        this.idTaiKhoan = idTaiKhoan;
        this.tenUser = tenUser;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public TaiKhoanDTO(int idTaiKhoan, int idKhachHang, String tenDangNhap, String matKhau) {
        this.idTaiKhoan = idTaiKhoan;
        this.idKhachHang = idKhachHang;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
