package org.group06.view.screen;

import org.group06.model.entity.NhanVien;
import org.group06.utils.ImagePath;
import org.group06.view.components.panels.ImagePanel;
import org.group06.view.components.tabbedPane.TabBanHang_DatHang;
import org.group06.view.container.khachHang.PanelKhachHang;
import org.group06.view.container.nhanVien.PanelBanHang_DatHang;
import org.group06.view.container.nhanVien.quanLyHoaDon.PanelHoaDon;
import org.group06.view.container.nhanVien.quanLyHoaDon.PanelPhieuTam;
import org.group06.view.container.nhanVien.quanLyNhanVien.PanelNhanVien;
import org.group06.view.container.nhanVien.thongKe.PanelThongKeDoanhThu;
import org.group06.view.container.nhanVien.thongKe.PanelThongKeKhachHang;
import org.group06.view.container.nhanVien.thongKe.PanelThongKeQuanAo;
import org.group06.view.container.quanAo.*;
import org.group06.view.container.taiKhoan.WinDoiMatKhau;

import javax.swing.*;
import java.awt.*;

/**
 * @author Le Minh Bao
 */
public class WinGiaoDienChinh extends JFrame {

    private JTabbedPane tabDonHang = null;
    private final NhanVien nv;

    // <editor-fold defaultstate="collapsed" desc="Khai báo biến">
    private final JMenuBar mnuMain = new JMenuBar();
    private final JMenu mnNhanVien = new JMenu("Nhân Viên");
    private final JMenu mnKhachHang = new JMenu("Khách Hàng");
    private final JMenu mnQuanAo = new JMenu("Quần Áo");
    private final JMenu mnHoaDon = new JMenu("Hóa Đơn");
    private final JMenu mnThongKe = new JMenu("Thống Kê");
    private final JMenu mnTaiKhoan = new JMenu("Tài Khoản");

    //Menu Nhan Vien
    private final JMenuItem mniBanHang_DatHang = new JMenuItem("Bán Hàng / Đặt Hàng");
    private final JMenuItem mniQuanLyNhanVien = new JMenuItem("Quản Lý Nhân Viên");

    //Menu Khach Hang
    private final JMenuItem mniQuanLyKhachHang = new JMenuItem("Quản Lý Khách Hàng");

    //Menu Quan Ao
    private final JMenuItem mniQuanLyQuanAo = new JMenuItem("Quản Lý Quần Áo");
    private final JMenuItem mniQuanLyLoaiQuanAo = new JMenuItem("Quản Lý Loại Quần Áo");
    private final JMenuItem mniQuanLyNhaCungCap = new JMenuItem("Quản Lý Nhà Cung Cấp");
    private final JMenuItem mniQuanLyKhuyenMai = new JMenuItem("Quản Lý Khuyến Mãi");
    private final JMenuItem mniQuanLyKichThuoc = new JMenuItem("Quản Lý Kích Thước");

    //Menu Hoa Don
    private final JMenuItem mniQuanLyHoaDonBanHang = new JMenuItem("Quản Lý Hóa Đơn Bán Hàng");
    private final JMenuItem mniQuanLyHoaDonDatHang = new JMenuItem("Quản Lý Phiếu Đặt Hàng");

    //Menu Thong Ke
    private final JMenuItem mniThongKeDoanhThu = new JMenuItem("Thống Kê Doanh Thu");
    private final JMenuItem mniThongKeQuanAo = new JMenuItem("Thống Kê Quần Áo");
    private final JMenuItem mniThongKeKhachHang = new JMenuItem("Thống Kê Khách Hàng");

    //Menu Tai Khoan
    private final JMenuItem mniDoiMatKhau = new JMenuItem("Đổi Mật Khẩu");
    private final JMenuItem mniCaiDat = new JMenuItem("Cài Đặt");
    private final JMenuItem mniDangXuat = new JMenuItem("Đăng Xuất");

    private JPanel pnlContainer;
    // </editor-fold>

    public WinGiaoDienChinh(NhanVien nv) {
        this.nv = nv;
        this.mnTaiKhoan.setText(nv.getTenNV());
        this.setJMenuBar(mnuMain);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setIconImage(ImagePath.loadImage(ImagePath.THUMBNAIL_ICON));
        this.setUndecorated(true);

        pnlContainer = new ImagePanel(ImagePath.THUMBNAIL_MAIN, 1920, 1080);
        this.add(pnlContainer, BorderLayout.CENTER);

        initMenu();
        addActionMenu();
    }

    private void addActionMenu() {
        addActionMenuNhanVien();
        addActionMenuKhachHang();
        addActionMenuQuanAo();
        addActionMenuHoaDon();
        addActionMenuThongKe();
        addActionMenuTaiKhoan();
    }

