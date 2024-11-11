/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient.methods;

import com.deliveryexpress.sdeu.objects.net.Param;
import com.deliveryexpress.sdeu.objects.net.commands.BussinesCotaizerCommand;
import com.deliveryexpress.sdeu.objects.net.commands.BussinesNewOrderCommand;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.net.responses.GetBussinesCotaizerResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetCustomerResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetFetchPlaceSuggestionsResponse;
import com.deliveryexpress.sdeu.objects.net.responses.GetOrderResponse;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketClient;
import com.deliveryexpress.sdeu.objects.orders.Order;
import com.deliveryexpress.sdeu.objects.orders.OrderStatus;
import com.google.gson.JsonObject;

/**
 *
 * @author DeliveryExpress
 */
public class BussinesMethods extends DefaultMethods {



    public static GetOrderResponse newOrder(Order order) {

        BussinesNewOrderCommand command = new BussinesNewOrderCommand(order);
        // Ejecuta el comando en el cliente de socket
        JsonObject executeR = SocketClient.execute_R(command,1000*10);
         GetOrderResponse anoResponse = SocketClient.gson.fromJson(executeR, GetOrderResponse.class);

        return anoResponse;
    }

    public static GetCustomerResponse getCustomer(String phone) {

        Command command = new Command();
        command.setCommand("getCustomer");
        command.addParam(new Param("phone", phone));
        JsonObject executeR = SocketClient.execute_R(command, 1000*10);
        GetCustomerResponse gcResponse = SocketClient.gson.fromJson(executeR, GetCustomerResponse.class);

        return gcResponse;

    }

    public static GetFetchPlaceSuggestionsResponse fetchPlaceSuggestions(String input) {
        Command command = new Command();
        command.setCommand("fetchPlaceSuggestions");
        command.addParam(new Param("input", input));
        JsonObject executeR = SocketClient.execute_R(command, 1000*10);

        GetFetchPlaceSuggestionsResponse cfsResponse = SocketClient.gson.fromJson(executeR, GetFetchPlaceSuggestionsResponse.class);

        return cfsResponse;

    }

    public static GetBussinesCotaizerResponse cotizer(String fromAddress, String toAddress) {

        BussinesCotaizerCommand bcCommand = new BussinesCotaizerCommand(fromAddress, toAddress);

        JsonObject executeR = SocketClient.execute_R(bcCommand, 1000*10);

        GetBussinesCotaizerResponse bcResponse = SocketClient.gson.fromJson(executeR, GetBussinesCotaizerResponse.class);

        return bcResponse;

    }
    
   

    /***
     * Inidica una orden lista para recolectar
     * @param orderId
     * @return true si se cambio el estado en el servidor
     */
    public static Response setOrderReady(String orderId) {

        return changeOrderStatus(orderId, OrderStatus.LISTO,null,null,null);
    }

  
  

}
