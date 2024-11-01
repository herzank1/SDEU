/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects;

import com.deliveryexpress.sdeu.objects.location.Position;
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
    private String id; /*Id of deliveryAccount in db*/
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
    
    /*Non storable values*/

    @Expose
    private long lastUpdate;
    @Expose
    private String position; /**current delivery position*/
    @Expose
    private boolean conected;
    
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
        
        /*conection variables*/
         this.lastUpdate = deliveryFromJson.lastUpdate;
          this.position = deliveryFromJson.position;
           this.conected = deliveryFromJson.conected;

    }

    public Delivery() {
    }
    
    
       
    public void disconect() {
        this.conected = false;
    }
    
     // Actualiza el lastUpdate con el timestamp actual
    public void connect() {
        this.conected=true;
    }
    
      // Actualiza el lastUpdate con el timestamp actual
    public void update(String position) {
        this.lastUpdate = System.currentTimeMillis();
        this.position = position;
    }
     // Retorna si está conectado
    public boolean isConected() {
        return sessionExpired()==false&&conected&&getPosition()!=null;
    }
    
    // Verifica si el lastUpdate es menor a 5 minutos
    public boolean sessionExpired() {
        long tiempoActual = System.currentTimeMillis();
        long cincoMinutosEnMilisegundos = 5 * 60 * 1000; // 5 minutos en milisegundos
        return (tiempoActual - this.lastUpdate) > cincoMinutosEnMilisegundos;
    }
    
    public Position getPosition() {
        if (this.position.isEmpty()) {
            return null;
        } else {
            return new Position(this.position);
        }

    }

  

    public void switchConected() {
      this.conected = !this.conected;// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public DeliveryDTO toDeliveryDTO() {
        return new DeliveryDTO(
                this.getId(),
                this.getName(),
                this.getPosition().toString(),
                this.isConected()
        );
    }

    
}
