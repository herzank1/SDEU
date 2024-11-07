/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.commands;

import com.deliveryexpress.sdeu.objects.orders.Order;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BussinesNewOrderCommand extends Command{
    Order order;

    public BussinesNewOrderCommand(Order order) {
        super();
        super.setCommand("addNewOrder");
        this.order = order;
    }
    
    
    
}
