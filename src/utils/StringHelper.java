package utils;

public class StringHelper {
	public static Boolean isNumeric(String str)
	{
	  return str.matches("\\d+");  //custom - no negative or decimal point
//	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}
