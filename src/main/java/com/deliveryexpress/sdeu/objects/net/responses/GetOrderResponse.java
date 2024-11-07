/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.orders.Order;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GetOrderResponse extends Response {

    @Expose
    Order order;

    public GetOrderResponse(String jsonString) {
        super(jsonString);
    }
    
    

    public GetOrderResponse(Command command, String status) {
        super(command, status);
    }

    public GetOrderResponse( Command _command, String status,Order order) {
        super(_command, status);
        this.order = order;
    }
    
    

}
