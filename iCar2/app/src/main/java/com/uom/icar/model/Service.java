package com.uom.icar.model;

import java.util.Date;

public class Service {
    private int id;
    private String vehicleNo;
    private String userNic;
    private String name;
    private String description;
    private Date serviceDate;
    private int currentMileage;

    public Service(){

    }

    public Service(int id, String vehicleNo, String userNic, String name, String description, Date serviceDate, int currentMileage) {
        this.id = id;
        this.vehicleNo = vehicleNo;
        this.userNic = userNic;
        this.name = name;
        this.description = description;
        this.serviceDate = serviceDate;
        this.currentMileage = currentMileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }
}
