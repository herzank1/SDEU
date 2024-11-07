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
import com.deliveryexpress.sdeu.objects.net.responses.GetUserBalanceResponse;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.net.socketclient.SocketClient;
import com.deliveryexpress.sdeu.objects.orders.Order;
import com.google.gson.JsonObject;

/**
 *
 * @author DeliveryExpress
 */
public class BussinesMethods extends DefaultMethods {

    public static void setReadyOrder(String id) {

        Command command = new Command();
        command.setCommand("setorderready");
        command.addParam(new Param("oid", id));
        SocketClient.execute(command);
    }

    public static void newOrder(Order order) {

        BussinesNewOrderCommand command = new BussinesNewOrderCommand(order);
        // Ejecuta el comando en el cliente de socket
        SocketClient.execute(command);
    }

    public static GetCustomerResponse getCustomer_R(String phone) {

        Command command = new Command();
        command.setCommand("getCustomer");
        command.addParam(new Param("phone", phone));
        JsonObject executeR = SocketClient.execute_R(command, 1500);
        GetCustomerResponse gcResponse = SocketClient.gson.fromJson(executeR, GetCustomerResponse.class);

        return gcResponse;

    }

    public static GetFetchPlaceSuggestionsResponse fetchPlaceSuggestions_R(String input) {
        Command command = new Command();
        command.setCommand("fetchPlaceSuggestions");
        command.addParam(new Param("input", input));
        JsonObject executeR = SocketClient.execute_R(command, 250);

        GetFetchPlaceSuggestionsResponse cfsResponse = SocketClient.gson.fromJson(executeR, GetFetchPlaceSuggestionsResponse.class);

        return cfsResponse;

    }

    public static GetBussinesCotaizerResponse cotizer_R(String fromAddress, String toAddress) {

        BussinesCotaizerCommand bcCommand = new BussinesCotaizerCommand(fromAddress, toAddress);

        JsonObject executeR = SocketClient.execute_R(bcCommand, 1500);

        GetBussinesCotaizerResponse bcResponse = SocketClient.gson.fromJson(executeR, GetBussinesCotaizerResponse.class);

        return bcResponse;

    }
    
   public static void cancelOrder(Order order) {

      Command command = new Command();
      command.setCommand("confirmOrderOrCancel");
      command.addParam(new Param("oid", order.getId()));
      command.addParam(new Param("value", "false"));
      SocketClient.execute(command);
    }


}
