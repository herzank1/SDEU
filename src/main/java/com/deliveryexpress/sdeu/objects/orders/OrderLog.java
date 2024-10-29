/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.orders;

import lombok.Data;
import com.deliveryexpress.sdeu.utils.DateUtils;
import com.google.gson.annotations.Expose;


/**
 *
 * @author DeliveryExpress
 */

@Data
public class OrderLog {
    @Expose
     String data;

  public void addLog(String event, String value, String by) {
    data += DateUtils.now() + String.join(" - ", event, value, by,"\n");

  }
  
}
