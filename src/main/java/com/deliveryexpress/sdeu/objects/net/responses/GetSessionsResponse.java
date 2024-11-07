/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.session.UserSession;
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
public class GetSessionsResponse extends Response {

    @Expose
    ArrayList<UserSession> sessions;

    public GetSessionsResponse(Command command, String status) {
        super(command, status);
    }

    public GetSessionsResponse( Command _command, String status,ArrayList<UserSession> sessions) {
        super(_command, status);
        this.sessions = sessions;
    }

}
