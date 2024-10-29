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
    private String command; // Puedes usar esto para indicar el estado de la respuesta
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
            this.command = r.getCommand();
            this.mensaje = r.getMensaje();

            this.dataArray = r.getDataArray();
            this.data = r.getData();

        } catch (JsonSyntaxException e) {
            e.printStackTrace(); // Manejo de excepciones si el json no es válido
        }
    }
    
    public  Response(String command, String mensaje, T input) {
        this.command = command;
        this.mensaje = mensaje;

         if (input instanceof Collection) {  // Verifica si es una colección
            this.dataArray = new ArrayList<>((Collection<T>) input);  // Convierte la colección a ArrayList
        } else {
            this.data = input;  // Si no es una colección, lo guarda como data
        }

    }

   
    
       // Método para convertir la instancia actual a JSON
    public String toJson() {
        String toJson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create().toJson(this);
            
        // Serializar el objeto
        return toJson;
    }
}
