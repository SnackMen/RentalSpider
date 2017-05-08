package com.ws.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ws.dateFormate.DateFormat;
import com.ws.dto.DataQueryVOPage;
import com.ws.dto.HouseDTO;
import com.ws.dto.SearchCriteriaDTO;
import com.ws.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


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

    private ComboPooledDataSource dataSource;

    @Autowired
    public HouseDAOImpl(ComboPooledDataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }


    /**
     * search 58 business house
     * @param searchCriteriaDTO
     * @return
     */
    @Override
    public DataQueryVOPage searchFiveEight(SearchCriteriaDTO searchCriteriaDTO) {
        int count = dataCount(searchCriteriaDTO.getMapLevel());
        java.sql.Date date = DateFormat.format(new java.util.Date());
        List<HouseDTO> houseDTOs = new ArrayList<>();
        try {
            String SQL = "SELECT * FROM 58housedata WHERE rental_house_date='" + date + "' LIMIT 0," + count;
            houseDTOs = jdbcTemplate.query(SQL, new HouseMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        DataQueryVOPage dataQueryVOPage = new DataQueryVOPage();
        dataQueryVOPage.setHouseDTOList(houseDTOs);
        return dataQueryVOPage;
    }

    /**
     * search 58 personal house
     * @param searchCriteriaDTO
     * @return
     */
    @Override
    public DataQueryVOPage searchFiveEightPersonal(SearchCriteriaDTO searchCriteriaDTO) {
        return new DataQueryVOPage();
    }

    /**
     * search anjuke house
     * @param searchCriteriaDTO
     * @return
     */
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

    private int dataCount(String mapLevel){
        int level = Integer.valueOf(mapLevel);
        if(level <= 10 )
            return 1000;
        if(level >= 19 )
            return 10000;
        return 1000*(level - 9);
    }
}
