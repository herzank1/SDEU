/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.database;


import com.deliveryexpress.sdeu.objects.AccountType;
import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.deliveryexpress.sdeu.objects.Moderator;
import com.deliveryexpress.sdeu.objects.User;
import com.deliveryexpress.sdeu.objects.contability.BalanceAccount;
import com.deliveryexpress.sdeu.objects.contability.Transacction;
import com.deliveryexpress.sdeu.objects.net.Command;
import com.deliveryexpress.sdeu.objects.net.Response;
import com.deliveryexpress.sdeu.objects.orders.Order;
import com.deliveryexpress.sdeu.objects.orders.StorableOrder;
import com.deliveryexpress.sdeu.utils.StringUtils;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
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
            TableUtils.createTableIfNotExists(connectionSource, StorableOrder.class);
            
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
    
    /*Create genesis account*/
          public static void createGenesisAccounts() {
              Command command = new Command();
              command.setCommand("registerUser");

          SqliteDataBaseInteraction.init("jdbc:sqlite:database.sqlite");

          String rootUserName = "root";
          Moderator root = SqliteDataBaseInteraction.moderatorDao.read(rootUserName);
          System.out.println("Verificando root moderator...");
          if(root==null){
              Response newRegisterUser = newRegisterUser(command,rootUserName
                      , "password"
                      , "6863095448"
                      , AccountType.MODERATOR
                      , "Diego V"
                      , "null"
                      , "null"
                      , 1000000f);
          
          System.out.println("root no existe, se creo uno nuevo. ");
          System.out.println(newRegisterUser);
          }
          
          String bussinesUserName = "jaz";
          Bussines genesisBussines = SqliteDataBaseInteraction.bussinesDao.read(bussinesUserName);
          if (genesisBussines == null) {
              newRegisterUser(command,bussinesUserName,
                       "password",
                       "6863095448",
                       AccountType.BUSSINES,
                       "Jugos Aztecas",
                       "Av San Luis Potosí 3133, Aztecas, 21137 Mexicali, B.C.",
                       "32.65127507501792, -115.52627820683988",
                       0f);
          }
          
          String deliveryUserName = "Alien";
          Delivery deliveryGenesisName = SqliteDataBaseInteraction.deliveriDao.read(deliveryUserName);
          if (deliveryGenesisName == null) {
              newRegisterUser(command,deliveryUserName,
                      "password",
                      "6863095448",
                      AccountType.DELIVERY,
                      "Monge ",
                      "null",
                      "null",
                      0f);
          }

  
        
    }
          
          
          
    /**
     * *Por el momento no usarse para registrar customer
     * Registra un Usuario validando su existencia previa, genera un tipo de cueta conforme se solicite,
     * asi mismo genera una cuent Balance para administracion de fondos
     * @param userName
     * @param pass no hashed
     * @param phone
     * @param accountType
     * @param accountName owner names
     * @param address
     * @param position user null if not is bussines
     * @param initialBalance balance of account
     * @return un Objeto Response, indicando el resultado del registro, succes si fue exitoso
     */
    public static Response newRegisterUser(Command command,String userName, String pass, String phone, String accountType,
            String accountName, String address, String position, float initialBalance) {

        /*revisamos si el usuario existe*/
        User checkUser = usersDao.read(userName);
        if (checkUser != null) {
            return new Response(command, "fail", "usuario ya existe");
        }

        phone = phone.replaceAll("[^0-9]", "");
        if (phone.length() < 10) {
            return new Response(command, "fail", "el telefono debe ser 10 digitos, y solo numeros");
        }

        User user = new User();

        if (!AccountType.isValidAccountType(accountType)) {
            return new Response(command, "fail", "accountType es invalido ");
        }
        user.setAccountType(accountType);

        user.setUsername(userName);
        user.setPhone(phone);

        String toSHA256 = StringUtils.toSHA256(pass);
        user.setPass(toSHA256);

        /*creamos la cuenta de balance*/
        BalanceAccount balanceAccount = new BalanceAccount();
        balanceAccount.setId(UUID.randomUUID().toString());
        // Validar el balance inicial
        if (initialBalance < 0) {
            return new Response(command, "fail", "El balance inicial no puede ser negativo");
        }
        balanceAccount.setBalance(initialBalance);

        Object account = null;

        switch (accountType) {

            case AccountType.DELIVERY:

                Delivery delivery = new Delivery();
                delivery.setId(UUID.randomUUID().toString());
                delivery.setName(accountName);
                delivery.setPhone(user.getPhone());
                delivery.setAddress(address);
                /*ligamos balance a la cuenta*/
                delivery.setBalanceAccountId(balanceAccount.getId());
                /*ligamos cuenta a usuario*/
                user.setAccountId(delivery.getId());

                account = delivery;

                deliveriDao.create(delivery);

                break;

            case AccountType.BUSSINES:
                if (position == null || position.isEmpty()) {
                    return new Response(command, "fail", "La posición es obligatoria para el tipo de cuenta BUSSINES");
                }

                Bussines bussines = new Bussines();
                 bussines.setId(UUID.randomUUID().toString());
                bussines.setName(accountName);
                bussines.setPhone(user.getPhone());
                bussines.setAddress(address);
                bussines.setPosition(position);
                /*ligamos balance a la cuenta*/
                bussines.setBalanceAccountId(balanceAccount.getId());
                /*ligamos cuenta a usuario*/
                user.setAccountId(bussines.getId());

                account = bussines;

                bussinesDao.create(bussines);

                break;

            case AccountType.MODERATOR:

                Moderator moderator = new Moderator();
                moderator.setId(UUID.randomUUID().toString());
                moderator.setName(accountName);
                moderator.setPhone(user.getPhone());
                moderator.setAddress(address);
                /*ligamos balance a la cuenta*/
                moderator.setBalanceAccountId(balanceAccount.getId());
                /*ligamos cuenta a usuario*/
                user.setAccountId(moderator.getId());

                account = moderator;

                moderatorDao.create(moderator);

                break;

        }

        /*registramos user y balance*/
       usersDao.create(user);
       balanceAccountsDao.create(balanceAccount);

        return new Response(command, "success", user.toString());

    }
}
