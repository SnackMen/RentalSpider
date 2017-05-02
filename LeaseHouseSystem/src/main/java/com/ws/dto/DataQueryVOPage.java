package com.ws.dto;

import java.io.Serializable;

/**
 * Created by laowang on 17-5-2.
 */
public class DataQueryVOPage implements Serializable {
    private static final long serialVersionUID = 1;

    private HouseDTO[] houseDTOS;

    public HouseDTO[] getHouseDTOS() {
        return houseDTOS;
    }

    public void setHouseDTOS(HouseDTO[] houseDTOS) {
        this.houseDTOS = houseDTOS;
    }
}
