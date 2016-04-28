package com.intelimina.pollwatcher;

import models.Setting;
import android.os.Environment;

public class Constants  {
	
//	public final static String SERVER_URL = "http://192.168.1.10:80/pollwatcher/web/index.php";
//	public final static String SERVER_URL = "http://10.0.2.2:80/pollwatcher/web/index.php";
	public final static String SERVER_URL = "http://pollwatcher.itforhumanity.com/web/index.php";
	
//	public final static String SERVER_URL = "http://192.168.1.17:80/tomas_accounting/web/index.php";
//	public final static String LOGIN_API_ENDPOINT_URL = Constants.SERVER_URL+"api/v1/courses?access_token=aeg3WeHgHuf93FHgNPad7jOfdZRkdC5yhNTYJAMD85bGKD6IjINSYZxRelRDQCAC";
//	public final static String LOGIN_API_ENDPOINT_URL = Constants.SERVER_URL+"api/v1/courses?access_token=dFw43tB2K69lWckYvOCfI8AOha2ptAm0qGWREAvL0MFUqHunc3rOGHn2TuyJ4xTR";
	public final static String accessToken1="asia";
	public final static String accessToken2="america";
	public final static String accessToken3="antarctica";
	
	public static final String finalDownloadFolder=Environment.getExternalStorageDirectory() + "/MapuaEnrollment/";
	public static final String pendingDownloadFolder=finalDownloadFolder + "pending/";
}