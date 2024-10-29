/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.session;

import com.deliveryexpress.sdeu.objects.User;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class UserSession{
 
    
    @Expose
    private String id;
    @Expose
    private long lastUpdate; /*unix time stamp*/
    @Expose
    private User user;/*basic user*/
    @Expose
    private Object account; /*Account of user*/
    
    

    public UserSession() {

    }
    
        /***
         * Use this Constructor when recive in nested objectos truount in json response
         * @param object to serailize json next UserSession
         */
    public UserSession(Object object) {
            Gson gson = new Gson();
            // Convertimos el objeto en un String JSON y luego lo deserializamos
            String json = gson.toJson(object);
            UserSession session = gson.fromJson(json, UserSession.class);

            // Asignamos los valores deserializados a los atributos
            this.id = session.id;
            this.lastUpdate = session.lastUpdate;
            this.user = session.user;
            this.account = session.account;
        }


    // Método que verifica si la sesión ha expirado
    public boolean isExpired() {
        // Obtener el tiempo actual en milisegundos
        long currentTime = System.currentTimeMillis();
        // Verificar si la diferencia es mayor o igual a 30 minutos (30 * 60 * 1000 ms)
        return (currentTime - lastUpdate) >= (30 * 60 * 1000);
    }
    
  
    
    public boolean isLogged() {
        return this.user != null;
    }


 
    public String getAccountType(){
    return this.user.getAccountType();
    
    }
    
    public String getAccountId(){
    return this.user.getAccountId();
    
    }

  
}
