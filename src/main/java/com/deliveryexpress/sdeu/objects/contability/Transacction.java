/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.contability;

import com.deliveryexpress.sdeu.database.SqliteDataBaseInteraction;
import com.deliveryexpress.sdeu.utils.DateUtils;
import com.j256.ormlite.field.DatabaseField;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Data;

/**
 *
 * @author HP
 */
@Data
public class Transacction {
    @DatabaseField(id = true)
    String id;
    @DatabaseField
    String from;
    @DatabaseField
    String to;
    @DatabaseField
    float mount;
    @DatabaseField
    String date;
    @DatabaseField
    String concept;
    @DatabaseField
    String ref;

    public Transacction() {
    }
    
    

    public Transacction(String id, String from, String to, float mount, String concept, String ref) {
        this.id = UUID.randomUUID().toString();
        this.from = from;
        this.to = to;
        this.mount = mount;
        this.date = DateUtils.now();
        this.concept = concept;
        this.ref = ref;
    }
    
    
    
    public void execute(){
        try {
        
            BalanceAccount bfrom = SqliteDataBaseInteraction.balanceAccountsDao.read(from);
            BalanceAccount bto = SqliteDataBaseInteraction.balanceAccountsDao.read(to);
            
            bfrom.setBalance(bfrom.getBalance()-this.mount);
            bto.setBalance(bto.getBalance()+this.mount);
            
            SqliteDataBaseInteraction.balanceAccountsDao.update(bfrom);
            SqliteDataBaseInteraction.balanceAccountsDao.update(bto);
            
            SqliteDataBaseInteraction.transacctionsDao.create(this);
            
            
        } catch (Exception ex) {
            Logger.getLogger(Transacction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
    
}
