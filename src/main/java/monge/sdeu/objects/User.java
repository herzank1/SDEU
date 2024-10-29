/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monge.sdeu.objects;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
@DatabaseTable(tableName = "users")
public class User {
    
    @DatabaseField(id = true)
    @Expose
    private String username;
    
    @DatabaseField
   @Expose
    private String phone;
  
    @DatabaseField
    @Expose
    private String pass;
    
    @DatabaseField
    @Expose
    
    private String accountType;
    
    @DatabaseField
    @Expose
    private String accountId;
    
    @DatabaseField
    @Expose
    private boolean blackList;

    /***
     * 
     * @param username
     * @param phone
     * @param pass
     * @param accountType
     * @param accountId
     * @param blackList 
     */
    public User(String username, String phone, String pass, String accountType, String accountId, boolean blackList) {
        this.username = username;
        this.phone = phone;
        this.pass = pass;
        this.accountType = accountType;
        this.accountId = accountId;
        this.blackList = blackList;
    }

    public User() {
    }
    
    

   
    public interface AccountType {

        String CUSTOMER = "CUSTOMER";
        String BUSSINES = "BUSSINES";
        String DELIVERY = "DELIVERY";
        String MODERATOR = "MODERATOR";
    }
    
    
}
