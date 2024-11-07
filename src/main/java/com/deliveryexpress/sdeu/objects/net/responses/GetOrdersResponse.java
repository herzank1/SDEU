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
public class GetOrdersResponse extends Response {

    @Expose
    ArrayList<Order> orderList;

    public GetOrdersResponse(Command command, String status) {
        super(command, status);
    }

    public GetOrdersResponse( Command _command, String status,ArrayList<Order> orderList) {
        super(_command, status);
        this.orderList = orderList;
    }
    
    

}
