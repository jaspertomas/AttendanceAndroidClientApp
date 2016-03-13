package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrettyDateTimeHelper {
	static SimpleDateFormat dateformat = new SimpleDateFormat("MMM d, yyyy hh:mm:ss a");
	static String emptydatestring=toString(DateHelper.toDate(""));
	public static String toString(Date date)
	{
		if(date==null)
			return emptydatestring;
		else
			return dateformat.format(date);
	}
	public static Date toDate(String datestring) throws ParseException
	{
		if(datestring==null||datestring.isEmpty())
			return dateformat.parse(emptydatestring);
		else
			return dateformat.parse(datestring);
	}
}
