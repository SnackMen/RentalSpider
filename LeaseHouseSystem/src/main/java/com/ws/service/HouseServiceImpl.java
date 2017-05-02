package com.ws.service;

import com.ws.config.MySQLDataConfiguration;
import com.ws.dao.HouseDAOImpl;
import com.ws.dao.IHouseDAO;
import com.ws.dto.HouseDTO;
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

    private HouseDTO[] houseDTOS;

    private List<HouseDTO> houseDTOList = new ArrayList<>();

    @Override
    public HouseDTO[] searchAll(String type) {
        if(type.equals("58")){
            houseDTOList = houseDAO.searchFiveEightAll();
        }else if(type.equals("anjuke")){
            houseDTOList = houseDAO.searchAnJuKe();
        }
        houseDTOS = new HouseDTO[houseDTOList.size()];
        houseDTOList.toArray(houseDTOS);
        return houseDTOS;
    }

    @Override
    public HouseDTO[] searchByPrice(int price) {
        return new HouseDTO[0];
    }

    @Autowired
    public void setHouseDAO(@Qualifier("houseDAOImpl") IHouseDAO houseDAO){
        this.houseDAO = houseDAO;
    }


    @Override
    public void searchById(int id) {
        System.out.println(houseDAO);
//        houseDAO.getId(id);
    }
}
