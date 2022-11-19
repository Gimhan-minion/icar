package com.uom.icar.model;

public class Vehicle {
    private String vehicleNo;
    private String userNic;
    private String vehicleType;
    private String chassisNo;
    private String engineNo;
    private int serviceMileage;
    private int fuelTankCapacity;
    private int currentFuelAmount;
    private int mileage;
    private int mileagePerLitre;

    public Vehicle(String vehicleNo, String userNic, String vehicleType, String chassisNo, String engineNo, int serviceMileage, int fuelTankCapacity, int currentFuelAmount, int mileage, int mileagePerLitre) {
        this.vehicleNo = vehicleNo;
        this.userNic = userNic;
        this.vehicleType = vehicleType;
        this.chassisNo = chassisNo;
        this.engineNo = engineNo;
        this.serviceMileage = serviceMileage;
        this.fuelTankCapacity = fuelTankCapacity;
        this.currentFuelAmount = currentFuelAmount;
        this.mileage = mileage;
        this.mileagePerLitre = mileagePerLitre;
    }

    public Vehicle(){

    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public int getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(int fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public int getCurrentFuelAmount() {
        return currentFuelAmount;
    }

    public void setCurrentFuelAmount(int currentFuelAmount) {
        this.currentFuelAmount = currentFuelAmount;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getServiceMileage() {
        return serviceMileage;
    }

    public void setServiceMileage(int serviceMileage) {
        this.serviceMileage = serviceMileage;
    }

    public int getMileagePerLitre() {
        return mileagePerLitre;
    }

    public void setMileagePerLitre(int mileagePerLitre) {
        this.mileagePerLitre = mileagePerLitre;
    }

    public String getUserNic() {
        return userNic;
    }

    public void setUserNic(String userNic) {
        this.userNic = userNic;
    }
}
