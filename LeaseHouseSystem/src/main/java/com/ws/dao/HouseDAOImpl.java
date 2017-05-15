package com.ws.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.ws.dateFormate.DateFormat;
import com.ws.dto.DataQueryVOPage;
import com.ws.dto.HouseDTO;
import com.ws.dto.SearchCriteriaDTO;
import com.ws.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by laowang on 17-4-16.
 */
@Repository
public class HouseDAOImpl  implements IHouseDAO {

    private JdbcTemplate jdbcTemplate;

    private final String HOUSE_PRICE = "AND rental_house_price";

    private final String LOW_PRICE = " >= # ";

    private final String AND = "AND";

    private final String HIGH_PRICE = " < # ";

    private final String WORK_PLACE = "AND rental_";

    private final String MAIN_SQL = "SELECT * FROM 58housedata WHERE ";

    private final String RENTAL_DATE = "rental_house_date='#' ";

    private final String COUNT = "LIMIT 0,#";

    private final String INCLUDE_58_OR_ANJUKE = "AND rental_house_link like '#' ";

    private final String PERSONAL_HOUSE = "AND rental_house_personal=# ";

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
//        count *= 0.01;
        Calendar calendar=Calendar.getInstance();
        calendar.set(2017, Calendar.MAY, 14, 0, 0,0);  //年月日  也可以具体到时分秒如calendar.set(2015, 10, 12,11,32,52);
        Date date1=calendar.getTime();//date就是你需要的时间
        java.sql.Date date = DateFormat.format(date1);
//        java.sql.Date date = DateFormat.format(new java.util.Date());
        List<HouseDTO> houseDTOs = new ArrayList<>();
        String lowPrice = searchCriteriaDTO.getLowPrice();
        String highPrice = searchCriteriaDTO.getHighPrice();
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(MAIN_SQL).append(RENTAL_DATE.replace("#",date.toString())).append(INCLUDE_58_OR_ANJUKE.replace("#","%58.com%"));
        if(lowPrice != null && !"".equals(lowPrice)){
            stringBuffer.append(HOUSE_PRICE);
            if(highPrice != null && !"".equals(highPrice))
                stringBuffer.append(LOW_PRICE.replace("#",lowPrice)).append(HOUSE_PRICE).append(HIGH_PRICE.replace("#",highPrice));
            else
                stringBuffer.append(LOW_PRICE.replace("#",lowPrice));
        }else if(highPrice != null && !"".equals(highPrice)){
            stringBuffer.append(HOUSE_PRICE).append(HIGH_PRICE.replace("#",highPrice));
        }
        stringBuffer.append(COUNT.replace("#",String.valueOf(count)));
        System.out.println(stringBuffer.toString());
        try {
//            String SQL = "SELECT * FROM 58housedata WHERE rental_house_date='" + date + "' LIMIT 0," + count;
            houseDTOs = jdbcTemplate.query(stringBuffer.toString(), new HouseMapper());
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
        int count = dataCount(searchCriteriaDTO.getMapLevel());
//        count *= 0.01;
        Calendar calendar=Calendar.getInstance();
        calendar.set(2017, Calendar.MAY, 14, 0, 0,0);  //年月日  也可以具体到时分秒如calendar.set(2015, 10, 12,11,32,52);
        Date date1=calendar.getTime();//date就是你需要的时间
        java.sql.Date date = DateFormat.format(date1);
//        java.sql.Date date = DateFormat.format(new java.util.Date());
        List<HouseDTO> houseDTOs = new ArrayList<>();
        String lowPrice = searchCriteriaDTO.getLowPrice();
        String highPrice = searchCriteriaDTO.getHighPrice();
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(MAIN_SQL).append(RENTAL_DATE.replace("#",date.toString())).append(INCLUDE_58_OR_ANJUKE.replace("#","%58.com%")).append(PERSONAL_HOUSE.replace("#","1"));
        if(lowPrice != null && !"".equals(lowPrice)){
            stringBuffer.append(HOUSE_PRICE);
            if(highPrice != null && !"".equals(highPrice))
                stringBuffer.append(LOW_PRICE.replace("#",lowPrice)).append(HOUSE_PRICE).append(HIGH_PRICE.replace("#",highPrice));
            else
                stringBuffer.append(LOW_PRICE.replace("#",lowPrice));
        }else if(highPrice != null && !"".equals(highPrice)){
            stringBuffer.append(HOUSE_PRICE).append(HIGH_PRICE.replace("#",highPrice));
        }
        stringBuffer.append(COUNT.replace("#",String.valueOf(count)));
        System.out.println(stringBuffer.toString());
        try {
//            String SQL = "SELECT * FROM 58housedata WHERE rental_house_date='" + date + "' LIMIT 0," + count;
            houseDTOs = jdbcTemplate.query(stringBuffer.toString(), new HouseMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        DataQueryVOPage dataQueryVOPage = new DataQueryVOPage();
        dataQueryVOPage.setHouseDTOList(houseDTOs);
        return dataQueryVOPage;
    }

    /**
     * search anjuke house
     * @param searchCriteriaDTO
     * @return
     */
    @Override
    public DataQueryVOPage searchAnJuKe(SearchCriteriaDTO searchCriteriaDTO) {
        int count = dataCount(searchCriteriaDTO.getMapLevel());
        count *= 0.01;
        Calendar calendar=Calendar.getInstance();
        calendar.set(2017, Calendar.MAY, 14, 0, 0,0);  //年月日  也可以具体到时分秒如calendar.set(2015, 10, 12,11,32,52);
        Date date1=calendar.getTime();//date就是你需要的时间
        java.sql.Date date = DateFormat.format(date1);
//        java.sql.Date date = DateFormat.format(new java.util.Date());
        List<HouseDTO> houseDTOs = new ArrayList<>();
        String lowPrice = searchCriteriaDTO.getLowPrice();
        String highPrice = searchCriteriaDTO.getHighPrice();
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append(MAIN_SQL).append(RENTAL_DATE.replace("#",date.toString())).append(INCLUDE_58_OR_ANJUKE.replace("#","%anjuke.com%"));
        if(lowPrice != null && !"".equals(lowPrice)){
            stringBuffer.append(HOUSE_PRICE);
            if(highPrice != null && !"".equals(highPrice))
                stringBuffer.append(LOW_PRICE.replace("#",lowPrice)).append(HOUSE_PRICE).append(HIGH_PRICE.replace("#",highPrice));
            else
                stringBuffer.append(LOW_PRICE.replace("#",lowPrice));
        }else if(highPrice != null && !"".equals(highPrice)){
            stringBuffer.append(HOUSE_PRICE).append(HIGH_PRICE.replace("#",highPrice));
        }
        stringBuffer.append(COUNT.replace("#",String.valueOf(count)));
        System.out.println(stringBuffer.toString());
        try {
//            String SQL = "SELECT * FROM 58housedata WHERE rental_house_date='" + date + "' LIMIT 0," + count;
            houseDTOs = jdbcTemplate.query(stringBuffer.toString(), new HouseMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        DataQueryVOPage dataQueryVOPage = new DataQueryVOPage();
        dataQueryVOPage.setHouseDTOList(houseDTOs);
        return dataQueryVOPage;
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
