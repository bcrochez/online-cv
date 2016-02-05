
package fr.upem.onlinecv.bean;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 *
 * @author Bastien
 */
public class DateBean {

    /**
     * Creates a new instance of DateBean
     */
    public DateBean() {
    }
    
    public String now() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }
}
