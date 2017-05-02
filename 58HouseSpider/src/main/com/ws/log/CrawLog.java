package com.ws.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by laowang on 17-4-15.
 */
public class CrawLog {
    public static Logger getLogger(){
//        PropertyConfigurator.configure("log4j.properties");
        return Logger.getLogger(CrawLog.class);
    }
}
