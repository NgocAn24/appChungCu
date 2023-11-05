/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.an;

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

	

	
	
		
}
