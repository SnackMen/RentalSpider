package com.ws.service;

import com.ws.dto.HouseDTO;


/**
 * Created by laowang on 17-4-16.
 */
public interface IHouseService {

    HouseDTO[] searchAll(String type);

    HouseDTO[] searchByPrice(int price);

    void searchById(int id);
}
