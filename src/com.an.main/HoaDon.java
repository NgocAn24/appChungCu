
package com.an;

import javax.swing.JTextField;

public class HoaDon {

	
	
   
    private int id;
    private String chuho;
    private double dien;
    private double nuoc;
    private double dichvu;
    private double tong;
    
    
public HoaDon(int id, String chuho, double dien, double nuoc, double dichvu, double tong) {
		this.id = id;
		this.chuho = chuho;
		this.dien = dien;
		this.nuoc = nuoc;
		this.dichvu = dichvu;
		this.tong = tong;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChuho() {
		return chuho;
	}

	public void setChuho(String chuho) {
		this.chuho = chuho;
	}

	public double getDien() {
		return dien;
	}

	public void setDien(double dien) {
		this.dien = dien;
	}

	public double getNuoc() {
		return nuoc;
	}

	public void setNuoc(double nuoc) {
		this.nuoc = nuoc;
	}

	public double getDichvu() {
		return dichvu;
	}

	public void setDichvu(double dichvu) {
		this.dichvu = dichvu;
	}

	public double getTong() {
		return tong;
	}

	public void setTong(double tong) {
		this.tong = tong;
	}
}
