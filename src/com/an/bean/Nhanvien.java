/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.an.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author ngoca
 */
public class Nhanvien {
	private int uid;
	private String Ten ,gioitinh,chucvu , dienthoai ;

	public Nhanvien(int uid,String Ten,  String gioitinh, String dienthoai, String chucvu ) {
		this.uid = uid;
		
		this.Ten = Ten;
		this.gioitinh = gioitinh;
		this.chucvu = chucvu;
		this.dienthoai = dienthoai;
	}

	public Nhanvien(String dienthoai) {
		this.dienthoai = dienthoai;
	}

	public String getDienthoai() {
		return dienthoai;
	}

	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	

	public String getTen() {
		return Ten;
	}

	public void setTen(String Ten) {
		this.Ten = Ten;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

	
	
	
	
	
	
	   public static void updateNhanvien(int uid, String ten, String gioitinh, String dienthoai, String chucvu) {
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // Tải driver SQL Server
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=DANGNHAP;user=sa;password=123456;encrypt=true;trustServerCertificate=true";
        Connection con = DriverManager.getConnection(connectionUrl);

        String sql = "UPDATE NHANVIEN SET ten=?, gioitinh=?, dienthoai=?, chucvu=? WHERE uid=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
	preparedStatement.setInt(1, uid);
        preparedStatement.setString(2, ten);
        preparedStatement.setString(3, gioitinh);
        preparedStatement.setString(4, dienthoai);
        preparedStatement.setString(5, chucvu);
        

        int rowsAffected = preparedStatement.executeUpdate();

        con.close();
    } catch (Exception e) {
        e.printStackTrace();
        // Xử lý lỗi nếu cần
    }
}

	
	
	
		
}
