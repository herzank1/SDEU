/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.commands;

import com.deliveryexpress.sdeu.objects.location.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryChangeOrderStatusCommand extends Command{
    String orderId;
    String status;
    String arrivedTo;
    String position;
    
    /***
     * 
     * @param orderId
     * @param status let arrived null for change order status
     * @param arrivedTo let status null for set ArrivedTo
     * @param position 
     */

    public DeliveryChangeOrderStatusCommand(String orderId, String status,String arrivedTo,String position) {
        super();
        super.setCommand("changeOrderStatus");
        this.orderId = orderId;
        this.status = status;
        this.arrivedTo = arrivedTo;
        this.position = position;
    }
    
     public Position getPosition() {
        return new Position(position);

    }
     
   

    
}
