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
    public static GenericDao<Order, String> OrderDao;

    /***
     * 
     * @param conector with filename 
     * example jdbc:sqlite:database.sqlite
     */
    public static void init(String filename) {

        // Crear la conexión a la base de datos y generacion de Daos
        JdbcConnectionSource connectionSource;
        try {
            connectionSource = new JdbcConnectionSource(SqliteDataBaseInteraction.DATABASE_URL);
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
            OrderDao =  new GenericDao<>(connectionSource, Order.class);
            TableUtils.createTableIfNotExists(connectionSource, Order.class);
             moderatorDao =  new GenericDao<>(connectionSource, Moderator.class);
            TableUtils.createTableIfNotExists(connectionSource, Moderator.class);

        } catch (SQLException ex) {
            Logger.getLogger(SqliteDataBaseInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void close() throws IOException, Exception {
        connectionSource.close();
    }
}
