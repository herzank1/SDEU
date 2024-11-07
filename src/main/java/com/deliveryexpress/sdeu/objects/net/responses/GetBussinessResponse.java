/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.Bussines;
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
public class GetBussinessResponse extends Response {

    @Expose
    ArrayList<Bussines> bussines;

    public GetBussinessResponse(Command command, String status) {
        super(command, status);
    }

    public GetBussinessResponse( Command _command, String status,ArrayList<Bussines> bussines) {
        super(_command, status);
        this.bussines = bussines;
    }
    
    

}
