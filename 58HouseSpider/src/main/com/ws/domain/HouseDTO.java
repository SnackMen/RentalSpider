package com.ws.domain;

import java.sql.Date;

/**
 * Created by laowang on 17-3-4.
 */
public class HouseDTO {
    private int id;
    private String houseMessage;
    private String houseLink;
    private String houseType;
    private String houseLocation;
    private String housePrice;
    private int housePersonal;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getHousePersonal() {
        return housePersonal;
    }

    public void setHousePersonal(int housePersonal) {
        this.housePersonal = housePersonal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseMessage() {
        return houseMessage;
    }

    public void setHouseMessage(String houseMessage) {
        this.houseMessage = houseMessage;
    }

    public String getHouseLink() {
        return houseLink;
    }

    public void setHouseLink(String houseLink) {
        this.houseLink = houseLink;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseLocation() {
        return houseLocation;
    }

    public void setHouseLocation(String houseLocation) {
        this.houseLocation = houseLocation;
    }

    public String getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(String housePrice) {
        this.housePrice = housePrice;
    }


    @Override
    public String toString() {
        return "HouseDTO{" +
                "id=" + id +
                ", houseMessage='" + houseMessage + '\'' +
                ", houseLink='" + houseLink + '\'' +
                ", houseType='" + houseType + '\'' +
                ", houseLocation='" + houseLocation + '\'' +
                ", housePrice=" + housePrice +
                '}';
    }
}
