/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.database;


import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.deliveryexpress.sdeu.objects.Moderator;
import com.deliveryexpress.sdeu.objects.User;
import com.deliveryexpress.sdeu.objects.contability.BalanceAccount;
import com.deliveryexpress.sdeu.objects.contability.Transacction;
import com.deliveryexpress.sdeu.objects.orders.Order;
import com.deliveryexpress.sdeu.objects.orders.StorableOrder;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author HP
 */
public class SqliteDataBaseInteraction {
    
  
    public static final String DATABASE_URL = "jdbc:sqlite:database.sqlite";
    private static ConnectionSource connectionSource;
    
    public static GenericDao<User, String> usersDao;
    public static GenericDao<Bussines, String> bussinesDao;
    public static GenericDao<Delivery, String> deliveriDao;
    public static GenericDao<Customer, String> customersDao;
    public static GenericDao<Moderator, String> moderatorDao;
    public static GenericDao<BalanceAccount, String> balanceAccountsDao;
    public static GenericDao<Transacction, String> transacctionsDao;
    public static GenericDao<StorableOrder, String> StorableOrderDao;
    
   

    /***
     * 
     * @param conector with filename 
     * example jdbc:sqlite:database.sqlite
     */
    public static void init(String filename) {

        // Crear la conexión a la base de datos y generacion de Daos
        
        try {
             // Cargar el controlador JDBC para SQLite
            Class.forName("org.sqlite.JDBC");
            connectionSource = new JdbcConnectionSource(DATABASE_URL);

            initDaos();
            validateClassChanges();

        } catch (SQLException ex) {
            Logger.getLogger(SqliteDataBaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SqliteDataBaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public static String getDATABASE_URL() {
        return DATABASE_URL;
    }
    
    
    
    

    public static void close() throws IOException, Exception {
        connectionSource.close();
    }
    
    public static void initDaos() {
        try {
            usersDao = new GenericDao<>(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            bussinesDao = new GenericDao<>(connectionSource, Bussines.class);
            TableUtils.createTableIfNotExists(connectionSource, Bussines.class);
            deliveriDao = new GenericDao<>(connectionSource, Delivery.class);
            TableUtils.createTableIfNotExists(connectionSource, Delivery.class);
            customersDao = new GenericDao<>(connectionSource, Customer.class);
            TableUtils.createTableIfNotExists(connectionSource, Customer.class);
            balanceAccountsDao = new GenericDao<>(connectionSource, BalanceAccount.class);
            TableUtils.createTableIfNotExists(connectionSource, BalanceAccount.class);
            transacctionsDao = new GenericDao<>(connectionSource, Transacction.class);
            TableUtils.createTableIfNotExists(connectionSource, Transacction.class);
            StorableOrderDao = new GenericDao<>(connectionSource, StorableOrder.class);
            TableUtils.createTableIfNotExists(connectionSource, Order.class);
            moderatorDao = new GenericDao<>(connectionSource, Moderator.class);
            TableUtils.createTableIfNotExists(connectionSource, Moderator.class);
        } catch (SQLException ex) {
            Logger.getLogger(SqliteDataBaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void validateClassChanges() {
        TableValidator.verifyTable(usersDao, User.class);
        TableValidator.verifyTable(bussinesDao, Bussines.class);
        TableValidator.verifyTable(deliveriDao, Delivery.class);
        TableValidator.verifyTable(customersDao, Customer.class);
        TableValidator.verifyTable(balanceAccountsDao, BalanceAccount.class);
        TableValidator.verifyTable(transacctionsDao, Transacction.class);
        TableValidator.verifyTable(StorableOrderDao, StorableOrder.class);
        TableValidator.verifyTable(moderatorDao, Moderator.class);

    }
}
