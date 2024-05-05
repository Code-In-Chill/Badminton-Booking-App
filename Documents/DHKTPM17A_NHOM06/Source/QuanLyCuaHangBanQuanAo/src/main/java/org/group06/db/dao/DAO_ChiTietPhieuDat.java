package org.group06.db.dao;

import org.group06.db.DatabaseConstant;
import org.group06.model.entity.ChiTietPhieuDat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAO_ChiTietPhieuDat implements DAO_Interface<ChiTietPhieuDat> {

    private Connection connection;
    private DAO_PhieuDat dao_PhieuDat;
    private DAO_QuanAo dao_QuanAo;

    public DAO_ChiTietPhieuDat(Connection connection) {
        this.connection = connection;
        dao_PhieuDat = new DAO_PhieuDat(connection);
        dao_QuanAo = new DAO_QuanAo(connection);
    }

    @Override
    public ArrayList<ChiTietPhieuDat> getAll() {
        ArrayList<ChiTietPhieuDat> dsChiTietPhieuDat = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieuDat";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ChiTietPhieuDat chiTietPhieuDat = new ChiTietPhieuDat();
                chiTietPhieuDat.setPhieuDat(dao_PhieuDat.getByID(resultSet.getString("MAPHIEUDAT")));
                chiTietPhieuDat.setQuanAo(dao_QuanAo.getByID(resultSet.getString("MAQA")));
                chiTietPhieuDat.setSoLuong(resultSet.getInt("SOLUONG"));
                chiTietPhieuDat.setGiaBan(resultSet.getDouble("GIABAN"));
                chiTietPhieuDat.setLoiNhuan(resultSet.getDouble("LOINHUAN"));
                dsChiTietPhieuDat.add(chiTietPhieuDat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietPhieuDat;
    }

    /**
     * 
     * @param id
     * @return danh sách các sản phẩm đã đặt, số lượng, giá bán... trong phiếu đặt đó
     */
    public ArrayList<ChiTietPhieuDat> getAllByID(String id) {
        ArrayList<ChiTietPhieuDat> dsChiTietPhieuDat = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieuDat WHERE MAPHIEUDAT = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ChiTietPhieuDat chiTietPhieuDat = new ChiTietPhieuDat();
                chiTietPhieuDat.setPhieuDat(dao_PhieuDat.getByID(resultSet.getString("MAPHIEUDAT")));
                chiTietPhieuDat.setQuanAo(dao_QuanAo.getByID(resultSet.getString("MAQA")));
                chiTietPhieuDat.setSoLuong(resultSet.getInt("SOLUONG"));
                chiTietPhieuDat.setGiaBan(resultSet.getDouble("GIABAN"));
                chiTietPhieuDat.setLoiNhuan(resultSet.getDouble("LOINHUAN"));
                chiTietPhieuDat.setGhiChu(resultSet.getString("GHICHU"));
                dsChiTietPhieuDat.add(chiTietPhieuDat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsChiTietPhieuDat;
    }
    
    /**
     * 
     * @param maQA
     * @return thông tin của quần áo đã chọn 
     */
    public ChiTietPhieuDat getQA(String maQA, String maPD) {
        ChiTietPhieuDat chiTietPhieuDat = null;
        try {
            String sql = "SELECT * FROM ChiTietPhieuDat WHERE MAQA = ? AND MAPHIEUDAT = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, maQA);
            statement.setString(2, maPD);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                chiTietPhieuDat = new ChiTietPhieuDat();
                chiTietPhieuDat.setPhieuDat(dao_PhieuDat.getByID(resultSet.getString("MAPHIEUDAT")));
                chiTietPhieuDat.setQuanAo(dao_QuanAo.getByID(resultSet.getString("MAQA")));
                chiTietPhieuDat.setSoLuong(resultSet.getInt("SOLUONG"));
                chiTietPhieuDat.setGiaBan(resultSet.getDouble("GIABAN"));
                chiTietPhieuDat.setLoiNhuan(resultSet.getDouble("LOINHUAN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chiTietPhieuDat;
    }
    
    /**
     * 
     * @param ctpd
     * @return cập nhật lại số lượng quần áo và ghi lại lí do trong chi tiết phiếu đặt nếu KH trả lại quần áo 
     */
    public boolean updateSoLuong(ChiTietPhieuDat ctpd) {
        try {
            String sql = "UPDATE ChiTietPhieuDat SET SOLUONG = ?, GHICHU = ? WHERE MAQA = ? AND MAPHIEUDAT = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ctpd.getSoLuong());
            statement.setString(2, ctpd.getGhiChu());
            statement.setString(3, ctpd.getQuanAo().getMaQA());
            statement.setString(4, ctpd.getPhieuDat().getMaPhieuDat());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public ChiTietPhieuDat getByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(ChiTietPhieuDat chiTietPhieuDat) {
        try {
            String sql = "INSERT INTO ChiTietPhieuDat (MAPHIEUDAT, MAQA, SOLUONG, GIABAN, LOINHUAN) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, chiTietPhieuDat.getPhieuDat().getMaPhieuDat());
            statement.setString(2, chiTietPhieuDat.getQuanAo().getMaQA());
            statement.setInt(3, chiTietPhieuDat.getSoLuong());
            statement.setDouble(4, chiTietPhieuDat.getGiaBan());
            statement.setDouble(5, chiTietPhieuDat.getLoiNhuan());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ChiTietPhieuDat chiTietPhieuDat) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            String sql = "DELETE FROM ChiTietPhieuDat WHERE MAPHIEUDAT = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<ChiTietPhieuDat> getBatch(int start, int end) {
        ArrayList<ChiTietPhieuDat> dsChiTietPhieuDat = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ChiTietPhieuDat ORDER BY MAPHIEUDAT OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, start);
            statement.setInt(2, end);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ChiTietPhieuDat chiTietPhieuDat = new ChiTietPhieuDat();
                chiTietPhieuDat.setPhieuDat(dao_PhieuDat.getByID(resultSet.getString("MAPHIEUDAT")));
                chiTietPhieuDat.setQuanAo(dao_QuanAo.getByID(resultSet.getString("MAQA")));
                chiTietPhieuDat.setSoLuong(resultSet.getInt("SOLUONG"));
                chiTietPhieuDat.setGiaBan(resultSet.getDouble("GIABAN"));
                chiTietPhieuDat.setLoiNhuan(resultSet.getDouble("LOINHUAN"));
                dsChiTietPhieuDat.add(chiTietPhieuDat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dsChiTietPhieuDat;
    }
}
