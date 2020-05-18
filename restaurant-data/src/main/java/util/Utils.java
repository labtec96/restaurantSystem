package util;

import lombok.experimental.UtilityClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by ch on 2020-05-17
 */
@UtilityClass
public class Utils {
    public final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterHour = DateTimeFormatter.ofPattern("H:mm");

    public static LocalDate parseDate(String date){
        return LocalDate.parse(date, formatter);
    }

    public static LocalTime parseHour(String hour){
        return LocalTime.parse(hour, formatterHour);
    }

    public static boolean isValidDate(String date, String hour) {

        String fullDate = date + " " + hour;
        dateFormat.setLenient(false);
        try {
            //System.out.println(fullDate);
            dateFormat.parse(fullDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
