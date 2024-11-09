/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.sqlitedatabase;

/**
 * Esta clase contiene las funciones necesarias para verificar si las clases han sido modificadas,
 * esto con el fin de evitar errores en la insecion de datos en la db, 
 * @author DeliveryExpress
 */


import com.j256.ormlite.field.DatabaseField;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class TableValidator {

    
    
     static void verifyTable(DbConection connectionSource, GenericDao<?, ?> dao, Class<?> clazz) {
    
     System.out.println("validando tabla..."+dao.getTableName());
        if (!verifyTableColumns(connectionSource,dao, clazz)) {
            addMissingColumns(connectionSource,dao, clazz);
        }

     }
    
 

    // Método principal que verifica si las columnas de la clase coinciden con las de la tabla en la base de datos
    private static <T> boolean verifyTableColumns(DbConection connectionSource,GenericDao dao, Class<T> clazz) {
        try {
            Set<String> classFields = getClassFields(clazz);
            System.out.println("Columnas en la classe: " + classFields.toString());
            Set<String> tableColumns = getTableColumns(connectionSource,dao);
            System.out.println("Columnas en la tabla: " + classFields.toString());

            return classFields.equals(tableColumns);

        } catch (Exception e) {
            return false;
        }
    }
    
        private static <T> void addMissingColumns(DbConection connectionSource,GenericDao dao, Class<T> clazz) {
        try {
            Set<String> tableColumns = getTableColumns(connectionSource,dao);
            Set<String> classFields = getClassFields(clazz);

            // Compara las columnas de la clase con las columnas de la tabla
            for (String field : classFields) {
                if (!tableColumns.contains(field)) {
                    addColumn(connectionSource,dao.getTableName(), field);
                }
            }
        } catch (Exception e) {
        }
    }

    // Método para obtener los nombres de las columnas de la clase a través de reflexión
    private static <T> Set<String> getClassFields(Class<T> clazz) {
        Set<String> classFields = new HashSet<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(DatabaseField.class)) {
                DatabaseField databaseField = field.getAnnotation(DatabaseField.class);
                String columnName = databaseField.columnName();
                if (columnName.isEmpty()) {
                    columnName = field.getName(); // Si no se especifica columnName, usa el nombre del campo
                }
                classFields.add(columnName);
            }
        }
        return classFields;
    }

    // Método para obtener los nombres de las columnas de la tabla en la base de datos SQLite usando PRAGMA table_info
    private static <T> Set<String> getTableColumns(DbConection connectionSource,GenericDao dao) throws Exception {
        Set<String> tableColumns = new HashSet<>();
        String tableName = dao.getTableName();

        try (Connection conn = DriverManager.getConnection(connectionSource.getUrl());
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("PRAGMA table_info(" + tableName + ")")) {
            while (rs.next()) {
                String columnName = rs.getString("name");
                tableColumns.add(columnName);
            }
        }
        return tableColumns;
    }
    
    // Método para agregar una nueva columna a la tabla
    private static void addColumn(DbConection connectionSource,String tableName, String columnName) {
        String sql = "ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " TEXT"; // Cambia el tipo según lo necesites

        try (Connection conn = DriverManager.getConnection(connectionSource.getUrl());
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Columna " + columnName + " agregada a la tabla " + tableName);
        } catch (Exception e) {
        }
    }
    
       public static String getSqlType(Field field) {
        // Verifica si el campo tiene la anotación @DatabaseField
        if (field.isAnnotationPresent(DatabaseField.class)) {
            Class<?> fieldType = field.getType();

            // Mapea el tipo de Java a un tipo de SQL
            if (fieldType == String.class) {
                return "TEXT"; // Tipo SQL para cadenas
            } else if (fieldType == int.class || fieldType == Integer.class) {
                return "INTEGER"; // Tipo SQL para enteros
            } else if (fieldType == long.class || fieldType == Long.class) {
                return "INTEGER"; // Tipo SQL para enteros largos
            } else if (fieldType == double.class || fieldType == Double.class) {
                return "REAL"; // Tipo SQL para números de punto flotante
            } else if (fieldType == float.class || fieldType == Float.class) {
                return "REAL"; // Tipo SQL para números de punto flotante
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                return "INTEGER"; // Usualmente se almacena como 0 o 1
            } else if (fieldType == byte.class || fieldType == Byte.class) {
                return "INTEGER"; // Almacenado como 0-255
            } else if (fieldType == short.class || fieldType == Short.class) {
                return "INTEGER"; // Almacenado como entero
            } else if (fieldType == java.util.Date.class) {
                return "INTEGER"; // Almacenado como timestamp (ej: epoch time)
            } else {
                // Para otros tipos, puedes decidir cómo manejarlos
                return "BLOB"; // Tipo SQL para objetos binarios
            }
        }
        return null; // Si el campo no tiene la anotación, devolver null
    }

   
}
