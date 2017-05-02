package com.ws.property;

import com.ws.log.CrawLog;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by laowang on 17-4-15.
 */
public class PropertySeting {
    private  static Properties properties = new Properties();
    public static Properties getProperties(Object object){
        try{
            InputStream inputStream = object.getClass().getClassLoader().getResourceAsStream("com/ws/db.properties");
            properties.load(inputStream);
            inputStream.close();
        }catch (Exception e){
            CrawLog.getLogger().error("get db.properties error "+e.getMessage());
            System.out.println("properties exception -- can not link to properties" + e.getMessage());
            e.printStackTrace();
        }
        return properties;
    }
}
