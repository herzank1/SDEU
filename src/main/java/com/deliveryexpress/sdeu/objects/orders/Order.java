/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.orders;

/**
 *
 * @author DeliveryExpress
 */

import com.deliveryexpress.sdeu.objects.AccountType;
import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.Customer;
import com.deliveryexpress.sdeu.objects.Delivery;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import com.deliveryexpress.sdeu.utils.DateUtils;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author HP
 */
@Data
@DatabaseTable(tableName = "orders") 

public class Order {

@DatabaseField(id = true) // Campo ID
  private String id;
@DatabaseField // Otros campos
  private String creationDate;
@DatabaseField // Otros campos
  private String status;
@DatabaseField // Otros campos
  private int preparationTime;//minutes

@DatabaseField // Otros campos
  private String bussinesJson;

@DatabaseField // Otros campos
  private String deliveryJson;

@DatabaseField // Otros campos
  private String customerJson;

@DatabaseField // Otros campos
  private float orderCost;
@DatabaseField // Otros campos
  private float deliveryCost;


@DatabaseField // Otros campos
  public String orderLogJson;
  /*ids de repartidores que cancelaron*/

  private String cancelersJson;

  /*true si el repartidor indica que llego al restaurante*/
  public boolean deliveryArrivedToBussines;
  /*true si el repartidor indica que llego con el cliente*/
  public boolean deliveryArrivedToCustomer;
  
  public boolean waitingDeliveryConfirmation = false;
  
  public boolean deliveryConfirmed = false;

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

    public boolean isRecolectable() {
        
        if (this.getDelivery() != null) {
            return false;
        }
        
        if (!isReadyToTake()) {
            return false;
        }
        
        return true;
    }

    
    Timer timer;
    public void startCountdown() {
     TimerTask task = new TimerTask() {
            int remainingTime = 10;//10 segundos

            @Override
            public void run() {
                if (deliveryConfirmed) {
                    deliveryConfirm();
                    timer.cancel(); // Detiene el temporizador
                } else {
                    remainingTime--;
                    System.out.println("Tiempo restante: " + remainingTime + " segundos");

                    if (remainingTime <= 0) {
                        deliveryReject(); // Finaliza si el tiempo se acaba
                        timer.cancel(); // Detiene el temporizador
                    }
                }
            }
        };

        // Programa la tarea para ejecutarse cada segundo (1000 ms)
        timer.scheduleAtFixedRate(task, 0, 1000);
    
    }

    /***
     * 
     * @return true si esta esperando confirmacion, asumiendo que la orden tiene un repartidor asignado
     */
    public boolean isWaitingConfirmation() {
      return waitingDeliveryConfirmation;
    }

    public boolean isDeliveryConfirmation() {
     
    return deliveryConfirmed;
    }

    public void deliveryReject() {
        timer.cancel();
        List<String> add = this.getCancelers();
        add.add(this.getDelivery().getId());
        this.setCancelers(add);
        this.setDelivery(null);
        this.deliveryConfirmed = false;
        this.waitingDeliveryConfirmation = false;
    }

    public void deliveryConfirm() {
        timer.cancel();
        if (this.getDelivery() != null) {
            this.deliveryConfirmed = true;
            this.waitingDeliveryConfirmation = false;
        }

    }

    public void arrivedTo(String where) {
     
        switch(where){
        
            case AccountType.BUSSINES:
                this.deliveryArrivedToBussines = true;
                break;
                
                case AccountType.CUSTOMER:
                this.deliveryArrivedToCustomer = true;
                break;
        }
    
    }

    public void changeStatusByDelivery(String status) {
     
        this.setStatus(status);
    
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
