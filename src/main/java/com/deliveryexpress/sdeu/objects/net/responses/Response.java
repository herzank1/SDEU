/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class Response <T> {
    @Expose
    private String id; /*same than command id*/
    @Expose
    private String command;
    @Expose
    private String status; //success or fail
    @Expose
    private T data;
    @Expose
    private String mensaje;

    
    
    
   // Constructor que acepta un JSON string
    public Response(String jsonString) {
        this(new Gson().fromJson(jsonString, JsonObject.class));
    }
    
      // Constructor que acepta un JsonObject
    public Response(JsonObject jsonObject) {
        try {
            this.id = jsonObject.has("id") ? jsonObject.get("id").getAsString() : null;
            this.command = jsonObject.has("command") ? jsonObject.get("command").getAsString() : null;
            this.status = jsonObject.has("status") ? jsonObject.get("status").getAsString() : null;
            this.mensaje = jsonObject.has("mensaje") ? jsonObject.get("mensaje").getAsString() : null;
        } catch (JsonSyntaxException e) {
            e.printStackTrace(); // Manejo de excepciones si el JSON no es válido
        }
    }
    
    /***
     * 
     * @param command head o nombre de la funcion que genero esta respuesta
     * @param status succes, fail u otro
     * @param input  cualquier tipo de objeto adjunto
     * el objeto debera deserealizarse usando exposed anotacion
     */
    public  Response(Command _command, String status, T input) {
        this.id = _command.getId();
        this.command = _command.getCommand();
        this.status = status;
        this.data = input;
        this.mensaje = "";


    }
    /***
     * 
     * @param _command
     * @param status 
     */
        public  Response(Command _command, String status) {
        this.id = _command.getId();
        this.command = _command.getCommand();
        this.status = status;
        this.mensaje = "";


    }
    
   public boolean success() {
    return this.status.equalsIgnoreCase("success") ||
           this.status.equalsIgnoreCase("ok") ||
           this.status.equals("1") ||
           this.status.equalsIgnoreCase("true");
}

    public interface Status {

        String SUCCESS = "SUCCESS";
        String FAIL = "FAIL";
    }
    
   // Método para convertir la instancia actual en un JSON string, solo con campos @Expose
    public String toJsonString() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        return gson.toJson(this);
    }

}
