/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects;

import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 *
 * @author DeliveryExpress
 */
@Data
public class SystemInfo {

    @Expose
    String code;
    @Expose
    int connectedDeliveries;
    @Expose
    int currentOrdersCount;
    @Expose
    float dinamicDeliveryFee;
    @Expose
    float dinamicSericesCost;

    /**
     * *
     *
     * @param code
     * @param connectedDeliveries
     * @param currentOrdersCount
     * @param dinamicDeliveryFee
     * @param dinamicSericesCost
     */
    public SystemInfo(String code, int connectedDeliveries, int currentOrdersCount, float dinamicDeliveryFee, float dinamicSericesCost) {
        this.code = code;
        this.connectedDeliveries = connectedDeliveries;
        this.currentOrdersCount = currentOrdersCount;
        this.dinamicDeliveryFee = dinamicDeliveryFee;
        this.dinamicSericesCost = dinamicSericesCost;
    }

    public interface Codes {

        String OPTIMO = "OPTIMO";
        String MODERADO = "MODERADO";
        String SATURADO = "SATURADO";

    }

}
