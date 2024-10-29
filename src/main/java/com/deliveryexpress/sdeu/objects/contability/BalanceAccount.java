/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.contability;

import com.j256.ormlite.field.DatabaseField;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class BalanceAccount {

    @DatabaseField(id = true)
    private String id;
    @DatabaseField
    private float balance;

}
