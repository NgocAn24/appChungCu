/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.an.admin;
import com.an.Xe;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author ngoca
 */
public class XeJPanel extends javax.swing.JPanel {

    private List<Xe> arr = new ArrayList<>();

 public void LoadDataToArrayList() {
        arr.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            String sql = "select * from XE"; // Sửa thành tên bảng XE
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id"); // Sửa tên cột và kiểu dữ liệu tương ứng
                String ten = rs.getString("ten");
                String biensoxe = rs.getString("biensoxe");
                String loaixe = rs.getString("loaixe");
		 String idthe = rs.getString("idthe");
                Xe xe = new Xe(id,ten,biensoxe, loaixe, idthe);
                arr.add(xe);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public XeJPanel() {
		initComponents();
		LoadDataToArrayList();
		 LoadDataArrayListToTable();

	}

    public void LoadDataArrayListToTable() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (Xe xe : arr) {
            tableModel.addRow(new Object[]{xe.getId(),xe.getTen(),xe.getBiensoxe(),xe.getLoaixe(),xe.getIdthe()});
        }
    }
	
	
	
	
	
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jTextField1 = new javax.swing.JTextField();
                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                txtTen = new javax.swing.JTextField();
                txtBienxe = new javax.swing.JTextField();
                txtIDthe = new javax.swing.JTextField();
                txtid = new javax.swing.JTextField();
                jdk = new javax.swing.JButton();
                jLabel5 = new javax.swing.JLabel();
                txtLoaixe = new javax.swing.JTextField();
                jLabel7 = new javax.swing.JLabel();
                jLabel8 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                table = new javax.swing.JTable();
                jPanel3 = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();

                jTextField1.setText("jTextField1");

                setBackground(new java.awt.Color(255, 255, 255));
                setPreferredSize(new java.awt.Dimension(804, 505));

                jPanel1.setBackground(new java.awt.Color(204, 255, 255));
                jPanel1.setPreferredSize(new java.awt.Dimension(872, 572));

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));

                jLabel1.setText("Tên");

                jLabel2.setText("Biển số");

                jLabel3.setText("id thẻ");

                jLabel4.setText("ID");

                txtTen.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtTenActionPerformed(evt);
                        }
                });

                txtBienxe.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtBienxeActionPerformed(evt);
                        }
                });

                txtid.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtidActionPerformed(evt);
                        }
                });

                jdk.setText("Chỉnh sửa");
                jdk.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jdkMouseClicked(evt);
                        }
                });
                jdk.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jdkActionPerformed(evt);
                        }
                });

                jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/an/anh/User4.png"))); // NOI18N
                jLabel5.setText("jLabel5");

                txtLoaixe.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtLoaixeActionPerformed(evt);
                        }
                });

                jLabel7.setText("Loai xe");

                jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(204, 204, 204));
                jLabel8.setText("nhập id để chỉnh sửa thông tin");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(144, 144, 144))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(90, 90, 90)
                                                .addComponent(jdk, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(92, 92, 92))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(40, 40, 40)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(txtLoaixe)
                                                        .addComponent(txtIDthe)
                                                        .addComponent(txtBienxe)
                                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(3, 3, 3)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1))
                                                .addGap(15, 15, 15)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtBienxe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2))
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txtLoaixe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7))
                                                .addGap(18, 18, 18))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtIDthe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(28, 28, 28)
                                .addComponent(jdk, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(57, 57, 57))
                );

                table.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null},
                                {null, null, null, null, null}
                        },
                        new String [] {
                                "id", "Tên", "Loại xe", "Biển số", "Id thẻ"
                        }
                ));
                table.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tableMouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(table);

                jPanel3.setBackground(new java.awt.Color(204, 255, 204));

                jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 204, 204));
                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel6.setText("Quản lí xe");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(375, 375, 375)
                                .addComponent(jLabel6)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jLabel6)
                                .addContainerGap(37, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                                .addContainerGap())
                );
        }// </editor-fold>//GEN-END:initComponents

        private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtTenActionPerformed

        private void txtBienxeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBienxeActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtBienxeActionPerformed

        private void jdkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdkActionPerformed
                // TODO add your handling code here:
		       // TODO add your handling code here:
		       
		
		       
		       
		       
		int rows = 0;
