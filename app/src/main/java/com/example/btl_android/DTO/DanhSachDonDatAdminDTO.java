package com.example.btl_android.DTO;

public class DanhSachDonDatAdminDTO {
    private int imgDsAdmin;
    private String nguoiDatDs;
    private String sanPhamDat;
    private String soLuongDat;
    private String donGiaSpDat;

    public DanhSachDonDatAdminDTO(int imgDsAdmin, String nguoiDatDs, String sanPhamDat, String soLuongDat, String donGiaSpDat) {
        this.imgDsAdmin = imgDsAdmin;
        this.nguoiDatDs = nguoiDatDs;
        this.sanPhamDat = sanPhamDat;
        this.soLuongDat = soLuongDat;
        this.donGiaSpDat = donGiaSpDat;
    }

    public int getImgDsAdmin() {
        return imgDsAdmin;
    }

    public void setImgDsAdmin(int imgDsAdmin) {
        this.imgDsAdmin = imgDsAdmin;
    }

    public String getNguoiDatDs() {
        return nguoiDatDs;
    }

    public void setNguoiDatDs(String nguoiDatDs) {
        this.nguoiDatDs = nguoiDatDs;
    }

    public String getSanPhamDat() {
        return sanPhamDat;
    }

    public void setSanPhamDat(String sanPhamDat) {
        this.sanPhamDat = sanPhamDat;
    }

    public String getSoLuongDat() {
        return soLuongDat;
    }

    public void setSoLuongDat(String soLuongDat) {
        this.soLuongDat = soLuongDat;
    }

    public String getDonGiaSpDat() {
        return donGiaSpDat;
    }

    public void setDonGiaSpDat(String donGiaSpDat) {
        this.donGiaSpDat = donGiaSpDat;
    }
}
