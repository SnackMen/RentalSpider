package com.ws.dao;

import com.ws.dto.DataQueryVOPage;
import com.ws.dto.HouseDTO;
import com.ws.dto.SearchCriteriaDTO;

import java.util.List;

/**
 * Created by laowang on 17-4-16.
 */
public interface IHouseDAO {

    DataQueryVOPage[] searchFiveEight(SearchCriteriaDTO searchCriteriaDTO);

    DataQueryVOPage[] searchFiveEightPersonal(SearchCriteriaDTO searchCriteriaDTO);

    DataQueryVOPage[] searchAnJuKe(SearchCriteriaDTO searchCriteriaDTO);

    void getId(int id);

}
