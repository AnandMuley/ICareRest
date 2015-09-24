package abm.icare.utils;

import java.text.ParseException;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DateConvertorUtilTest extends DateConvertorUtil {

	public DateConvertorUtilTest() {
		super();
	}

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

	@Test
	public void shoulCheckForAvailableDatePatterns() {
		Assert.assertEquals(DateConvertorUtil.PATTERN_DD_MM_YYYY, "dd-MM-yyyy");
		Assert.assertEquals(DateConvertorUtil.PATTERN_DD_MMM_YYYY,
				"dd-MMM-yyyy");

	}

	@Test(expectedExceptions = ParseException.class)
	public void shouldThrowParseException() throws ParseException {
		// GIVEN
		final String givenDate = "20-10-2015";

		// WHEN
		DateConvertorUtil.toDate(givenDate,
				DateConvertorUtil.PATTERN_DD_MMM_YYYY);
	}

}
