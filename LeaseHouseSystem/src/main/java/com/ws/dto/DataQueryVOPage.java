package com.ws.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by laowang on 17-5-2.
 */
public class DataQueryVOPage implements Serializable {
    private static final long serialVersionUID = 1;

    private int visitQuantity;

    private List<HouseDTO> houseDTOList;

    public List<HouseDTO> getHouseDTOList() {
        return houseDTOList;
    }

    public void setHouseDTOList(List<HouseDTO> houseDTOList) {
        this.houseDTOList = houseDTOList;
    }

    public int getVisitQuantity() {
        return visitQuantity;
    }

    public void setVisitQuantity(int visitQuantity) {
        this.visitQuantity = visitQuantity;
    }
}
