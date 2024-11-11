/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.contability;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import java.util.UUID;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class BalanceAccount {

    @DatabaseField(id = true)
     @Expose
    private String id;
    @DatabaseField
     @Expose
    private float balance;

    public BalanceAccount() {
        this.id = UUID.randomUUID().toString();
        this.balance = 0f;
    }
    
     public BalanceAccount( float balance) {
        this.id = UUID.randomUUID().toString();
        this.balance = balance;
    }

    public BalanceAccount(String id, float balance) {
        this.id = id;
        this.balance = balance;
    }
    
    

}
