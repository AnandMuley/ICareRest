package abm.icare.utils;

import java.util.Calendar;
import java.util.Date;

public abstract class DateUtils {

	public static Date getDateLater(int noOfDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, noOfDays);
		return calendar.getTime();
	}

}
