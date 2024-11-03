/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.session;

import com.deliveryexpress.sdeu.objects.AccountType;
import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.deliveryexpress.sdeu.objects.Moderator;
import com.deliveryexpress.sdeu.objects.User;
import com.google.gson.Gson;
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
    private Object account;

    /*Account of user*/


    public UserSession() {

    }

    /**
     * *
     * Use this Constructor when recive in nested objectos truount in json
     * response
     *
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

    public String getAccountType() {
        return this.user.getAccountType();

    }

    public String getAccountId() {
        return this.user.getAccountId();

    }

    public Delivery getDelivery() {
        if (this.getAccount() != null&&this.user.getAccountType().equals(AccountType.DELIVERY)) {
            return new Delivery(this.account);
        } else {
            return null;
        }

    }

    public Bussines getBussines() {

      if (this.getAccount() != null&&this.user.getAccountType().equals(AccountType.DELIVERY)) {
            return new Bussines(this.account);
        } else {
            return null;
        }

    }

    public Moderator getModerator() {

        if (this.getAccount() != null&&this.user.getAccountType().equals(AccountType.MODERATOR)) {
            return new Moderator(this.account);
        } else {
            return null;
        }

    }

    public Customer getCustomer() {
        if (this.getAccount() != null&&this.user.getAccountType().equals(AccountType.CUSTOMER)) {
            return new Customer(this.account);
        } else {
            return null;
        }

    }
    
     // Método genérico para deserializar a un tipo específico
    private <T> T deserializeAccount(Object accountObject, Class<T> clazz) {
        Gson gson = new Gson();
        
        // Serializa el objeto a JSON
        String json = gson.toJson(accountObject);
        
        // Deserializa el JSON al tipo especificado
        return gson.fromJson(json, clazz);
    }
    
    // Método que usa deserializeAccount
    /***
     * Usarse para deseralizar correctamente account
     * @param <T>
     * @param clazz
     * @return 
     */
    public <T> T getAccountAs(Class<T> clazz) {
        if (this.account != null) {
            return deserializeAccount(this.account, clazz);
        }
        return null;
    }


}
