/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.an.admin;
import java.awt.Component;
import com.an.Nhanvien;
import com.an.Nhanvien;
import com.an.bean.TableActionCellEditor;
import com.an.bean.TableActionCellRender;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import raven.cell.TableActionEvent;

/**
 *
 * @author ngoca
 */
public class NhanVienJPanel extends javax.swing.JPanel {
	private int uidCounter = 0;

        ArrayList<Nhanvien> arr = new ArrayList<Nhanvien>();
        // Thêm sách mới vào ArrayList
    public void LoadDataToArrayList() {
        arr.clear();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url,"sa", "123456");
            String sql = "select * from NHANVIEN";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);	   
            while (rs.next()) {
                int uid = rs.getInt("uid");
                String ten = rs.getString("ten");
                String gioitinh = rs.getString("gioitinh");
                String dienthoai = rs.getString("dienthoai");
                String chucvu = rs.getString("chucvu");
                

                Nhanvien nhanvien = new Nhanvien(uid, ten, gioitinh, dienthoai, chucvu); // Sửa thành int
                arr.add(nhanvien);
            }

            con.close();
	}catch(Exception e){
		e.printStackTrace();
	}
        
    }

    public void LoadDataArrayListToTable() {
        DefaultTableModel tableModel =  (DefaultTableModel) table.getModel(); 
	tableModel.setRowCount(0);
        for (Nhanvien nhanvien : arr) {
           
            tableModel.addRow( new Object[]{nhanvien.getUid(), nhanvien.getTen(), nhanvien.getGioitinh(), nhanvien.getDienthoai(), nhanvien.getChucvu()});
        }
    }

       
    

    public NhanVienJPanel() {
        initComponents();
        LoadDataToArrayList();
	LoadDataArrayListToTable();
	
	
	
	TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row : " + row);
		 int rows = 0;
       try{
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            System.out.println(table.getRowCount());
            for(int i = 0; i < table.getRowCount(); i++ ){
                String sql = "update NHANVIEN set ten = ?, gioitinh = ?, dienthoai = ?, chucvu = ? where uid = ?";
		PreparedStatement stm = con.prepareStatement(sql);
                
                String ten = table.getValueAt(i, 1).toString();
		String gioitinh = table.getValueAt(i, 2).toString();
		String dienthoai = table.getValueAt(i, 3).toString();
		String chucvu = table.getValueAt(i, 4).toString();
		int uid = Integer.parseInt(table.getValueAt(i, 0).toString());
                stm.setString(1, ten);
		stm.setString(2, gioitinh);
		stm.setString(3, dienthoai);
		stm.setString(4, chucvu);
		stm.setInt(5, uid);
                    System.out.println(ten);
                    System.out.println(chucvu);
		    System.out.println(gioitinh);
		    System.out.println(dienthoai);
		    System.out.println(uid);
		    
                    rows = stm.executeUpdate();
	    }
            if(rows>=1){
                System.out.println("Update thành công");
                System.out.println(rows);
            }
            else{
                System.out.println(rows);
                System.out.println("Chưa update được");
                }
            con.close();
            LoadDataToArrayList();
	
            
    }
       catch(Exception ex){
           ex.printStackTrace();
            System.out.println("Update thất bại");
            System.out.println(rows);
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
                System.out.println("View row : " + row);
		// Tạo giá trị `uid` mới trong Java
    
            }
        };
        table.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        table.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                setHorizontalAlignment(SwingConstants.RIGHT);
                return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1);
            }
        });
    }

    // Phần còn lại của lớp của bạn



    // Phần còn lại của lớp của bạn
	
	
	
	

	
	
	
	

	
	
	
	
	
	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jpopomenu = new javax.swing.JPopupMenu();
                xoaxoa = new javax.swing.JMenuItem();
                jMenuItem2 = new javax.swing.JMenuItem();
                jMenuItem3 = new javax.swing.JMenuItem();
                jPopupMenu2 = new javax.swing.JPopupMenu();
                jPanel1 = new javax.swing.JPanel();
                jPanel5 = new javax.swing.JPanel();
                txttim = new javax.swing.JTextField();
                jLabel2 = new javax.swing.JLabel();
                jPanel2 = new javax.swing.JPanel();
                jLabel1 = new javax.swing.JLabel();
                jScrollPane2 = new javax.swing.JScrollPane();
                table = new javax.swing.JTable();
                button1 = new java.awt.Button();

                xoaxoa.setText("jMenuItem1");
                xoaxoa.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                xoaxoaActionPerformed(evt);
                        }
                });
                jpopomenu.add(xoaxoa);

                jMenuItem2.setText("jMenuItem2");
                jpopomenu.add(jMenuItem2);

                jMenuItem3.setText("jMenuItem3");
                jpopomenu.add(jMenuItem3);

                setPreferredSize(new java.awt.Dimension(872, 584));

                jPanel1.setBackground(new java.awt.Color(204, 255, 255));
                jPanel1.setPreferredSize(new java.awt.Dimension(872, 584));

                jPanel5.setBackground(new java.awt.Color(204, 255, 204));

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

                jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/an/anh/Search.png"))); // NOI18N

                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setText("Tìm kiếm");

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 31, Short.MAX_VALUE))
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttim, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(117, Short.MAX_VALUE))
                );
                jPanel5Layout.setVerticalGroup(
                        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txttim, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                                .addContainerGap(38, Short.MAX_VALUE))
                );

                table.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null},
                                {null, null, null, null, null, null}
                        },
                        new String [] {
                                "UID", "Họ và tên", "Giới tính", "số điện thoại", "Chức vụ", "Action"
                        }
                ));
                jScrollPane2.setViewportView(table);

                button1.setLabel("button1");
                button1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                button1MouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2)
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(231, 231, 231))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
        }// </editor-fold>//GEN-END:initComponents

        private void txttimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txttimActionPerformed

        private void txttimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimKeyReleased
                // TODO add your handling code here:
		
	arr.clear(); // Xóa danh sách hiện có để làm mới danh sách.

