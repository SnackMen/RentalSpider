package com.ws.domain;

import com.ws.log.CrawLog;
import com.ws.property.PropertySeting;
import dateFormate.DateFormat;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Properties;
import java.util.Date;
/**
 * Created by laowang on 17-3-4.
 */
public class ConnectToDomain {
    private static String url;
    private static String user;
    private static String pwd;
    private PreparedStatement preparedStatement;
    private Logger logger = CrawLog.getLogger();

    static {
        try{
            Properties properties = PropertySeting.getProperties(new ConnectToDomain());
            String driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.user");
            pwd = properties.getProperty("jdbc.password");
            Class.forName(driver);//regist driver
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get connection
    private Connection getConnection() {
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(url,user,pwd);
        }catch (Exception e){
            logger.error("connect to db error: " + e.getMessage());
            e.printStackTrace();
            System.out.println("connect to sql wrong "+e.getMessage());
        }
        return connection;
    }

    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertMessage(HouseDTO houseDTO) throws SQLException {
        Connection connection = getConnection();
        try {
            String sql = "insert into 58housedata(rental_house_message,rental_house_link,rental_house_type,rental_house_location,rental_house_price,rental_house_personal,rental_house_date" +
                    ") values(?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,houseDTO.getHouseMessage());
            preparedStatement.setString(2,houseDTO.getHouseLink());
            preparedStatement.setString(3,houseDTO.getHouseType());
            preparedStatement.setString(4,houseDTO.getHouseLocation());
            preparedStatement.setString(5,houseDTO.getHousePrice());
            preparedStatement.setInt(6,houseDTO.getHousePersonal());
            preparedStatement.setDate(7, DateFormat.format(new Date()));
            preparedStatement.executeUpdate();
        }catch (Exception e){
            logger.error("insert to db error: "+e.getMessage());
            e.printStackTrace();
            System.out.println("insert into db wrong "+e.getMessage());
        }finally {
            preparedStatement.close();
            closeConnection(connection);
        }
    }

    public void insert(HouseDTO houseDTO) throws SQLException {
        insertMessage(houseDTO);
    }

}
