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
import com.deliveryexpress.sdeu.objects.net.socketclient.interfaces.BussinesResponseListener;
import com.deliveryexpress.sdeu.objects.net.socketclient.interfaces.DeliveryResponseListener;
import com.deliveryexpress.sdeu.objects.net.socketclient.interfaces.ModeratorResponseListener;
import com.google.gson.JsonObject;
import lombok.Data;

/**
 *
 * @author DeliveryExpress Tigger es el disparador de todos los eventos de los
 * usuarios los listeners personalizados implementan funciones basicas las
 * cuales ya estan en el swich cualquier comando que no se encuentre en el
 * swicth se lanzara como void onResponseReceive(JsonObject toJsonObject);
 */
@Data
public class Tigger {

    private BussinesResponseListener brListener;
    private DeliveryResponseListener drListener;
    private ModeratorResponseListener modListener;

    void execute(JsonObject obj) {
        switch (obj.get("command").getAsString()) {

            case "loggin" -> {
                GetSessionResponse getSessionResponse = SocketClient.gson.fromJson(obj, GetSessionResponse.class);
                if (brListener != null) {
                    brListener.onLogginResponse(getSessionResponse);
                }
                if (drListener != null) {
                    drListener.onLogginResponse(getSessionResponse);
                }

                if (modListener != null) {
                    modListener.onLogginResponse(getSessionResponse);
                }
            }

            case "loggingWithSessionId" -> {
                GetSessionResponse getSessionResponse = SocketClient.gson.fromJson(obj, GetSessionResponse.class);
                if (brListener != null) {
                    brListener.onLogginWithSessionIdResponse(getSessionResponse);
                }
                if (drListener != null) {
                    drListener.onLogginWithSessionIdResponse(getSessionResponse);
                }
                if (modListener != null) {
                    modListener.onLogginWithSessionIdResponse(getSessionResponse);
                }
            }

            case "updateOrder", "getCurrentOrders" -> {
                GetOrdersResponse rOrders = SocketClient.gson.fromJson(obj, GetOrdersResponse.class);
                if (brListener != null) {
                    brListener.onCurrentsOrderReceive(rOrders);
                }
                if (drListener != null) {
                    drListener.onCurrentsOrderReceive(rOrders);
                }

                if (modListener != null) {
                    modListener.onCurrentsOrderReceive(rOrders);
                }
            }
            case "getFinishedOrders" -> {
                GetOrdersResponse rOrders = SocketClient.gson.fromJson(obj, GetOrdersResponse.class);
                if (brListener != null) {
                    brListener.onFinishedOrderReceive(rOrders);
                }
                if (drListener != null) {
                    drListener.onFinishedOrderReceive(rOrders);
                }
                if (modListener != null) {
                    modListener.onFinishedOrderReceive(rOrders);
                }
            }

            
            case "orderAsigned" -> {
                GetOrderResponse getOrderResponse = SocketClient.gson.fromJson(obj, GetOrderResponse.class);
                if (drListener != null) {
                    drListener.onNewOrderReceived(getOrderResponse);
                }
            }

            case "getConectedDeliveries" -> {
                GetDeliveriesResponse getDeliveriesResponse = SocketClient.gson.fromJson(obj, GetDeliveriesResponse.class);
                if (modListener != null) {
                    modListener.onGetConnectedDeliveries(getDeliveriesResponse);
                }
            }

            case "getSessions" -> {
                GetSessionsResponse getSessionsResponse = SocketClient.gson.fromJson(obj, GetSessionsResponse.class);
                if (modListener != null) {
                    modListener.onGetSessions(getSessionsResponse);
                }
            }

            default -> {
                if (brListener != null) {
                    brListener.onResponseReceive(obj);
                }
                if (drListener != null) {
                    drListener.onResponseReceive(obj);
                }

                if (modListener != null) {
                    modListener.onResponseReceive(obj);
                }
            }
        }
    }

}
