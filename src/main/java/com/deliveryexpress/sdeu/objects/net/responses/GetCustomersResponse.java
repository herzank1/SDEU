/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
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
public class GetCustomersResponse extends Response {

    @Expose
    ArrayList<Customer> customers;

    public GetCustomersResponse(Command command, String status) {
        super(command, status);
    }

    public GetCustomersResponse( Command _command, String status,ArrayList<Customer> customers) {
        super(_command, status);
        this.customers = customers;
    }
    
    

}
