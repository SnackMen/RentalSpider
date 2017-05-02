package com.ws.service;

import com.ws.config.MySQLDataConfiguration;
import com.ws.dao.HouseDAOImpl;
import com.ws.dao.IHouseDAO;
import com.ws.dto.DataQueryVOPage;
import com.ws.dto.HouseDTO;
import com.ws.dto.SearchCriteriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by laowang on 17-4-16.
 */
@Service
public class HouseServiceImpl implements IHouseService {


//    private IHouseDAO houseDAO;

    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MySQLDataConfiguration.class);

    private IHouseDAO houseDAO = (HouseDAOImpl) ctx.getBean("getHouseDAO");




    @Autowired
    public void setHouseDAO(@Qualifier("houseDAOImpl") IHouseDAO houseDAO){
        this.houseDAO = houseDAO;
    }


    @Override
    public DataQueryVOPage[] searchAll(SearchCriteriaDTO searchCriteriaDTO) {
        if(searchCriteriaDTO.isAnjuke())
            return houseDAO.searchAnJuKe(searchCriteriaDTO);
        else if(searchCriteriaDTO.isPersonal())
            return houseDAO.searchFiveEightPersonal(searchCriteriaDTO);
        return houseDAO.searchFiveEight(searchCriteriaDTO);
    }

    @Override
    public void searchById(int id) {
        System.out.println(houseDAO);
//        houseDAO.getId(id);
    }
}
