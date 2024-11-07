/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.User;
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
public class GetUsersResponse extends Response {

    @Expose
    ArrayList<User> users ;

    public GetUsersResponse(Command command, String status) {
        super(command, status);
    }

    public GetUsersResponse( Command _command, String status,ArrayList<User> users) {
        super(_command, status);
        this.users = users;
    }

    
    

}
