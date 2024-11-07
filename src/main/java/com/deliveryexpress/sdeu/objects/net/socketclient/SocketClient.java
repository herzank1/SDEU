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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
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
  public static boolean waitingResponse = false;
  public static  String SESSION_ID = "SESSION_ID";

  //private static final String SERVER_IP = "ws://192.168.0.7:8080/ws";//Local Host
 // private static final String SERVER_IP = "ws://34.71.89.3:8080/ws";
  public static  String SERVER_IP = "ws://34.71.89.3:8080/ws";


  public static ConcurrentHashMap<String, CountDownLatch> latchMap = new ConcurrentHashMap<>();
  public static ConcurrentHashMap<String, JsonObject> responseMap = new ConcurrentHashMap<>();


  /***
   * 
   * @param serverIp  ws://192.168.0.7:8080/ws  or ws://34.71.89.3:8080/ws
   * @throws Exception 
   */
    public static void init(String sessionId, String serverIp) throws Exception {
        
        SERVER_IP = serverIp;
        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        SESSION_ID = sessionId;

        client = new OkHttpClient();

        // Crear la solicitud de conexión
        Request request = new Request.Builder().url(SERVER_IP).build();
        // Crear WebSocketListener
        SocketListener listener = new SocketListener();
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

  /***
   * executa un comando y espera la respuesta
   * @param command comand to send
   * @param waitMilliseconds waiting time for response
   * @return
   */
    public static JsonObject execute_R(Command command, long waitMilliseconds) {

        execute(command);

        // Crear un CountDownLatch para esperar la respuesta
        CountDownLatch latch = new CountDownLatch(1);
        latchMap.put(command.getId(), latch);

        // Esperar a que se reciba la respuesta
        try {
            latch.await(waitMilliseconds, TimeUnit.MILLISECONDS); // Espera hasta 10 segundos
            if (responseMap.get(command.getId().toString()) != null) {

                return responseMap.get(command.getId().toString());
            } else {

                return null;
            }
        } catch (Exception ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, "Error esperando respuesta", ex);
            return null;
        } finally {
            // Limpiar el mapa después de recibir la respuesta
            latchMap.remove(command.getId());
            responseMap.remove(command.getId());
        }

    }



}