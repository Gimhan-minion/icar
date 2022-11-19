package com.uom.icar.model;

import java.util.Date;

public class Fuel {
    private String userId;
    private String vehicleNo;
    private int fuelInLitres;
    private int fuelCharge;
    private Date refuelDate;
    private int totalFuel;

    public Fuel(){

    }

    public Fuel(String userId, String vehicleNo, int fuelInLitres, int fuelCharge, Date refuelDate, int totalFuel) {
        this.userId = userId;
        this.vehicleNo = vehicleNo;
        this.fuelInLitres = fuelInLitres;
        this.fuelCharge = fuelCharge;
        this.refuelDate = refuelDate;
        this.totalFuel = totalFuel;
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

    public Date getRefuelDate() {
        return refuelDate;
    }

    public void setRefuelDate(Date refuelDate) {
        this.refuelDate = refuelDate;
    }

    public int getTotalFuel() {
        return totalFuel;
    }

    public void setTotalFuel(int totalFuel) {
        this.totalFuel = totalFuel;
    }
}
