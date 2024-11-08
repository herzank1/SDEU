/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

/**
 *
 * @author DeliveryExpress
 */
public class ModeratorUpdateObjectResponse extends Response {
 @Expose
    Object object;
  @Expose
    String clazzName;

    public ModeratorUpdateObjectResponse(Command command, String status, Object object, Class clazz) {
        super(command, status);
        super.setCommand("updateObjectInDB");
        this.object = object;
        this.clazzName = clazz.getName(); // Guardamos el nombre de la clase
    }
    
     public Class<?> getClazz() {
    try {
        return Class.forName(clazzName);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        return null; // O maneja el error de otra forma
    }
}
    
        // Función genérica para deserializar el campo `object` como el tipo especificado
    public <T> T deserializeObject(Class<T> type) {
        Gson gson = new Gson();
        // Convertimos `object` a JSON y luego deserializamos como el tipo especificado
        return gson.fromJson(gson.toJson(object), type);
    }

}
