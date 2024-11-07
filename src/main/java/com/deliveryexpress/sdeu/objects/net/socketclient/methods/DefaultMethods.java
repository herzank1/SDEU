/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.methods;

import com.deliveryexpress.sdeu.objects.net.Param;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.net.responses.GetUserBalanceResponse;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketClient;
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

    public static GetUserBalanceResponse getBalance_R() {

        Command command = new Command();
        command.setCommand("getBalance");

        JsonObject executeR = SocketClient.execute_R(command, 300);
        GetUserBalanceResponse gubResponse = SocketClient.gson.fromJson(executeR, GetUserBalanceResponse.class);
        return gubResponse;

    }
    
    
     public static void getConnectionStatus() {

      Command command = new Command();
      command.setCommand("getConnectionStatus");
      SocketClient.execute(command);

    }

}
