/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
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
    private String mensaje;
    @Expose
    private Object data;
    @Expose
    private ArrayList<T> dataArray;
    
       // Constructor que acepta un jsonString
    public Response(String jsonString) {
        Gson gson = new Gson();
        try {
            // Deserializa el jsonString en este objeto Command
            Response r = gson.fromJson(jsonString, Response.class);
            this.id = r.getId();
            this.command = r.getCommand();
            this.status = r.getStatus();
            this.mensaje = r.getMensaje();

            this.dataArray = r.getDataArray();
            this.data = r.getData();

        } catch (JsonSyntaxException e) {
            e.printStackTrace(); // Manejo de excepciones si el json no es válido
        }
    }
    
    /***
     * 
     * @param command head o nombre de la funcion que genero esta respuesta
     * @param mensaje succes, fail u otro
     * @param input  cualquier tipo de objeto adjunto
     * el objeto debera deserealizarse usando exposed anotacion
     */
    public  Response(Command command, String status, T input) {
        this.id = command.id;
        this.command = command.command;
        this.status = status;
        this.mensaje = "";

         if (input instanceof Collection) {  // Verifica si es una colección
            this.dataArray = new ArrayList<>((Collection<T>) input);  // Convierte la colección a ArrayList
        } else {
            this.data = input;  // Si no es una colección, lo guarda como data
        }

    }
    
   public boolean success() {
    return this.status.equalsIgnoreCase("success") ||
           this.status.equalsIgnoreCase("ok") ||
           this.status.equals("1") ||
           this.status.equalsIgnoreCase("true");
}

    public interface Status{
    
    String SUCCESS = "SUCCESS";
    String FAIL = "FAIL";
    }

}
