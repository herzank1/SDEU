/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

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
public class GetSessionResponse extends Response{
     @Expose
    UserSession session;

    public GetSessionResponse(Command command, String status) {
        super(command, status);
    }

    public GetSessionResponse( Command _command, String status,UserSession session) {
        super(_command, status);
        this.session = session;
    }
    
    
    
}
