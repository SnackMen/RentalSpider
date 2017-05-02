package com.ws.service;

import com.ws.dto.DataQueryVOPage;
import com.ws.dto.HouseDTO;
import com.ws.dto.SearchCriteriaDTO;


/**
 * Created by laowang on 17-4-16.
 */
public interface IHouseService {

    DataQueryVOPage[] searchAll(SearchCriteriaDTO searchCriteriaDTO);

    void searchById(int id);
}
