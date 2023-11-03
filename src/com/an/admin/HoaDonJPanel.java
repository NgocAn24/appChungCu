/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.an.admin;

import com.an.HoaDon;
import com.an.bean.TableActionCellEditor;
import com.an.bean.TableActionCellRender;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import raven.cell.TableActionEvent;

/**
 *
 * @author ngoca
 */
public class HoaDonJPanel extends javax.swing.JPanel {
	private int uidCounter = 0;
    private ArrayList<HoaDon> arr = new ArrayList<HoaDon>();

    public HoaDonJPanel() {
        initComponents();
        LoadDataToArrayList();
        LoadDataArrayListToTable();
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
    System.out.println("Edit row: " + row);
    int rows = 0;
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
        Connection con = DriverManager.getConnection(url, "sa", "123456");
        System.out.println(table.getRowCount());
        for (int i = 0; i < table.getRowCount(); i++) {
            String sql = "update HOADON set chuho = ?, dien = ?, nuoc = ?, dichvu = ?, tong = ? where id = ?";
            PreparedStatement stm = con.prepareStatement(sql);

            String chuho = table.getValueAt(i, 1).toString();
            double dien = Double.parseDouble(table.getValueAt(i, 2).toString());
            double nuoc = Double.parseDouble(table.getValueAt(i, 3).toString());
            double dichvu = Double.parseDouble(table.getValueAt(i, 4).toString());
            
            // Tính lại tổng sau khi chỉnh sửa dien hoặc nuoc
            double tong = dien + nuoc + dichvu;
            
            int id = Integer.parseInt(table.getValueAt(i, 0).toString());

            stm.setString(1, chuho);
            stm.setDouble(2, dien);
            stm.setDouble(3, nuoc);
            stm.setDouble(4, dichvu);
            stm.setDouble(5, tong);
            stm.setInt(6, id);

            rows = stm.executeUpdate();
        }
        if (rows >= 1) {
            System.out.println("Update thành công");
            System.out.println(rows);
        } else {
            System.out.println(rows);
            System.out.println("Chưa update được");
        }
        con.close();
        LoadDataToArrayList();
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Update thất bại");
        System.out.println(rows);
    }
}


            @Override
            public void onDelete(int row) {
                if (row >= 0 && row < arr.size()) {
                    int idXoa;
                    int dong;
                    dong = row;
                    idXoa = (int) table.getValueAt(dong, 0);
                    System.out.println(" " + idXoa);
                    xoa(idXoa);
                    LoadDataToArrayList();
                    LoadDataArrayListToTable();

                    if (table.isEditing()) {
                        table.getCellEditor().stopCellEditing();
                    }
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(row);
                }
            }

            @Override
            public void onView(int row) {
                System.out.println("View row : " + row);
                // Tạo giá trị `id` mới trong Java
                int newId = generateNewId();

                // Thêm dòng mới vào ArrayList
                HoaDon hoaDon = new HoaDon(newId, "Tên Mới", 0, 0, 0, 0);
                arr.add(hoaDon);

                // Thêm dòng mới vào SQL
                insertNewRowIntoSql(newId, hoaDon);

                // Cập nhật bảng
                LoadDataArrayListToTable();
            }
        };
        table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
    }
    
    public void LoadDataToArrayList() {
        arr.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            String sql = "select * from HOADON";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String chuho = rs.getString("chuho");
                double dien = rs.getDouble("dien");
                double nuoc = rs.getDouble("nuoc");
                double dichvu = rs.getDouble("dichvu");
                double tong = rs.getDouble("tong");

                HoaDon hoaDon = new HoaDon(id, chuho, dien, nuoc, dichvu, tong);
                arr.add(hoaDon);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadDataArrayListToTable() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (HoaDon hoaDon : arr) {
            tableModel.addRow(new Object[]{hoaDon.getId(), hoaDon.getChuho(), hoaDon.getDien(), hoaDon.getNuoc(), hoaDon.getDichvu(), hoaDon.getTong()});
        }
    }
    
    public void xoa(int id) {
        int rows = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            String sql = "Delete from HOADON where id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rows = stm.executeUpdate();
            if (rows >= 1) {
                System.out.println("Xóa thành công");
                System.out.println(rows);
            } else {
                System.out.println(rows);
                System.out.println("Thất Bại");
                stm.close();
                con.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Cập nhật thất bại");
        }
    }
    
    private int generateNewId() {
        // Thay đổi mã để tạo giá trị `id` mới theo logic của bạn, có thể sử dụng một biến đếm.
        // Ví dụ:
        int newId = getNewIdValueFromDatabase(); // Lấy giá trị `id` lớn nhất hiện tại từ cơ sở dữ liệu
        newId++; // Tạo giá trị mới
        return newId;
    }

    private void insertNewRowIntoSql(int id, HoaDon hoaDon) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");

            String sql = "INSERT INTO HOADON (id, chuho, dien, nuoc, dichvu, tong) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setInt(1, id);
            stm.setString(2, hoaDon.getChuho());
            stm.setDouble(3, hoaDon.getDien());
            stm.setDouble(4, hoaDon.getNuoc());
            stm.setDouble(5, hoaDon.getDichvu());
            stm.setDouble(6, hoaDon.getTong());

            int rows = stm.executeUpdate();
            if (rows >= 1) {
                System.out.println("Chèn thành công");
            } else {
                System.out.println("Chưa chèn được");
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Chèn thất bại");
        }
    }

    private int getNewIdValueFromDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");

            String sql = "SELECT MAX(id) FROM HOADON";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                int maxId = rs.getInt(1);
                return maxId;
            }
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Lấy giá trị id lớn nhất thất bại");
        }

        // Nếu có lỗi, trả về một giá trị mặc định hoặc -1
        return -1;
    }
	
	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel5 = new javax.swing.JLabel();
                txtNuoc = new javax.swing.JTextField();
                txtDien = new javax.swing.JTextField();
                txtDichvu = new javax.swing.JTextField();
                jLabel8 = new javax.swing.JLabel();
                txtId = new javax.swing.JTextField();
                txtChuho = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                table = new javax.swing.JTable();
                jPanel3 = new javax.swing.JPanel();
                jLabel7 = new javax.swing.JLabel();

                jPanel1.setBackground(new java.awt.Color(204, 255, 255));

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

                jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(204, 204, 204));
                jLabel3.setText("Số điện");

                jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(204, 204, 204));
                jLabel4.setText("Số nước");

                jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
                jLabel5.setForeground(new java.awt.Color(204, 204, 204));
                jLabel5.setText("Tiền dịch vụ");

                txtNuoc.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtNuocActionPerformed(evt);
                        }
                });

                txtDien.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                txtDienMouseClicked(evt);
                        }
                });
                txtDien.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtDienActionPerformed(evt);
                        }
                });

                txtDichvu.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtDichvuActionPerformed(evt);
                        }
                });

                txtChuho.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtChuhoActionPerformed(evt);
                        }
                });

                jLabel1.setForeground(new java.awt.Color(204, 204, 204));
                jLabel1.setText("ID");

                jLabel2.setForeground(new java.awt.Color(204, 204, 204));
                jLabel2.setText("Chủ hộ");

                table.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null, null, null, null},
                                {null, null, null, null, null, null, null},
                                {null, null, null, null, null, null, null},
                                {null, null, null, null, null, null, null}
                        },
                        new String [] {
                                "id", "Tên", "Điện", "Nước", "Dịch vụ", "Tổng", "Action"
                        }
                ));
                table.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tableMouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(table);

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtChuho, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(118, 118, 118)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(txtDichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(130, 130, 130))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(txtNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel8))
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(txtDien, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addContainerGap()))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 763, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(91, 91, 91)
                                                .addComponent(jLabel8))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(23, 23, 23)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel1))
                                                                .addGap(44, 44, 44)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(txtChuho, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel2)))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(25, 25, 25)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(txtDien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel3))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(txtNuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel4))))
                                                .addGap(1, 1, 1)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtDichvu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel5))
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(23, Short.MAX_VALUE))
                );

                jPanel3.setBackground(new java.awt.Color(204, 255, 204));

                jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(255, 204, 204));
                jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel7.setText("Hóa đơn");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(274, 274, 274)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel7)
                                .addContainerGap(20, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(51, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                getAccessibleContext().setAccessibleName("872");
                getAccessibleContext().setAccessibleDescription("548");
        }// </editor-fold>//GEN-END:initComponents

        private void txtDichvuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDichvuActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtDichvuActionPerformed

        private void txtNuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNuocActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtNuocActionPerformed

        private void txtDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDienMouseClicked
                // TODO add your handling code here:
        }//GEN-LAST:event_txtDienMouseClicked

        private void txtDienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDienActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtDienActionPerformed

        private void txtChuhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChuhoActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtChuhoActionPerformed

        private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
                // TODO add your handling code here:
		int row = table.getSelectedRow();
		String id = table.getValueAt(row, 0).toString();
		String ten = table.getValueAt(row, 1).toString();
		String dien = table.getValueAt(row, 2).toString();
		String nuoc = table.getValueAt(row, 3).toString();
		String dichvu = table.getValueAt(row, 4).toString();
		String tong = table.getValueAt(row, 5).toString();
		
		
		txtId.setText(id);
		txtChuho.setText(ten);
		
		
		
		
        }//GEN-LAST:event_tableMouseClicked


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTable table;
        private javax.swing.JTextField txtChuho;
        private javax.swing.JTextField txtDichvu;
        private javax.swing.JTextField txtDien;
        private javax.swing.JTextField txtId;
        private javax.swing.JTextField txtNuoc;
        // End of variables declaration//GEN-END:variables



}
