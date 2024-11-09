/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.methods;

import com.deliveryexpress.sdeu.objects.net.Param;
import com.deliveryexpress.sdeu.objects.net.commands.ChangeOrderStatusCommand;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.net.responses.GetUserBalanceResponse;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketClient;
import com.deliveryexpress.sdeu.objects.orders.OrderStatus;
import com.google.gson.JsonObject;

/**
 *
 * @author DeliveryExpress
 */
public class DefaultMethods {

    /**
     * *
     * Envia los datos de loggin al servidor
     *
     * @param user
     * @param pass
     */
    public static void sendLoggin(String user, String pass) {
        Command command = new Command();
        command.setCommand("loggin");
        command.addParam(new Param("user", user));
        command.addParam(new Param("pass", pass));

        SocketClient.execute(command);
    }

    public static void logginWithSession() {

        Command command = new Command();
        command.setCommand("loggingWithSessionId");
        SocketClient.execute(command);

    }

    public static void getFinishedOrders() {
        Command command = new Command();
        command.setCommand("getFinishedOrders");
        SocketClient.execute(command);

    }

    public static void getCurrentOrders() {
        Command command = new Command();
        command.setCommand("getCurrentOrders");
        SocketClient.execute(command);

    }

    public static GetUserBalanceResponse getBalance() {

        Command command = new Command();
        command.setCommand("getBalance");

        JsonObject executeR = SocketClient.execute_R(command, 300);
        GetUserBalanceResponse gubResponse = SocketClient.gson.fromJson(executeR, GetUserBalanceResponse.class);
        return gubResponse;

    }
    
    
    public static Response getConnectionStatus() {
        Command command = new Command();
        command.setCommand("getConnectionStatus");

        JsonObject executeR = SocketClient.execute_R(command, 300);
        Response gcsResponse = SocketClient.gson.fromJson(executeR, Response.class);
        return gcsResponse;

    }

    public static Response switchConnectionStatus() {
        Command command = new Command();
        command.setCommand("switchConnectionStatus");

        JsonObject executeR = SocketClient.execute_R(command, 300);
        Response scsResponse = SocketClient.gson.fromJson(executeR, Response.class);
        return scsResponse;

    }
    
      /***
     * cancela una orden que no haya sido reclectada
     * @param orderId
     * @param reason
     * @return true si se cambio el estado en el servidor
     */
    public static Response cancelOrder(String orderId,String reason) {

        return changeOrderStatus(orderId, OrderStatus.CANCELADO,null,reason,null);
    }

    
        /***
     * cambia el estado de una orden de Bussines
     * @param orderId
     * @param status
     * @param arrivedTo (Use by delivery only)
     * @param reason
     * @param position
     * @return true si se cambio el estado en el servidor
     */
    public static Response changeOrderStatus(String orderId, String status,String arrivedTo,String reason,String position) {

        ChangeOrderStatusCommand command = new ChangeOrderStatusCommand(orderId,status,arrivedTo,reason,position);
        JsonObject executeR = SocketClient.execute_R(command, 1500);
        Response cosResponse = SocketClient.gson.fromJson(executeR, Response.class);
        return cosResponse;
    }


}
