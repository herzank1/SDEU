/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.interfaces;

import com.deliveryexpress.sdeu.objects.net.responses.GetOrderResponse;

/**
 *
 * @author DeliveryExpress
 */
public interface DeliveryResponseListener extends ResponseListener{
    
      void onNewOrderReceived(GetOrderResponse getOrderResponse);
    
}
