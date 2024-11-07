/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.sqlitedatabase;

import com.deliveryexpress.sdeu.objects.AccountType;
import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.deliveryexpress.sdeu.objects.Moderator;
import com.deliveryexpress.sdeu.objects.User;
import com.deliveryexpress.sdeu.objects.contability.BalanceAccount;
import com.deliveryexpress.sdeu.objects.contability.Transacction;
import com.deliveryexpress.sdeu.objects.location.Position;
import com.deliveryexpress.sdeu.objects.metadata.Ratings;
import com.deliveryexpress.sdeu.objects.metadata.Tags;
import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.deliveryexpress.sdeu.objects.net.commands.ModeratorUpdateObjectCommand;
import com.deliveryexpress.sdeu.objects.net.responses.Response;
import com.deliveryexpress.sdeu.objects.orders.StorableOrder;
import static com.deliveryexpress.sdeu.sqlitedatabase.DbBalancer.Accounts.newRegisterUser;
import com.deliveryexpress.sdeu.utils.StringUtils;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DeliveryExpress a SQLITE balancer avoid frezzen
 */
public class DbBalancer {

    static DbConection accounts;
    static DbConection contability;
    static DbConection ordersHistory;
    static DbConection stadistics;
    static DbConection customers;

    public static void init() {

        accounts = new DbConection("db_accounts.sqlite");
        accounts.addDao(User.class);
        accounts.addDao(Delivery.class);
        accounts.addDao(Bussines.class);
        accounts.addDao(Moderator.class);

        contability = new DbConection("db_contability.sqlite");
        contability.addDao(Transacction.class);
        contability.addDao(BalanceAccount.class);

        ordersHistory = new DbConection("db_ordersHistory.sqlite");
        ordersHistory.addDao(StorableOrder.class);

        stadistics = new DbConection("db_stadistics.sqlite");
        stadistics.addDao(Ratings.class);
        stadistics.addDao(Tags.class);

        customers = new DbConection("db_customers.sqlite");
        customers.addDao(Customer.class);

        createGenesisAccounts();

    }

    /*Create genesis account*/
    public static void createGenesisAccounts() {
        Command command = new Command();
        command.setCommand("registerUser");
        
     
      
        Moderator root = Accounts.Moderators.Moderators().read("root");
        System.out.println("Verificando root moderator...");
        if (root == null) {
            Response newRegisterUser = newRegisterUser(command, "root",
                     "password",
                     "6863095448",
                     AccountType.MODERATOR,
                     "PowerOverWhelming",
                     "null",
                     "null",
                     1000000f);

            System.out.println("root no existe, se creo uno nuevo. ");
            System.out.println(newRegisterUser);
        }

        /*Restaurantes*/
       
        Bussines babbq = Accounts.Bussiness.Bussiness().read("abbq");
        if (babbq == null) {
            newRegisterUser(command, "abbq",
                    "password",
                    "6862644096",
                    AccountType.BUSSINES,
                    "La Ahumadera BBQ (San Marcos)",
                    "P.º de La Rumorosa 403, San Marcos, 21050 Mexicali, B.C.",
                    "32.63358005891992, -115.49190169316554",
                    0f);
        }
        
             
        Bussines bjaz = Accounts.Bussiness.Bussiness().read("jaz");
        if (bjaz == null) {
            newRegisterUser(command, "jaz",
                    "password",
                    "6863095448",
                    AccountType.BUSSINES,
                    "Jugos Aztecas",
                    "Av San Luis Potosí 3133, Aztecas, 21137 Mexicali, B.C.",
                    "32.65127507501792, -115.52627820683988",
                    0f);
        }

       /*Repartidores*/
        Delivery dmonge = Accounts.Deliveries.Deliveries().read("alien");
        if (dmonge == null) {
            newRegisterUser(command, "alien",
                    "password",
                    "6863095448",
                    AccountType.DELIVERY,
                    "Diego Monge ",
                    "null",
                    "null",
                    0f);
        }
        
                
        Delivery dTontin= Accounts.Deliveries.Deliveries().read("tontileon");
        if (dTontin == null) {
            newRegisterUser(command, "tontileon",
                    "password",
                    "6863093494",
                    AccountType.DELIVERY,
                    "Ivan Yanez ",
                    "null",
                    "null",
                    0f);
        }
        
              
        Delivery dOmar = Accounts.Deliveries.Deliveries().read("elbowser");
        if (dOmar == null) {
            newRegisterUser(command, "elbowser",
                    "password",
                    "6862544238",
                    AccountType.DELIVERY,
                    "Omar Roman ",
                    "null",
                    "null",
                    0f);
        }
        
        /*Clientes*/
        Customer cmama = Accounts.Customers.Customers().findByColumn("phone", "6861095152");
        if (cmama == null) {

            cmama = new Customer("Cecy", "6861095152", "av san luis potosi 3129 fracc aztecas", "casa azul");
            cmama.setPosition("32.65126152498157, -115.5263157576324");
            Accounts.Customers.Customers().create(cmama);
        }

        Customer cbubus = Accounts.Customers.Customers().findByColumn("phone", "6863093494");
        if (cbubus == null) {

            cbubus = new Customer("Ivan yanes", "6863093494", "Revolución 2165, San Luis, 21160 Mexicali, B.C.", "casa");
            cbubus.setPosition("32.641529170376714, -115.51135359904268");
            Accounts.Customers.Customers().create(cbubus);
        }

        Customer comar = Accounts.Customers.Customers().findByColumn("phone", "6862544238");
        if (comar == null) {

            comar = new Customer("Omar roman", "6862544238", "Sierra San Agustín 2602, Solidaridad INFONAVIT I, 21188 Mexicali, B.C.", "casa");
            comar.setPosition("32.61953028647941, -115.51635818157561");
            Accounts.Customers.Customers().create(comar);
        }


          
          

    }
    
