package rekkisoft.trongvu.com.note.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {

    public static String getTimeByPattern(String pattern) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
        String time = sdf.format(c.getTime());
        return time;
    }
}
