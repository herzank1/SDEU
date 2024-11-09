/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.sqlitedatabase;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Use this class to create more sql nodes for load distribution in sqlite
 * @author DeliveryExpress
 */
public class DbConection {
    
    private ConnectionSource connectionSource;
    private String url;
    
    private Map<Class<?>,GenericDao<?,String>>daos = new HashMap<>();

    /***
     * 
     * @param fileName exmaple: database.sqlite
     */
    public DbConection(String fileName) {
        
        
        
         try {
             // Verificar si el archivo de la base de datos existe
            File dbFile = new File(fileName);
            if (!dbFile.exists()) {
                dbFile.createNewFile(); // Crear el archivo de base de datos si no existe
            }
             
             // Cargar el controlador JDBC para SQLite
            Class.forName("org.sqlite.JDBC");
            this.url = "jdbc:sqlite:"+fileName;
            connectionSource = new JdbcConnectionSource(this.url );

        } catch (SQLException | ClassNotFoundException | IOException ex) {
        }
    }
    
 public <T> void addDao(Class<T> clazz) {
    try {
        GenericDao<T, String> dao = new GenericDao<>(connectionSource, clazz);
        TableUtils.createTableIfNotExists(connectionSource, clazz);
        daos.put(clazz, dao); // Agregar el DAO al HashMap
    } catch (SQLException ex) {
        Logger.getLogger(DbConection.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    @SuppressWarnings("unchecked")
    public <T> GenericDao<T, String> getDao(Class<T> clazz) {
        return (GenericDao<T, String>) daos.get(clazz);
    }

       // Función para recorrer el HashMap y validar cada DAO
    public  void validateClassChanges() {
        for (Map.Entry<Class<?>, GenericDao<?,String>> entry : daos.entrySet()) {
            Class<?> entityClass = entry.getKey();
            GenericDao<?,?> dao = entry.getValue();
            TableValidator.verifyTable(this,dao, entityClass);
        }
    }

    public String getUrl() {
        return url;
    }


    
}
