/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author HP
 */
public class DateUtils {
    
    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static String now(){
     // Obtener la fecha y hora actuales
        LocalDateTime now = LocalDateTime.now();

        // Definir un formato de fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        // Formatear la fecha y hora

        return now.format(formatter);
    
    }
    
        // Función que determina si la orden está casi lista o le faltan al menos 5 minutos
    public static boolean isOrderAlmostReady(String creationDate, int preparationMinutes) {
       SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT); // Cambia el formato según tus necesidades

        try {
            // Convertir creationDate a objeto Date
            Date creationDateObj = dateFormat.parse(creationDate);
            // Calcular la fecha y hora en que la orden debe estar lista
            long preparationTimeInMillis = preparationMinutes * 60 * 1000; // Convertir minutos a milisegundos
            Date readyDate = new Date(creationDateObj.getTime() + preparationTimeInMillis);

            // Obtener la fecha y hora actuales
            Date currentDate = new Date();

            // Calcular la diferencia en milisegundos
            long differenceInMillis = readyDate.getTime() - currentDate.getTime();

            // Convertir la diferencia a minutos
            long differenceInMinutes = differenceInMillis / (60 * 1000);

            // Verificar si le faltan al menos 5 minutos
            // Verificar si faltan 5 minutos o si ya ha pasado el tiempo de preparación
        return differenceInMinutes <= 5; // Retorna true si faltan 5 minutos o más tiempo ha pasado

        } catch (ParseException e) {
            return false; // Manejo de excepción en caso de formato de fecha incorrecto
        }
        
        
    }
    
     public static long getUnixTimestamp() {
        return System.currentTimeMillis() / 1000L; // Dividir entre 1000 para convertir de milisegundos a segundos
    }
}
