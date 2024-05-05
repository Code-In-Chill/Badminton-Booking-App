/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.group06.db.dao;

import org.group06.db.DatabaseConstant;
import org.group06.model.entity.KhachHang;
import org.group06.model.entity.KhuyenMai;
import org.group06.model.entity.NhanVien;
import org.group06.model.entity.PhieuDat;

import java.sql.*;
import java.util.ArrayList;

public class DAO_PhieuDat implements DAO_Interface<PhieuDat> {

    private Connection connection;
    private DAO_NhanVien dao_NhanVien;
    private DAO_KhuyenMai dao_KhuyenMai;
    private DAO_KhachHang dao_KhachHang;

    public DAO_PhieuDat(Connection connection) {
        this.connection = connection;
        dao_NhanVien = new DAO_NhanVien(connection);
        dao_KhuyenMai = new DAO_KhuyenMai(connection);
        dao_KhachHang = new DAO_KhachHang(connection);
    }

    @Override
    public ArrayList<PhieuDat> getAll() {
        ArrayList<PhieuDat> dsPD = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PhieuDat";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PhieuDat phieuDat = new PhieuDat();
                String maPD = resultSet.getString("MAPHIEUDAT");
                Date ngayLap = resultSet.getDate("NGAYTAO");
                Date ngayNhan = resultSet.getDate("NGAYNHAN");
                KhachHang khachHang = dao_KhachHang.getByMAKH(resultSet.getString("MAKH"));
                NhanVien nhanVien = dao_NhanVien.getByID(resultSet.getString("MANV"));
                KhuyenMai khuyenMai = dao_KhuyenMai.getByID(resultSet.getString("MAKM"));
                int trangThai = resultSet.getInt("TRANGTHAI");
                boolean thanhToan = resultSet.getBoolean("THANHTOAN");
                phieuDat = new PhieuDat(maPD, ngayLap, ngayNhan, khachHang, nhanVien, khuyenMai, trangThai, thanhToan);
                dsPD.add(phieuDat);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách phiếu đặt");
        }
        return dsPD;
    }

    @Override
    public PhieuDat getByID(String id) {
        PhieuDat phieuDat = null;
        try {
            String sql = "SELECT * FROM PhieuDat WHERE MAPHIEUDAT = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                phieuDat = new PhieuDat();
                phieuDat.setMaPhieuDat(resultSet.getString("MAPHIEUDAT"));
                phieuDat.setKhuyenMai(new DAO_KhuyenMai(connection).getByID(resultSet.getString("MAKM")));
                phieuDat.setNgayTao(resultSet.getDate("NGAYTAO"));
                phieuDat.setNgayNhan(resultSet.getDate("NGAYNHAN"));
                phieuDat.setKhachHang(new DAO_KhachHang(connection).getByMAKH(resultSet.getString("MAKH")));
                phieuDat.setNhanVien(new DAO_NhanVien(connection).getByID(resultSet.getString("MANV")));
                phieuDat.setTrangThai(resultSet.getInt("TRANGTHAI"));
                phieuDat.setThanhToan(resultSet.getBoolean("THANHTOAN"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy phiếu đặt theo mã phiếu đặt");
        }
        return phieuDat;
    }

    @Override
    public boolean add(PhieuDat phieuDat) {
        boolean success = false;
        String sql = "INSERT INTO PhieuDat (MAPHIEUDAT, NGAYTAO, NGAYNHAN, MAKH, MANV, MAKM, TRANGTHAI, THANHTOAN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, phieuDat.getMaPhieuDat());
            statement.setDate(2, phieuDat.getNgayTao());
            statement.setDate(3, phieuDat.getNgayNhan());
            statement.setString(4, phieuDat.getKhachHang().getMaKhachHang());
            statement.setString(5, phieuDat.getNhanVien().getMaNV());
            statement.setString(6, phieuDat.getKhuyenMai() == null ? null : phieuDat.getKhuyenMai().getMaKhuyenMai());
            statement.setInt(7, phieuDat.getTrangThai());
            statement.setBoolean(8, phieuDat.isThanhToan());
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi thêm phiếu đặt");
        }
        return success;
    }

    @Override
    public boolean update(PhieuDat t) {
        boolean success = false;
        String sql = "UPDATE PhieuDat SET TRANGTHAI = ? WHERE MAPHIEUDAT = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getTrangThai());
            statement.setString(2, t.getMaPhieuDat());
            success = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật trạng thái phiếu đặt");
        }
        return success;
    }

    @Override
    public boolean delete(String id) {
        new DAO_ChiTietPhieuDat(connection).delete(id);
        try {
            String sql = "DELETE FROM PhieuDat WHERE MAPHIEUDAT = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Lỗi xóa phiếu đặt");
            return false;
        }
    }

    @Override
    public ArrayList<PhieuDat> getBatch(int start, int end) {
        ArrayList<PhieuDat> dsPD = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PhieuDat ORDER BY MAPHIEUDAT OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, end);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PhieuDat phieuDat = new PhieuDat();
                String maPD = resultSet.getString("MAPHIEUDAT");
                Date ngayLap = resultSet.getDate("NGAYTAO");
                Date ngayNhan = resultSet.getDate("NGAYNHAN");
                KhachHang khachHang = dao_KhachHang.getByMAKH(resultSet.getString("MAKH"));
                NhanVien nhanVien = dao_NhanVien.getByID(resultSet.getString("MANV"));
                KhuyenMai khuyenMai = dao_KhuyenMai.getByID(resultSet.getString("MAKM"));
                int trangThai = resultSet.getInt("TRANGTHAI");
                boolean thanhToan = resultSet.getBoolean("THANHTOAN");
                phieuDat = new PhieuDat(maPD, ngayLap, ngayNhan, khachHang, nhanVien, khuyenMai, trangThai, thanhToan);
                dsPD.add(phieuDat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsPD;
    }

    /**
     * 
     * @return số lớn nhất trong CSDL để tạo một mã phiếu đặt mới 
     */
    public int loadMaPDCount() {
        int countMaPD = 0;
        String sql = "SELECT MAX(MAPHIEUDAT) FROM PhieuDat";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String maxMaPD = resultSet.getString(1);
                if (maxMaPD != null) {
                    countMaPD = Integer.parseInt(maxMaPD.substring(2)); // Bỏ đi 2 ký tự đầu (VD: PD) để lấy số
                }
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Lỗi khi tải giá trị countMaPD từ cơ sở dữ liệu.");
        }
        return countMaPD;
    }
    
    /**
     * 
     * @param date
     * @return danh sách phiếu đặt hàng theo ngày tạo
     */
    public ArrayList<PhieuDat> getByDateDat(String date) {
        ArrayList<PhieuDat> dsDateDat = new ArrayList<PhieuDat>();
        try {
            String sql = "SELECT *FROM PhieuDat WHERE NGAYTAO = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PhieuDat phieuDat = new PhieuDat();
                String maHD = rs.getString("MAPHIEUDAT");
                KhuyenMai khuyenMai = dao_KhuyenMai.getByID(rs.getString("MAKM"));
                Date ngayLap = rs.getDate("NGAYTAO");
                Date ngayNhan = rs.getDate("NGAYNHAN");
                KhachHang khachHang = dao_KhachHang.getByMAKH(rs.getString("MAKH"));
                NhanVien nhanVien = dao_NhanVien.getByID(rs.getString("MANV"));
                int trangThai = rs.getInt("TRANGTHAI");
                boolean thanhToan = rs.getBoolean("THANHTOAN");
                phieuDat = new PhieuDat(maHD, ngayLap, ngayNhan, khachHang, nhanVien, khuyenMai, trangThai, thanhToan);
                dsDateDat.add(phieuDat);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách phiếu đặt theo ngày đặt hàng");
        }
        return dsDateDat;
    }

    /**
     * 
     * @param date
     * @return danh sách phiếu đặt hàng theo ngày nhận
     */
    public ArrayList<PhieuDat> getByDateNhan(String date) {
        ArrayList<PhieuDat> dsDateNhan = new ArrayList<PhieuDat>();
        try {
            String sql = "SELECT *FROM PhieuDat WHERE NGAYNHAN = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, date);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PhieuDat phieuDat = new PhieuDat();
                String maHD = rs.getString("MAPHIEUDAT");
                KhuyenMai khuyenMai = dao_KhuyenMai.getByID(rs.getString("MAKM"));
                Date ngayLap = rs.getDate("NGAYTAO");
                Date ngayNhan = rs.getDate("NGAYNHAN");
                KhachHang khachHang = dao_KhachHang.getByMAKH(rs.getString("MAKH"));
                NhanVien nhanVien = dao_NhanVien.getByID(rs.getString("MANV"));
                int trangThai = rs.getInt("TRANGTHAI");
                boolean thanhToan = rs.getBoolean("THANHTOAN");
                phieuDat = new PhieuDat(maHD, ngayLap, ngayNhan, khachHang, nhanVien, khuyenMai, trangThai, thanhToan);
                dsDateNhan.add(phieuDat);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách phiếu đặt theo ngày nhận hàng");
        }
        return dsDateNhan;
    }

    /**
     * @param dateDat
     * @param dateNhan
     * @return danh sách phiếu đặt theo ngày đặt hàng hoặc ngày nhận hàng
     */
    public ArrayList<PhieuDat> getByDateDatAndDateNhan(String dateDat, String dateNhan) {
        ArrayList<PhieuDat> dsDateDatAndDateNhan = new ArrayList<PhieuDat>();
        try {
            String sql = "SELECT *FROM PhieuDat WHERE NGAYTAO = ? OR NgayNhan = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, dateDat);
            statement.setString(2, dateNhan);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                PhieuDat phieuDat = new PhieuDat();
                String maHD = rs.getString("MAPHIEUDAT");
                KhuyenMai khuyenMai = dao_KhuyenMai.getByID(rs.getString("MAKM"));
                Date ngayLap = rs.getDate("NGAYTAO");
                Date ngayNhan = rs.getDate("NGAYNHAN");
                KhachHang khachHang = dao_KhachHang.getByMAKH(rs.getString("MAKH"));
                NhanVien nhanVien = dao_NhanVien.getByID(rs.getString("MANV"));
                int trangThai = rs.getInt("TRANGTHAI");
                boolean thanhToan = rs.getBoolean("THANHTOAN");
                phieuDat = new PhieuDat(maHD, ngayLap, ngayNhan, khachHang, nhanVien, khuyenMai, trangThai, thanhToan);
                dsDateDatAndDateNhan.add(phieuDat);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách phiếu đặt theo ngày đặt hàng hoặc ngày nhận hàng");
        }
        return dsDateDatAndDateNhan;
    }
}
