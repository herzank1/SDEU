/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class Delivery {
    
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
    private String balanceAccountId;
    
        // Constructor que recibe un objeto y lo convierte a JSON
    public Delivery(Object obj) {
        // Usa Gson para convertir el objeto a JSON
        String json = new Gson().toJson(obj);
        // Deserializa el JSON a un objeto Delivery
        Delivery deliveryFromJson = new Gson().fromJson(json, Delivery.class);
        
        // Asigna los valores a las variables de instancia
        this.id = deliveryFromJson.id;
        this.name = deliveryFromJson.name;
        this.phone = deliveryFromJson.phone;
        this.address = deliveryFromJson.address;
        this.balanceAccountId = deliveryFromJson.balanceAccountId;
    }

    public Delivery() {
    }
    
    
    
}
