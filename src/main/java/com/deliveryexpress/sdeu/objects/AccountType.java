/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.deliveryexpress.sdeu.objects;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DeliveryExpress
 */

   
public interface AccountType {

    String CUSTOMER = "CUSTOMER";
    String BUSSINES = "BUSSINES";
    String DELIVERY = "DELIVERY";
    String MODERATOR = "MODERATOR";

   public static boolean isValidAccountType(String input) {
        return CUSTOMER.equals(input)
                || BUSSINES.equals(input)
                || DELIVERY.equals(input)
                || MODERATOR.equals(input);
    }
   
    public static List<String> getAllAccountTypes() {
        List<String> accountTypes = new ArrayList<>();
        accountTypes.add(CUSTOMER);
        accountTypes.add(BUSSINES);
        accountTypes.add(DELIVERY);
        accountTypes.add(MODERATOR);
        return accountTypes;
    }
}
