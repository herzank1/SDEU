/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.orders;

/**
 *
 * @author DeliveryExpress
 */
  public interface OrderStatus {

    String PREPARANDO = "PREPARANDO";
    String LISTO = "LISTO";
    String EN_CAMINO = "EN_CAMINO";
    String EN_DOMICILIO = "EN_DOMICILIO";
    String ENTREGADO = "ENTREGADO";
    String CANCELADO = "CANCELADO";
  }