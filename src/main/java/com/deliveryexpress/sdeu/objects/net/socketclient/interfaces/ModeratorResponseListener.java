/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.interfaces;

import com.deliveryexpress.sdeu.objects.net.responses.GetDeliveriesResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetSessionsResponse;

/**
 *
 * @author DeliveryExpress
 * metodos implementados no son execute_R
 */
public interface ModeratorResponseListener extends ResponseListener{
    
    public void onGetSessions(GetSessionsResponse getSessionsResponse);
    
    public void onGetConnectedDeliveries(GetDeliveriesResponse getDeliveriesResponse);
    
    
   
    
}
