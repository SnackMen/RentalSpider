package dateFormate;

import com.ws.log.CrawLog;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by laowang on 17-4-15.
 */
public class DateFormat {
    public static Date format(java.util.Date date)  {

        return new  Date(date.getTime());
    }
}