    private void addActionMenuNhanVien() {
        mniBanHang_DatHang.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            if (tabDonHang == null) {
                pnlContainer = new PanelBanHang_DatHang(nv);
                tabDonHang = ((PanelBanHang_DatHang) pnlContainer).getTab();
            } else {
                // sync data in all tab
                for (int i = 0; i < tabDonHang.getTabCount(); i++) {
                    if (tabDonHang.getComponentAt(i) instanceof TabBanHang_DatHang tabBanHangDatHang) {
                        tabBanHangDatHang.synchronizeData();
                    }
                }
                pnlContainer = new PanelBanHang_DatHang(nv, tabDonHang);
            }
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });

        mniQuanLyNhanVien.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelNhanVien();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });
    }

    private void addActionMenuKhachHang() {
        mniQuanLyKhachHang.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelKhachHang();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });
    }

    private void addActionMenuQuanAo() {
        mniQuanLyQuanAo.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelQuanAo();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });

        mniQuanLyLoaiQuanAo.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelLoaiQuanAo();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });

        mniQuanLyNhaCungCap.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelNhaCungCap();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });

        mniQuanLyKhuyenMai.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelKhuyenMai();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });
        
        mniQuanLyKichThuoc.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            pnlContainer = new PanelKichThuocQA();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });
    }

    private void addActionMenuHoaDon() {
        mniQuanLyHoaDonBanHang.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelHoaDon();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });

        mniQuanLyHoaDonDatHang.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelPhieuTam();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });
    }

    private void addActionMenuThongKe() {
        mniThongKeDoanhThu.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelThongKeDoanhThu();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });

        mniThongKeQuanAo.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelThongKeQuanAo();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });

        mniThongKeKhachHang.addActionListener(e -> {
            getContentPane().remove(pnlContainer);
            tamLuuDonHang();
            pnlContainer = new PanelThongKeKhachHang();
            getContentPane().add(pnlContainer, BorderLayout.CENTER);
            this.revalidate();
            this.repaint();
        });
    }

    private void addActionMenuTaiKhoan() {
        mniDoiMatKhau.addActionListener(e -> {
            new WinDoiMatKhau(nv).setVisible(true);
        });

        mniDangXuat.addActionListener(e -> {
            // Đóng cửa sổ chính
            this.dispose();
            // Mở WinDangNhap Frame
            WinDangNhap winDangNhap = new WinDangNhap();
            winDangNhap.setVisible(true);
        });
    }

    private void tamLuuDonHang() {
        if (pnlContainer instanceof PanelBanHang_DatHang panelBanHangDatHang) {
            this.tabDonHang = panelBanHangDatHang.getTab();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Tạo Menu với cài đặt font và icon">
    private void initMenu() {
        mnuMain.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mnNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mnKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mnQuanAo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mnHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mnThongKe.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mnTaiKhoan.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        mnNhanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mnKhachHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mnQuanAo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mnHoaDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mnThongKe.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mnTaiKhoan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniBanHang_DatHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyNhanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyKhachHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyQuanAo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyLoaiQuanAo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyNhaCungCap.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyKhuyenMai.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyKichThuoc.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyHoaDonBanHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniQuanLyHoaDonDatHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniThongKeDoanhThu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniThongKeQuanAo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniThongKeKhachHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniDoiMatKhau.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mniDangXuat.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Menu Nhan Vien
        mniBanHang_DatHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniQuanLyNhanVien.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //Menu Khach Hang
        mniQuanLyKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //Menu Quan Ao
        mniQuanLyQuanAo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniQuanLyLoaiQuanAo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniQuanLyNhaCungCap.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniQuanLyKhuyenMai.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniQuanLyKichThuoc.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //Menu Hoa Don
        mniQuanLyHoaDonBanHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniQuanLyHoaDonDatHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //Menu Thong Ke
        mniThongKeDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniThongKeQuanAo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniThongKeKhachHang.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        //Menu Tai Khoan
        mniDoiMatKhau.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        mniDangXuat.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        mnNhanVien.setIcon(ImagePath.loadIcon(ImagePath.ICON_EMPLOYEE));
        mnKhachHang.setIcon(ImagePath.loadIcon(ImagePath.ICON_CUSTOMER));
        mnQuanAo.setIcon(ImagePath.loadIcon(ImagePath.ICON_CLOTHES));
        mnHoaDon.setIcon(ImagePath.loadIcon(ImagePath.ICON_BILL));
        mnThongKe.setIcon(ImagePath.loadIcon(ImagePath.ICON_CHART));
        mnTaiKhoan.setIcon(ImagePath.loadIcon(ImagePath.ICON_USER));

        mnuMain.add(mnNhanVien);
        mnuMain.add(Box.createHorizontalStrut(10));
        mnuMain.add(mnKhachHang);
        mnuMain.add(Box.createHorizontalStrut(10));
        if (nv.getChucVu().equals(NhanVien.NVQL)) {
            mnuMain.add(mnQuanAo);
        }
        mnuMain.add(Box.createHorizontalGlue());
        mnuMain.add(mnTaiKhoan);

        //Menu Nhan Vien
        mnNhanVien.add(mniBanHang_DatHang);
        mnNhanVien.add(new JSeparator());
        if (nv.getChucVu().equals(NhanVien.NVQL)) {
            mnNhanVien.add(mniQuanLyNhanVien);
            mnNhanVien.add(new JSeparator());
        }
        mnNhanVien.add(mnHoaDon);
        mnNhanVien.add(mnThongKe);

        //Menu Khach Hang
        mnKhachHang.add(mniQuanLyKhachHang);

        //Menu Quan Ao
        mnQuanAo.add(mniQuanLyQuanAo);
        mnQuanAo.add(new JSeparator());
        mnQuanAo.add(mniQuanLyLoaiQuanAo);
        mnQuanAo.add(mniQuanLyNhaCungCap);
        mnQuanAo.add(mniQuanLyKhuyenMai);
        mnQuanAo.add(mniQuanLyKichThuoc);

        //Menu Hoa Don
        mnHoaDon.add(mniQuanLyHoaDonBanHang);
        mnHoaDon.add(mniQuanLyHoaDonDatHang);

        //Menu Thong Ke
        mnThongKe.add(mniThongKeDoanhThu);
        mnThongKe.add(mniThongKeQuanAo);
        mnThongKe.add(mniThongKeKhachHang);

        //Menu Tai Khoan
        mnTaiKhoan.add(mniDoiMatKhau);
        mnTaiKhoan.add(mniDangXuat);
    }
    // </editor-fold>

}
