package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StandardDateHelper {
	static SimpleDateFormat dateformat = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
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
