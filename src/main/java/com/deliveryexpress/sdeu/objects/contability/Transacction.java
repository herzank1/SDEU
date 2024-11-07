/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.contability;


import com.deliveryexpress.sdeu.utils.DateUtils;
import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class Transacction {

    @DatabaseField(id = true)
    @Expose
    String id;
    @DatabaseField
    @Expose
    String from;
    @DatabaseField
    @Expose
    String to;
    @DatabaseField
    @Expose
    float mount;
    @DatabaseField
    @Expose
    String date;
    @DatabaseField
    @Expose
    String concept;
    @DatabaseField
    @Expose
    String ref;
     @DatabaseField
    @Expose
    String msg;

    public Transacction() {

        this.id = UUID.randomUUID().toString();
        this.from = "";
        this.to = "";
        this.mount = 0f;
        this.date = DateUtils.now();
        this.concept = "null";
        this.ref = "";
        this.msg = "";
    }

    /***
     * 
     * @param id
     * @param from
     * @param to
     * @param mount
     * @param concept aqui se almacena la cuenta asociada a este concepto, la cual 
     * se debera usar cuando el usuario relize un pago a la plataforma y esta pueda redirigirse ala cuenta asociada
     * @param ref 
     */
    public Transacction(String id, String from, String to, float mount, String concept, String ref) {
        this.id = UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.mount = mount;
        this.date = DateUtils.now();
        this.concept = concept;
        this.ref = ref;
         this.msg = "";
    }



}
