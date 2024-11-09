/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.commands;

import com.deliveryexpress.sdeu.objects.location.Position;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChangeOrderStatusCommand extends Command{
     @Expose
    String orderId;
      @Expose
    String status;
       @Expose
    String arrivedTo; /*Use only by delivery*/
             @Expose
    String reson;
        @Expose
    String position;
    
    /***
     * 
     * @param orderId
     * @param status let arrived null for change order status
     * @param arrivedTo let status null for set ArrivedTo
     * @param reason
     * @param position 
     */

    public ChangeOrderStatusCommand(String orderId, String status,String arrivedTo,String reason,String position) {
        super();
        super.setCommand("changeOrderStatus");
        this.orderId = orderId;
        this.status = status;
        this.arrivedTo = arrivedTo;
        this.reson = reson;
        this.position = position;
    }
    
     public Position getPosition() {
        return new Position(position);

    }
     
   

    
}
