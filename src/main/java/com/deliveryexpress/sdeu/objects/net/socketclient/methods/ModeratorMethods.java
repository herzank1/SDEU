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
import com.deliveryexpress.sdeu.objects.location.Position;
import com.deliveryexpress.sdeu.objects.net.Param;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.net.commands.ModeratorNewUserAccountCommand;
import com.deliveryexpress.sdeu.objects.net.commands.ModeratorUpdateObjectCommand;
import com.deliveryexpress.sdeu.objects.net.responses.ModeratorUpdateObjectResponse;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketClient;
import com.deliveryexpress.sdeu.objects.orders.Order;
import com.deliveryexpress.sdeu.sqlitedatabase.DbBalancer;
import com.google.gson.JsonObject;

/**
 *
 * @author DeliveryExpress
 */
public class ModeratorMethods extends DefaultMethods {
       public static void newUserResgistration(User user, Bussines bussines, BalanceAccount balanceAccount){

        ModeratorNewUserAccountCommand mnuaCommand = new ModeratorNewUserAccountCommand(user,bussines,balanceAccount);
        SocketClient.execute(mnuaCommand);
    
    }
          public static void newUserResgistration(User user, Delivery deliveri, BalanceAccount balanceAccount){

        ModeratorNewUserAccountCommand mnuaCommand = new ModeratorNewUserAccountCommand(user,deliveri,balanceAccount);
        SocketClient.execute(mnuaCommand);
    
    }
    
    public static void newUserResgistration(User user, Moderator moderator, BalanceAccount balanceAccount){

        ModeratorNewUserAccountCommand mnuaCommand = new ModeratorNewUserAccountCommand(user,moderator,balanceAccount);
        SocketClient.execute(mnuaCommand);
    
    }
    
    public static Response assignOrderToDelivery(String orderId, String deliveryId) {

        Command command = new Command();
        command.setCommand("orderAsigned");
        command.addParam(new Param("oid", orderId));
        command.addParam(new Param("did", deliveryId));
        JsonObject execute_R = SocketClient.execute_R(command,2000);
        Response gcResponse = SocketClient.gson.fromJson(execute_R, Response.class);
        return gcResponse;
    }
  
    
     public static void cancelOrder(Order order) {

      Command command = new Command();
      command.setCommand("confirmOrderOrCancel");
      command.addParam(new Param("oid", order.getId()));
      command.addParam(new Param("value", "false"));
      SocketClient.execute(command);
    }
     
     
     public static void changeOrderStatus(){
     
     }
     
         public static void updateOrder(Order selectedOrder) {

        Command command = new Command();
        command.setCommand("updateOrder");
        command.addParam(new Param("order", selectedOrder));

        SocketClient.execute(command);
    }
         
          public static void getConectedDeliveries() {
        Command command = new Command();
        command.setCommand("getConectedDeliveries");

        SocketClient.execute(command);
    }
          
            
    public static void switchAtm(String action) {
        Command command = new Command();
        command.setCommand("switchAtmAsignator");

        switch (action) {

            case "get":
                command.addParam(new Param("action", "get"));
                break;

            case "switch":
                command.addParam(new Param("action", "switch"));
                break;
        }

        SocketClient.execute(command);
    }
    
       public static void getUsersFromDb() {
        Command command = new Command();
        command.setCommand("getUsersFromDb");
        SocketClient.execute(command);
    }

    public static void getBussinesFromDb() {
        Command command = new Command();
        command.setCommand("getBussinesFromDb");
        SocketClient.execute(command);
    }

    public static void getDeliveriesFromDb() {
        Command command = new Command();
        command.setCommand("getDeliveriesFromDb");
        SocketClient.execute(command);
    }

    @Deprecated
    public static   void updateObjectInDB(Object object, Class clazz) {

        ModeratorUpdateObjectCommand command = new ModeratorUpdateObjectCommand(object,clazz);

        SocketClient.execute(command);

    }
    
       public static void getCustomersFromDb() {

        Command command = new Command();
        command.setCommand("getCustomersFromDb");
        SocketClient.execute(command);
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
