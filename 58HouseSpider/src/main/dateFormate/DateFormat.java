package dateFormate;


import java.sql.Date;

/**
 * Created by laowang on 17-4-15.
 */
public class DateFormat {
    public static Date format(java.util.Date date)  {

        return new  Date(date.getTime());
    }
}
