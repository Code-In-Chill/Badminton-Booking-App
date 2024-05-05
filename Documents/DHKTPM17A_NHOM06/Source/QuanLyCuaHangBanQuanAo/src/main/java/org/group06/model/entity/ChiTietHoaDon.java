package org.group06.model.entity;

public class ChiTietHoaDon {
    private HoaDon hoaDon;
    private QuanAo quanAo;
    private int soLuong;
    private double giaBan;
    private double loiNhuan;
    private String ghiChu;

    public ChiTietHoaDon() {
        this.hoaDon = new HoaDon();
        this.quanAo = new QuanAo();
        this.soLuong = 0;
        this.giaBan = 0;
        this.loiNhuan = 0;
        this.ghiChu = null;
    }
    
    public ChiTietHoaDon(HoaDon hoaDon, QuanAo quanAo, int soLuong, double giaBan, double loiNhuan) {
        this.setHoaDon(hoaDon);
        this.setQuanAo(quanAo);
        this.setSoLuong(soLuong);
        this.setGiaBan(giaBan);
        this.setLoiNhuan(loiNhuan);
    }

    public ChiTietHoaDon(QuanAo quanAo, double giaBan, int soLuong) {
        this.setQuanAo(quanAo);
        this.setSoLuong(soLuong);
        this.setGiaBan(giaBan);
    }
    
    public ChiTietHoaDon(HoaDon hoaDon, QuanAo quanAo, int soLuong, String ghiChu) {
        this.setHoaDon(hoaDon);
        this.setQuanAo(quanAo);
        this.setSoLuong(soLuong);
        this.setGhiChu(ghiChu);
    }


    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public QuanAo getQuanAo() {
        return quanAo;
    }

    public void setQuanAo(QuanAo quanAo) {
        this.quanAo = quanAo;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(double loiNhuan) {
        this.loiNhuan = loiNhuan;
    }
    
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon{" +
                "hoaDon=" + hoaDon +
                ", quanAo=" + quanAo +
                ", soLuong=" + soLuong +
                ", giaBan=" + giaBan +
                ", loiNhuan=" + loiNhuan +
                ", ghiChu=" + ghiChu +
                '}';
    }
}
