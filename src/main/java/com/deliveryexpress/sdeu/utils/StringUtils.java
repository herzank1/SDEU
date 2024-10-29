/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 *
 * @author HP
 */
public class StringUtils {
    
  // Método genérico para serializar cualquier objeto a JSON
    public static <T> String toTJson(T obj) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(obj);
    }

    public static String toJson(Object o) {

        // Crear un objeto Gson con pretty printing
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type responseType = new TypeToken<>() {}.getType();
        // Convertir el objeto Java a JSON formateado

        return gson.toJson(o);

    }

}
