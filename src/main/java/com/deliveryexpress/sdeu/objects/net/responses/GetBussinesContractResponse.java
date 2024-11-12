/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.contability.BussinesContract;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.session.UserSession;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GetBussinesContractResponse extends Response{
     @Expose
    BussinesContract contract;

    public GetBussinesContractResponse(Command command, String status) {
        super(command, status);
    }

    public GetBussinesContractResponse( Command _command, String status,BussinesContract contract) {
        super(_command, status);
        this.contract = contract;
    }
    
    
    
}
