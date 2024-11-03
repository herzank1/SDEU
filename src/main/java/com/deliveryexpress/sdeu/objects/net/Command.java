/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.ArrayList;
import lombok.Data;
import com.deliveryexpress.sdeu.utils.StringUtils;
import com.google.gson.annotations.Expose;
import java.util.UUID;

/**
 *
 * @author monge.686 Representa el commando de un usuario, este se debera enviar
 * en formato json al servidor
 */
@Data
public class Command {
    
    @Expose
    String id;

    /*Id de la session*/
    @Expose
    String sessionId;
    /*nombre del comando o funcion principal a executar*/
    @Expose
    String command;
    /*lista de parametros*/
    @Expose
    ArrayList<Param> params;

    public Command() {

        this.id = UUID.randomUUID().toString();
        this.sessionId = sessionId;

        params = new ArrayList<>();
    }

    // Constructor que acepta un jsonString
    public Command(String jsonString) {
        Gson gson = new Gson();
        try {
            // Deserializa el jsonString en este objeto Command
            Command commandFromJson = gson.fromJson(jsonString, Command.class);
            this.id = commandFromJson.getId();
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

   

}
