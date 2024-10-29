/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
@DatabaseTable(tableName = "users")
public class User {
    
    @DatabaseField(id = true)
    @Expose
    private String username;
    
    @DatabaseField
   @Expose
    private String phone;
  
    @DatabaseField
    @Expose
    private String pass;
    
    @DatabaseField
    @Expose
    
    private String accountType;
    
    @DatabaseField
    @Expose
    private String accountId;
    
    @DatabaseField
    @Expose
    private boolean blackList;

    /***
     * 
     * @param username
     * @param phone
     * @param pass
     * @param accountType
     * @param accountId
     * @param blackList 
     */
    public User(String username, String phone, String pass, String accountType, String accountId, boolean blackList) {
        this.username = username;
        this.phone = phone;
        this.pass = pass;
        this.accountType = accountType;
        this.accountId = accountId;
        this.blackList = blackList;
    }
    
        // Constructor que recibe un objeto y lo convierte a JSON
    public User(Object obj) {
        // Usa Gson para convertir el objeto a JSON
        String json = new Gson().toJson(obj);
        // Deserializa el JSON a un objeto User
        User userFromJson = new Gson().fromJson(json, User.class);
        
        // Asigna los valores a las variables de instancia
        this.username = userFromJson.username;
        this.phone = userFromJson.phone;
        this.pass = userFromJson.pass;
        this.accountType = userFromJson.accountType;
        this.accountId = userFromJson.accountId;
        this.blackList = userFromJson.blackList;
    }

    public User() {
    }
    
    

   
    public interface AccountType {

        String CUSTOMER = "CUSTOMER";
        String BUSSINES = "BUSSINES";
        String DELIVERY = "DELIVERY";
        String MODERATOR = "MODERATOR";
    }
    
    
}
