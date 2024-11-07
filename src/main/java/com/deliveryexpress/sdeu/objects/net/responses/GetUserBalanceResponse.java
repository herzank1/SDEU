/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.contability.BalanceAccount;
import com.deliveryexpress.sdeu.objects.contability.Transacction;
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
public class GetUserBalanceResponse extends Response {

    @Expose
    BalanceAccount bal;
    @Expose
    ArrayList<Transacction> txs;

    public GetUserBalanceResponse(Command command, String status) {
        super(command, status);
    }

    public GetUserBalanceResponse(Command _command, String status,BalanceAccount bal, ArrayList<Transacction> txs) {
        super(_command, status);
        this.bal = bal;
        this.txs = txs;
    }
    
    

}
