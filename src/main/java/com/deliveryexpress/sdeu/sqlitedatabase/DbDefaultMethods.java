/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.sqlitedatabase;

import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.deliveryexpress.sdeu.objects.Moderator;
import com.deliveryexpress.sdeu.objects.User;
import com.deliveryexpress.sdeu.objects.net.commands.ModeratorUpdateObjectCommand;
import com.deliveryexpress.sdeu.objects.net.responses.ModeratorUpdateObjectResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 *
 * @author DeliveryExpress
 */
public class DbDefaultMethods {
    
        /*Create genesis account*/

public static ModeratorUpdateObjectResponse updateObjectInDb(ModeratorUpdateObjectCommand moderatorUpdateObjectCommand) {
    try {
        // Map de clases y sus funciones de actualización
        Map<String, Consumer<Object>> updateActions = new HashMap<>();
        updateActions.put(Bussines.class.getName(), obj -> DbBalancer.Accounts.Bussiness.Bussiness().update((Bussines) obj));
        updateActions.put(Delivery.class.getName(), obj -> DbBalancer.Accounts.Deliveries.Deliveries().update((Delivery) obj));
        updateActions.put(Moderator.class.getName(), obj -> DbBalancer.Accounts.Moderators.Moderators().update((Moderator) obj));
        updateActions.put(Customer.class.getName(), obj -> DbBalancer.Accounts.Customers.Customers().update((Customer) obj));
        updateActions.put(User.class.getName(), obj -> DbBalancer.Accounts.Users().update((User) obj));

        // Obtener el nombre de la clase y la acción de actualización
        String clazzName = moderatorUpdateObjectCommand.getClazzName();
        Consumer<Object> updateAction = updateActions.get(clazzName);

        if (updateAction != null) {
            // Deserializar el objeto
            Object deserializeObject = moderatorUpdateObjectCommand.deserializeObject(Class.forName(clazzName));
            // Ejecutar la acción de actualización
            updateAction.accept(deserializeObject);
            // Responder con éxito
            return new ModeratorUpdateObjectResponse(moderatorUpdateObjectCommand, "success", 
                                                     moderatorUpdateObjectCommand.getObject(), 
                                                     moderatorUpdateObjectCommand.getClazz());
        } else {
            throw new IllegalArgumentException("Clase no soportada: " + clazzName);
        }

    } catch (Exception e) {
        // Responder con fallo
        ModeratorUpdateObjectResponse moderatorUpdateObjectResponse = new ModeratorUpdateObjectResponse(moderatorUpdateObjectCommand, "fail", 
                moderatorUpdateObjectCommand.getObject(),
                moderatorUpdateObjectCommand.getClazz());
        moderatorUpdateObjectResponse.setMensaje(e.getLocalizedMessage());
        
        return moderatorUpdateObjectResponse;
    }
}

 
    
}
