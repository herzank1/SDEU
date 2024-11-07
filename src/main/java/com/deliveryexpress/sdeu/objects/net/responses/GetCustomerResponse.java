/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GetCustomerResponse extends Response {

    @Expose
    Customer customer;

    public GetCustomerResponse(Command command, String status) {
        super(command, status);
    }

    public GetCustomerResponse(Command _command, String status,Customer customer) {
        super(_command, status);
        this.customer = customer;
    }
    
    

}
