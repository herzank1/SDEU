/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.methods;

import com.deliveryexpress.sdeu.objects.AccountType;
import com.deliveryexpress.sdeu.objects.location.Position;
import com.deliveryexpress.sdeu.objects.net.Param;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.net.commands.DeliveryOrderConfirmationCommand;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketClient;
import com.deliveryexpress.sdeu.objects.orders.OrderStatus;
import com.google.gson.JsonObject;

/**
 *
 * @author DeliveryExpress
 */
public class DeliveryMethods extends DefaultMethods {
    
   
     
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
    
    public static Response confirmOrder(String orderId) {
        DeliveryOrderConfirmationCommand command = new DeliveryOrderConfirmationCommand(orderId, true);
        JsonObject execute_R = SocketClient.execute_R(command, 1500);
        Response coResponse = SocketClient.gson.fromJson(execute_R, Response.class);
        return coResponse;
    }

    public static Response rejectlOrder(String orderId) {
        DeliveryOrderConfirmationCommand command = new DeliveryOrderConfirmationCommand(orderId, false);
        JsonObject execute_R = SocketClient.execute_R(command, 1500);
        Response coResponse = SocketClient.gson.fromJson(execute_R, Response.class);
        return coResponse;
    }
    
    /***
     * Indica que llego al  negocio, esto cambia la variable boolean Order.arrivedToBusines
     * @param orderId
     * @param position
     * @return 
     */
    public static Response setArrivedToBussines(String orderId, String position) {
        return changeOrderStatus(orderId, null, AccountType.BUSSINES,null, position);
    }
    
 
    
    /***
     * Indica recoleccion del pedido del negocio y cambia el estado de la orden en EN_CAMINO
     * @param orderId
     * @param position
     * @return 
     */
    public static Response setPickedUp(String orderId, String position) {
        return changeOrderStatus(orderId, OrderStatus.EN_CAMINO, null,null, position);
    }
    
    /***
     * Indica llegada con el cliente y cambia el estado de la orden en EN_DOMICILIO
     * @param orderId
     * @param position
     * @return 
     */
    public static Response setArrivedToCustomer(String orderId, String position) {
        return changeOrderStatus(orderId, OrderStatus.EN_DOMICILIO, null,null, position);
    }
    
       /***
     * cambia el estado de la orden en ENTREGADO
     * @param orderId
     * @param position
     * @return 
     */
    public static Response setDelivered(String orderId, String position) {
        return changeOrderStatus(orderId, OrderStatus.ENTREGADO, null, null,position);
    }
    
    /***
     * 
     * @param orderId
     * @param reason razon por la cancelacion de recoleccionj
     * @param position
     * @return 
     */
    public static Response cancelPickUp(String orderId, String reason,String position) {
        return changeOrderStatus(orderId, OrderStatus.CANCELADO, null,reason, position);
    }
    
    /***
     * 
     * @param orderId
     * @param reason por cancelacion de la entrega
     * @param position
     * @return 
     */
    public static Response cancelDelivering(String orderId, String reason,String position) {
        return changeOrderStatus(orderId, OrderStatus.CANCELADO, null,reason, position);
    }
    

    
}
