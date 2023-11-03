/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.an;

/**
 *
 * @author ngoca
 */
public class Account {
    private int id;
    private String ten;
    private String username;
    private String gmail;

    public Account(int id, String ten, String username, String gmail) {
        this.id = id;
        this.ten = ten;
        this.username = username;
        this.gmail = gmail;
    }

    public int getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getUsername() {
        return username;
    }

    public String getGmail() {
        return gmail;
    }

    // Các phương thức getter và setter khác (nếu cần)
}