try {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
    Connection con = DriverManager.getConnection(url, "sa", "123456");
    System.out.println(table.getRowCount());
    
   for (int i = 0; i < table.getRowCount(); i++) {
    String sql = "update XE set ten = ?, biensoxe = ?, idthe = ?, loaixe = ? where id = ?";
    PreparedStatement stm = con.prepareStatement(sql);

        String id = txtid.getText();
	String ten = txtTen.getText();
	String loaixe = txtLoaixe.getText();
	String biensoxe = txtBienxe.getText();
	String idthe = txtIDthe.getText();

    stm.setString(1, ten);
    stm.setString(2, biensoxe);
    stm.setString(3, idthe);
    stm.setString(4, loaixe);
    stm.setString(5, id);

    rows = stm.executeUpdate();
}


    
    if (rows >= 1) {
        System.out.println("Update thành công");
        System.out.println(rows);
    } else {
        System.out.println("Chưa update được");
    }
    
    con.close();
    
    // You may call the methods to reload data and update the table here.
    LoadDataToArrayList();
    LoadDataArrayListToTable();
    
} catch (Exception ex) {
    ex.printStackTrace();
    System.out.println("Update thất bại");
    System.out.println(rows);
}

	
        }//GEN-LAST:event_jdkActionPerformed

        private void txtLoaixeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoaixeActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtLoaixeActionPerformed

        private void jdkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jdkMouseClicked
         
        }//GEN-LAST:event_jdkMouseClicked

        private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtidActionPerformed

        private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
                // TODO add your handling code here:
		int row = table.getSelectedRow();
		String id = table.getValueAt(row, 0).toString();
		String ten = table.getValueAt(row, 1).toString();
		String biensoxe = table.getValueAt(row, 2).toString();
		String loaixe = table.getValueAt(row, 3).toString();
		String idthe = table.getValueAt(row, 4).toString();
		
		txtid.setText(id);
		txtTen.setText(ten);
		txtBienxe.setText(biensoxe);
		txtLoaixe.setText(loaixe);
		txtIDthe.setText(idthe);
		
		
        }//GEN-LAST:event_tableMouseClicked


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel8;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JTextField jTextField1;
        private javax.swing.JButton jdk;
        private javax.swing.JTable table;
        private javax.swing.JTextField txtBienxe;
        private javax.swing.JTextField txtIDthe;
        private javax.swing.JTextField txtLoaixe;
        private javax.swing.JTextField txtTen;
        private javax.swing.JTextField txtid;
        // End of variables declaration//GEN-END:variables

   private void updateXeInDatabase(Xe xe) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
            Connection con = DriverManager.getConnection(url, "sa", "123456");

            // Chuẩn bị truy vấn cập nhật
            String updateQuery = "update XE set ten=?, biensoxe=?, loaixe=? , idthe =? where id=?";

            PreparedStatement preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setString(1, xe.getTen());
            preparedStatement.setString(2, xe.getBiensoxe());
            preparedStatement.setString(3, xe.getLoaixe());
	    preparedStatement.setString(4, xe.getIdthe());
            preparedStatement.setInt(5, xe.getId());

            // Thực hiện truy vấn cập nhật
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật thành công trong cơ sở dữ liệu.");
            }else {
            System.out.println("Xe update failed.");
        }

            preparedStatement.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hiển thị thông báo cập nhật thành công
    private void showSuccessDialog() {
        String message = "Cập nhật thành công!";
        JOptionPane.showMessageDialog(this, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
	
}
