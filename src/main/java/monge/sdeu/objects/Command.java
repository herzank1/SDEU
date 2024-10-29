/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monge.sdeu.objects;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import lombok.Data;
import monge.deliveryexpressadmin.General;
import monge.deliveryexpressadmin.utils.StringUtils;
import monge.deliveryexpressadmin.websocket.SocketClient;

/**
 *
 * @author monge.686
 * Representa el commando de un usuario, este se debera enviar en formato json al servidor
 */

@Data
public class Command {

    /*Id de la session*/
    String sessionId;
    /*nombre del comando o funcion principal a executar*/
    String command;
    /*lista de parametros*/
    ArrayList<Param> params;

    public Command() {
        if(General.storedSessionId!=null) {

              this.sessionId = General.storedSessionId;
          }

        params = new ArrayList<>();
    }

        // Constructor que acepta un jsonString
    public Command(String jsonString) {
        Gson gson = new Gson();
        try {
            // Deserializa el jsonString en este objeto Command
            Command commandFromJson = gson.fromJson(jsonString, Command.class);
            this.sessionId = commandFromJson.getSessionId();
            this.command = commandFromJson.getCommand();
            this.params = commandFromJson.getParams();
        } catch (JsonSyntaxException e) {
            e.printStackTrace(); // Manejo de excepciones si el json no es válido
        }
    }
    
    public void addParam(Param p) {
        params.add(p);
    }
        // Método para buscar un parámetro por nombre
    public Param getParamByName(String name) {
        for (Param param : params) {
            if (param.getName().equals(name)) {
                return param; // Retorna el objeto Param si se encuentra
            }
        }
        return null; // Retorna null si no se encuentra
    }
    
    public String toJson() {
        return StringUtils.toJson(this);
    }

        public void execute() {


        try {
            String json = StringUtils.toJson(this);
            System.out.println(json);
            SocketClient.socketClient.sendMessage(json);
            SocketClient.waitingResponse = true;
        }catch (Exception e){

            e.printStackTrace();
        }

    }

}
