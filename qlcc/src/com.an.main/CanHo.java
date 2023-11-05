/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.an;

/**
 *
 * @author ngoca
 */
public class CanHo {
    private int ma;
    private int tang;
    private double dientich;
    private String tinhtrang;
    private String loai;
    private double gia;

    public CanHo(int ma, int tang, double dientich, String tinhtrang, String loai, double gia) {
        this.ma = ma;
        this.tang = tang;
        this.dientich = dientich;
        this.tinhtrang = tinhtrang;
        this.loai = loai;
        this.gia = gia;
    }

    public int getMa() {
        return ma;
    }

    public int getTang() {
        return tang;
    }

    public double getDientich() {
        return dientich;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public String getLoai() {
        return loai;
    }

    public double getGia() {
        return gia;
    }
}
