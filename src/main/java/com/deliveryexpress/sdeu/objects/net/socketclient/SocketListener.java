/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 *
 * @author DeliveryExpress
 */
public class SocketListener extends WebSocketListener {
    
     private static final int NORMAL_CLOSURE_STATUS = 1000;
     
        @Override
    public void onOpen(WebSocket webSocket, Response response) {
        System.out.println("WebSocket Conexión abierta");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

        JsonObject obj = JsonParser.parseString(text).getAsJsonObject();
        String id = obj.get("id").getAsString();

        /*command_R*/
        if(SocketClient.latchMap.containsKey(id)){
            SocketClient.responseMap.put(id,obj);
        }else{
        Tigger.execute(obj);
        }
     
        System.out.println("WebSocket Mensaje recibido: " + text);

    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
      
        System.out.println("WebSocket Mensaje recibido (bytes): " + bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
    
        webSocket.close(NORMAL_CLOSURE_STATUS, null);
        System.out.println("WebSocket Conexión cerrando: " + code + " / " + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
    
        System.out.println("WebSocket Error en WebSocket");
        
    }

    private void reconnect() {
       
        int attempts = 0;
        while (attempts < 5) { // Intentar reconectar hasta 6 veces
            try {
                System.out.println("Intentando reconectar...");
                Thread.sleep(5000); // Esperar 2 segundos antes de intentar de nuevo
                SocketClient.init(SocketClient.SESSION_ID,SocketClient.SERVER_IP);
                return; // Salir si la reconexión es exitosa
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Manejo de interrupción
            } catch (Exception e) {
              e.printStackTrace();
            }
          attempts++;
        }
        System.err.println("No se pudo reconectar después de varios intentos.");
    }
    
}
