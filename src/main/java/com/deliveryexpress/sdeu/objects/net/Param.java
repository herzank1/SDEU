/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net;


import java.awt.image.BufferedImage;
import lombok.Data;
import com.deliveryexpress.sdeu.utils.GeneralUtils;
import com.google.gson.annotations.Expose;


/**
 *
 * @author monge.686 Diego Villarreal
 * Representa el parametro de un commando
 */
@Data
public class Param {

    @Expose
    String name;
    @Expose
    Object value;
    @Expose
    byte[] values;

    public Param(String name, Object value, byte[] values) {
        this.name = name;
        this.value = value;
        this.values = values;
    }

    public Param(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Param() {
    }

    public Param(String name, byte[] values) {
        this.name = name;
        this.values = values;
    }

  
    
    public String getValueAsString() {
        return (String) this.value;
    }

    public boolean getValueAsBoolean() {
     return Boolean.parseBoolean(this.value.toString());
    
    }

    public float getAsFloat() {
    return Float.parseFloat(this.value.toString());  
    }
}
