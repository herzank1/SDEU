/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.metadata;

/**
 *
 * @author DeliveryExpress
 */
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import java.lang.reflect.Type;
import java.util.HashMap;
import lombok.Data;



@Data
public class Ratings {
    
    @Expose
    @DatabaseField(id = true) // Campo ID
    private String userId; /*owner*/
    // Mapa para almacenar las calificaciones con el ID del usuario como clave
    @Expose
    @DatabaseField // Otros campos
    private String userRatingsJson;
    
   
    private transient HashMap<String, Integer> userRatings; // Mapa para uso interno

    public Ratings() {
        this.userRatings = new HashMap<>();
        this.userRatingsJson = "{}"; // Inicializar como un JSON vacío
    }

    
    
    // Constructor
    public Ratings(String userId) {
         this.userRatings = new HashMap<>();
         this.userRatingsJson = "{}"; // Inicializar como un JSON vacío
    }

    // Método para agregar o actualizar la calificación de un usuario
    public void addRating(String userId, int rating) {
        if (rating >= 1 && rating <= 5) { // Validar que la calificación esté entre 1 y 5
            userRatings.put(userId, rating); // Agregar o actualizar la calificación del usuario
            updateJson();
        } else {
            System.out.println("Calificación inválida. Debe estar entre 1 y 5.");
        }
    }

    // Método para calcular el promedio de calificaciones
    public int getAverageRating() {
        if (userRatings.isEmpty()) {
            return 0; // Si no hay calificaciones, devolver 0
        }

        int sum = 0;
        for (int rating : userRatings.values()) {
            sum += rating;
        }
        return sum / userRatings.size(); // Calcular el promedio y devolverlo como entero
    }

    // Método para obtener todas las calificaciones (opcional)
    public HashMap<String, Integer> getAllRatings() {
        return new HashMap<>(userRatings); // Devolver una copia del mapa de calificaciones
    }
    
    // Método para actualizar el JSON a partir del HashMap
    private void updateJson() {
        Gson gson = new Gson();
        userRatingsJson = gson.toJson(userRatings); // Convertir el HashMap a JSON
    }
    
     // Método para cargar las calificaciones desde el JSON
    public void loadRatingsFromJson() {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Integer>>() {}.getType();
        userRatings = gson.fromJson(userRatingsJson, type); // Convertir el JSON a HashMap
    }
}
