/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.an.view;

import com.an.view.ThongTinJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import net.miginfocom.swing.MigLayout;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author ngoca
 */
public class DangNhapJDialog extends javax.swing.JDialog {
private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=DANGNHAP;user=sa;password=123456;encrypt=false;trustServerCertificate=true;";

 private ThongTinJPanel thongTinPanel; 
    /**
     * Creates new form DangNhapJDialog
     */
    public DangNhapJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
	 thongTinPanel = new ThongTinJPanel();
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField(); // Sử dụng JPasswordField cho mật khẩu

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword()); // Lấy mật khẩu từ JPasswordField

                int dang = kiemTraDangNhap(username, password);
                if (dang != 0) {
                    // Nếu đăng nhập thành công, chuyển đến giao diện chính
                   
                } else {
                    // Xử lý trường hợp đăng nhập thất bại
                }
            }
        });

        add(usernameField, BorderLayout.NORTH);
        add(passwordField, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
    }

    private int kiemTraDangNhap(String username, String password) {
        try (Connection con = DriverManager.getConnection(connectionUrl)) {
            String query = "SELECT [dang] FROM [DANGNHAP].[dbo].[ACCOUNT] WHERE [username] = ? AND [pass] = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("dang");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // Đăng nhập thất bại
    }
		
		
		
		
	
	private void quenMatKhau() {
    // Hiển thị giao diện cho người dùng nhập tài khoản và địa chỉ Gmail
    JTextField txtUsername = new JTextField();
    JTextField txtEmail = new JTextField();
    Object[] message = {
        "Tài khoản:", txtUsername,
        "Địa chỉ Gmail:", txtEmail
    };

    int option = JOptionPane.showConfirmDialog(this, message, "Nhập thông tin tài khoản và Gmail", JOptionPane.OK_CANCEL_OPTION);
    if (option == JOptionPane.OK_OPTION) {
        String username = txtUsername.getText();
        String email = txtEmail.getText();

        // Kiểm tra tài khoản và địa chỉ Gmail từ cơ sở dữ liệu SQL Server và hiển thị mật khẩu nếu hợp lệ
        String password = kiemTraTaiKhoanVaEmail(username, email);
        if (password != null) {
            // Hiển thị mật khẩu khi hợp lệ
            hienThiMatKhau(password);
        } else {
            JOptionPane.showMessageDialog(this, "Tài khoản hoặc Gmail không hợp lệ");
        }
    }
}

private String kiemTraTaiKhoanVaEmail(String username, String email) {
    try (Connection con = DriverManager.getConnection(connectionUrl)) {
        // Thực hiện truy vấn SQL để kiểm tra tài khoản và địa chỉ Gmail từ cơ sở dữ liệu
        String query = "SELECT password FROM ACCOUNT WHERE username = ? AND gmail = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, email);

        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            // Tài khoản và Gmail hợp lệ, trả về mật khẩu
            return resultSet.getString("password");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý ngoại lệ (ghi log hoặc hiển thị thông báo lỗi)
    }

    return null;
}

private void hienThiMatKhau(String password) {
    // Hiển thị mật khẩu ở đây, ví dụ: lblMatKhau.setText("Mật khẩu: " + password);
}
	
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jPanel2 = new javax.swing.JPanel();
                DangNhap = new javax.swing.JPanel();
                jfpass = new javax.swing.JPasswordField();
                txtdn = new javax.swing.JTextField();
                jSeparator1 = new javax.swing.JSeparator();
                jSeparator2 = new javax.swing.JSeparator();
                jPanel3 = new javax.swing.JPanel();
                btnlogin = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                jLabel4 = new javax.swing.JLabel();
                jLabel9 = new javax.swing.JLabel();
                jLabel10 = new javax.swing.JLabel();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                jLabel7 = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

                jPanel1.setBackground(new java.awt.Color(204, 255, 255));

                jPanel2.setBackground(new java.awt.Color(195, 255, 230));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 68, Short.MAX_VALUE)
                );

                DangNhap.setBackground(new java.awt.Color(255, 255, 255));

                jfpass.setBorder(null);
                jfpass.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jfpassActionPerformed(evt);
                        }
                });

                txtdn.setBorder(null);
                txtdn.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                txtdn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                txtdnActionPerformed(evt);
                        }
                });

                btnlogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                btnlogin.setText("Đăng Nhập");
                btnlogin.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                btnloginMouseClicked(evt);
                        }
                });

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel3Layout.setVerticalGroup(
                        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnlogin, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                .addContainerGap())
                );

                jLabel3.setText("Quên mật khẩu ?");
                jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jLabel3MouseClicked(evt);
                        }
                });

                jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
                jLabel4.setText("Đăng nhập thành viên Apartment");

                jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
                jLabel9.setForeground(new java.awt.Color(204, 204, 204));
                jLabel9.setText("tài khoản");

                jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
                jLabel10.setForeground(new java.awt.Color(204, 204, 204));
                jLabel10.setText("Mật khẩu");

                javax.swing.GroupLayout DangNhapLayout = new javax.swing.GroupLayout(DangNhap);
                DangNhap.setLayout(DangNhapLayout);
                DangNhapLayout.setHorizontalGroup(
                        DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(DangNhapLayout.createSequentialGroup()
                                .addGroup(DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(DangNhapLayout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addGroup(DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel9)
                                                        .addGroup(DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jSeparator2)
                                                                .addComponent(jfpass, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                                                .addComponent(txtdn, javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(jLabel10)
                                                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING))))
                                        .addGroup(DangNhapLayout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addGroup(DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel4)
                                                                .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addContainerGap(28, Short.MAX_VALUE))
                );
                DangNhapLayout.setVerticalGroup(
                        DangNhapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(DangNhapLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addGap(2, 2, 2)
                                .addComponent(txtdn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel10)
                                .addGap(0, 0, 0)
                                .addComponent(jfpass, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(24, 24, 24)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(129, Short.MAX_VALUE))
                );

                jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/an/anh/pngegg.png"))); // NOI18N

                jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
                jLabel2.setText("Mọi thứ dễ dàng hơn khi có Apartment ");

                jLabel6.setText("công việc quản lí tốt hơn");

                jLabel7.setText("tra cứu thông tin nhanh");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(92, 92, 92)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(54, 54, 54)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                .addComponent(DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                                .addComponent(jLabel1)
                                                .addGap(106, 106, 106))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(DangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 803, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 459, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void jfpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jfpassActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_jfpassActionPerformed

        private void txtdnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdnActionPerformed
                // TODO add your handling code here:
        }//GEN-LAST:event_txtdnActionPerformed

        private void btnloginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnloginMouseClicked
               try (Connection con = DriverManager.getConnection(connectionUrl)) {
        String sql = "SELECT [id], [dang] FROM [DANGNHAP].[dbo].[ACCOUNT] WHERE [username] = ? AND [pass] = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, txtdn.getText());
        ps.setString(2, jfpass.getText());

        ResultSet resultSet = ps.executeQuery();

	if (txtdn.getText().isEmpty() || jfpass.getText().isEmpty()) {
	    JOptionPane.showMessageDialog(this, "Chưa nhập user và mật khẩu");
	} else if (resultSet.next()) {
	      int accountId = resultSet.getInt("id");
            
            String ten = "Tên người dùng"; // Thay thế bằng thông tin thực tế
            String gmail = "example@gmail.com"; // Thay thế bằng thông tin thực tế
            String id = Integer.toString(accountId);

            // Gọi phương thức setTen, setGmail và setId từ thể hiện của ThongTinJPanel
             thongTinPanel.setTen(ten);
            thongTinPanel.setGmail(gmail);
            thongTinPanel.setId(id);

            // Đóng cửa sổ Đăng nhập
            dispose();

            // Tạo đối tượng của trang chính và hiển thị nó
            MainJFrame frame = new MainJFrame();
	    frame.setVisible(true);
	} else {
    JOptionPane.showMessageDialog(this, "Đăng nhập thất bại");
}

    } catch (SQLException e) {
        e.printStackTrace();
    }
        }//GEN-LAST:event_btnloginMouseClicked

        private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
                // TODO add your handling code here:

                quenMatKhau();

        }//GEN-LAST:event_jLabel3MouseClicked


        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel DangNhap;
        private javax.swing.JLabel btnlogin;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel10;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JLabel jLabel7;
        private javax.swing.JLabel jLabel9;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private javax.swing.JPanel jPanel3;
        private javax.swing.JSeparator jSeparator1;
        private javax.swing.JSeparator jSeparator2;
        private javax.swing.JPasswordField jfpass;
        private javax.swing.JTextField txtdn;
        // End of variables declaration//GEN-END:variables

	private static class controller {

		public controller() {
		}
	}
	
	
	
}
