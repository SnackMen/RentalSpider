package com.ws.dao;

import com.ws.dto.DataQueryVOPage;
import com.ws.dto.HouseDTO;
import com.ws.dto.SearchCriteriaDTO;
import com.ws.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by laowang on 17-4-16.
 */
@Repository
public class HouseDAOImpl  implements IHouseDAO {

    private JdbcTemplate jdbcTemplate;

    private List<HouseDTO> houseDTO = new ArrayList<>();

    @Autowired
    public HouseDAOImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public DataQueryVOPage searchFiveEight(SearchCriteriaDTO searchCriteriaDTO) {
        return new DataQueryVOPage();
    }

    @Override
    public DataQueryVOPage searchFiveEightPersonal(SearchCriteriaDTO searchCriteriaDTO) {
        return new DataQueryVOPage();
    }

    @Override
    public DataQueryVOPage searchAnJuKe(SearchCriteriaDTO searchCriteriaDTO) {
        return new DataQueryVOPage();
    }

    @Override
    public void getId(int id){
        try {
            String SQL = "select * from 58housedata where rental_id=?";
            List<HouseDTO> houseDTO = jdbcTemplate.query(SQL,new HouseMapper(),id);
            System.out.println(houseDTO.size());
            System.out.println(houseDTO.get(0).getHouseLink());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
