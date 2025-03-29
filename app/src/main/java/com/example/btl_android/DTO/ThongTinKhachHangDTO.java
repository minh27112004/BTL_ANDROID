package com.example.btl_android.DTO;

public class ThongTinKhachHangDTO {
    private int idKhachHang;
    private String tenKhachHang;
    private String email;
    private String soDienThoai;
    private String gioiTinh;
    private String ngaySinh;
    private String diaChi;

    public ThongTinKhachHangDTO() {
    }

    public ThongTinKhachHangDTO(int idKhachHang, String tenKhachHang, String email, String soDienThoai, String gioiTinh, String ngaySinh) {
        this.idKhachHang = idKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

//    public ThongTinKhachHangDTO(String tenKhachHang, String email, String soDienThoai, String gioiTinh, String ngaySinh) {
//        this.tenKhachHang = tenKhachHang;
//        this.email = email;
//        this.soDienThoai = soDienThoai;
//        this.gioiTinh = gioiTinh;
//        this.ngaySinh = ngaySinh;
//    }

    public ThongTinKhachHangDTO(String tenKhachHang, String soDienThoai, String diaChi) {
        this.tenKhachHang = tenKhachHang;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
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

    public String toString(){

        return String.valueOf(this.getIdKhachHang());

    }
}
