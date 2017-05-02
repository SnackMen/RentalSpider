import com.ws.domain.ConnectToDomain;
import com.ws.dto2domain.DTO2Domain;
import com.ws.log.CrawLog;
import com.ws.property.PropertySeting;
import com.ws.spider.CrawlPageProgress;
import gregorianCalendar.GregorianCalendarTime;
import us.codecraft.webmagic.Spider;

import java.util.Properties;

/**
 * Created by laowang on 17-3-4.
 */
public class Test {
    public static void main(String []args) throws Exception {
        Properties properties = PropertySeting.getProperties(new DTO2Domain());
        String anjukeUrl = properties.getProperty("anjuke_house_personal_url");
        for(int i=1; i<= 1;i++){
            Spider.create(new CrawlPageProgress()).addUrl(anjukeUrl+String.valueOf(i)+"/").thread(3).run();
        }
    }
}
