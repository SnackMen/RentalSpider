package com.ws.mapper;

import com.ws.dto.HouseDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by laowang on 17-4-16.
 */
public class HouseMapper implements RowMapper<HouseDTO> {
    @Override
    public HouseDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId(resultSet.getInt("rental_id"));
        houseDTO.setHouseMessage(resultSet.getString("rental_house_message"));
        houseDTO.setHouseLink(resultSet.getString("rental_house_link"));
        houseDTO.setHouseType(resultSet.getString("rental_house_type"));
        houseDTO.setHouseLocation(resultSet.getString("rental_house_location"));
        houseDTO.setHousePrice(resultSet.getString("rental_house_price"));
        houseDTO.setHousePersonal(resultSet.getInt("rental_house_personal"));
        houseDTO.setDate(resultSet.getDate("rental_house_date"));
        return houseDTO;
    }
}
