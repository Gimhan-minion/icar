package com.uom.icar.model;

import java.util.Date;

public class Service {
    private String id;
    private String vehicleNo;
    private String userNic;
    private String name;
    private String description;
    private String serviceDate;
    private String currentMileage;

    public Service(){

    }

    public Service(String id, String vehicleNo, String userNic, String name, String description, String serviceDate, String currentMileage) {
        this.id = id;
        this.vehicleNo = vehicleNo;
        this.userNic = userNic;
        this.name = name;
        this.description = description;
        this.serviceDate = serviceDate;
        this.currentMileage = currentMileage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getUserNic() {
        return userNic;
    }

    public void setUserNic(String userNic) {
        this.userNic = userNic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(String currentMileage) {
        this.currentMileage = currentMileage;
    }
}
