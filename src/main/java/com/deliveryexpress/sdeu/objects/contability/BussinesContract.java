/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.contability;

import com.deliveryexpress.sdeu.objects.Bussines;
import com.deliveryexpress.sdeu.objects.orders.Order;
import com.deliveryexpress.sdeu.sqlitedatabase.DbBalancer;
import com.deliveryexpress.sdeu.sqlitedatabase.SQLiteDB;
import com.deliveryexpress.sdeu.utils.DateUtils;
import com.deliveryexpress.sdeu.utils.StringUtils;
import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import lombok.Data;

/**
 *
 * @author DeliveryExpress Esta clase representa el contrato o acuerdo con un
 * negocio/restuarante
 */
@Data
public class BussinesContract {

    @DatabaseField(id = true)
    @Expose
    String bussinesId;

    @DatabaseField
    @Expose
    String chargeableBalanceAccountId;

    @DatabaseField
    @Expose
    String creationDate;

    @DatabaseField
    @Expose
    /*cargo sobre ChargeMode si es por porcentaje ejem. 10% = 10*/
    float charge;

    @DatabaseField
    @Expose
    String chargeMode;

    /*si verdadero, debera tener saldo positivo para enviar ordenes*/
    @DatabaseField
    @Expose
    boolean rechargeable;

    @DatabaseField
    @Expose
    /*maximo de deuda ex. -500*/
    float maxDebt;

    /*cuota semanal*/
    @DatabaseField
    @Expose
    boolean paysCuota;

    @DatabaseField
    @Expose
    float cuotaCost;

    /*Seguro de ordenes activo*/
    @DatabaseField
    @Expose
    String orderInsuranceType;

    @DatabaseField
    @Expose
    float orderInsuranceCost;

    @DatabaseField
    @Expose
    String deliveryFeeMode;

    @DatabaseField
    @Expose
    float deliveryBaseCost;

    @DatabaseField
    @Expose
    float deliveryKmBase;

    @DatabaseField
    @Expose
    float deliveryKmExtra;

    @DatabaseField
    @Expose
    float deliveryExceded10Km;

    public BussinesContract() {
    }
    
    

    public BussinesContract(Bussines bussines) {
        this.bussinesId = bussinesId;
        this.chargeableBalanceAccountId = bussines.getBalanceAccountId();
        this.creationDate = DateUtils.now();
        this.charge = 22;
        this.chargeMode = ChargeMode.PER_ORDER;
        this.rechargeable = false;
        this.maxDebt = -500;
        this.paysCuota = false;
        this.cuotaCost = 150;
        this.orderInsuranceType = OrderInsuranceType.NONE;
        this.orderInsuranceCost = 0;
        this.deliveryFeeMode = DeliveryFeesMode.CUSTOM;
        this.deliveryBaseCost = 45;
        this.deliveryKmBase = 5;
        this.deliveryKmExtra = 8;
        this.deliveryExceded10Km = 20;
    }

    /**
     * *
     * cargo del costo de servicio, executar cada vez que se entrega un pedido
     *
     * @param order
     * @param toAccount cuenta de la deuda
     * @param conceptAccount cuenta o la que se dirigira los montos al pagar el
     * servicio
     */
    public void onOrderDelivered(Order order) {
        Transacction t = new Transacction();

        String toAccount = DbBalancer.Contability.BalancesAccounts.MainBalancesAccountsIDs.CARGO;
        String conceptAccount = DbBalancer.Contability.BalancesAccounts.MainBalancesAccountsIDs.SERVICIOS_REPARTOS;

        t.setFrom(chargeableBalanceAccountId);
        t.setTo(toAccount);
        t.setConcept(conceptAccount);

        switch (this.chargeMode) {
            case ChargeMode.PER_ORDER:
                t.setMount(this.charge);
                break;

            case ChargeMode.ORDER_PERCENTAGE:
                double orderCost = order.getOrderCost();
                if (orderCost > 0) {
                    t.setMount((float) ((this.charge / 100) * orderCost));
                } else {
                    throw new IllegalArgumentException("Order cost must be greater than 0");
                }
                break;

            default:
                t.setMount(0);
        }

        /*cargamos el costo de servicio*/
        t.setRef("Entrega de orden " + order.getShortId());
        SQLiteDB.Contability.Transacctions.execute(t);
        /*Verificamos y cargamos seguro*/
        if (!this.orderInsuranceType.equals(OrderInsuranceType.NONE)) {
            chargeOrderInsurance(order);
        }

        /*verificamos el costo de envio, si es cargo a cuenta*/
        if (this.deliveryFeeMode.equals(DeliveryFeesMode.CUSTOM_WITH_ACCOUNT_CHARGE)
                || this.deliveryFeeMode.equals(DeliveryFeesMode.FIXED_WITH_ACCOUNT_CHARGE)) {
            chargeDeliveryFeeToAccount(order);
        }

    }

