package com.ws.dto;

/**
 * Created by laowang on 17-5-2.
 */
public class SearchCriteriaDTO {
    private String dataSource;

    private String lowPrice;

    private String highPrice;

    private String workPlace;

    private String mapLevel;

    private String ip;

    private boolean isAnjuke;

    private boolean isPersonal;

    public boolean isAnjuke() {
        return isAnjuke;
    }

    public void setAnjuke(boolean anjuke) {
        isAnjuke = anjuke;
    }

    public boolean isPersonal() {
        return isPersonal;
    }

    public void setPersonal(boolean personal) {
        isPersonal = personal;
    }

    public String getMapLevel() {
        return mapLevel;
    }

    public void setMapLevel(String mapLevel) {
        this.mapLevel = mapLevel;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(String lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(String highPrice) {
        this.highPrice = highPrice;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
}
