/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.interfaces;

import com.deliveryexpress.sdeu.objects.net.responses.GetOrdersResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetSessionResponse;
import com.google.gson.JsonObject;

/**
 *
 * @author DeliveryExpress
 */
public interface ResponseListener {
    
    void onResponseReceive(JsonObject toJsonObject);
    
    void onCurrentsOrderReceive(GetOrdersResponse getOrdersResponse);
    
    void onFinishedOrderReceive(GetOrdersResponse getOrdersResponse);
   
    
    void onLogginWithSessionIdResponse(GetSessionResponse getSessionResponse);
    
    void onLogginResponse(GetSessionResponse getSessionResponse);
 
    
}
