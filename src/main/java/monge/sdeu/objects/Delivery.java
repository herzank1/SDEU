/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monge.sdeu.objects;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import lombok.Data;

/**
 *
 * @author HP
 */

@Data
public class Delivery {
    
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
    private String balanceAccountId;
    
}
