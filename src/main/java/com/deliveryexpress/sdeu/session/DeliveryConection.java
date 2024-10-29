/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.session;


import com.deliveryexpress.sdeu.objects.location.Position;
import lombok.Data;

/**
 *
 * @author monge.686
 * Representa la session de un Delivery, maneja la informacion del socket, estado de conexion y posicion GPS
 */

@Data
public class DeliveryConection {

    private String sessionId;
    private String socketSessionId;
    private String accId;//deliveryaccountId
    private long lastUpdate;
    private String position;
    private boolean conected;

    public DeliveryConection(String sessionId,String socketSession,String accId) {
      
        this.sessionId = sessionId;
        this.socketSessionId = socketSession;
        this.accId = accId;
        this.lastUpdate = System.currentTimeMillis();
        this.position = "";
        this.conected = false;
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
}
