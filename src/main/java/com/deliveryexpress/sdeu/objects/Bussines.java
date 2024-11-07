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
 * @author monge.686
 * 
 * Representa un Negocio o restaurante
 */

@Data
public class Bussines {
    
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
    private String balanceAccountId;
    
       /*Non storable values*/

    @Expose
    private long conected;

    public Bussines() {
         this.id = UUID.randomUUID().toString();
    }
    
    
    
        // Constructor que recibe un objeto y lo convierte a JSON
    public Bussines(Object obj) {
        // Usa Gson para convertir el objeto a JSON
        String json = new Gson().toJson(obj);
        // Deserializa el JSON a un objeto Business
        Bussines businessFromJson = new Gson().fromJson(json, Bussines.class);
        
        // Asigna los valores a las variables de instancia
        this.id = businessFromJson.id;
        this.name = businessFromJson.name;
        this.phone = businessFromJson.phone;
        this.address = businessFromJson.address;
        this.position = businessFromJson.position;
        this.balanceAccountId = businessFromJson.balanceAccountId;
        
            /*conection variables*/
        this.conected = businessFromJson.conected;
    }
    
    
    
    
}
