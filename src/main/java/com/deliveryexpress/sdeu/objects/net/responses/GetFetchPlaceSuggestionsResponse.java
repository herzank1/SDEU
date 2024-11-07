/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

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
public class GetFetchPlaceSuggestionsResponse extends Response {

    @Expose
    ArrayList<String> fetchPlaceSuggestions;

    public GetFetchPlaceSuggestionsResponse(Command command, String status) {
        super(command, status);
    }

    public GetFetchPlaceSuggestionsResponse( Command _command, String status,ArrayList<String> fetchPlaceSuggestions) {
        super(_command, status);
        this.fetchPlaceSuggestions = fetchPlaceSuggestions;
    }
    
    

}
