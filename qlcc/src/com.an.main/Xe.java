/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.an;



/**
 *
 * @author ngoca
 */
public class Xe {

	// Constructor và các phương thức khác
	

    private int id;
    private String ten;
    private String biensoxe;
    private String loaixe;
    private String idthe;
	
   
public Xe(int id, String ten, String biensoxe, String loaixe, String idthe) {
		this.id = id;
		this.ten = ten;
		this.biensoxe = biensoxe;
		this.loaixe = loaixe;
		this.idthe = idthe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getBiensoxe() {
		return biensoxe;
	}

	public void setBiensoxe(String biensoxe) {
		this.biensoxe = biensoxe;
	}

	public String getLoaixe() {
		return loaixe;
	}

	public void setLoaixe(String loaixe) {
		this.loaixe = loaixe;
	}

	public String getIdthe() {
		return idthe;
	}

	public void setIdthe(String idthe) {
		this.idthe = idthe;
	}


}


