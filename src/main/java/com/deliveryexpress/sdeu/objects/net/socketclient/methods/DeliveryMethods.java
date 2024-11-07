/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.methods;

import com.deliveryexpress.sdeu.objects.location.Position;
import com.deliveryexpress.sdeu.objects.net.Param;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketClient;
import com.deliveryexpress.sdeu.objects.orders.Order;

/**
 *
 * @author DeliveryExpress
 */
public class DeliveryMethods {
    
     public static void confirmOrder(Order order) {
      Command command = new Command();
      command.setCommand("confirmOrderOrCancel");
      command.addParam(new Param("oid", order.getId()));
      command.addParam(new Param("value", "true"));
      SocketClient.execute(command);
    }
     
      /***
     * Actualiza la posicion en el servidor
     * @param position
     */
    public static void updatePosition(Position position) {

      Command command = new Command();
      command.setCommand("locationUpdate");
      command.addParam(new Param("position", position.toString()));
      SocketClient.execute(command);
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

    
}
