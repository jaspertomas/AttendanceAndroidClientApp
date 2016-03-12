package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrettyDateHelper {
	static SimpleDateFormat dateformat = new SimpleDateFormat("MMM dd, yyyy");
	public static String toString(Date date)
	{
		return dateformat.format(date);
	}
	public static Date toDate(String datestring)
	{
		try {
			return dateformat.parse(datestring);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
