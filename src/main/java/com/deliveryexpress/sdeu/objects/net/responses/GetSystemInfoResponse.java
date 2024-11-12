/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.SystemInfo;
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
public class GetSystemInfoResponse extends Response{
     @Expose
    SystemInfo systemInfo;

    public GetSystemInfoResponse(Command command, String status) {
        super(command, status);
    }

    public GetSystemInfoResponse( Command _command, String status,SystemInfo systemInfo) {
        super(_command, status);
        this.systemInfo = systemInfo;
    }
    
    
    
}
