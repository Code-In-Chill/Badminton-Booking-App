package org.group06.view.container.quanAo;

import org.group06.db.DatabaseConstant;
import org.group06.db.dao.DAO_NhaCungCap;
import org.group06.db.dao.DAO_QuanAo;
import org.group06.model.entity.NhaCungCap;
import org.group06.model.entity.QuanAo;
import org.group06.utils.*;
import org.group06.view.components.panels.ImagePanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.group06.model.manager.Manager_QuanAo;

/**
 * @author lemin
 */
public class PanelQuanAo extends javax.swing.JPanel {

    /**
     * Creates new form PanelQuanAo
     */
    public PanelQuanAo() {
        initComponents();
        tblQuanAo.getTableHeader().setFont(FontConstant.FONT_TABLE_HEADER);
        tblQuanAo.getTableHeader().setForeground(ColorConstant.WHITE);
        tblQuanAo.getTableHeader().setBackground(ColorConstant.BACKGROUND_TABLEHEADER);
        loadDataTable();
        loadDataForComboboxLoaiQuanAo();
        loadDataForComboboxSize();
        loadDataForComboboxNCC();
        setStatusAllBtnsStart();
        FormatCellRenderer.formatCellRendererCenter(this.tblQuanAo, 3);
        FormatCellRenderer.formatCellRendererCenter(this.tblQuanAo, 4);
        FormatCellRenderer.formatCellRendererRight(this.tblQuanAo, 7);
        FormatCellRenderer.formatCellRendererCenter(this.tblQuanAo, 8);
        FormatCellRenderer.formatCellRendererRight(this.tblQuanAo, 9);
        FormatCellRenderer.formatCellRendererCenter(this.tblQuanAo, 10);
    }

    /**
     * Load dữ liệu cho loại quần áo, danh sách loại quần áo được lấy từ DAO_QuanAo được khai báo với dạng hashmap<String, String>
     * Do loại quần áo chỉ có 2 field cho nên rất thích hợp áp dụng hashmap
     */
    public void loadDataForComboboxLoaiQuanAo() {
        DefaultComboBoxModel<String> cmbModel = new DefaultComboBoxModel<>();
        cmbModel.addElement("Chọn Loại Quần Áo");
        for (HashMap.Entry<String, String> item : loaiQuanAo.entrySet()) {
            cmbModel.addElement(item.getValue());
        }
        this.cmbLoaiQA.setModel(cmbModel);
    }

    /**
     * Load dữ liệu cho kích thước, danh sách kích thước được lấy từ DAO_QuanAo được khai báo với dạng hashmap<String, String>
     * Do kích thước lượng dữ liệu không nhiều, và chỉ có 2 field cho nên rất thích hợp áp dụng hashmap
     */
    public void loadDataForComboboxSize() {
        DefaultComboBoxModel<String> cmbModel = new DefaultComboBoxModel<>();
        cmbModel.addElement("Chọn Kích Thước Quần Áo");
        for (HashMap.Entry<String, String> item : dsKichThuocQA.entrySet()) {
            cmbModel.addElement(item.getValue());
        }
        this.cmbSize.setModel(cmbModel);
    }

    /**
     * Load dữ liệu cho nhà cung cấp lên combobox
     */
    public void loadDataForComboboxNCC() {
        DefaultComboBoxModel<String> cmbModel = new DefaultComboBoxModel<>();
        cmbModel.addElement("Chọn Nhà Cung Cấp");
        for (NhaCungCap ncc : dsNCC) {
            cmbModel.addElement(ncc.getTenNCC());
        }
        this.cmbNhaCungCap.setModel(cmbModel);
    }

