/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.interfaces;

import com.deliveryexpress.sdeu.objects.net.responses.GetOrdersResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetSessionResponse;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketListener;

/**
 *
 * @author DeliveryExpress
 */
public interface ResponseListener {
    
    void onCurrentsOrderReceive(GetOrdersResponse getOrdersResponse);
    
    void onFinishedOrderReceive(GetOrdersResponse getOrdersResponse);
   
    
    void onLogginWithSessionId(GetSessionResponse getSessionResponse);
    
    void onLogginSuccess(GetSessionResponse getSessionResponse);
    
   // void onMessageReceived();
    
   // void onNotificationReceived();

    
    
    
}
