/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.an.controller;

import com.an.view.DangNhapJDialog;
import com.an.view.MainJFrame;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ngoca
 */
public class DangNhapController {
private final String connectionUrl = "jdbc:sqlserver://localhost;databaseName=DANGNHAP;user=sa;password=123456;encrypt=false;trustServerCertificate=true;";
    private DangNhapJDialog dialog;
    private JLabel btnlogin;
    private JTextField txtdn;
    private JTextField jfpass;

    // Cần thay thế "TaiKhoanService" bằng tên lớp hoặc giao diện dịch vụ của bạn
    // TaiKhoanService taiKhoanService = null;

    public DangNhapController(DangNhapJDialog dialog, JLabel btnlogin, JTextField txtdn, JTextField jfpass) {
        this.dialog = dialog;
        this.btnlogin = btnlogin;
        this.txtdn = txtdn;
        this.jfpass = jfpass;

        // Khởi tạo taiKhoanService ở đây, ví dụ:
        // taiKhoanService = new TaiKhoanServiceImpl();
    }

    
    public void setEvent() {
        btnlogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (txtdn.getText().length() == 0 || jfpass.getText().length() == 0) {
                        JOptionPane.showMessageDialog(dialog, "Vui lòng nhập đầy đủ thông tin!");
                    } else {
                        String username = txtdn.getText();
                        String password = new String(jfpass.getText()); // Lấy mật khẩu từ JPasswordField

                        // Thực hiện kiểm tra đăng nhập sử dụng JDBC
                        boolean loginSuccessful = kiemTraDangNhap(username, password);

                        if (!loginSuccessful) {
                            JOptionPane.showMessageDialog(dialog, "Tên đăng nhập và mật khẩu không đúng!");
                        } else {
                            dialog.dispose();
                            MainJFrame frame = new MainJFrame();
                            frame.setSize(100, 650);
                            frame.setResizable(false);
                            frame.setVisible(true);
			    
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, ex.getMessage());
                }
            }
        });
    }
       
  private boolean kiemTraDangNhap(String username, String password) {
        try (Connection con = DriverManager.getConnection(connectionUrl)) {
            String query = "SELECT [dang] FROM [DANGNHAP].[dbo].[ACCOUNT] WHERE [username] = ? AND [pass] = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


            public void mouseEntered(MouseEvent e) {
                btnlogin.setBackground(new Color(0, 200, 83));
            }

            public void mouseExited(MouseEvent e) {
                btnlogin.setBackground(new Color(100, 221, 23));
            }
        
}



