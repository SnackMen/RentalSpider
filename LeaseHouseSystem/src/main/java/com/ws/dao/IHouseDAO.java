package com.ws.dao;

import com.ws.dto.HouseDTO;

import java.util.List;

/**
 * Created by laowang on 17-4-16.
 */
public interface IHouseDAO {

    List<HouseDTO> searchFiveEightAll();

    List<HouseDTO> searchFiveEightPersonalAll();

    List<HouseDTO> searchFiveEightByPrice(String price, String personal, boolean isAnjuke);

    List<HouseDTO> searchAnJuKe();

    void getId(int id);

}
