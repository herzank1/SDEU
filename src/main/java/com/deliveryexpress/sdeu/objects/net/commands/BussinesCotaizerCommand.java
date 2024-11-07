/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.commands;

import com.deliveryexpress.sdeu.objects.location.Position;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BussinesCotaizerCommand extends Command {
    
    String fromAddress;
    String toAddress;
    String fromPosition;
    String toPosition;

    public BussinesCotaizerCommand(String fromAddress, String toAddress) {
        super();
        super.setCommand("cotaizer");
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
    }
    
    public BussinesCotaizerCommand (Position from,Position to){
        super();
        super.setCommand("cotaizer");
        this.fromPosition = from.toString();
        this.toPosition = to.toString();
    
    }
    
    
    
    
    
}