    public static boolean updateObjectInDb(ModeratorUpdateObjectCommand moderatorUpdateObjectCommand) {

        try {
            String clazzName = moderatorUpdateObjectCommand.getClazzName();

            if (clazzName.equals(Bussines.class.getName())) {
                Bussines deserializeObject = moderatorUpdateObjectCommand.deserializeObject(Bussines.class);
                Accounts.Bussiness.Bussiness().update(deserializeObject);

            } else if (clazzName.equals(Delivery.class.getName())) {

                Delivery deserializeObject = moderatorUpdateObjectCommand.deserializeObject(Delivery.class);
                Accounts.Deliveries.Deliveries().update(deserializeObject);

            } else if (clazzName.equals(Moderator.class.getName())) {

                Moderator deserializeObject = moderatorUpdateObjectCommand.deserializeObject(Moderator.class);
                Accounts.Moderators.Moderators().update(deserializeObject);

            } else if (clazzName.equals(User.class.getName())) {

                User deserializeObject = moderatorUpdateObjectCommand.deserializeObject(User.class);
                Accounts.Users().update(deserializeObject);
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static class Accounts {

        /**
         * *Por el momento no usarse para registrar customer Registra un
         * Usuario validando su existencia previa, genera un tipo de cueta
         * conforme se solicite, asi mismo genera una cuent Balance para
         * administracion de fondos
         *
         * @param userName
         * @param pass no hashed
         * @param phone
         * @param accountType
         * @param accountName owner names
         * @param address
         * @param position user null if not is bussines
         * @param initialBalance balance of account
         * @return un Objeto Response, indicando el resultado del registro,
         * succes si fue exitoso
         */
        public static Response newRegisterUser(Command command, String userName, String pass, String phone, String accountType,
                String accountName, String address, String position, float initialBalance) {

            /*revisamos si el usuario existe*/
            User checkUser = Users().read(userName);
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

                    Deliveries.Deliveries().create(delivery);

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

                    Bussiness.Bussiness().create(bussines);

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

                    Moderators.Moderators().create(moderator);

                    break;

            }

            /*registramos user y balance*/
            Users().create(user);
            Contability.BalancesAccounts.BalancesAccounts().create(balanceAccount);

            return new Response(command, "success", user.toString());

        }

        public static GenericDao<User, String> Users() {

            GenericDao<User, String> dao = accounts.getDao(User.class);
            return dao;

        }

        public static class Deliveries {

            public static GenericDao<Delivery, String> Deliveries() {

                GenericDao<Delivery, String> dao = accounts.getDao(Delivery.class);
                return dao;

            }
        }

        public static class Moderators {

            public static GenericDao<Moderator, String> Moderators() {

                GenericDao<Moderator, String> dao = accounts.getDao(Moderator.class);
                return dao;

            }

        }

        public static class Bussiness {

            public static GenericDao<Bussines, String> Bussiness() {

                GenericDao<Bussines, String> dao = accounts.getDao(Bussines.class);
                return dao;

            }
        }

        public static class Customers {

            public static void registerOrUpdateCustomer(Customer customer) {

                /*Buscamos customer por cel*/
                Customer findByColumn = Customers().findByColumn("phone", customer.getPhone());
                boolean update = false;
                if (findByColumn == null) {
                    if (customer.getId().isEmpty()) {
                        customer.setId(UUID.randomUUID().toString());
                    }
                    Customers().create(customer);

                    return;
                } else {

                    /*actualizamos el customer en la base de datos con el customer recibido*/
                    if (!findByColumn.getAddress().equals(customer.getAddress())) {
                        findByColumn.setAddress(customer.getAddress());
                        update = true;
                    }

                    if (!findByColumn.getNote().equals(customer.getNote())) {
                        findByColumn.setNote(customer.getNote());
                        update = true;
                    }

                    if (!findByColumn.getPosition().equals(customer.getPosition())) {
                        findByColumn.setPosition(customer.getPosition());
                        update = true;
                    }

                }

                if (update) {
                    Customers().update(findByColumn);
                }

            }

            public static GenericDao<Customer, String> Customers() {

                GenericDao<Customer, String> dao = customers.getDao(Customer.class);
                return dao;

            }
        }

    }

    public static class Contability {

        public static class BalancesAccounts {

            public static GenericDao<BalanceAccount, String> BalancesAccounts() {

                GenericDao<BalanceAccount, String> dao = contability.getDao(BalanceAccount.class);
                return dao;

            }

        }

        public static class Transacctions {

            public static GenericDao<Transacction, String> Transacctions() {

                GenericDao<Transacction, String> dao = contability.getDao(Transacction.class);
                return dao;

            }

            public static void execute(Transacction transacction) {
                try {

                    BalanceAccount bfrom = Contability.BalancesAccounts.BalancesAccounts().read(transacction.getFrom());
                    BalanceAccount bto = Contability.BalancesAccounts.BalancesAccounts().read(transacction.getTo());

                    bfrom.setBalance(bfrom.getBalance() - transacction.getMount());
                    bto.setBalance(bto.getBalance() + transacction.getMount());

                    Contability.BalancesAccounts.BalancesAccounts().update(bfrom);
                    Contability.BalancesAccounts.BalancesAccounts().update(bto);

                    Contability.Transacctions.Transacctions().create(transacction);

                } catch (Exception ex) {
                    Logger.getLogger(Transacction.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

    public static class OrdersHistory {

        public static class Orders {

            public static GenericDao<StorableOrder, String> Orders() {

                GenericDao<StorableOrder, String> dao = ordersHistory.getDao(StorableOrder.class);
                return dao;

            }
        }

    }

    public static class Stadistics {

        public static class Ratings {

            public static GenericDao<Ratings, String> Ratings() {

                GenericDao<Ratings, String> dao = stadistics.getDao(Ratings.class);
                return dao;

            }
        }

        public static class Tags {

            public static GenericDao<Tags, String> Tags() {

                GenericDao<Tags, String> dao = stadistics.getDao(Tags.class);
                return dao;

            }
        }

    }

}
