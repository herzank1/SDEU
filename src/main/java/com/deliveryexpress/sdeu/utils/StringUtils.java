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

}
