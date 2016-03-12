package utils;

public class MyMathHelper {
	public static String toOrdinal(Integer i)
	{
		Integer j=i%10;
		switch(j)
		{
			case 1:return i.toString()+"st";
			case 2:return i.toString()+"nd";
			case 3:return i.toString()+"rd";
			default:return i.toString()+"th";
		}
	}
}
