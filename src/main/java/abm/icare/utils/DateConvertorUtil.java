package abm.icare.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateConvertorUtil {

	public static final String PATTERN_DD_MM_YYYY = "dd-MM-yyyy";

	public static Date toDate(String dateIn, String pattern)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateIn);
	}

}
