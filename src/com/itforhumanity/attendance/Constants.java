package com.itforhumanity.attendance;

import models.Setting;
import android.os.Environment;

public class Constants  {
	
	static String serverUrl=null;
	public static void initServerUrl() {
	    Setting serverIp=Setting.getByName("server_ip");
	    if(serverIp!=null)
	    	serverUrl=serverIp.getValue();
	}
	public static String getServerUrl() {
		return serverUrl;
	}
	public static void setServerUrl(String server_url) {
		Constants.serverUrl = server_url;
	}
	
	//	public final static String SERVER_URL = "http://10.0.2.2:80/tomas_accounting/web/index.php";
//	public final static String SERVER_URL = "http://192.168.1.17:80/tomas_accounting/web/index.php";
//	public final static String LOGIN_API_ENDPOINT_URL = Constants.SERVER_URL+"api/v1/courses?access_token=aeg3WeHgHuf93FHgNPad7jOfdZRkdC5yhNTYJAMD85bGKD6IjINSYZxRelRDQCAC";
//	public final static String LOGIN_API_ENDPOINT_URL = Constants.SERVER_URL+"api/v1/courses?access_token=dFw43tB2K69lWckYvOCfI8AOha2ptAm0qGWREAvL0MFUqHunc3rOGHn2TuyJ4xTR";
	public final static String accessToken="tomas_accounting";
	public final static String accessToken2="its";
	
//	public final static Integer MAX_IDLE_TIME_SECONDS = 1800;	
//	public static String DOWNLOADPATH="unilab_edetailing";

//	public static final String ACTION_LOGIN = "com.intelimina.biofemme.action.LOGIN";
//	public static final String ACTION_LOGOUT = "com.intelimina.biofemme.action.LOGOUT";
//	public static final String EXTRA_LOGIN_EMPLOYEE_ID = "com.intelimina.biofemme.EXTRA_LOGIN_EMPLOYEE_ID";
//	public static final String EXTRA_LOGIN_DOCTOR_ID = "com.intelimina.biofemme.EXTRA_LOGIN_DOCTOR_ID";
	
	public static final String finalDownloadFolder=Environment.getExternalStorageDirectory() + "/MapuaEnrollment/";
	public static final String pendingDownloadFolder=finalDownloadFolder + "pending/";

//	public static final String SHARED_FILE_EXTERNAL_FOLDER="/crs_middleware";
//	public static final String SHARED_FILE_EXTERNAL_FILENAME="com.intelimina.biomedis.json";

//	public static final String CLIENT_ID="10000000000001";
//	public static final String CLIENT_SECRET="8VdHIdpd95cTyR6nz84dAoVloR5A9FE7Zg7V5M8cIw7ovfJUcd5wvAmQKT2MWNNk";
	
//	public final static String COURSE_API_LISTUSERS_URL = Constants.SERVER_URL+"/api/v1/courses/:course_id/users";
//	public final static String COURSE_API_LISTUSERS_URL_2 = Constants.SERVER_URL+"/api/v1/courses/:course_id/search_users";
}