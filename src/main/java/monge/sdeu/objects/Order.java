/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package monge.sdeu.objects;

/**
 *
 * @author DeliveryExpress
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import monge.deliveryexpressadmin.utils.DateUtils;

/**
 *
 * @author HP
 */
@Data

public class Order {


  private String id;

  private String creationDate;

  private String status;

  private int preparationTime;//minutes


  private String bussinesJson;


  private String deliveryJson;


  private String customerJson;


  private float orderCost;

  private float deliveryCost;



  public String orderLogJson;
  /*ids de repartidores que cancelaron*/

  private String cancelersJson;

  /*true si el repartidor indica que llego al restaurante*/
  public boolean deliveryArrivedToBussines;
  /*true si el repartidor indica que llego con el cliente*/
  public boolean deliveryArrivedToCustomer;

  // Usaremos Gson para la serialización
  private static final Gson gson = new Gson();

  public Order() {
  }

  public Order(Bussines bussines) {
    this.id = UUID.randomUUID().toString();
    this.creationDate = DateUtils.now();
    this.status = OrderStatus.PREPARANDO;
    this.preparationTime = 0;
    this.setBussines(bussines);
    this.deliveryJson = gson.toJson(null);
    this.customerJson = gson.toJson(null);

    this.orderCost = 0;
    this.deliveryCost = 0;

   // this.chatJson = gson.toJson(new ChatBox());
    this.orderLogJson = gson.toJson(new OrderLog());
    this.cancelersJson = gson.toJson(new ArrayList());

    /*true si el repartidor indica que llego al restaurante*/

  }

  /**
   * *
   *
   * @return if order has no delivery and its about ti be ready
   */
  public boolean isReadyToTake() {

    if (!status.equals(OrderStatus.PREPARANDO) && !status.equals(OrderStatus.LISTO)) {
      return false;
    }

    boolean orderAlmostReady = DateUtils.isOrderAlmostReady(this.creationDate, this.preparationTime);
    return orderAlmostReady && this.deliveryJson == null;
  }


  public float getTotal() {

    return this.orderCost + this.deliveryCost;
  }

  public static interface OrderStatus {

    String PREPARANDO = "PREPARANDO";
    String LISTO = "LISTO";
    String EN_CAMINO = "EN_CAMINO";
    String EN_DOMICILIO = "EN_DOMICILIO";
    String ENTREGADO = "ENTREGADO";
    String CANCELADO = "CANCELADO";
  }

  // Getters y Setters para los campos JSON
  public Bussines getBussines() {
    return gson.fromJson(bussinesJson, Bussines.class);
  }

  public void setBussines(Bussines bussines) {
    this.bussinesJson = gson.toJson(bussines);
  }

  public Delivery getDelivery() {
    return gson.fromJson(deliveryJson, Delivery.class);
  }

  public void setDelivery(Delivery delivery) {
    this.deliveryJson = gson.toJson(delivery);
  }

  public Customer getCustomer() {
    return gson.fromJson(customerJson, Customer.class);
  }

  public void setCustomer(Customer customer) {
    this.customerJson = gson.toJson(customer);
  }



  // Getter y setter para OrderLog
  public OrderLog getOrderLog() {
    return gson.fromJson(orderLogJson, OrderLog.class);
  }

  public void setOrderLog(OrderLog orderLog) {
    this.orderLogJson = gson.toJson(orderLog);
  }

  // Getter y setter para cancelers (lista de IDs de repartidores que cancelaron)
  public List<String> getCancelers() {
    Type listType = new TypeToken<ArrayList<String>>() {
    }.getType();
    return gson.fromJson(cancelersJson, listType);
  }

  public void setCancelers(List<String> cancelers) {
    this.cancelersJson = gson.toJson(cancelers);
  }

  public  String getShortId() {
    if (this.id == null || this.id.length() < 5) {
      return this.id; // Devuelve la cadena completa si tiene menos de 5 caracteres
    }
    return this.id.substring(this.id.length() - 5);
  }

}
