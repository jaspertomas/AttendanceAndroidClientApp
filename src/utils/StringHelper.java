package utils;

public class StringHelper {
	public static Boolean isNumeric(String str)
	{
	  return str.matches("\\d+");  //custom - no negative or decimal point
//	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
	public static String toCamelCase(String s){
		s=s.replace(" ", "_");
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts){
			camelCaseString = camelCaseString + toProperCase(part);
		}
		return camelCaseString;
	}
	public static String toFirstCaps(String s){
		s=s.replace(" ", "_");
		String[] parts = s.split("_");
		String camelCaseString = "";
		for (String part : parts){
			camelCaseString = camelCaseString + toProperCase(part) + " ";
		}
		return camelCaseString.trim();
	}
	public static String toProperCase(String s) {
	    return s.substring(0, 1).toUpperCase() +
	               s.substring(1).toLowerCase();
	}
}
