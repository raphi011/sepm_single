package sepm.ws14.e0828630.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateUtil {

    // from  java.sql.Date  to LocalDate:
    public static LocalDate dateToLocalDate(java.sql.Date d) {
        return LocalDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime dateToLocalTime(java.sql.Date d) {
        return LocalDateTime.ofInstant(d, ZoneId.systemDefault());
    }

    public static java.sql.Date localTimeToDate(LocalDateTime lt) {
        return new java.sql.Date(lt.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    public static java.sql.Date localDateToDate(LocalDate lt) {
        return new java.sql.Date(lt.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }
}
