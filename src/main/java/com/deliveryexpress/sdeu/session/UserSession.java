/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.session;

import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.deliveryexpress.sdeu.objects.Moderator;
import com.deliveryexpress.sdeu.objects.User;
import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 *
 * @author HP Representa la session de un usuario si account es nulo, el usuario
 * no esta logeado
 */
@Data
public class UserSession {

    @Expose
    private String id;
    @Expose
    private long lastUpdate;
    /*unix time stamp*/
    @Expose
    private User user;/*basic user*/
    @Expose
    private Customer customer;
    @Expose
    private Delivery delivery;
    @Expose
    private Bussines bussines;
    @Expose
    private Moderator moderator;

    /*Account of user*/


    public UserSession() {
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

    public String getAccountType() {
        return this.user.getAccountType();

    }

    public String getAccountId() {
        return this.user.getAccountId();

    }
    
  

}
