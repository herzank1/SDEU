/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monge.sdeu.objects.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/***
 * 
 * @author DeliveryExpress
 * Algunas funciones de Google Maps Api
 */
public class Geocoding {

    // Tu API Key de Google
    private static final String API_KEY = "AIzaSyBybOUg1fBCWK5DP1tgLYbgBgDguJFHRDo";

    public static String coorToAddress(String coordenadas) {
        String direccionFormateada = "";
        try {
            // Crear URL con coordenadas
            String urlStr = String.format(
                "https://maps.googleapis.com/maps/api/geocode/json?latlng=%s&key=%s",
                coordenadas, API_KEY);
            URL url = new URL(urlStr);

            // Abrir la conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Convertir respuesta a JSON usando Gson
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

            // Verificar si hay resultados
            if (jsonResponse.getAsJsonArray("results").size() > 0) {
                direccionFormateada = jsonResponse.getAsJsonArray("results")
                        .get(0).getAsJsonObject()
                        .get("formatted_address").getAsString();
            } else {
                direccionFormateada = "No se encontró dirección para estas coordenadas.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            direccionFormateada = "Error al obtener la dirección: " + e.getMessage();
        }

        return direccionFormateada;
    }

    public static void main(String[] args) {
        // Coordenadas en formato "latitud,longitud"
        String coordenadas = "37.4219999,-122.0840575";

        // Obtener dirección formateada
        String direccion = coorToAddress(coordenadas);
        System.out.println("Dirección: " + direccion);
    }
    
       public static String addressToCoor(String direccion) {
        String coordenadas = "";
        try {
            // Codificar la dirección para que sea válida en una URL
            String direccionCodificada = direccion.replace(" ", "%20");
            
            // Crear la URL con la dirección
            String urlStr = String.format(
                "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",
                direccionCodificada, API_KEY);
            URL url = new URL(urlStr);

            // Abrir la conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Convertir respuesta a JSON usando Gson
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

            // Verificar si hay resultados
            if (jsonResponse.getAsJsonArray("results").size() > 0) {
                JsonObject location = jsonResponse.getAsJsonArray("results")
                        .get(0).getAsJsonObject()
                        .getAsJsonObject("geometry")
                        .getAsJsonObject("location");

                // Obtener latitud y longitud
                String latitud = location.get("lat").getAsString();
                String longitud = location.get("lng").getAsString();

                // Formatear las coordenadas
                coordenadas = latitud + "," + longitud;
            } else {
                coordenadas = "No se encontraron coordenadas para esta dirección.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            coordenadas = "Error al obtener las coordenadas: " + e.getMessage();
        }

        return coordenadas;
    }
       
          public static String getDistanceAndTime(String origen, String destino) {
        String resultado = "";
        try {
            // Codificar la dirección o coordenadas para que sean válidas en una URL
            String origenCodificado = origen.replace(" ", "%20");
            String destinoCodificado = destino.replace(" ", "%20");
            
            // Crear la URL para la Distance Matrix API
            String urlStr = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=%s",
                origenCodificado, destinoCodificado, API_KEY);
            URL url = new URL(urlStr);

            // Abrir la conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer la respuesta
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Convertir respuesta a JSON usando Gson
            JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();

            // Verificar si hay resultados
            if (jsonResponse.getAsJsonArray("rows").size() > 0) {
                JsonObject elemento = jsonResponse.getAsJsonArray("rows")
                        .get(0).getAsJsonObject()
                        .getAsJsonArray("elements")
                        .get(0).getAsJsonObject();

                // Obtener distancia y duración
                String distancia = elemento.getAsJsonObject("distance").get("text").getAsString();
                String duracion = elemento.getAsJsonObject("duration").get("text").getAsString();

                // Formatear el resultado
                resultado = "Distancia: " + distancia + ", Duración: " + duracion;
            } else {
                resultado = "No se pudo calcular la distancia o tiempo entre los puntos.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultado = "Error al obtener la distancia y tiempo: " + e.getMessage();
        }

        return resultado;
    }
}

