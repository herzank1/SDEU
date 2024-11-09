/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.deliveryexpress.sdeu.objects.net.responses;

import com.deliveryexpress.sdeu.objects.net.commands.Command;
import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author DeliveryExpress
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetBussinesCotaizerResponse extends Response {

    @Expose
    float distancekm;
    @Expose
    String startAddress;
    @Expose
    String endAddress;
    @Expose
    String startLocation;
    @Expose
    String endLocation;
    @Expose
    String humanReadable;

    @Expose
    double baseCost;
    @Expose
    double extraPerKm;
    @Expose
    double extraPer10Km;
    @Expose
    double totalCost;

 /***
  * 
  * @param _command
  * @param status
  * @param distancekm
  * @param startAddress
  * @param endAddress
  * @param startLocation
  * @param endLocation
  * @param humanReadable
  * @param baseCost
  * @param extraPerKm
  * @param extraPer10Km
  * @param totalCost 
  */
    public GetBussinesCotaizerResponse(Command _command, String status,float distancekm, String startAddress, String endAddress, String startLocation, String endLocation, String humanReadable, double baseCost, double extraPerKm, double extraPer10Km, double totalCost) {
        super(_command, status);
        this.distancekm = distancekm;
        this.startAddress = startAddress;
        this.endAddress = endAddress;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.humanReadable = humanReadable;
        this.baseCost = baseCost;
        this.extraPerKm = extraPerKm;
        this.extraPer10Km = extraPer10Km;
        this.totalCost = totalCost;
    }
    
    

}
