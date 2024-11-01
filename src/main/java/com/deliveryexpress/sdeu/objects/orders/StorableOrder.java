/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.orders;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import lombok.Data;

/**
 *
 * @author DeliveryExpress
 * Contiene la informacion basica de la orden ,usarse para almacenar y disminuir el tamaño
 * evitando guardar variables inecesarias de la Order.class
 */

@Data
public class StorableOrder {

    @Expose
    @DatabaseField(id = true) // Campo ID
    String id;
    @Expose
    @DatabaseField
    String creationDate;
    @Expose
    @DatabaseField
    String status;
    @Expose
    @DatabaseField
    String bussinesId;
    @Expose
    @DatabaseField
    String bussinesName;
    @Expose
    @DatabaseField
    String deliveryId;
    @Expose
    @DatabaseField
    String deliveryName;
    @Expose
    @DatabaseField
    String orderCost;
    @Expose
    @DatabaseField
    String deliveryCost;
    @Expose
    @DatabaseField
    String log;
    @Expose
    @DatabaseField
    String cancelers;

    public StorableOrder() {
    }

    public StorableOrder(Order order) {
        this.id = order.getId();
        this.creationDate = order.getCreationDate();
        this.status = order.getId();

        this.bussinesId = order.getId();
        this.bussinesName = order.getId();

        if (order.getDelivery() != null) {
            this.deliveryId = order.getId();
            this.deliveryName = order.getId();
        } else {

            this.deliveryId = "null";
            this.deliveryName = "Sin repartidor";
        }

        this.orderCost = order.getId();
        this.deliveryCost = order.getId();

        this.log = order.getId();
        this.cancelers = order.getId();

    }
    
    
    
}