    /**
     * Vẽ hình ảnh lên giao diện dưới dạng ImagePanel
     *
     * @param path đường dẫn hình ảnh lấy được từ loaiImgWithRowData
     */
    public void loadImage(URL path) {
//      Xóa bỏ bản vẽ cũ
        this.pnImgUpLoad.remove(pnImg);
//      Vẽ mới
        this.pnImg = new ImagePanel(path, 400, 400);
        // <editor-fold defaultstate="collapsed" desc="Định Dạng layout hình ảnh">
        javax.swing.GroupLayout pnImgLayout = new javax.swing.GroupLayout(pnImg);
        pnImg.setLayout(pnImgLayout);
        pnImgLayout.setHorizontalGroup(
                pnImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        pnImgLayout.setVerticalGroup(
                pnImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        javax.swing.GroupLayout pnImgUpLoadLayout = new javax.swing.GroupLayout(pnImgUpLoad);
        pnImgUpLoad.setLayout(pnImgUpLoadLayout);
        pnImgUpLoadLayout.setHorizontalGroup(
                pnImgUpLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnImgUpLoadLayout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addComponent(pnImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                        .addGroup(pnImgUpLoadLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(btnUploadImg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnImgUpLoadLayout.setVerticalGroup(
                pnImgUpLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnImgUpLoadLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(pnImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(btnUploadImg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
        );
        // </editor-fold>
        this.revalidate();
        this.repaint();
    }

    /**
     * Xử lý load hình ảnh quần áo lên panel khi chọn dòng dữ liệu quần áo trên bảng dữ liệu tương ứng.
     * Khi chọn dòng ta lấy được mã quần áo mã quần áo là đối số của hàm sau đó lấy
     * quần áo từ dsQuanLyQuanAo để lấy được hình ảnh của quần áo tương ứng
     */
    private void loadImgWithRowData(String maQuanAo) {
        QuanAo qa = this.dsQuanLyQuanAo.getByID(maQuanAo);
        try {
            URL urlImg = qa.getHinhAnh().startsWith("file:")
                    ? new URL(qa.getHinhAnh())
                    : new File(qa.getHinhAnh()).toURI().toURL();
            loadImage(urlImg);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println("Hình ảnh không tồn tại!");
            loadImage(ImagePath.UPLOAD_IMG);
        }

    }

    /**
     * Load dữ liệu từ danh sách quần áo lên bảng
     */
    private void loadDataTable() {
        DefaultTableModel modelQuanAo = (DefaultTableModel) this.tblQuanAo.getModel();
        modelQuanAo.setRowCount(0);
        for (QuanAo qa : this.dsQuanLyQuanAo.getAll()) {
            String tenKichThuocQA = "";
            for (Map.Entry<String, String> maKichThuoc : dsKichThuocQA.entrySet()) {
                if (qa.getMaKichThuoc().equals(maKichThuoc.getKey())) {
                    tenKichThuocQA = maKichThuoc.getValue();
                }
            }
            Object[] data = {qa.getMaQA(), qa.getTenQA(), this.dsQuanLyQuanAo.getTenLoaiQuanAo(qa.getLoaiQuanAo()), tenKichThuocQA,
                    qa.getSoLuong(), qa.getThuongHieu(), qa.getNhaCungCap().getTenNCC(), NumberStandard.formatMoney(qa.getGiaNhap()), NumberStandard.formatPercent(qa.getLoiNhuan()), tinhGiaBan(String.valueOf(qa.getGiaNhap()), String.valueOf(qa.getLoiNhuan())), qa.isTrangThai() ? "Còn Kinh Doanh" : "Dừng Kinh Doanh"};
//      Thêm dữ liệu vào table
            modelQuanAo.addRow(data);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTimKiemQA = new javax.swing.JLabel();
        pnInformationFields = new javax.swing.JPanel();
        pnImgUpLoad = new javax.swing.JPanel();
        pnImg = new ImagePanel(ImagePath.UPLOAD_IMG, 400, 400);
        btnUploadImg = new javax.swing.JButton();
        pnFields = new javax.swing.JPanel();
        pnFieldsLeft = new javax.swing.JPanel();
        lblMaQA = new javax.swing.JLabel();
        txtMaQA = new javax.swing.JTextField();
        lbTenQA = new javax.swing.JLabel();
        txtTenQA = new javax.swing.JTextField();
        lblLoaiQA = new javax.swing.JLabel();
        cmbLoaiQA = new javax.swing.JComboBox<>();
        lblSoLuong = new javax.swing.JLabel();
        lblSize = new javax.swing.JLabel();
        cmbSize = new javax.swing.JComboBox<>();
        txtThuongHieu = new javax.swing.JTextField();
        lblNhaCungCap = new javax.swing.JLabel();
        cmbNhaCungCap = new javax.swing.JComboBox<>();
        lblThuongHieu = new javax.swing.JLabel();
        txtSoLuongQA = new javax.swing.JTextField();
        pnFieldsRight = new javax.swing.JPanel();
        lblGiaNhap = new javax.swing.JLabel();
        lblLoiNhuan = new javax.swing.JLabel();
        lblGiaBan = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        txtLoiNhuan = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        lblTrangThai = new javax.swing.JLabel();
        cmbTrangThai = new javax.swing.JComboBox<>();
        pnControl = new javax.swing.JPanel();
        btnXoaTrang = new javax.swing.JButton();
        btnThemMoi = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnNhapHang = new javax.swing.JButton();
        scrQuanAo = new javax.swing.JScrollPane();
        tblQuanAo = new javax.swing.JTable();
        lblTitleQA1 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        cmbTimKiemQATheoTieuChi = new javax.swing.JComboBox<>();

        setBackground(ColorConstant.WHITE);

        lblTimKiemQA.setFont(org.group06.utils.FontConstant.FONT_TABLE_HEADER);
        lblTimKiemQA.setForeground(org.group06.utils.ColorConstant.BLACK);
        lblTimKiemQA.setText("Tìm Kiếm Quần Áo");
        lblTimKiemQA.setMaximumSize(new java.awt.Dimension(180, 60));
        lblTimKiemQA.setMinimumSize(new java.awt.Dimension(180, 60));
        lblTimKiemQA.setPreferredSize(new java.awt.Dimension(180, 60));

        pnInformationFields.setBackground(ColorConstant.WHITE);
        pnInformationFields.setPreferredSize(new java.awt.Dimension(0, 500));

        pnImgUpLoad.setBackground(ColorConstant.WHITE);

        pnImg.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout pnImgLayout = new javax.swing.GroupLayout(pnImg);
        pnImg.setLayout(pnImgLayout);
        pnImgLayout.setHorizontalGroup(
                pnImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        pnImgLayout.setVerticalGroup(
                pnImgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );

        btnUploadImg.setBackground(ColorConstant.BACKGROUND_TABLEHEADER);
        btnUploadImg.setFont(FontConstant.FONT_BUTTON);
        btnUploadImg.setForeground(ColorConstant.WHITE);
        btnUploadImg.setText("Tải Lên Hình Ảnh");
        btnUploadImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUploadImg.setMaximumSize(new java.awt.Dimension(200, 40));
        btnUploadImg.setMinimumSize(new java.awt.Dimension(200, 40));
        btnUploadImg.setPreferredSize(new java.awt.Dimension(200, 40));
        btnUploadImg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImgActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnImgUpLoadLayout = new javax.swing.GroupLayout(pnImgUpLoad);
        pnImgUpLoad.setLayout(pnImgUpLoadLayout);
        pnImgUpLoadLayout.setHorizontalGroup(
                pnImgUpLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnImgUpLoadLayout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addComponent(pnImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))
                        .addGroup(pnImgUpLoadLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(btnUploadImg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnImgUpLoadLayout.setVerticalGroup(
                pnImgUpLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnImgUpLoadLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(pnImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(btnUploadImg, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
        );

        pnFields.setBackground(ColorConstant.BACKGROUND_CONTAINER);
        pnFields.setLayout(new java.awt.GridLayout(1, 2, 0, 10));

        pnFieldsLeft.setBackground(ColorConstant.WHITE);
        pnFieldsLeft.setPreferredSize(new java.awt.Dimension(420, 0));

        lblMaQA.setFont(FontConstant.FONT_LABEL);
        lblMaQA.setText("Mã Quần Áo:");
        lblMaQA.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txtMaQA.setEditable(false);
        txtMaQA.setBackground(ColorConstant.DISABLE_FIELD);
        txtMaQA.setFont(FontConstant.FONT_TEXT);
        txtMaQA.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtMaQA.setEnabled(false);

        lbTenQA.setFont(FontConstant.FONT_LABEL);
        lbTenQA.setText("Tên Quần Áo:");
        lbTenQA.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txtTenQA.setFont(FontConstant.FONT_TEXT);
        txtTenQA.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTenQA.setEnabled(false);
        txtTenQA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTenQAFocusLost(evt);
            }
        });
        txtTenQA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenQAKeyReleased(evt);
            }
        });

        lblLoaiQA.setFont(FontConstant.FONT_LABEL);
        lblLoaiQA.setText("Loại Quần Áo:");
        lblLoaiQA.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cmbLoaiQA.setFont(FontConstant.FONT_TEXT);
        cmbLoaiQA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Chọn Loại Quần Áo", "Jean Jacket", "Váy", "Áo Croptop", "Quần"}));
        cmbLoaiQA.setEnabled(false);
        cmbLoaiQA.setLightWeightPopupEnabled(false);
        cmbLoaiQA.setPreferredSize(new java.awt.Dimension(72, 40));
        cmbLoaiQA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cmbLoaiQAFocusGained(evt);
            }
        });
        cmbLoaiQA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLoaiQAActionPerformed(evt);
            }
        });

        lblSoLuong.setFont(FontConstant.FONT_LABEL);
        lblSoLuong.setText("Số Lượng:");
        lblSoLuong.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblSize.setFont(FontConstant.FONT_LABEL);
        lblSize.setText("Size:");
        lblSize.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cmbSize.setFont(FontConstant.FONT_TEXT);
        cmbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Chọn size", "S", "M", "L", "XL", "XXL", "XXXL"}));
        cmbSize.setEnabled(false);
        cmbSize.setPreferredSize(new java.awt.Dimension(72, 40));
        cmbSize.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbSizeItemStateChanged(evt);
            }
        });
        cmbSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSizeActionPerformed(evt);
            }
        });

        txtThuongHieu.setFont(FontConstant.FONT_TEXT);
        txtThuongHieu.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtThuongHieu.setEnabled(false);
        txtThuongHieu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtThuongHieuFocusLost(evt);
            }
        });
        txtThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThuongHieuActionPerformed(evt);
            }
        });

        lblNhaCungCap.setFont(FontConstant.FONT_LABEL);
        lblNhaCungCap.setText("Nhà Cung Cấp:");
        lblNhaCungCap.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cmbNhaCungCap.setFont(FontConstant.FONT_TEXT);
        cmbNhaCungCap.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Chọn Nhà Cung Cấp", "Chợ Đồng Xuân", "Chợ Bến Thành", "Hạnh Thông Tây"}));
        cmbNhaCungCap.setEnabled(false);
        cmbNhaCungCap.setPreferredSize(new java.awt.Dimension(72, 40));
        cmbNhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNhaCungCapActionPerformed(evt);
            }
        });

        lblThuongHieu.setFont(FontConstant.FONT_LABEL);
        lblThuongHieu.setText("Thương Hiệu:");
        lblThuongHieu.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txtSoLuongQA.setFont(FontConstant.FONT_TEXT);
        txtSoLuongQA.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSoLuongQA.setEnabled(false);
        txtSoLuongQA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSoLuongQAFocusLost(evt);
            }
        });
        txtSoLuongQA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuongQAKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnFieldsLeftLayout = new javax.swing.GroupLayout(pnFieldsLeft);
        pnFieldsLeft.setLayout(pnFieldsLeftLayout);
        pnFieldsLeftLayout.setHorizontalGroup(
                pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnFieldsLeftLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnFieldsLeftLayout.createSequentialGroup()
                                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(lbTenQA, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                                                                .addComponent(lblMaQA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(lblLoaiQA, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtMaQA)
                                                        .addComponent(txtTenQA)
                                                        .addComponent(cmbLoaiQA, 0, 243, Short.MAX_VALUE)))
                                        .addGroup(pnFieldsLeftLayout.createSequentialGroup()
                                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblSoLuong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtThuongHieu, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtSoLuongQA, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(cmbSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cmbNhaCungCap, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        pnFieldsLeftLayout.setVerticalGroup(
                pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnFieldsLeftLayout.createSequentialGroup()
                                .addContainerGap(39, Short.MAX_VALUE)
                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtMaQA, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblMaQA))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTenQA, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbTenQA))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbLoaiQA, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblLoaiQA))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSize))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtSoLuongQA, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSoLuong))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblThuongHieu))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNhaCungCap))
                                .addGap(22, 22, 22))
        );

        pnFields.add(pnFieldsLeft);

        pnFieldsRight.setBackground(ColorConstant.WHITE);
        pnFieldsRight.setPreferredSize(new java.awt.Dimension(420, 0));

        lblGiaNhap.setFont(FontConstant.FONT_LABEL);
        lblGiaNhap.setText("Giá Nhập:");
        lblGiaNhap.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblLoiNhuan.setFont(FontConstant.FONT_LABEL);
        lblLoiNhuan.setText("Lợi Nhuận:");
        lblLoiNhuan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        lblGiaBan.setFont(FontConstant.FONT_LABEL);
        lblGiaBan.setText("Giá Bán:");
        lblGiaBan.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txtGiaBan.setBackground(ColorConstant.DISABLE_FIELD);
        txtGiaBan.setFont(FontConstant.FONT_TEXT);
        txtGiaBan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGiaBan.setEnabled(false);
        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });

        txtLoiNhuan.setFont(FontConstant.FONT_TEXT);
        txtLoiNhuan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtLoiNhuan.setEnabled(false);
        txtLoiNhuan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLoiNhuanFocusLost(evt);
            }
        });
        txtLoiNhuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLoiNhuanKeyReleased(evt);
            }
        });

        txtGiaNhap.setFont(FontConstant.FONT_TEXT);
        txtGiaNhap.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtGiaNhap.setEnabled(false);
        txtGiaNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGiaNhapFocusLost(evt);
            }
        });
        txtGiaNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGiaNhapKeyReleased(evt);
            }
        });

        lblTrangThai.setFont(FontConstant.FONT_LABEL);
        lblTrangThai.setText("Trạng Thái:");
        lblTrangThai.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cmbTrangThai.setFont(FontConstant.FONT_TEXT);
        cmbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Chọn Trạng Thái", "Còn Kinh Doanh", "Dừng Kinh Doanh"}));
        cmbTrangThai.setEnabled(false);
        cmbTrangThai.setPreferredSize(new java.awt.Dimension(72, 40));
        cmbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnFieldsRightLayout = new javax.swing.GroupLayout(pnFieldsRight);
        pnFieldsRight.setLayout(pnFieldsRightLayout);
        pnFieldsRightLayout.setHorizontalGroup(
                pnFieldsRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnFieldsRightLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(pnFieldsRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblLoiNhuan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addComponent(lblGiaNhap, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnFieldsRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbTrangThai, 0, 263, Short.MAX_VALUE)
                                        .addComponent(txtLoiNhuan))
                                .addContainerGap())
        );
        pnFieldsRightLayout.setVerticalGroup(
                pnFieldsRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnFieldsRightLayout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(pnFieldsRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGiaNhap))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtLoiNhuan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblLoiNhuan, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblGiaBan))
                                .addGap(18, 18, 18)
                                .addGroup(pnFieldsRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(cmbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTrangThai))
                                .addContainerGap(173, Short.MAX_VALUE))
        );

        pnFields.add(pnFieldsRight);

        pnControl.setBackground(ColorConstant.WHITE);

        btnXoaTrang.setBackground(ColorConstant.BACKGROUND_TABLEHEADER);
        btnXoaTrang.setFont(FontConstant.FONT_BUTTON);
        btnXoaTrang.setForeground(ColorConstant.WHITE);
        btnXoaTrang.setText("Xóa Trắng");
        btnXoaTrang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaTrang.setMaximumSize(new java.awt.Dimension(200, 50));
        btnXoaTrang.setMinimumSize(new java.awt.Dimension(200, 50));
        btnXoaTrang.setPreferredSize(new java.awt.Dimension(200, 50));
        btnXoaTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTrangActionPerformed(evt);
            }
        });

        btnThemMoi.setBackground(ColorConstant.BACKGROUND_TABLEHEADER);
        btnThemMoi.setFont(FontConstant.FONT_BUTTON);
        btnThemMoi.setForeground(ColorConstant.WHITE);
        btnThemMoi.setText("Thêm Mới");
        btnThemMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThemMoi.setMaximumSize(new java.awt.Dimension(200, 50));
        btnThemMoi.setMinimumSize(new java.awt.Dimension(200, 50));
        btnThemMoi.setPreferredSize(new java.awt.Dimension(200, 50));
        btnThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMoiActionPerformed(evt);
            }
        });

        btnLuu.setBackground(ColorConstant.BACKGROUND_TABLEHEADER);
        btnLuu.setFont(FontConstant.FONT_BUTTON);
        btnLuu.setForeground(ColorConstant.WHITE);
        btnLuu.setText("Lưu");
        btnLuu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLuu.setMaximumSize(new java.awt.Dimension(200, 50));
        btnLuu.setMinimumSize(new java.awt.Dimension(200, 50));
        btnLuu.setPreferredSize(new java.awt.Dimension(200, 50));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnCapNhat.setBackground(ColorConstant.BACKGROUND_TABLEHEADER);
        btnCapNhat.setFont(FontConstant.FONT_BUTTON);
        btnCapNhat.setForeground(ColorConstant.WHITE);
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhat.setMaximumSize(new java.awt.Dimension(200, 50));
        btnCapNhat.setMinimumSize(new java.awt.Dimension(200, 50));
        btnCapNhat.setPreferredSize(new java.awt.Dimension(200, 50));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnHuy.setBackground(ColorConstant.BACKGROUND_TABLEHEADER);
        btnHuy.setFont(FontConstant.FONT_BUTTON);
        btnHuy.setForeground(ColorConstant.WHITE);
        btnHuy.setText("Hủy");
        btnHuy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnHuy.setMaximumSize(new java.awt.Dimension(200, 50));
        btnHuy.setMinimumSize(new java.awt.Dimension(200, 50));
        btnHuy.setPreferredSize(new java.awt.Dimension(200, 50));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnNhapHang.setBackground(ColorConstant.BACKGROUND_TABLEHEADER);
        btnNhapHang.setFont(FontConstant.FONT_BUTTON);
        btnNhapHang.setForeground(ColorConstant.WHITE);
        btnNhapHang.setText("Nhập Hàng");
        btnNhapHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNhapHang.setMaximumSize(new java.awt.Dimension(200, 50));
        btnNhapHang.setMinimumSize(new java.awt.Dimension(200, 50));
        btnNhapHang.setPreferredSize(new java.awt.Dimension(200, 50));
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnControlLayout = new javax.swing.GroupLayout(pnControl);
        pnControl.setLayout(pnControlLayout);
        pnControlLayout.setHorizontalGroup(
                pnControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnControlLayout.createSequentialGroup()
                                .addContainerGap(14, Short.MAX_VALUE)
                                .addGroup(pnControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14))
        );
        pnControlLayout.setVerticalGroup(
                pnControlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnControlLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btnNhapHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoaTrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(btnThemMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnInformationFieldsLayout = new javax.swing.GroupLayout(pnInformationFields);
        pnInformationFields.setLayout(pnInformationFieldsLayout);
        pnInformationFieldsLayout.setHorizontalGroup(
                pnInformationFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnInformationFieldsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pnImgUpLoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnFields, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );
        pnInformationFieldsLayout.setVerticalGroup(
                pnInformationFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnInformationFieldsLayout.createSequentialGroup()
                                .addGroup(pnInformationFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pnControl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnInformationFieldsLayout.createSequentialGroup()
                                                .addGroup(pnInformationFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(pnFields, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(pnImgUpLoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(3, 3, 3))
        );

        tblQuanAo.setAutoCreateRowSorter(true);
        tblQuanAo.setBackground(ColorConstant.WHITE);
        tblQuanAo.setFont(FontConstant.FONT_TEXT);
        tblQuanAo.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Mã QA", "Tên QA", "Loại QA", "Size", "Số Lượng", "Thương Hiệu", "NCC", "Giá Nhập", "Lợi Nhuận", "Giá Bán", "Trạng Thái"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblQuanAo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblQuanAo.setFillsViewportHeight(true);
        tblQuanAo.setRowHeight(30);
        tblQuanAo.setSelectionBackground(new java.awt.Color(102, 153, 255));
        tblQuanAo.setSelectionForeground(new java.awt.Color(204, 204, 204));
        tblQuanAo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblQuanAo.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblQuanAo.setShowGrid(true);
        tblQuanAo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQuanAoMouseClicked(evt);
            }
        });
        scrQuanAo.setViewportView(tblQuanAo);
        if (tblQuanAo.getColumnModel().getColumnCount() > 0) {
            tblQuanAo.getColumnModel().getColumn(0).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(1).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(2).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(3).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblQuanAo.getColumnModel().getColumn(4).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(4).setPreferredWidth(40);
            tblQuanAo.getColumnModel().getColumn(5).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(6).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(7).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(8).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(9).setResizable(false);
            tblQuanAo.getColumnModel().getColumn(10).setResizable(false);
        }

        lblTitleQA1.setFont(org.group06.utils.FontConstant.FONT_HEADER);
        lblTitleQA1.setForeground(org.group06.utils.ColorConstant.BLACK);
        lblTitleQA1.setText("Thông Tin Quần Áo");
        lblTitleQA1.setMaximumSize(new java.awt.Dimension(103, 60));
        lblTitleQA1.setMinimumSize(new java.awt.Dimension(103, 60));
        lblTitleQA1.setPreferredSize(new java.awt.Dimension(103, 60));

        txtTimKiem.setFont(FontConstant.FONT_TEXT);
        txtTimKiem.setEnabled(true);
        txtTimKiem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTimKiemFocusLost(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }

            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        cmbTimKiemQATheoTieuChi.setFont(FontConstant.FONT_TEXT);
        cmbTimKiemQATheoTieuChi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Chọn Tiêu Chí Tìm Kiếm", "Tìm Theo Mã", "Tìm Theo Tên", "Tìm Theo Thương Hiệu"}));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnInformationFields, javax.swing.GroupLayout.DEFAULT_SIZE, 1480, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrQuanAo)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(835, 835, 835)
                                                .addComponent(lblTimKiemQA, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTimKiem)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cmbTimKiemQATheoTieuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(lblTitleQA1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(995, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblTimKiemQA, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbTimKiemQATheoTieuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnInformationFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrQuanAo, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(lblTitleQA1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(935, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Thiết lập trạng thái cho các button khi bắt đầu tất cả điều disable trừ btnThemMoi
     */
    public void setStatusAllBtnsStart() {
        java.util.List<JButton> listBtn = java.util.Arrays.asList(this.btnHuy, this.btnXoaTrang, this.btnCapNhat, this.btnUploadImg, this.btnLuu);
        ComponentStatus.setStatusBtn(listBtn, false);
        ComponentStatus.setStatusBtn(this.btnThemMoi, true);
    }

    /**
     * @param status trạng thái của các fields muốn thiết lập
     */
    public void setAllField(Boolean status) {
        java.util.List<JTextField> listTxt = java.util.Arrays.asList(this.txtTenQA, this.txtSoLuongQA,
                this.txtThuongHieu, this.txtGiaNhap, this.txtLoiNhuan, this.txtGiaBan);
        java.util.List<JTextField> listStatusTxt = java.util.Arrays.asList(this.txtTenQA, this.txtSoLuongQA,
                this.txtThuongHieu, this.txtGiaNhap, this.txtLoiNhuan);
        java.util.List<JComboBox> listCmb = java.util.Arrays.asList(this.cmbLoaiQA, this.cmbSize, this.cmbNhaCungCap, this.cmbTrangThai);
//        Xóa trắng fields
        ComponentStatus.emptyField(listTxt);
//        Thiết lập trạng thái của fields enable hoặc disable
        ComponentStatus.setFieldStatus(listStatusTxt, status);
//        Thiết lập combobox mặc định về index 0
        ComponentStatus.setDefaultCmb(listCmb);
//        Thiết lập trạng thái của combobox enable hoặc disable
        ComponentStatus.setComboBoxStatus(listCmb, status);
    }

    /**
     * Thiết lập các field khi update
     */
    public void setFieldUpdate() {
        java.util.List<JTextField> listTxt = java.util.Arrays.asList(this.txtTenQA, this.txtSoLuongQA,
                this.txtThuongHieu, this.txtGiaNhap, this.txtLoiNhuan, this.txtGiaBan);
        java.util.List<JTextField> listStatusTxt = java.util.Arrays.asList(this.txtTenQA, this.txtSoLuongQA,
                this.txtThuongHieu, this.txtGiaNhap, this.txtLoiNhuan);
        java.util.List<JComboBox> listCmb = java.util.Arrays.asList(this.cmbLoaiQA, this.cmbSize, this.cmbNhaCungCap, this.cmbTrangThai);
//        xóa trắng các fields
        ComponentStatus.emptyField(listTxt);
//        Thiết lập trạng thái của các fields được enable
        ComponentStatus.setFieldStatus(listStatusTxt, true);
//        Thiết lập mặc định cho các combobox
        ComponentStatus.setDefaultCmb(listCmb);
//        Thiết lập trạng thái của các combobox được enable
        ComponentStatus.setComboBoxStatus(listCmb, true);
    }

    /**
     * Tính toán giá bán
     *
     * @param giaNhap  là giá gốc quần áo được nhập vào với đơn vị tiền tệ là VNĐ
     * @param loiNhuan là phần trăm lợi nhuận mà cửa hàng muốn thu về đơn vị %
     * @return giá bán của quần áo = giá nhập + giá nhập * (lợi nhuận / 100)
     */
    public String tinhGiaBan(String giaNhap, String loiNhuan) {
        Double giaBan = NumberStandard.parseDouble(giaNhap) + NumberStandard.parseDouble(giaNhap) * NumberStandard.parsePercent(loiNhuan) / 100;
        return NumberStandard.formatMoney(giaBan);
    }

    /**
     * @param text chuỗi được chuyền vào field tên
     * @return mã quần áo của quần áo cần thêm mới
     */
    public String taoMaQuanAo(String text) {
        String resultFormat = "";
        String trimText = text.trim();
        String lowerText = trimText.toLowerCase();
        ArrayList<String> splitResult = new ArrayList<>();
//        Tách chuỗi thành mảng các từ của chuỗi
        String[] splitText = lowerText.split(" ");
        for (int i = 0; i < splitText.length; i++) {
//            Kiểm tra nếu phần tử trong mảng là từ khác khoảng trắng thì từ đó được thêm vào mảng splitResult
            if (!splitText[i].isEmpty()) {
                splitResult.add(splitText[i]);
            }
        }
        for (String item : splitResult) {
//            lặp qua các phần tử là từ của chuỗi trong splitResult ta lấy ký tự đầu tiên của từ và In Hoa Ký tự đó lên
            resultFormat += item.substring(0, 1).toUpperCase();
        }
//        Loại bỏ dấu tiếng việt
        String resultName = NameStandard.removeDiacritics(resultFormat.trim());
//        Định dạng lượng số
        NumberFormat nf = new DecimalFormat("0000");
        int number = 1;
        String formattedNumber = nf.format(number);
        String code = ""; // code là mã của quần áo có được qua việc nối các chuỗi của resultName + formattedNumber + kích thước quần áo đã chọn
        code = resultName + "-" + formattedNumber + "-" + this.cmbSize.getItemAt(this.cmbSize.getSelectedIndex());

        if (this.tblQuanAo.getModel().getRowCount() >= 1) {
//            Lấy toàn bộ các mã quần áo có trong table quần áo đã load dữ liệu lên để kiểm tra trùng
            ArrayList<String> arrayCode = new ArrayList<>();
            for (int i = 0; i < this.tblQuanAo.getModel().getRowCount(); i++) {
                arrayCode.add(this.tblQuanAo.getValueAt(i, 0).toString());
            }
//            nếu trùng thì tăng number
            for (String item : arrayCode) {
                if (code.contains(item)) {
                    number++;
                    code = resultName + "-" + nf.format(number) + "-" + this.cmbSize.getItemAt(this.cmbSize.getSelectedIndex());
                } else {
                    number = 1;
                }
            }
        }
        return code;
    }

    private void btnUploadImgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImgActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn hình ảnh quần áo");

//        Xử lý định dạng các file hình ảnh hợp lệ
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".png") || filename.endsWith(".jpg") || filename.endsWith(".jpeg");
                }
            }

            @Override
            public String getDescription() {
                return "Image";
            }
        });
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                file = chooser.getSelectedFile();
//                Load hình ảnh
                loadImage(file.toURI().toURL());
            } catch (MalformedURLException ex) {
                Logger.getLogger(PanelQuanAo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PanelQuanAo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnUploadImgActionPerformed

    private void btnXoaTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTrangActionPerformed
        if (this.statusBtnCapNhat == false && this.statusBtnThemMoi == true) {
//            Trường hợp chọn nút xóa trắng khi đang thêm mới quần áo
            this.tblQuanAo.clearSelection();
//            loadImage(ImagePath.UPLOAD_IMG);
            setAllField(true);
            this.txtMaQA.setText("");
        } else if (this.statusBtnCapNhat == true && this.statusBtnThemMoi == false) {
//            Trường hợp chọn nút xóa trắng khi đang cập nhật quần áo
            setFieldUpdate();
        }

        this.txtTenQA.requestFocus();
    }//GEN-LAST:event_btnXoaTrangActionPerformed

    private void btnThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMoiActionPerformed
        setAllField(true);
//        Thiết lập trạng thái của các button
        java.util.List<JButton> listBtnDisable = java.util.Arrays.asList(this.btnThemMoi, this.btnCapNhat);
        java.util.List<JButton> listBtnEnable = java.util.Arrays.asList(this.btnUploadImg, this.btnXoaTrang, this.btnLuu, this.btnHuy);
        ComponentStatus.setStatusBtn(listBtnDisable, false);
        ComponentStatus.setStatusBtn(listBtnEnable, true);
        this.statusBtnThemMoi = true;
        this.btnNhapHang.setEnabled(false);
        this.statusBtnCapNhat = false;
        this.statusBtnHuy = true;
        this.txtTenQA.requestFocus();
        this.cmbTrangThai.setSelectedIndex(1);
    }//GEN-LAST:event_btnThemMoiActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (this.txtTenQA.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên quần áo!");
            this.txtTenQA.requestFocus();
        } else if (this.cmbLoaiQA.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại quần áo!");
            ComponentStatus.CheckSelectOption(this.cmbLoaiQA);
        } else if (this.cmbSize.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn size!");
            ComponentStatus.CheckSelectOption(this.cmbSize);
        } else if (this.txtMaQA.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng không để trống field này! Hãy kiểm tra Tên quần áo và size đã chọn chưa!");
            this.txtTenQA.requestFocus();
        } else if (this.txtSoLuongQA.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng quần áo!");
            this.txtSoLuongQA.requestFocus();
        } else if (this.txtThuongHieu.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên thương hiệu!");
            this.txtThuongHieu.requestFocus();
        } else if (this.cmbNhaCungCap.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhà cung cấp!");
            ComponentStatus.CheckSelectOption(this.cmbNhaCungCap);
        } else if (this.txtGiaNhap.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập vào giá nhập quần áo!");
            this.txtGiaNhap.requestFocus();
        } else if (this.txtLoiNhuan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập vào lợi nhuận!");
            this.txtLoiNhuan.requestFocus();
        } else if (this.txtGiaBan.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng không để trống field này! Hãy kiểm tra giá nhập và lợi nhuận đã nhập chưa!");
            this.txtGiaBan.requestFocus();
        } else if (this.cmbTrangThai.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái kinh doanh!");
            ComponentStatus.CheckSelectOption(this.cmbTrangThai);
        } else if (file == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hình ảnh quần áo");
        } else {
            if (JOptionPane.showConfirmDialog(null, "Bạn chắn chắn lưu dữ liệu này?", "Xác nhận hành động", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                if (this.statusBtnCapNhat == true) {
                    viTri = this.tblQuanAo.getSelectedRow();
//                Lấy quần áo cần cập nhật
                    qaCapNhat = new DAO_QuanAo(DatabaseConstant.getConnection()).getByID(this.txtMaQA.getText());
//                Tên quần áo
                    String tenQuanAo = NameStandard.formatCapitalize(this.txtTenQA.getText());
                    this.qaCapNhat.setTenQA(tenQuanAo);
                    this.tblQuanAo.getModel().setValueAt(tenQuanAo, viTri, 1);
//                Loại quần áo
                    int loaiQAIndex = this.cmbLoaiQA.getSelectedIndex();
                    String loaiQA = this.cmbLoaiQA.getItemAt(loaiQAIndex);
                    for (Map.Entry<String, String> item : loaiQuanAo.entrySet()) {
                        String tenLoaiQuanAo = item.getValue();
                        if (tenLoaiQuanAo.equals(loaiQA)) {
                            this.qaCapNhat.setLoaiQuanAo(item.getKey());
                            this.tblQuanAo.getModel().setValueAt(tenLoaiQuanAo, viTri, 2);
                        }
                    }
//                Size quần áo
                    int size = this.cmbSize.getSelectedIndex();
                    String sizeQuanAo = this.cmbSize.getItemAt(size);

                    for (HashMap.Entry<String, String> item : dsKichThuocQA.entrySet()) {
                        if (sizeQuanAo.equals(item.getValue())) {
                            qaCapNhat.setMaKichThuoc(item.getKey());
                            break;
                        }
                    }


                    this.tblQuanAo.getModel().setValueAt(sizeQuanAo, viTri, 3);
//                Số lượng quần áo
                    int soLuongQuanAo = NumberStandard.parseInt(this.txtSoLuongQA.getText());
                    qaCapNhat.setSoLuong(soLuongQuanAo);
                    this.tblQuanAo.getModel().setValueAt(this.txtSoLuongQA.getText(), viTri, 4);
//                Tên thương hiệu
                    String tenThuongHieu = this.txtThuongHieu.getText();
                    qaCapNhat.setThuongHieu(tenThuongHieu);
                    this.tblQuanAo.getModel().setValueAt(tenThuongHieu, viTri, 5);
//                Mã nhà cung cấp
                    int nhaCungCap = this.cmbNhaCungCap.getSelectedIndex();
                    for (NhaCungCap ncc : dsNCC) {
                        if (ncc.getTenNCC().equalsIgnoreCase(this.cmbNhaCungCap.getItemAt(nhaCungCap))) {
                            qaCapNhat.setNhaCungCap(ncc);
                            this.tblQuanAo.getModel().setValueAt(ncc.getTenNCC(), viTri, 6);
                        }
                    }
//                Giá nhập quần áo
                    double giaNhap = NumberStandard.parseMoney(this.txtGiaNhap.getText());
                    qaCapNhat.setGiaNhap(giaNhap);

                    this.tblQuanAo.getModel().setValueAt(NumberStandard.formatMoney(giaNhap), viTri, 7);
//                Lợi nhuận của quần áo
                    double loiNhuan = NumberStandard.parsePercent(this.txtLoiNhuan.getText());
                    qaCapNhat.setLoiNhuan(loiNhuan);
                    this.tblQuanAo.getModel().setValueAt(NumberStandard.formatPercent(loiNhuan), viTri, 8);
                    this.tblQuanAo.getModel().setValueAt(this.txtGiaBan.getText().trim(), viTri, 9);
//                Trạng thái sản phẩm 1: true là đang kinh doanh; 2: false là dừng kinh doanh
                    int trangThai = this.cmbTrangThai.getSelectedIndex();
                    if (trangThai == 1) {
                        qaCapNhat.setTrangThai(true);
                        this.tblQuanAo.getModel().setValueAt("Còn Kinh Doanh", viTri, 10);
                    } else if (trangThai == 2) {
                        qaCapNhat.setTrangThai(false);
                        this.tblQuanAo.getModel().setValueAt("Dừng Kinh Doanh", viTri, 10);
                    }

//                    Xử lý lấy hình ảnh mới cần cập nhật
                    if (file != null) {
                        String urlHinhAnh = file.getPath();
                        qaCapNhat.setHinhAnh(urlHinhAnh);
                    }
//                    Cập nhật quần áo vào cơ sở dữ liệu
                    DAO_QuanAo updateQuanAo = new DAO_QuanAo(DatabaseConstant.getConnection());
                    if (updateQuanAo.update(qaCapNhat) && dsQuanLyQuanAo.update(qaCapNhat)) {
                        System.out.println("Cập nhật thành công");
                    }

                    this.tblQuanAo.clearSelection();
                    file = null;
                } else if (this.statusBtnThemMoi == true && this.statusBtnCapNhat == false) {
//                Tên quần áo
                    String tenQuanAo = NameStandard.formatCapitalize(this.txtTenQA.getText());
                    this.qaThemMoi.setTenQA(tenQuanAo);
//                Loại quần áo
                    int loaiQAIndex = this.cmbLoaiQA.getSelectedIndex();
                    String loaiQA = this.cmbLoaiQA.getItemAt(loaiQAIndex);
                    String tenLoaiQA = "";
                    for (Map.Entry<String, String> item : loaiQuanAo.entrySet()) {
                        String tenLQA = item.getValue();
                        if (tenLQA.equals(loaiQA)) {
                            this.qaThemMoi.setLoaiQuanAo(item.getKey());
                            tenLoaiQA = item.getValue();
                        }
                    }
//                Size quần áo
                    int size = this.cmbSize.getSelectedIndex();
                    String sizeQuanAo = this.cmbSize.getItemAt(size);
                    for (HashMap.Entry<String, String> item : dsKichThuocQA.entrySet()) {
                        if (sizeQuanAo.equals(item.getValue())) {
                            qaThemMoi.setMaKichThuoc(item.getKey());
                            break;
                        }
                    }
//                Mã quần áo
                    String maQuanAo = this.txtMaQA.getText().trim();
                    qaThemMoi.setMaQA(maQuanAo);
//                Số lượng quần áo
                    int soLuongQuanAo = NumberStandard.parseInt(this.txtSoLuongQA.getText());
                    qaThemMoi.setSoLuong(soLuongQuanAo);
//                Tên thương hiệu
                    String tenThuongHieu = this.txtThuongHieu.getText();
                    qaThemMoi.setThuongHieu(tenThuongHieu);
//                Mã nhà cung cấp
                    int nhaCungCap = this.cmbNhaCungCap.getSelectedIndex();
                    String tenNhaCungCap = "";
                    for (NhaCungCap ncc : dsNCC) {
                        if (ncc.getTenNCC().equalsIgnoreCase(this.cmbNhaCungCap.getItemAt(nhaCungCap))) {
                            qaThemMoi.setNhaCungCap(ncc);
                            tenNhaCungCap = ncc.getTenNCC();
                        }
                    }
//                Giá nhập quần áo
                    String regexGiaNhap = "^*VNĐ+$";
                    Pattern patternGiaNhap = Pattern.compile(regexGiaNhap);
                    Matcher matcherGiaNhap = patternGiaNhap.matcher(this.txtGiaNhap.getText().trim());
                    double giaNhap = 0;
                    String giaNhapFormat = "";
                    if (matcherGiaNhap.find()) {
                        giaNhap = NumberStandard.parseMoney(this.txtGiaNhap.getText().trim());
                        qaThemMoi.setGiaNhap(giaNhap);
                        giaNhapFormat = NumberStandard.formatMoney(giaNhap);
                    } else {
                        giaNhap = NumberStandard.parseDouble(this.txtGiaNhap.getText().trim());
                        qaThemMoi.setGiaNhap(giaNhap);
                        giaNhapFormat = NumberStandard.formatMoney(giaNhap);
                    }
//                Lợi nhuận của quần áo
                    String regexLoiNhuan = "^*%+$";
                    Pattern patternLoiNhuan = Pattern.compile(regexLoiNhuan);
                    Matcher matcherLoiNhuan = patternLoiNhuan.matcher(this.txtLoiNhuan.getText());
                    double loiNhuan = 0;
                    String loiNhuanFormat = "";
                    if (matcherLoiNhuan.find()) {
                        loiNhuan = NumberStandard.parsePercent(this.txtLoiNhuan.getText());
                        qaThemMoi.setLoiNhuan(loiNhuan);
                        loiNhuanFormat = NumberStandard.formatPercent(loiNhuan);
                    } else {
                        loiNhuan = NumberStandard.parsePercent(this.txtLoiNhuan.getText().trim());
                        qaThemMoi.setLoiNhuan(loiNhuan);
                        loiNhuanFormat = NumberStandard.formatPercent(loiNhuan);
                    }
//                Giá bán của quần áo
                    String giaBan = this.txtGiaBan.getText();
//                Trạng thái sản phẩm 1: true là đang kinh doanh; 2: false là dừng kinh doanh
                    int trangThai = this.cmbTrangThai.getSelectedIndex();
                    String tenTrangThai = "";
                    if (trangThai == 1) {
                        qaThemMoi.setTrangThai(true);
                        tenTrangThai = "Còn Kinh Doanh";
                    } else if (trangThai == 2) {
                        qaThemMoi.setTrangThai(false);
                        tenTrangThai = "Dừng Kinh Doanh";
                    }
                    Object[] row = {maQuanAo, tenQuanAo, tenLoaiQA, sizeQuanAo, soLuongQuanAo, tenThuongHieu,
                            tenNhaCungCap, giaNhapFormat, loiNhuanFormat, giaBan, tenTrangThai};
                    DefaultTableModel model = (DefaultTableModel) this.tblQuanAo.getModel();
                    model.addRow(row);
//                    Xử lý lấy hình ảnh quần áo thêm mới
//                    Xử lý lấy hình ảnh mới cần cập nhật
                    if (file != null) {
                        String urlHinhAnh = file.getPath();
                        qaThemMoi.setHinhAnh(urlHinhAnh);
                    } else if (file == null) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn hình ảnh quần áo");
                    }
                    dsQA.add(qaThemMoi);
//                Lưu quần áo vào cơ sở dữ liệu
                    DAO_QuanAo addQuanAo = new DAO_QuanAo(DatabaseConstant.getConnection());
                    if (addQuanAo.add(qaThemMoi) && dsQuanLyQuanAo.add(qaThemMoi)) {
                        System.out.println("Thêm mới thành công");
                    }
                }

                this.statusBtnThemMoi = false;
                this.statusBtnCapNhat = false;
                this.statusBtnHuy = false;
                this.btnNhapHang.setEnabled(true);
                xoaTrang();
                ComponentStatus.setStatusBtn(this.btnXoaTrang, false);
                ComponentStatus.setStatusBtn(this.btnLuu, false);
                ComponentStatus.setStatusBtn(this.btnHuy, false);
                ComponentStatus.setStatusBtn(this.btnThemMoi, true);
                ComponentStatus.setStatusBtn(this.btnUploadImg, false);
                loadImage(ImagePath.UPLOAD_IMG);
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        java.util.List<JTextField> listTxt = java.util.Arrays.asList(this.txtTenQA, this.txtSoLuongQA, this.txtThuongHieu, this.txtGiaNhap, this.txtLoiNhuan);
        java.util.List<JComboBox> listCmb = java.util.Arrays.asList(this.cmbLoaiQA, this.cmbSize, this.cmbNhaCungCap, this.cmbTrangThai);
        if (JOptionPane.showConfirmDialog(null, "Bạn chắn chắn cập nhật?", "Xác nhận hành động", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            this.txtTenQA.requestFocus();
            this.txtTenQA.selectAll();
//            Set buttons
            ComponentStatus.setStatusBtn(this.btnCapNhat, false);
            ComponentStatus.setStatusBtn(this.btnXoaTrang, true);
            ComponentStatus.setStatusBtn(this.btnUploadImg, true);
            ComponentStatus.setStatusBtn(this.btnLuu, true);
//            Set fields
            ComponentStatus.setFieldStatus(listTxt, true);
//            Set combobox
            ComponentStatus.setComboBoxStatus(listCmb, true);
            this.statusBtnThemMoi = true;
            this.statusBtnCapNhat = true;
            this.statusBtnHuy = true;
            int viTri = tblQuanAo.getSelectedRow();
            String ma = tblQuanAo.getModel().getValueAt(viTri, 0).toString();
            QuanAo qa = this.dsQuanLyQuanAo.getByID(ma);
            this.file = new File(qa.getHinhAnh());
            this.btnNhapHang.setEnabled(false);
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn chắn chắn hủy?", "Xác nhận hành động", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            setAllField(false);
            setStatusAllBtnsStart();
            ComponentStatus.emptyField(this.txtMaQA);
            file = null;
//            Set image default
            loadImage(ImagePath.UPLOAD_IMG);
            this.statusBtnThemMoi = false;
            this.statusBtnCapNhat = false;
            this.tblQuanAo.clearSelection();
            this.statusBtnHuy = true;
            this.btnNhapHang.setEnabled(true);
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void cmbLoaiQAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLoaiQAActionPerformed
        ComponentStatus.CheckSelectOption(this.cmbLoaiQA);
    }//GEN-LAST:event_cmbLoaiQAActionPerformed

    private void txtThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThuongHieuActionPerformed

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    //    Load dữ liệu lên fields
    private void tblQuanAoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQuanAoMouseClicked
        java.util.List<JButton> listBtnEnable = java.util.Arrays.asList(this.btnHuy, this.btnCapNhat);
        ComponentStatus.setStatusBtn(listBtnEnable, true);
        ComponentStatus.setStatusBtn(this.btnXoaTrang, false);
        ComponentStatus.setStatusBtn(this.btnThemMoi, false);
        ComponentStatus.setStatusBtn(this.btnLuu, false);
        setAllField(false);

        this.viTri = this.tblQuanAo.getSelectedRow();
//      Xử lý đổ dữ liệu từ table lên fields
        this.txtMaQA.setText(tblQuanAo.getValueAt(viTri, 0).toString());
        this.txtTenQA.setText(tblQuanAo.getValueAt(viTri, 1).toString());
//        Xử lý lấy loại quần áo
        for (Map.Entry<String, String> item : loaiQuanAo.entrySet()) {
            if (tblQuanAo.getValueAt(viTri, 2).toString().equalsIgnoreCase(item.getValue())) {
                this.cmbLoaiQA.setSelectedItem(item.getValue());
            }
        }
//        Xử lý lấy size
        for (int i = 0; i < this.cmbSize.getItemCount(); i++) {
            if (tblQuanAo.getValueAt(viTri, 3).toString().equalsIgnoreCase(this.cmbSize.getItemAt(i))) {
                this.cmbSize.setSelectedIndex(i);
            }
        }
        this.txtSoLuongQA.setText(tblQuanAo.getValueAt(viTri, 4).toString());
        this.txtThuongHieu.setText(tblQuanAo.getValueAt(viTri, 5).toString());
//        Xử lý lấy nhà cung cấp
        for (int i = 0; i < this.cmbNhaCungCap.getItemCount(); i++) {
            if (tblQuanAo.getValueAt(viTri, 6).toString().equalsIgnoreCase(this.cmbNhaCungCap.getItemAt(i))) {
                this.cmbNhaCungCap.setSelectedIndex(i);
            }
        }
        this.txtGiaNhap.setText(NumberStandard.parseMoney(tblQuanAo.getValueAt(viTri, 7).toString()) + "");
        this.txtLoiNhuan.setText(NumberStandard.parsePercent(tblQuanAo.getValueAt(viTri, 8).toString()) + "");
        this.txtGiaBan.setText(tblQuanAo.getValueAt(viTri, 9).toString());
//        Xử lý lấy trạng thái của quần áo
        for (int i = 0; i < this.cmbTrangThai.getItemCount(); i++) {
            if (tblQuanAo.getValueAt(viTri, 10).toString().equalsIgnoreCase(this.cmbTrangThai.getItemAt(i))) {
                this.cmbTrangThai.setSelectedIndex(i);
            }
        }
        file = null;
//        Xử lý lấy hình ảnh theo dòng dữ liệu
        loadImgWithRowData(tblQuanAo.getValueAt(viTri, 0).toString());
        this.statusBtnHuy = true;

    }//GEN-LAST:event_tblQuanAoMouseClicked

    private void txtTenQAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenQAFocusLost
        String tenQuanAo = this.txtTenQA.getText();
        this.txtTenQA.setText(NameStandard.formatCapitalize(tenQuanAo));
    }//GEN-LAST:event_txtTenQAFocusLost

    private void txtLoiNhuanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLoiNhuanFocusLost

    }//GEN-LAST:event_txtLoiNhuanFocusLost

    private void cmbSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSizeActionPerformed
        ComponentStatus.CheckSelectOption(this.cmbSize);
    }//GEN-LAST:event_cmbSizeActionPerformed

    private void cmbNhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNhaCungCapActionPerformed
        ComponentStatus.CheckSelectOption(this.cmbNhaCungCap);
    }//GEN-LAST:event_cmbNhaCungCapActionPerformed

    private void cmbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTrangThaiActionPerformed
        ComponentStatus.CheckSelectOption(this.cmbTrangThai);
    }//GEN-LAST:event_cmbTrangThaiActionPerformed

    private void txtSoLuongQAFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongQAFocusLost

    }//GEN-LAST:event_txtSoLuongQAFocusLost

    private void txtThuongHieuFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtThuongHieuFocusLost
        String tenThuongHieu = this.txtThuongHieu.getText().trim();
        this.txtThuongHieu.setText(NameStandard.formatCapitalize(tenThuongHieu));
    }//GEN-LAST:event_txtThuongHieuFocusLost

    private void txtGiaNhapFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaNhapFocusLost

    }//GEN-LAST:event_txtGiaNhapFocusLost

    private void txtTimKiemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTimKiemFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemFocusLost

    //    Xử lý load dữ liệu quần áo tìm kiếm được lên bảng
    private void loadAllTableQA(ArrayList<QuanAo> dsQATimDuoc) {
        DefaultTableModel modelQA = (DefaultTableModel) this.tblQuanAo.getModel();
        modelQA.setRowCount(0);
        String tenLoaiQuanAo = "", kichThuocQuanAo = "";
        for (QuanAo qa : dsQATimDuoc) {
            for (HashMap.Entry<String, String> item : loaiQuanAo.entrySet()) {
                if (qa.getLoaiQuanAo().equalsIgnoreCase(item.getKey())) {
                    tenLoaiQuanAo = item.getValue();
                }
            }
            for (HashMap.Entry<String, String> item : dsKichThuocQA.entrySet()) {
                if (qa.getMaKichThuoc().equalsIgnoreCase(item.getKey())) {
                    kichThuocQuanAo = item.getValue();
                }
            }
            Object[] data = {qa.getMaQA(), qa.getTenQA(), tenLoaiQuanAo, kichThuocQuanAo,
                    qa.getSoLuong(), qa.getThuongHieu(), qa.getNhaCungCap().getTenNCC(),
                    NumberStandard.formatMoney(qa.getGiaNhap()), NumberStandard.formatPercent(qa.getLoiNhuan()),
                    tinhGiaBan(String.valueOf(qa.getGiaNhap()), String.valueOf(qa.getLoiNhuan())),
                    qa.isTrangThai() ? "Còn Kinh Doanh" : "Dừng Kinh Doanh"};
//      Thêm dữ liệu vào table
            modelQA.addRow(data);
        }
    }

    //    Xử lý chọn tiêu chí tìm kiếm
    private void xuLyTimKiemQA() {
        String duLieuTimKiem = this.txtTimKiem.getText().trim();
        int tieuChiTimKiem = this.cmbTimKiemQATheoTieuChi.getSelectedIndex();

        if (duLieuTimKiem.isEmpty() || tieuChiTimKiem == 0) {
            loadDataTable();
        } else {
            ArrayList<QuanAo> dsQATimDuoc = new ArrayList<>();
            if (tieuChiTimKiem == 1) {
                for (QuanAo qa : this.dsQA) {
                    if (qa.getMaQA().contains(duLieuTimKiem)) {
                        dsQATimDuoc.add(qa);
                    }
                }
            } else if (tieuChiTimKiem == 2) {
                for (QuanAo qa : this.dsQA) {
                    if (qa.getTenQA().contains(duLieuTimKiem)) {
                        dsQATimDuoc.add(qa);
                    }
                }
            } else if (tieuChiTimKiem == 3) {
                for (QuanAo qa : this.dsQA) {
                    if (qa.getThuongHieu().contains(duLieuTimKiem)) {
                        dsQATimDuoc.add(qa);
                    }
                }
            }
//        Cập nhật kết quả tìm kiếm lên bảng
            loadAllTableQA(dsQATimDuoc);
        }
    }

    //    Xử lý tìm kiếm quần áo
    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        xuLyTimKiemQA();
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        if (cmbTimKiemQATheoTieuChi.getSelectedIndex() == 1) {
            String timKiem = txtTimKiem.getText();
            txtTimKiem.setText(timKiem.toUpperCase());
        }
        xuLyTimKiemQA();
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cmbLoaiQAFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cmbLoaiQAFocusGained

    }//GEN-LAST:event_cmbLoaiQAFocusGained

    private void cmbSizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbSizeItemStateChanged
        if (this.statusBtnThemMoi == true && this.statusBtnCapNhat == false) {
            String maQA = taoMaQuanAo(this.txtTenQA.getText());
            this.txtMaQA.setText(maQA);
            qaThemMoi.setMaQA(maQA);
        }
        ComponentStatus.CheckSelectOption(this.cmbSize);
    }//GEN-LAST:event_cmbSizeItemStateChanged

    private void txtGiaNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGiaNhapKeyReleased
        if (!txtGiaNhap.getText().trim().matches("^[0-9]+\\.?[0-9]*$")) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng nhập số!");
            this.txtGiaNhap.selectAll();
            this.txtGiaNhap.requestFocus();
        } else {
            if (!this.txtLoiNhuan.getText().isEmpty()) {
                this.txtGiaBan.setText(tinhGiaBan(this.txtGiaNhap.getText(), this.txtLoiNhuan.getText()));
            } else {
                this.txtGiaBan.setText(tinhGiaBan(this.txtGiaNhap.getText(), 0 + ""));
            }
        }
    }//GEN-LAST:event_txtGiaNhapKeyReleased

    private void txtLoiNhuanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoiNhuanKeyReleased
        if (!txtLoiNhuan.getText().isEmpty()) {
            if (!txtLoiNhuan.getText().trim().matches("^[0-9]+\\.?[0-9]*$")) {
                JOptionPane.showMessageDialog(null,
                        "Vui lòng nhập số(sử dụng dấu . với số thực)!");
                this.txtLoiNhuan.selectAll();
                this.txtLoiNhuan.requestFocus();
            } else {
                if (this.txtGiaNhap.getText().isEmpty()) {
                    this.txtGiaBan.setText(tinhGiaBan(0 + "", this.txtLoiNhuan.getText()));
                } else {
                    this.txtGiaBan.setText(tinhGiaBan(this.txtGiaNhap.getText(), this.txtLoiNhuan.getText()));
                }
            }
        }
    }//GEN-LAST:event_txtLoiNhuanKeyReleased

    private void txtSoLuongQAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuongQAKeyReleased
        if (!txtSoLuongQA.getText().trim().matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng nhập số nguyên dương!");
            this.txtSoLuongQA.selectAll();
            this.txtSoLuongQA.requestFocus();
        }
    }//GEN-LAST:event_txtSoLuongQAKeyReleased

    private void txtTenQAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenQAKeyReleased
        if (this.statusBtnThemMoi == true && this.statusBtnCapNhat == false) {
            String tenQuanAo = this.txtTenQA.getText();
            this.txtMaQA.setText(taoMaQuanAo(tenQuanAo));
        }
    }//GEN-LAST:event_txtTenQAKeyReleased

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chọn file excel thông tin quần áo cần nhập hàng");

