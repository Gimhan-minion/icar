package com.uom.icar.model;

public class Vehicle {
    private String vehicleNo;
    private String userNic;
    private String vehicleType;
    private String chassisNo;
    private String engineNo;
    private String serviceMileage;
    private String fuelTankCapacity;
    private String currentFuelAmount;
    private String mileage;
    private String mileagePerLitre;

    public Vehicle(String vehicleNo, String userNic, String vehicleType, String chassisNo, String engineNo, String serviceMileage, String fuelTankCapacity, String currentFuelAmount, String mileage, String mileagePerLitre) {
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

    public String getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(String fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public String getCurrentFuelAmount() {
        return currentFuelAmount;
    }

    public void setCurrentFuelAmount(String currentFuelAmount) {
        this.currentFuelAmount = currentFuelAmount;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getServiceMileage() {
        return serviceMileage;
    }

    public void setServiceMileage(String serviceMileage) {
        this.serviceMileage = serviceMileage;
    }

    public String getMileagePerLitre() {
        return mileagePerLitre;
    }

    public void setMileagePerLitre(String mileagePerLitre) {
        this.mileagePerLitre = mileagePerLitre;
    }

    public String getUserNic() {
        return userNic;
    }

    public void setUserNic(String userNic) {
        this.userNic = userNic;
    }
}
