/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.an.bean;

/**
 *
 * @author 84948
 */
public class RegisterdCard {
   private String type;
        private String name;
        private String licensePlate;
        private String apartment;
        private String registrationDate;
        private int id;

        public RegisterdCard(String type, String name, String licensePlate, String apartment, String registrationDate, int id) {
            this.type = type;
            this.name = name;
            this.licensePlate = licensePlate;
            this.apartment = apartment;
            this.registrationDate = registrationDate;
            this.id = id;
        }

        public String getRegistrationInfo() {
            return "Loại phương tiện: " + type + "\n" +
                "Tên: " + name + "\n" +
                "Biển số: " + licensePlate + "\n" +
                "Căn hộ: " + apartment + "\n" +
                "Ngày tạo: " + registrationDate + "\n" +
                "ID: " + id;
        }  
}
