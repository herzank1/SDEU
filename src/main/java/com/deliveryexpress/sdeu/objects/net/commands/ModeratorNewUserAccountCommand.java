/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.commands;

import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.deliveryexpress.sdeu.objects.Moderator;
import com.deliveryexpress.sdeu.objects.User;
import com.deliveryexpress.sdeu.objects.contability.BalanceAccount;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModeratorNewUserAccountCommand extends Command{
    User user;
    Bussines bussines;
    Delivery deliveri;
    Moderator moderator;
    Customer customer;
    BalanceAccount balanceAccount;

    public ModeratorNewUserAccountCommand() {
        super();
        super.setCommand("registerUser");
    }

    public ModeratorNewUserAccountCommand(User user, Bussines bussines, BalanceAccount balanceAccount) {
        super();
        super.setCommand("registerUser");
        this.user = user;
        this.bussines = bussines;
        this.balanceAccount = balanceAccount;
    }

    public ModeratorNewUserAccountCommand(User user, Delivery deliveri, BalanceAccount balanceAccount) {
        super();
        super.setCommand("registerUser");
        this.user = user;
        this.deliveri = deliveri;
        this.balanceAccount = balanceAccount;
    }

    public ModeratorNewUserAccountCommand(User user, Moderator moderator, BalanceAccount balanceAccount) {
        super();
        super.setCommand("registerUser");
        this.user = user;
        this.moderator = moderator;
        this.balanceAccount = balanceAccount;
    }

    
    
}