    /**
     * *
     * Carga el costo de envio a esta cuenta o contro, se asume que el cotizador
     * regreso 0
     */
    public void chargeDeliveryFeeToAccount(Order order) {

        String toAccount = DbBalancer.Contability.BalancesAccounts.MainBalancesAccountsIDs.CARGO;
        String conceptAccount = DbBalancer.Contability.BalancesAccounts.MainBalancesAccountsIDs.SERVICIOS_REPARTOS;

        Transacction t = new Transacction();
        t.setFrom(chargeableBalanceAccountId);
        t.setTo(toAccount);
        t.setConcept(conceptAccount);
        t.setMount(order.getDeliveryCost());

        /*cargamos el costo de servicio*/
        t.setRef("Envio con cargo a cuenta: " + order.getShortId());
        SQLiteDB.Contability.Transacctions.execute(t);

    }

    /**
     * *
     * Cargo de seguro de ordenes
     *
     * @param order
     */
    public void chargeOrderInsurance(Order order) {

        if (this.orderInsuranceType.equals(OrderInsuranceType.PER_ORDER)) {
            String toAccount = DbBalancer.Contability.BalancesAccounts.MainBalancesAccountsIDs.CARGO;
            String conceptAccount = DbBalancer.Contability.BalancesAccounts.MainBalancesAccountsIDs.SERGUROS_ORDENES;

            Transacction t = new Transacction();

            t.setFrom(chargeableBalanceAccountId);

            t.setTo(toAccount);
            t.setConcept(conceptAccount);
            t.setMount((this.orderInsuranceCost / 100) * order.getOrderCost());
            t.setRef("Seguro " + order.getShortId());
            SQLiteDB.Contability.Transacctions.execute(t);

        }

    }

    /*Correr cuando sea necesario, raliza el cargo de cuota a este contrato*/
    public void chargeCuota(String toAccount, String conceptAccount) {
        if (this.paysCuota) {
            Transacction t = new Transacction();

            t.setFrom(chargeableBalanceAccountId);
            t.setTo(toAccount);
            t.setConcept(conceptAccount);
            t.setMount(this.cuotaCost);
            t.setRef("Pago de cuota ");
            SQLiteDB.Contability.Transacctions.execute(t);

        }

    }

    // Función para obtener un objeto con los datos de entrega
    public DeliveryDetails getDeliveryDetails() {
        boolean fixed = this.deliveryFeeMode.equals(DeliveryFeesMode.FIXED) || this.deliveryFeeMode.equals(DeliveryFeesMode.FIXED_WITH_ACCOUNT_CHARGE);
        return new DeliveryDetails(fixed, deliveryBaseCost, deliveryKmBase, deliveryKmExtra, deliveryExceded10Km);
    }

    // Clase contenedora para los datos de entrega
    /*tipo de cotizacion de las tarifas de entrega*/
    interface DeliveryFeesMode {

        /*No se cobra tarifa de envio*/
        String NONE = "NONE";
        /*Fixed indica que la tarifa de entrega o el cotizador dara un monto fijo BaseCost*/
        String FIXED = "FIXED";
        /*se carga a la cuenta del bussines*/
        String FIXED_WITH_ACCOUNT_CHARGE = "FIXED_WITH_ACCOUNT_CHARGE";
        /*Tarifa dependera de las distancias y km extras*/
        String CUSTOM = "CUSTOM";
        /*se carga a la cuenta del bussines*/
        String CUSTOM_WITH_ACCOUNT_CHARGE = "CUSTOM_WITH_ACCOUNT_CHARGE";
    }

    /*tipo de seguro de ordenes*/
    interface OrderInsuranceType {

        /*sin seguro, cualquier perdidad es a cargo del repartidor y del restaurante*/
        String NONE = "NONE";
        /*un porcentaje por orden*/
        String PER_ORDER = "PER_ORDER";
        /*seguro con pago semanal para cubrir las perdidas*/
        String WEEKLY = "WEEKLY";
        String MONTHDLY = "MONTHDLY";
    }

    /*Modo de cobro por el costo de servicio*/
    interface ChargeMode {

        /*cobrar por % de cada orden*/
        String ORDER_PERCENTAGE = "ORDER_PERCENTAGE";
        /*cobro por cada orden*/
        String PER_ORDER = "PER_ORDER";
        String WEEKLY = "WEEKLY";
        String MONTHDLY = "MONTHDLY";

    }
    
    public BalanceAccount getBalanceAccount() {
        return SQLiteDB.Contability.BalancesAccounts.BalancesAccounts().read(this.chargeableBalanceAccountId);
    }

    @Data

    /*si es fiex, el cotizador tomara el costo de envio usando deliveryBaseCost*/
    public class DeliveryDetails {

        private boolean fixed;
        private float deliveryBaseCost;
        private float deliveryKmBase;
        private float deliveryKmExtra;
        private float deliveryExceded10Km;

        // Constructor
        public DeliveryDetails(boolean fixed, float deliveryBaseCost, float deliveryKmBase, float deliveryKmExtra, float deliveryExceded10Km) {
            this.fixed = fixed;
            this.deliveryBaseCost = deliveryBaseCost;
            this.deliveryKmBase = deliveryKmBase;
            this.deliveryKmExtra = deliveryKmExtra;
            this.deliveryExceded10Km = deliveryExceded10Km;
        }

    }

}
