/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monge.sdeu.objects;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import java.util.UUID;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class Customer {
    @DatabaseField(id = true)
     @Expose
    private String id;
    
    @DatabaseField
     @Expose
    private String name;
    @DatabaseField
     @Expose
    private String phone;
    @DatabaseField
     @Expose
    private String address;
    @DatabaseField
     @Expose
    private String position;
    @DatabaseField
     @Expose
    private String note;
    @DatabaseField
     @Expose
    private String balanceAccountId;

    /***
     * 
     * @param name
     * @param phone
     * @param address
     * @param note 
     */
    public Customer(String name, String phone, String address, String note) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.position = "";
        this.note = note;
        this.balanceAccountId = "";
    }

    public Customer() {
      }

}
