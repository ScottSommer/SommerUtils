package au.com.sommer.common.util;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class DateUtils {
	
	private static SimpleDateFormat timeFormat;

	static {
		timeFormat = new SimpleDateFormat("HH:mm");
	}
	
	
	public static Long getTimeOffset(String time) {
				
		StringTokenizer str = new StringTokenizer(time,"-");
		
		if (str.countTokens() !=6) {
			throw new InvalidParameterException("Value is not a complete date offset expecting yyyy-mm-dd-hh-mm-ss");
		}

		Long year = new Long(String.valueOf(str.nextElement()));
		Long month = new Long(String.valueOf(str.nextElement())); 
		Long day = new Long(String.valueOf(str.nextElement()));
		Long hour = new Long(String.valueOf(str.nextElement()));
		Long minute = new Long(String.valueOf(str.nextElement()));
		Long second = new Long(String.valueOf(str.nextElement()));
				
		return getTimeOffset(year, month, day, hour, minute, second);		
	}
	
	
	public static Long getTimeOffset(Long year, Long month, Long day, Long hour, Long minute, Long second) {
		
		Long offset;

		Calendar offsetCalendar = Calendar.getInstance();
		offsetCalendar.setTimeInMillis(0);

		offsetCalendar.add(Calendar.SECOND,second.intValue());
		offsetCalendar.add(Calendar.MINUTE,minute.intValue());
		offsetCalendar.add(Calendar.HOUR,hour.intValue());
		offsetCalendar.add(Calendar.DAY_OF_YEAR,day.intValue());
		offsetCalendar.add(Calendar.MONTH,month.intValue());
		offsetCalendar.add(Calendar.YEAR,year.intValue());
				
		offset = offsetCalendar.getTimeInMillis();
		
		return offset;		
	}
	
	
	
	public static String format24HrTime(Date time) {
		return timeFormat.format(time);
	}

	
	public static Date replaceTime(Date date,String time) {
		
		Date result = null;
		Calendar calendar = Calendar.getInstance();
		
		try {
			result = timeFormat.parse(time);
			calendar.setTime(result);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			int minute = calendar.get(Calendar.MINUTE);
			int second = calendar.get(Calendar.SECOND);
			
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY,hour);
			calendar.set(Calendar.MINUTE,minute);
			calendar.set(Calendar.SECOND,second);
			result = calendar.getTime();
			
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Get the current dates Timestamp
	 * @return current dates Timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(new java.util.Date().getTime());
	}
	
	
	/**
	 * Get current date with time component cleared.
	 * @return current date
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.clear(Calendar.MILLISECOND);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.HOUR);		
		calendar.clear(Calendar.AM_PM);		
		return calendar.getTime();
	}

	
	/**
	 * Get current date time with millisecond component cleared.
	 * @return current date / time
	 */
	public static Date getCurrentDateTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.clear(Calendar.MILLISECOND);
		return calendar.getTime();
	}
	
	
	/**
	 * Get a calendar with a date at the start of the week for the supplied calendar.
	 * @param calendar to find start of week for.
	 * @return calendar set to start of the week.
	 */
	public static Calendar getStartOfWeek(Calendar calendar) {
		
		Calendar result = Calendar.getInstance();		
		result.setTime(calendar.getTime());
				
		int startDay = calendar.getActualMinimum(Calendar.DAY_OF_WEEK);		
		result.set(Calendar.DAY_OF_WEEK,startDay);
		
		return result;
	}

	
	/**
	 * Get a calendar with a date at the end of the week for the supplied calendar.
	 * @param calendar to find end of week for.
	 * @return calendar set to end of the week.
	 */
	public static Calendar getEndOfWeek(Calendar calendar) {
		
		Calendar result = Calendar.getInstance();		
		result.setTime(calendar.getTime());
				
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_WEEK);		
		result.set(Calendar.DAY_OF_WEEK,endDay);
		
		return result;
	}
	
	
	/**
	 * Get a calendar with a date at the start of the month for the supplied calendar.
	 * @param calendar to find start of month for.
	 * @return calendar set to start of the month.
	 */	
	public static Calendar getStartOfMonth(Calendar calendar) {
		
		Calendar result = (Calendar)calendar.clone();		
		result.setTime(calendar.getTime());
		
		int startDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		result.set(Calendar.DAY_OF_MONTH,startDay);

		return result;
	}

	
	/**
	 * Get a calendar with a date at the end of the month for the supplied calendar.
	 * @param calendar to find end of month for.
	 * @return calendar set to end of the month.
	 */	
	public static Calendar getEndOfMonth(Calendar calendar) {
		
		Calendar result = (Calendar)calendar.clone();
		result.setTime(calendar.getTime());
						
		int endDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		result.set(Calendar.DATE,endDay);
		
		return result;
	}
	
	
	/**
	 * Check if the calendar and date are in the same month.
	 * @param calendar
	 * @param date
	 * @return true month is the same; false otherwise.
	 */
	public static boolean isThisMonth(Calendar calendar, Date date) {
		
		boolean result = false;
		
		Calendar checkCalendar = Calendar.getInstance();
		checkCalendar.setTime(date);
		
		if (calendar.get(Calendar.YEAR) == checkCalendar.get(Calendar.YEAR) && 
			calendar.get(Calendar.MONTH) == checkCalendar.get(Calendar.MONTH)) {
			result = true;
		}
		
		return result;
	}
	
	
	/**
	 * Check if the calendar and date are in the same year.
	 * @param calendar
	 * @param date
	 * @return true year is the same; false otherwise.
	 */
	public static boolean isThisYear(Calendar calendar, Date date) {
		
		boolean result = false;
		
		Calendar checkCalendar = Calendar.getInstance();
		checkCalendar.setTime(date);
		
		if (calendar.get(Calendar.YEAR) == checkCalendar.get(Calendar.YEAR)) {
			result = true;
		}
		return result;
	}
	
	public static Date getCalendarStartWeek(Date date) {
		
		Date result = null;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, 0);

		int dow = c.get(Calendar.DAY_OF_WEEK) - 1;
		dow--;
		if (dow < 0) {
			dow = 6;
		}
		c.add(Calendar.DATE, -dow);

		result = c.getTime();
		
		return result;
	}

	public static Date getCalendarEndWeek(Date date) {
		
		Date result = null;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DATE, c.getActualMaximum(Calendar.DAY_OF_MONTH));

		int dow = c.get(Calendar.DAY_OF_WEEK) - 1;
		dow--;
		if (dow < 0) {
			dow = 6;
		}
		c.add(Calendar.DATE, 6-dow);

		System.out.println(dow + ":" + c.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		result = c.getTime();
		
		return result;
	}	

	
	
	
	public static boolean inDateRange(Date startDate, Date endDate, Date checkDate, boolean inclusive) {
		
		boolean result = false;
		
		startDate = clearTime(startDate);
		endDate = clearTime(endDate);
		checkDate = clearTime(checkDate);

		result = inDateTimeRange(startDate, endDate, checkDate,inclusive);
		
		return result;
	}

	
	public static boolean outOfDateRange(Date startDate, Date endDate, Date checkDate, boolean inclusive) {
		boolean result = !inDateRange(startDate ,endDate, checkDate,inclusive);
		return result;
	}

	
	public static boolean inDateTimeRange(Date startDate, Date endDate, Date checkDate, boolean inclusive) {
		
		boolean result = false;
		
		if (inclusive) {
			if ((checkDate.after(startDate) || checkDate.equals(checkDate)) && (checkDate.before(endDate) || checkDate.equals(checkDate))) {
				result = true;
			}
		} else {
			if (checkDate.after(startDate) && checkDate.before(endDate)) {
				result = true;
			}
		}
		return result;
	}
	
	
	public static boolean outOfDateTimeRange(Date startDate, Date endDate, Date checkDate, boolean inclusive) {
		boolean result = !inDateTimeRange(startDate ,endDate, checkDate,inclusive);
		return result;
	}

	

	public static boolean dateGreaterThan(Date endDate, Date checkDate, boolean inclusive) {

		boolean result = false;

		endDate = clearTime(endDate);
		checkDate = clearTime(checkDate);
		result = dateTimeGreaterThan(endDate, checkDate, inclusive);
		
		return result;
	}


	public static boolean dateLessThan(Date startDate ,Date checkDate, boolean inclusive) {

		boolean result = false;

		startDate = clearTime(startDate);
		checkDate = clearTime(checkDate);
		result = dateTimeLessThan(startDate, checkDate, inclusive);
		
		return result;
	}

	
	public static boolean dateTimeGreaterThan(Date startDate, Date checkDate, boolean inclusive) {

		boolean result = false;
		
		if (inclusive) {
			if ((checkDate.after(startDate) || checkDate.equals(checkDate))) {
				result = true;
			}
		} else {
			if (checkDate.after(startDate)) {
				result = true;
			}
		}
		return result;
	}
	

	public static boolean dateTimeLessThan(Date endDate, Date checkDate, boolean inclusive) {

		boolean result = false;
		
		if (inclusive) {
			if ((checkDate.before(endDate) || checkDate.equals(checkDate))) {
				result = true;
			}
		} else {
			if (checkDate.before(endDate)) {
				result = true;
			}
		}
		return result;
	}
	
	
	public static boolean lessThan(Date startDate, Date checkDate, boolean inclusive) {
		boolean result = false;
		return result;
	}

	
	public static boolean notEqualDate(Date startDate, Date checkDate) {
		boolean result = false;
		
		startDate = clearTime(startDate);
		checkDate = clearTime(checkDate);
		
		result = !clearTime(startDate).equals(clearTime(checkDate));
		return result;
	}
	
	
	public static boolean equalDate(Date startDate, Date checkDate) {
		boolean result = false;		
		
		startDate = clearTime(startDate);
		checkDate = clearTime(checkDate);
		
		result = equalDateTime(startDate,checkDate);
		return result;
	}

	
	public static boolean notEqualDateTime(Date startDate, Date checkDate) {
		boolean result = false;
		result = !equalDateTime(startDate,checkDate);
		return result;
	}
	
	
	public static boolean equalDateTime(Date startDate, Date checkDate) {
		boolean result = false;
		result = startDate.equals(checkDate);
		return result;
	}
	

	public static Date clearTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.clear(Calendar.MILLISECOND);
		calendar.clear(Calendar.SECOND);
		calendar.clear(Calendar.MINUTE);
		calendar.clear(Calendar.AM_PM);		
		calendar.clear(Calendar.HOUR_OF_DAY);		
		calendar.clear(Calendar.HOUR);		
		return calendar.getTime();
	}

	
}
