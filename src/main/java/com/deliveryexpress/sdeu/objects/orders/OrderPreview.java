/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.orders;

/**
 *
 * @author DeliveryExpress
 */
import lombok.Data;

@Data
public class OrderPreview {

  String id;
  String name;
  String status;
  boolean isReady;
  String creationDate;

  public OrderPreview(String id,String name, String status, boolean isReady,String creationDate) {
    this.id = id;
    this.name = name;
    this.status = status;
    this.isReady = isReady;
    this.creationDate = creationDate;

  }

}