package abm.icare.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {

	public static Date getDateLater(int noOfDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, noOfDays);
		return calendar.getTime();
	}

	public static String getStringDateLater(int noOfDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, noOfDays);
		SimpleDateFormat sdf = new SimpleDateFormat(
				DateConvertorUtil.PATTERN_DD_MMM_YYYY);
		return sdf.format(calendar.getTime());
	}

}
