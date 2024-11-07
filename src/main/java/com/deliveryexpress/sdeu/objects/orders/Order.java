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
import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author DeliveryExpress
 */
@Data

public class Order {

    @Expose

    private String id;
    @Expose

    private String creationDate;
    @Expose

    private String status;
    @Expose

    private int preparationTime;//minutes

    /**
     * ********************Nested Accounts************************************
     */
    @Expose

    private Bussines bussines;
    @Expose

    private Delivery delivery;
    @Expose

    private Customer customer;

    /**
     * ********************Nested Accounts************************************
     */
    @Expose

    private float orderCost;
    @Expose

    private float deliveryCost;

    @Expose

    public String orderLogJson;
    /*ids de repartidores que cancelaron*/
    @Expose
    private String cancelersJson;
    @Expose
    /*true si el repartidor indica que llego al restaurante*/
    public boolean deliveryArrivedToBussines;
    @Expose
    /*true si el repartidor indica que llego con el cliente*/
    public boolean deliveryArrivedToCustomer;
    @Expose
    /*bandera - si la orden esta esperando confirmacion del repartidor, evita que se le asigne otra orden al momento*/
    public boolean waitingDeliveryConfirmation = false;
    @Expose
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
        this.delivery = null;
        this.customer = null;

        this.orderCost = 0;
        this.deliveryCost = 0;

        this.orderLogJson = gson.toJson(new OrderLog());
        this.cancelersJson = gson.toJson(new ArrayList());

    }

    /**
     * @return if order has no delivery and its about ti be ready
     */
    public boolean isReadyToTake() {
        // Si el estado es 'LISTO', se puede recoger
        if (status.equals(OrderStatus.LISTO)) {
            return true;
        }

        // Si el estado no es 'PREPARANDO' ni 'LISTO', no se puede recoger
        if (!status.equals(OrderStatus.PREPARANDO) && !status.equals(OrderStatus.LISTO)) {
            return false;
        }

        // Verificar si la orden está casi lista
        boolean orderAlmostReady = DateUtils.isOrderAlmostReady(this.creationDate, this.preparationTime);

        /*en caso de que este casi lista debera establecerse como lista pa recolectar*/
        if (orderAlmostReady && status.equals(OrderStatus.PREPARANDO)) {
            this.setStatus(OrderStatus.LISTO);

        }

        // Devolver true solo si está casi lista y no hay un deliveryJson asociado
        return orderAlmostReady && this.delivery == null;
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

        this.waitingDeliveryConfirmation = true;

        timer = new Timer();
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

    /**
     * *
     *
     * @return true si esta esperando confirmacion, asumiendo que la orden tiene
     * un repartidor asignado
     */
    public boolean isWaitingConfirmation() {
        return waitingDeliveryConfirmation;
    }

    /*retorna true si el repartidor confirmo*/
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

        switch (where) {

            case AccountType.BUSSINES:
                this.deliveryArrivedToBussines = true;
                break;

            case AccountType.CUSTOMER:
                this.deliveryArrivedToCustomer = true;
                break;
        }

    }

    @Deprecated
    /**
     * *
     * Use a OrderControl set Status alternative
     */
    public void changeStatusByDelivery(String status) {

        this.setStatus(status);

    }

    /**
     * *
     *
     * @param event what happened
     * @param value some reference
     * @param by User who made it
     */
    public void addLog(String event, String value, String by) {
        OrderLog orderLog = this.getOrderLog();
        orderLog.addLog(event, value, by);
        this.setOrderLog(orderLog);
    }

    // Getter y setter para OrderLog
    public OrderLog getOrderLog() {
        return gson.fromJson(orderLogJson, OrderLog.class);
    }

    public void setOrderLog(OrderLog orderLog) {
        this.orderLogJson = gson.toJson(orderLog);
    }

    /**
     * *
     * agregar repartidor a la lista de canceladores
     *
     * @param bayGuyDelivery
     */
    public void addCanceler(String bayGuyDelivery) {
        List<String> cancelers = getCancelers();
        cancelers.add(bayGuyDelivery);
        this.setCancelers(cancelers);
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

    public String getShortId() {
        if (this.id == null || this.id.length() < 5) {
            return this.id; // Devuelve la cadena completa si tiene menos de 5 caracteres
        }
        return this.id.substring(this.id.length() - 5);
    }

    /**
     * *
     *
     * @return la fecha en que la orden debe estar lista
     */
    public String getReadyDateTime() {
        // Formato de fecha y hora
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Convertir creationDate a LocalDateTime
        LocalDateTime creationDateTime = LocalDateTime.parse(this.creationDate, formatter);
        // Sumar preparationTime en minutos
        LocalDateTime readyDateTime = creationDateTime.plusMinutes(this.preparationTime);
        // Convertir el resultado de vuelta a String en el mismo formato
        return readyDateTime.format(formatter);
    }

    /**
     * *
     *
     * @param minutes
     * @return verdadero si an pasado los minutos indicados desde el momento que
     * deberia estar lista
     */
    public boolean timePassedSinceReadyTime(int minutes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime readyDateTime = LocalDateTime.parse(getReadyDateTime(), formatter);
        LocalDateTime currentTime = LocalDateTime.now();

        // Calcular la diferencia en minutos
        long minutesBetween = ChronoUnit.MINUTES.between(readyDateTime, currentTime);

        // Retorna true si han pasado más de 30 minutos
        return minutesBetween > minutes;
    }

    /***
     * 
     * @return verdadero si la orden esta en preparacion/listo, no tiene repartidor y han pasado 20 minutos
     */
    public boolean isCancelableForBussines() {
        if (this.status.equals(OrderStatus.PREPARANDO)
                || this.status.equals(OrderStatus.LISTO) && this.getDelivery() != null) {
            return timePassedSinceReadyTime(20);
        }
        return false;

    }

    /**
     * *
     * Return the storable representation of this order
     *
     * @return
     */
    public StorableOrder getStorableOrder() {
        return new StorableOrder(this);
    }

}
