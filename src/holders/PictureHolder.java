package holders;

public class PictureHolder {
//	static String adPicFilename="";
//	static String odoPicFilename="";
//	public static String getAdPicFilename() {
//		return adPicFilename;
//	}
//	public static void setAdPicFilename(String adPicFilename) {
//		PictureHolder.adPicFilename = adPicFilename;
//	}
//	public static String getOdoPicFilename() {
//		return odoPicFilename;
//	}
//	public static void setOdoPicFilename(String odoPicFilename) {
//		PictureHolder.odoPicFilename = odoPicFilename;
//	}
	public static void reset()
	{
//		adPicFilename="";
//		odoPicFilename="";
		datetimestring="";
		action=0;
	}
	
	static String datetimestring="";
	public static String getDatetimestring() {
		return datetimestring;
	}
	public static void setDatetimestring(String datetimestring) {
		PictureHolder.datetimestring = datetimestring;
	}
	
	public static final Integer ACTION_ADPIC=1;
	public static final Integer ACTION_ODOPIC=2;
	static Integer action=0;
	public static Integer getAction() {
		return action;
	}
	public static void setAction(Integer action) {
		PictureHolder.action = action;
	}

}
