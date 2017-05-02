package com.ws.dto2domain;

import com.ws.spider.CrawlPageProgress;
import com.ws.property.PropertySeting;
import gregorianCalendar.GregorianCalendarTime;
import us.codecraft.webmagic.Spider;

import java.util.Properties;

/**
 * Created by laowang on 17-3-4.
 */
public class DTO2Domain {
    public static void main(String []args){
        Properties properties = PropertySeting.getProperties(new DTO2Domain());
        String url = properties.getProperty("58_house_url");
        String personalUrl = properties.getProperty("58_house_personal_url");
        String anjukeUrl = properties.getProperty("anjuke_house_personal_url");
        if(!GregorianCalendarTime.isMorningOrAfternoon()){
            for(int i = 1;i <= 40; i++){
                Spider.create(new CrawlPageProgress()).addUrl(url+String.valueOf(i)+"/").thread(1).run();
            }

            for(int i = 1;i <=40; i++){
                Spider.create(new CrawlPageProgress()).addUrl(personalUrl+String.valueOf(i)+"/").thread(1).run();
            }
        }else{
            for(int i=1; i<= 40;i++){
                Spider.create(new CrawlPageProgress()).addUrl(anjukeUrl+String.valueOf(i)+"/").thread(1).run();
            }
        }

    }
}
