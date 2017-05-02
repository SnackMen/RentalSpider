package gregorianCalendar;

import java.util.GregorianCalendar;

/**
 * Created by laowang on 17-4-15.
 */
public class GregorianCalendarTime {
    public static boolean isMorningOrAfternoon(){
        //false : morning, true : afternoon
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.get(GregorianCalendar.AM_PM) == 1;
    }
}
