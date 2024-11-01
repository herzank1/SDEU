/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects;

import lombok.Data;

/**
 * Representacion o resumen en de un repartidor en linea, usarse para enviar 
 * solo lo necesario del estado de conceccion de un repartidor
 * @author DeliveryExpress
 */

@Data
public class DeliveryDTO {
    private String id;
    private String name;
    private String position;
    private boolean connected;

    // Constructor
    public DeliveryDTO(String id, String name, String position, boolean connected) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.connected = connected;
    }

 
}
