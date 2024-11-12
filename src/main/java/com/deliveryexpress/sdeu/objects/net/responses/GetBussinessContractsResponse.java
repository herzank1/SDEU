/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.contability.BussinesContract;
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
public class GetBussinessContractsResponse extends Response {

    @Expose
    ArrayList<BussinesContract> contracts;

    public GetBussinessContractsResponse(Command command, String status) {
        super(command, status);
    }

    public GetBussinessContractsResponse( Command _command, String status,ArrayList<BussinesContract> sessions) {
        super(_command, status);
        this.contracts = contracts;
    }

}
