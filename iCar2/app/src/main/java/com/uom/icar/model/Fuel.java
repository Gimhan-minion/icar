package com.uom.icar.model;

import java.util.Date;

public class Fuel {
    private String id;
    private String userId;
    private String vehicleNo;
    private int fuelInLitres;
    private int fuelCharge;
    private String refuelDate;
    private String total;


    public Fuel(){

    }

    public Fuel(String id,String userId, String vehicleNo, int fuelInLitres, int fuelCharge, String refuelDate,String total) {
        this.id=id;
        this.userId = userId;
        this.vehicleNo = vehicleNo;
        this.fuelInLitres = fuelInLitres;
        this.fuelCharge = fuelCharge;
        this.refuelDate = refuelDate;
        this.total = total;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public int getFuelInLitres() {
        return fuelInLitres;
    }

    public void setFuelInLitres(int fuelInLitres) {
        this.fuelInLitres = fuelInLitres;
    }

    public int getFuelCharge() {
        return fuelCharge;
    }

    public void setFuelCharge(int fuelCharge) {
        this.fuelCharge = fuelCharge;
    }

    public String getRefuelDate() {
        return refuelDate;
    }

    public void setRefuelDate(String refuelDate) {
        this.refuelDate = refuelDate;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