float so = 0; // Di chuyển biến so vào phạm vi của phương thức

// Thay đổi các thông số kết nối
try {
    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Tải driver SQL Server
    String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
    Connection con = DriverManager.getConnection(connectionUrl);

    if (txttim.getText().isEmpty())
        so = 0;
    else if (txttim.getText().chars().allMatch(Character::isDigit))
        so = Float.parseFloat(txttim.getText());

    String sql = "SELECT * FROM NHANVIEN WHERE ten LIKE N'%" + txttim.getText() + "%' OR chucvu = '" + txttim.getText() + "' OR uid = " + so;
    System.out.println(sql); // In ra lệnh SQL

    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery(sql);

    while (rs.next()) {
        int uid = rs.getInt("uid");
        String ten = rs.getString("ten");
        String gioitinh = rs.getString("gioitinh");
        String dienthoai = rs.getString("dienthoai");
        String chucvu = rs.getString("chucvu");

        Nhanvien nhanvien = new Nhanvien(uid, ten, gioitinh, dienthoai, chucvu);
        arr.add(nhanvien);
    }

    con.close();
    LoadDataArrayListToTable();

} catch (Exception e) {
    e.printStackTrace();
}


	
        }//GEN-LAST:event_txttimKeyReleased

        private void xoaxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaxoaActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_xoaxoaActionPerformed

        private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
                // TODO add your handling code here:
		
		 int newUid = generateNewUid();

    // Thêm dòng mới vào ArrayList
    Nhanvien nhanvien = new Nhanvien(newUid, "Tên Mới", "Giới Tính Mới", "Điện Thoại Mới", "Chức Vụ Mới");
    arr.add(nhanvien);

    // Thêm dòng mới vào SQL
    insertNewRowIntoSql(newUid, nhanvien);

    // Cập nhật bảng
    LoadDataArrayListToTable();
		
		
		
		
        }//GEN-LAST:event_button1MouseClicked


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private java.awt.Button button1;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JMenuItem jMenuItem2;
        private javax.swing.JMenuItem jMenuItem3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel5;
        private javax.swing.JPopupMenu jPopupMenu2;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JPopupMenu jpopomenu;
        private javax.swing.JTable table;
        private javax.swing.JTextField txttim;
        private javax.swing.JMenuItem xoaxoa;
        // End of variables declaration//GEN-END:variables

	public void initEvent(TableActionEvent event, int row) {
	}
	
	
	 public void xoa(int id){
        int rows = 0; 
        try{
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
            Connection con = DriverManager.getConnection(url, "sa", "123456");
            String sql = "Delete from NHANVIEN where uid=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            rows = stm.executeUpdate();
            if(rows >= 1){
                System.out.println("Xóa thành công");
                System.out.println(rows);
            }
            else
            {
                System.out.println(rows);
                System.out.println("Thất Bại");
                stm.close(); con.close();
            }
	} catch(Exception ex){
                ex.printStackTrace();
                    System.out.println("cập nhật thát bại");
                    System.out.println(rows);
            
        }
    }
//thêm bảng mới
	
private int generateNewUid() {
    // Thay đổi mã để tạo giá trị `uid` mới theo logic của bạn, có thể sử dụng một biến đếm.
    // Ví dụ:
    int newUid = getNewUidValueFromDatabase(); // Lấy giá trị `uid` lớn nhất hiện tại từ cơ sở dữ liệu
    newUid++; // Tạo giá trị mới
    return newUid;
}

private void insertNewRowIntoSql(int uid, Nhanvien nhanvien) {
    try {
        // Thực hiện câu lệnh INSERT để chèn dòng mới vào SQL với giá trị `uid` mới
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
        Connection con = DriverManager.getConnection(url, "sa", "123456");

        String sql = "INSERT INTO NHANVIEN (uid, ten, gioitinh, dienthoai, chucvu) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setInt(1, uid); // Sử dụng giá trị `uid` mới
        stm.setString(2, nhanvien.getTen());
        stm.setString(3, nhanvien.getGioitinh());
        stm.setString(4, nhanvien.getDienthoai());
        stm.setString(5, nhanvien.getChucvu());

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

private int getNewUidValueFromDatabase() {
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true ";
        Connection con = DriverManager.getConnection(url, "sa", "123456");

        String sql = "SELECT MAX(uid) FROM NHANVIEN";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        if (rs.next()) {
            int maxUid = rs.getInt(1);
            return maxUid;
        }

        con.close();
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Lấy giá trị uid lớn nhất thất bại");
    }

    // Nếu có lỗi, trả về một giá trị mặc định hoặc -1
    return -1;
}






}
