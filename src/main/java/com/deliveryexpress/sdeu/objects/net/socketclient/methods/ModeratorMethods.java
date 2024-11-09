/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.methods;

import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.deliveryexpress.sdeu.objects.Moderator;
import com.deliveryexpress.sdeu.objects.User;
import com.deliveryexpress.sdeu.objects.contability.BalanceAccount;
import com.deliveryexpress.sdeu.objects.net.Param;
import com.deliveryexpress.sdeu.objects.net.commands.BussinesNewOrderCommand;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.net.commands.ModeratorNewUserAccountCommand;
import com.deliveryexpress.sdeu.objects.net.commands.ModeratorUpdateObjectCommand;
import com.deliveryexpress.sdeu.objects.net.commands.ModeratorUpdateOrderCommand;
import com.deliveryexpress.sdeu.objects.net.responses.GetBussinessResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetCustomersResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetDeliveriesResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetOrderResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetUsersResponse;
import com.deliveryexpress.sdeu.objects.net.responses.ModeratorUpdateObjectResponse;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketClient;
import com.deliveryexpress.sdeu.objects.orders.Order;
import com.google.gson.JsonObject;

/**
 *
 * @author DeliveryExpress
 */
public class ModeratorMethods extends DefaultMethods {
/***
 * 
 * @param user
 * @param bussines
 * @param balanceAccount 
 */
    public static void newUserResgistration(User user, Bussines bussines, BalanceAccount balanceAccount) {

        ModeratorNewUserAccountCommand mnuaCommand = new ModeratorNewUserAccountCommand(user, bussines, balanceAccount);
        SocketClient.execute(mnuaCommand);

    }

    /***
     * 
     * @param user
     * @param deliveri
     * @param balanceAccount 
     */
    public static void newUserResgistration(User user, Delivery deliveri, BalanceAccount balanceAccount) {

        ModeratorNewUserAccountCommand mnuaCommand = new ModeratorNewUserAccountCommand(user, deliveri, balanceAccount);
        SocketClient.execute(mnuaCommand);

    }
    /***
     * 
     * @param user
     * @param moderator
     * @param balanceAccount 
     */
    public static void newUserResgistration(User user, Moderator moderator, BalanceAccount balanceAccount) {

        ModeratorNewUserAccountCommand mnuaCommand = new ModeratorNewUserAccountCommand(user, moderator, balanceAccount);
        SocketClient.execute(mnuaCommand);

    }
    
      /***
     * remueve el repartidor asignado
     * @param orderId
     * @param deliveryId
     * @return 
     */
    public static GetOrderResponse removeOrdersDeliveryBeforePickUp(Order order) {
        if (order.getDelivery() != null) {
            order.addCanceler(order.getDelivery().getId());
            order.addLog("REMOVE DELIVERY", "", "MODERATOR");
            order.deliveryConfirmed = false;
            order.deliveryArrivedToBussines = false;
            order.setDelivery(null);
            GetOrderResponse updateOrder = updateOrder(order);
            return updateOrder;
        } else {

            GetOrderResponse orderResponse = new GetOrderResponse(null, "fail",null);
            orderResponse.setMensaje("Esta orden no tiene repartidor");
            return orderResponse;

        }

    }
    
    /***
     * Asignacion manual de ordenes
     * @param orderId
     * @param deliveryId
     * @return 
     */
    public static Response assignOrderToDelivery(String orderId, String deliveryId) {

        Command command = new Command();
        command.setCommand("orderAsigned");
        command.addParam(new Param("oid", orderId));
        command.addParam(new Param("did", deliveryId));
        JsonObject execute_R = SocketClient.execute_R(command,2000);
        Response gcResponse = SocketClient.gson.fromJson(execute_R, Response.class);
        return gcResponse;
    }
    
     public static GetOrderResponse newOrder(Order order) {

        BussinesNewOrderCommand command = new BussinesNewOrderCommand(order);
        // Ejecuta el comando en el cliente de socket
        JsonObject executeR = SocketClient.execute_R(command,2000L);
         GetOrderResponse anoResponse = SocketClient.gson.fromJson(executeR, GetOrderResponse.class);

        return anoResponse;
    }
    
   
    
     /***
      * Actualiza el estado o la informacion de una orden
      * @param order orden con la informacion actualizada, usarse con seguridad
      * @return 
      */
    public static GetOrderResponse updateOrder(Order order) {

        ModeratorUpdateOrderCommand command = new ModeratorUpdateOrderCommand(order);
        command.setCommand("updateOrder");

        JsonObject executeR = SocketClient.execute_R(command, 1500L);
        GetOrderResponse muoResponse = SocketClient.gson.fromJson(executeR, GetOrderResponse.class);

        return muoResponse;

    }
         
    public static void getConectedDeliveries() {
        Command command = new Command();
        command.setCommand("getConectedDeliveries");

        SocketClient.execute(command);
    }
          
           
          /***
           * Activa o desactiva el asignador automatico
           * @param action 
           */
    public static void switchAtm(String action) {
        Command command = new Command();
        command.setCommand("switchAtmAsignator");

        switch (action) {

            case "get" -> command.addParam(new Param("action", "get"));

            case "switch" -> command.addParam(new Param("action", "switch"));
        }

        SocketClient.execute(command);
    }
    
       public static GetUsersResponse getUsersFromDb() {
        Command command = new Command();
        command.setCommand("getUsersFromDb");
        JsonObject execute_R = SocketClient.execute_R(command,2000L);
        GetUsersResponse guResponse = SocketClient.gson.fromJson(execute_R, GetUsersResponse.class);

        return guResponse;
    }

    public static GetBussinessResponse getBussinesFromDb() {
        Command command = new Command();
        command.setCommand("getBussinesFromDb");
        JsonObject execute_R = SocketClient.execute_R(command,2000L);
        GetBussinessResponse gbResponse = SocketClient.gson.fromJson(execute_R, GetBussinessResponse.class);

        return gbResponse;
    }

    public static GetDeliveriesResponse getDeliveriesFromDb() {
        Command command = new Command();
        command.setCommand("getDeliveriesFromDb");
         JsonObject execute_R = SocketClient.execute_R(command,2000L);
        GetDeliveriesResponse gdResponse = SocketClient.gson.fromJson(execute_R, GetDeliveriesResponse.class);

        return gdResponse;
    }
    
      public static GetCustomersResponse getCustomersFromDb() {

        Command command = new Command();
        command.setCommand("getCustomersFromDb");
          JsonObject execute_R = SocketClient.execute_R(command,2000L);
        GetCustomersResponse gcResponse = SocketClient.gson.fromJson(execute_R, GetCustomersResponse.class);

        return gcResponse;
    }


    public static void getSessions() {

        Command command = new Command();
        command.setCommand("getSessions");
        SocketClient.execute(command);
    }
    
      public static ModeratorUpdateObjectResponse updateObjectInDb(Object obj, Class clazz) {

        ModeratorUpdateObjectCommand command = new ModeratorUpdateObjectCommand(obj,clazz);
        JsonObject executeR = SocketClient.execute_R(command, 2000);
        ModeratorUpdateObjectResponse muoResponse = SocketClient.gson.fromJson(executeR, ModeratorUpdateObjectResponse.class);

        return muoResponse;

    }

    
}
