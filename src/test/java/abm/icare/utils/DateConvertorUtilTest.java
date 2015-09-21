package abm.icare.utils;

import java.text.ParseException;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DateConvertorUtilTest {

	@Test
	public void shouldConvertStringToDateObj() throws ParseException {
		// GIVEN
		final String givenDate = "20-Sep-2015";

		// WHEN
		Date convertedDate = DateConvertorUtil.toDate(givenDate,
				DateConvertorUtil.PATTERN_DD_MMM_YYYY);

		// THEN
		// Convert this date back to string
		String convertedStrDate = DateConvertorUtil.toString(convertedDate,
				DateConvertorUtil.PATTERN_DD_MMM_YYYY);

		Assert.assertEquals(convertedStrDate, givenDate);
	}

}
