/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient;

import com.deliveryexpress.sdeu.objects.net.responses.GetDeliveriesResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetOrderResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetOrdersResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetSessionResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetSessionsResponse;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.net.socketclient.interfaces.BussinesResponseListener;
import com.deliveryexpress.sdeu.objects.net.socketclient.interfaces.DeliveryResponseListener;
import com.deliveryexpress.sdeu.objects.net.socketclient.interfaces.ModeratorResponseListener;
import com.google.gson.JsonObject;

/**
 *
 * @author DeliveryExpress
 */
class Tigger {
    
    private static BussinesResponseListener brListener;
    private static DeliveryResponseListener drListener;
    private static ModeratorResponseListener modListener;

    static void execute(JsonObject obj) {
     
        
        switch (obj.get("command").getAsString()) {

            case "loggin":
                GetSessionResponse getSessionResponse = SocketClient.gson.fromJson(obj, GetSessionResponse.class);
                brListener.onLogginSuccess(getSessionResponse);
                
                drListener.onLogginSuccess(getSessionResponse);
                break;

            case "loggingWithSessionId":
                getSessionResponse = SocketClient.gson.fromJson(obj, GetSessionResponse.class);
                brListener.onLogginWithSessionId(getSessionResponse);
                
                drListener.onLogginSuccess(getSessionResponse);
                break;

            case "updateOrder":
            case "getCurrentOrders":

                GetOrdersResponse rOrders = SocketClient.gson.fromJson(obj, GetOrdersResponse.class);
                brListener.onCurrentsOrderReceive(rOrders);
                
                drListener.onCurrentsOrderReceive(rOrders);

                break;

            case "getFinishedOrders":
                rOrders = SocketClient.gson.fromJson(obj, GetOrdersResponse.class);
                brListener.onFinishedOrderReceive(rOrders);
                
                drListener.onFinishedOrderReceive(rOrders);
                break;

            case "addNewOrder":
                Response response = SocketClient.gson.fromJson(obj, Response.class);
                brListener.onNewOrderConfirmation(response);

                break;

            case "orderAsigned":
                GetOrderResponse getOrderResponse = SocketClient.gson.fromJson(obj, GetOrderResponse.class);
                drListener.onNewOrderReceived(getOrderResponse);
                break;
                
            case "getConectedDeliveries":

                GetDeliveriesResponse getDeliveriesResponse = SocketClient.gson.fromJson(obj, GetDeliveriesResponse.class);
                modListener.onGetConnectedDeliveries(getDeliveriesResponse);

                break;

            case "getSessions":

                GetSessionsResponse getSessionsResponse = SocketClient.gson.fromJson(obj, GetSessionsResponse.class);
                modListener.onGetSessions(getSessionsResponse);

                break;

        }

    
    
    }
    
}
