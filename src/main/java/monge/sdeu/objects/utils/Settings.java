/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monge.sdeu.objects.utils;

/**
 *
 * @author DeliveryExpress
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Settings {
    private static final String FILE_PATH = "settings.json";
    private static final Map<String, Object> settingsMap = new HashMap<>();
    private static final Gson gson = new Gson();

    // Cargar el archivo JSON al HashMap o crear uno vacío si no existe
    public static void cargar() {
        File file = new File(FILE_PATH);

        // Si el archivo no existe, crearlo vacío
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Archivo de configuración creado: " + FILE_PATH);
                    guardar(); // Guarda un archivo JSON vacío
                }
            } catch (IOException e) {
                System.out.println("No se pudo crear el archivo: " + e.getMessage());
            }
        }

        // Intentar cargar el archivo JSON al mapa de configuraciones
        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<HashMap<String, Object>>() {}.getType();
            Map<String, Object> data = gson.fromJson(reader, type);
            if (data != null) {
                settingsMap.putAll(data);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
        }
    }

    // Guardar el HashMap en un archivo JSON
    public static void guardar() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(settingsMap, writer);
        } catch (IOException e) {
            System.out.println("No se pudo guardar el archivo: " + e.getMessage());
        }
    }

    // Agregar o actualizar una propiedad
    public static void set(String key, Object value) {
        settingsMap.put(key, value);
    }

    // Obtener una propiedad como String
    public static String getString(String key, String defaultValue) {
        Object value = settingsMap.get(key);
        if (value instanceof String) {
            return (String) value;
        }
        System.out.println("Advertencia: clave no encontrada o tipo no coincide, devolviendo valor por defecto.");
        return defaultValue;
    }

    // Obtener una propiedad como Integer
    public static int getInt(String key, int defaultValue) {
        Object value = settingsMap.get(key);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        System.out.println("Advertencia: clave no encontrada o tipo no coincide, devolviendo valor por defecto.");
        return defaultValue;
    }

    // Obtener una propiedad como Boolean
    public static boolean getBoolean(String key, boolean defaultValue) {
        Object value = settingsMap.get(key);
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        System.out.println("Advertencia: clave no encontrada o tipo no coincide, devolviendo valor por defecto.");
        return defaultValue;
    }

    // Eliminar una propiedad
    public static void remove(String key) {
        if (settingsMap.containsKey(key)) {
            settingsMap.remove(key);
        } else {
            System.out.println("Advertencia: clave no encontrada.");
        }
    }

    // Borrar todas las propiedades
    public static void clear() {
        settingsMap.clear();
    }
}
