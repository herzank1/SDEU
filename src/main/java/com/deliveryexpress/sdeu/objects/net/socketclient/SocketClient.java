/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.socketclient;



import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;


@Data
public class SocketClient {
    
  public static Gson gson;

  private static OkHttpClient client;
  public static WebSocket webSocket;
  public static  String SESSION_ID = "SESSION_ID";

  //private static final String SERVER_IP = "ws://192.168.0.7:8080/ws";//Local Host
 // private static final String SERVER_IP = "ws://34.71.89.3:8080/ws";
  public static  String SERVER_IP = "ws://34.71.89.3:8080/ws";
  
  public static Tigger tiger;


  public static final ConcurrentHashMap<String, CompletableFuture<JsonObject>> futureMap = new ConcurrentHashMap<>();
   

  /***
   * 
     * @param sessionId
   * @param serverIp  ws://192.168.0.7:8080/ws  or ws://34.71.89.3:8080/ws
     * @param tigger
   * @throws Exception 
   */
    public static void init(String sessionId, String serverIp,Tigger tigger) throws Exception {
        tiger = tigger;
        SERVER_IP = serverIp;
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        SESSION_ID = sessionId;

        client = new OkHttpClient();

        // Crear la solicitud de conexión
        Request request = new Request.Builder().url(SERVER_IP).build();
        // Crear WebSocketListener
        SocketListener listener = new SocketListener(tigger);
        // Crear la conexión WebSocket
        webSocket = client.newWebSocket(request, listener);
        // Cerrar el cliente cuando ya no sea necesario
        client.dispatcher().executorService().shutdown();

    }

    /***
     * Forzamos a enviar el session id en cada comando
     * @param command 
     */
    public static void execute(Command command) {

        try {
            if (command.getSessionId() == null || command.getSessionId().isEmpty()) {
                command.setSessionId(SESSION_ID);
            }
            String toJson = StringUtils.toJson(command, true);
            System.out.println(toJson);
            webSocket.send(toJson);
        } catch (Exception ex) {
            Logger.getLogger(SocketClient.class.getName())
                    .log(Level.SEVERE, "Error enviando mensaje", ex);
        }
    }

  /**
 * Ejecuta un comando y espera hasta recibir una respuesta o que expire el tiempo de espera.
 * @param command Comando a enviar
 * @param waitMilliseconds Tiempo máximo de espera en milisegundos
 * @return JsonObject que contiene la respuesta, o null si no se recibe a tiempo
 */
    public static JsonObject execute_R(Command command, long waitMilliseconds) {
        // Crear un CompletableFuture para manejar la respuesta
        CompletableFuture<JsonObject> futureResponse = new CompletableFuture<>();
        futureMap.put(command.getId(), futureResponse);

        execute(command);

        try {
            // Esperar la respuesta o hasta que expire el tiempo
            return futureResponse.get(waitMilliseconds, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            return null; // o lanzar una excepción personalizada si prefieres
        } finally {
            // Asegurarse de limpiar el futureMap
            futureMap.remove(command.getId());
        }
    }

}
