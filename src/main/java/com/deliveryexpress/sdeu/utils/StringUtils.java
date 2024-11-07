/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author HP
 */
public class StringUtils {
    
 
    /***
     * 
     * @param o object to serialized
     * @param exposedOnly true if want to exclude Fields Without Expose Annotation
     * @return 
     */
    public static String toJson(Object o, boolean exposedOnly) {

        Gson gson;
        if (exposedOnly) {
            gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        } else {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }

        return gson.toJson(o);

    }
    
    public static String toSHA256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            
            // Convertir el hash en una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }
    
      public static JsonObject toJsonObject(String jsonString) {
        // Convertir el JSON string en un JsonObject
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }
    
  
}
