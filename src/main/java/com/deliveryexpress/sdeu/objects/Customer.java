/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import java.util.UUID;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class Customer {
    @DatabaseField(id = true)
     @Expose
    private String id;
    
    @DatabaseField
     @Expose
    private String name;
    @DatabaseField
     @Expose
    private String phone;
    @DatabaseField
     @Expose
    private String address;
    @DatabaseField
     @Expose
    private String position;
    @DatabaseField
     @Expose
    private String note;
    @DatabaseField
     @Expose
    private String balanceAccountId;
    
        // Constructor que recibe un objeto y lo convierte a JSON
    public Customer(Object obj) {
        // Usa Gson para convertir el objeto a JSON
        String json = new Gson().toJson(obj);
        // Deserializa el JSON a un objeto Customer
        Customer customerFromJson = new Gson().fromJson(json, Customer.class);

        // Asigna los valores a las variables de instancia
        this.id = customerFromJson.id;
        this.name = customerFromJson.name;
        this.phone = customerFromJson.phone;
        this.address = customerFromJson.address;
        this.position = customerFromJson.position;
        this.note = customerFromJson.note; // Asegúrate de incluir la nota
        this.balanceAccountId = customerFromJson.balanceAccountId;
    }
    

    /***
     * 
     * @param name
     * @param phone
     * @param address
     * @param note 
     */
    public Customer(String name, String phone, String address, String note) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.position = "";
        this.note = note;
        this.balanceAccountId = "";
    }

    public Customer() {
      }

}
