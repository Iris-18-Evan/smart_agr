/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

public class Field implements Serializable {
    private int id;
    private int farmerId;     // or User farmer
    private String fieldCode;
    private String name;
    private String location;
    private double areaHectares;
    private Double lat;
    private Double lng;

    public Field() {}

    public Field(int id, int farmerId, String fieldCode, String name,
                 String location, double areaHectares, Double lat, Double lng) {
        this.id = id;
        this.farmerId = farmerId;
        this.fieldCode = fieldCode;
        this.name = name;
        this.location = location;
        this.areaHectares = areaHectares;
        this.lat = lat;
        this.lng = lng;
    }

        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAreaHectares() {
        return areaHectares;
    }

    public void setAreaHectares(double areaHectares) {
        this.areaHectares = areaHectares;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}

