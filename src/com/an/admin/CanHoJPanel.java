/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.an.admin;
import com.an.CanHo;
import java.sql.PreparedStatement;
import com.an.Nhanvien;
import com.an.bean.TableActionCellEditor;
import com.an.bean.TableActionCellRender;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import raven.cell.TableActionEvent;

/**
 *
 * @author ngoca
 */
public class CanHoJPanel extends javax.swing.JPanel {

	 private ArrayList<CanHo> arr = new ArrayList<CanHo>();

    public CanHoJPanel() {
        initComponents();
        LoadDataToArrayList();
        LoadDataArrayListToTable();

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                int rows = 0;
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
                    Connection con = DriverManager.getConnection(url, "sa", "123456");

                    for (int i = 0; i < table.getRowCount(); i++) {
                        String sql = "UPDATE CANHO SET tang = ?, dientich = ?, tinhtrang = ?, loai = ?, gia = ? WHERE ma = ?";
                        PreparedStatement stm = con.prepareStatement(sql);

                        int ma = Integer.parseInt(table.getValueAt(i, 0).toString());
                        int tang = Integer.parseInt(table.getValueAt(i, 1).toString());
                        double dientich = Double.parseDouble(table.getValueAt(i, 2).toString());
                        String tinhtrang = table.getValueAt(i, 3).toString();
                        String loai = table.getValueAt(i, 4).toString();
                        double gia = Double.parseDouble(table.getValueAt(i, 5).toString());

                        stm.setInt(1, tang);
                        stm.setDouble(2, dientich);
                        stm.setString(3, tinhtrang);
                        stm.setString(4, loai);
                        stm.setDouble(5, gia);
                        stm.setInt(6, ma);

                        rows = stm.executeUpdate();
                    }

                    if (rows >= 1) {
                        System.out.println("Update thành công");
                        System.out.println(rows);
                    } else {
                        System.out.println("Chưa update được");
                    }
                    con.close();
                    LoadDataToArrayList();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Update thất bại");
                }
            }

            @Override
            public void onDelete(int row) {
    if (row >= 0 && row < table.getRowCount()) {
        // Tránh lỗi ArrayIndexOutOfBoundsException
        int maXoa = (int) table.getValueAt(row, 0);
        System.out.println("Xóa mã: " + maXoa);
        xoa(maXoa);
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
                System.out.println("View row: " + row);

                int newMa = generateNewMa();

                CanHo canHo = new CanHo(newMa, 0, 0.0, "Mới", "Loại Mới", 0.0);
                arr.add(canHo);

                insertNewRowIntoSql(newMa, canHo);

                LoadDataArrayListToTable();
            }
        };
        table.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
    }

     public void xoa(int ma) {
        int rows = 0;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            String sql = "Delete from CANHO where ma=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, ma);
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

    private int generateNewMa() {
        int newMa = getNewMaValueFromDatabase();
        newMa++;
        return newMa;
    }

    private void insertNewRowIntoSql(int ma, CanHo canHo) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");

            String sql = "INSERT INTO CANHO (ma, tang, dientich, tinhtrang, loai, gia) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setInt(1, ma);
            stm.setInt(2, canHo.getTang());
            stm.setDouble(3, canHo.getDientich());
            stm.setString(4, canHo.getTinhtrang());
            stm.setString(5, canHo.getLoai());
            stm.setDouble(6, canHo.getGia());

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

    private int getNewMaValueFromDatabase() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");

            String sql = "SELECT MAX(ma) FROM CANHO";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                int maxMa = rs.getInt(1);
                return maxMa;
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Lấy giá trị ma lớn nhất thất bại");
        }

        return -1;
    }

    // Các phương thức khác

	

	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jMenu1 = new javax.swing.JMenu();
                jMenuItem1 = new javax.swing.JMenuItem();
                jPanel1 = new javax.swing.JPanel();
                jSeparator1 = new javax.swing.JSeparator();
                jSeparator2 = new javax.swing.JSeparator();
                txttim = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                table = new javax.swing.JTable();
                jPanel2 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();

                jMenu1.setText("jMenu1");

                jMenuItem1.setText("jMenuItem1");

                setPreferredSize(new java.awt.Dimension(872, 584));

                jPanel1.setBackground(new java.awt.Color(204, 255, 255));

                txttim.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txttimActionPerformed(evt);
                        }
                });
                txttim.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                txttimKeyReleased(evt);
                        }
                });

                jLabel1.setText("Tra cứu");

                jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/an/anh/ban-ve-phoi-canh-chung-cu.jpg"))); // NOI18N

                table.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null, null, null, null},
                                {null, null, null, null, null, null, null},
                                {null, null, null, null, null, null, null},
                                {null, null, null, null, null, null, null}
                        },
                        new String [] {
                                "Mã căn hộ", "Tầng", "Diện tích", "Tình trạng", "Loại", "Giá", "Chỉnh sửa"
                        }
                ));
                jScrollPane1.setViewportView(table);
                if (table.getColumnModel().getColumnCount() > 0) {
                        table.getColumnModel().getColumn(5).setResizable(false);
                }

                jPanel2.setBackground(new java.awt.Color(195, 255, 230));

                jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 102, 102));
                jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel4.setText("Quản lí căn hộ");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(248, 248, 248)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(273, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(36, Short.MAX_VALUE))
                );

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jSeparator2)
                                                .addContainerGap())
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addComponent(jLabel1)
                                                .addGap(63, 63, 63)
                                                .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(101, 101, 101))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 769, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator1)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(87, 87, 87)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                getAccessibleContext().setAccessibleName("872");
                getAccessibleContext().setAccessibleDescription("584");
        }// </editor-fold>//GEN-END:initComponents

        private void txttimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimActionPerformed
                // TODO add your handling code here:
		
        }//GEN-LAST:event_txttimActionPerformed

        private void txttimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKeyReleased
                // TODO add your handling code here:
		performSearch();
        }//GEN-LAST:event_txttimKeyReleased


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JMenu jMenu1;
        private javax.swing.JMenuItem jMenuItem1;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JSeparator jSeparator1;
        private javax.swing.JSeparator jSeparator2;
        private javax.swing.JTable table;
        private javax.swing.JTextField txttim;
        // End of variables declaration//GEN-END:variables


	

	
	

 public void LoadDataToArrayList() {
        arr.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            String sql = "select * from CANHO";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int ma = rs.getInt("ma");
                int tang = rs.getInt("tang");
                double dientich = rs.getDouble("dientich");
                String tinhtrang = rs.getString("tinhtrang");
                String loai = rs.getString("loai");
                double gia = rs.getDouble("gia");

                CanHo canHo = new CanHo(ma, tang, dientich, tinhtrang, loai, gia);
                arr.add(canHo);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LoadDataArrayListToTable() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);
        for (CanHo canHo : arr) {
            tableModel.addRow(new Object[]{canHo.getMa(), canHo.getTang(), canHo.getDientich(), canHo.getTinhtrang(), canHo.getLoai(), canHo.getGia()});
        }
    }
    
    
    
    
   private void performSearch() {
    arr.clear(); // Xóa danh sách hiện có để làm mới danh sách.

    float so = 0; // Khai báo biến số

    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Tải driver SQL Server
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
        Connection con = DriverManager.getConnection(connectionUrl);

        if (txttim.getText().isEmpty())
            so = 0;
        else if (txttim.getText().chars().allMatch(Character::isDigit))
            so = Float.parseFloat(txttim.getText());

        // Tạo câu truy vấn SQL với các điều kiện đúng cú pháp
        String sql = "SELECT * FROM CANHO WHERE ma LIKE '%" + txttim.getText() + "%' OR tang = " + so + " OR dientich = " + so + " OR tinhtrang LIKE '%" + txttim.getText() + "%' OR loai LIKE '%" + txttim.getText() + "%' OR gia = " + so;
        System.out.println(sql); // In ra lệnh SQL

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            int ma = rs.getInt("ma");
            int tang = rs.getInt("tang");
            float dientich = rs.getFloat("dientich");
            String tinhtrang = rs.getString("tinhtrang");
            String loai = rs.getString("loai");
            float gia = rs.getFloat("gia");

            // Tạo đối tượng CanHo và thêm vào danh sách
            CanHo canho = new CanHo(ma, tang, dientich, tinhtrang, loai, gia);
            arr.add(canho);
        }

        con.close();
        LoadDataArrayListToTable();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    
    
    
}


