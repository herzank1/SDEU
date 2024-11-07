/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeliveryOrderConfirmationCommand extends Command {
    String orderId;
    boolean confirm;

    public DeliveryOrderConfirmationCommand(String orderId, boolean confirm) {
        super();
        super.setCommand("confirmOrderOrCancel");
        this.orderId = orderId;
        this.confirm = confirm;
    }
    
    
    
}