//        Xử lý định dạng các file hình ảnh hợp lệ
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".xlsx") || filename.endsWith(".XLS");
                }
            }

            @Override
            public String getDescription() {
                return "FileExcel";
            }
        });
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

            try {
                file = chooser.getSelectedFile();
//                load file excel
                ArrayList<QuanAo> dsQAExcel = ExcelReader.readExcel(file);

                WinKiemTraNhapHang winKiemTraNhapHang = new WinKiemTraNhapHang(this.dsQuanLyQuanAo, dsQAExcel);
                winKiemTraNhapHang.setVisible(true);
                loadAllTableQA(dsQuanLyQuanAo.getAll());
            } catch (MalformedURLException ex) {
                Logger.getLogger(PanelQuanAo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PanelQuanAo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnNhapHangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JButton btnThemMoi;
    private javax.swing.JButton btnUploadImg;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> cmbLoaiQA;
    private javax.swing.JComboBox<String> cmbNhaCungCap;
    private javax.swing.JComboBox<String> cmbSize;
    private javax.swing.JComboBox<String> cmbTimKiemQATheoTieuChi;
    private javax.swing.JComboBox<String> cmbTrangThai;
    private javax.swing.JLabel lbTenQA;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaNhap;
    private javax.swing.JLabel lblLoaiQA;
    private javax.swing.JLabel lblLoiNhuan;
    private javax.swing.JLabel lblMaQA;
    private javax.swing.JLabel lblNhaCungCap;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblThuongHieu;
    private javax.swing.JLabel lblTimKiemQA;
    private javax.swing.JLabel lblTitleQA1;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnControl;
    private javax.swing.JPanel pnFields;
    private javax.swing.JPanel pnFieldsLeft;
    private javax.swing.JPanel pnFieldsRight;
    private javax.swing.JPanel pnImg;
    private javax.swing.JPanel pnImgUpLoad;
    private javax.swing.JPanel pnInformationFields;
    private javax.swing.JScrollPane scrQuanAo;
    private javax.swing.JTable tblQuanAo;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtLoiNhuan;
    private javax.swing.JTextField txtMaQA;
    private javax.swing.JTextField txtSoLuongQA;
    private javax.swing.JTextField txtTenQA;
    private javax.swing.JTextField txtThuongHieu;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
    private int viTri = -1;
    private File file = null;
    private QuanAo qaThemMoi = new QuanAo();
    private QuanAo qaCapNhat = null;
    private ArrayList<QuanAo> dsQA = new DAO_QuanAo(DatabaseConstant.getConnection()).getAll();
    private ArrayList<NhaCungCap> dsNCC = new DAO_NhaCungCap(DatabaseConstant.getConnection()).getAll();
    private HashMap<String, String> loaiQuanAo = new DAO_QuanAo(DatabaseConstant.getConnection()).getAllLoaiQuanAo();
    private HashMap<String, String> dsKichThuocQA = new DAO_QuanAo(DatabaseConstant.getConnection()).getAllKichThuocQA();
    private boolean statusBtnCapNhat = false;
    private boolean statusBtnThemMoi = false;
    private boolean statusBtnHuy = false;
    private Manager_QuanAo dsQuanLyQuanAo = new Manager_QuanAo(DatabaseConstant.getConnection());

    private void xoaTrang() {
        java.util.List<JTextField> listTxt = java.util.Arrays.asList(this.txtMaQA, this.txtTenQA, this.txtSoLuongQA, this.txtThuongHieu, this.txtGiaNhap, this.txtLoiNhuan, this.txtGiaBan);
        java.util.List<JComboBox> listCmb = java.util.Arrays.asList(this.cmbLoaiQA, this.cmbSize, this.cmbNhaCungCap, this.cmbTrangThai);
        ComponentStatus.emptyField(listTxt);
        ComponentStatus.setDefaultCmb(listCmb);
        ComponentStatus.setFieldStatus(listTxt, false);
        ComponentStatus.setComboBoxStatus(listCmb, false);
        this.txtTenQA.requestFocus();
    }
}
