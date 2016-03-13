/*usage:
AndroidUpdateApi api = new AndroidUpdateApi(context);
api.setAccessToken("");//possible values:
api.setMessageLoading("message here...");
api.execute();

controller: android
name: update
description: 
return type: employees (Array)
method: GET
path: /android/update
Parameters:
access_token

*/
package apis.android;
import java.io.IOException;
import java.util.ArrayList;

import models.Employee;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import apis.BaseApi;

import com.itforhumanity.attendance.Constants;

import converters.EmployeeFromJsonConverter;

public class BaseAndroidUpdateApi extends BaseApi{
	String contextString="BaseAndroidUpdateApi";
	public String getContextString(){return contextString;}
	String accessToken;//options: []
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public BaseAndroidUpdateApi(Context context) {
		super(context);
	}

	@Override
	public void formulateData(Context context, JSONObject json, HttpPost post) {
		//this does not use the Post method
	}
	@Override public String getUrl() {
            String url="/android/update";

            ArrayList<String> paramslist=new ArrayList<String>();
            String params="";

            if(accessToken!=null)paramslist.add("access_token="+accessToken.toString());
            for(int i=0;i<paramslist.size();i++)
            {
                if(i==0)params+="?";else params+="&";
                params+=paramslist.get(i);
            }
            Log.i("url",Constants.getServerUrl()+url+params);
            return Constants.getServerUrl()+url+params;
	}
	@Override public Integer getMethod() {
		return BaseApi.METHOD_GET;
	}
	@Override
	public void processResponse(Context context, JSONObject json) {
		try {
			JSONArray array=json.getJSONArray("array");
			ArrayList<Employee> list=EmployeeFromJsonConverter.convertList(array);
			for(Employee item:list)
			{
				item.save();
			}
		} catch (JSONException e){
			e.printStackTrace();
		}
	}
	@Override public void catchHttpResponseException(HttpResponseException e, JSONObject json) throws JSONException
	{
		e.printStackTrace();
		Log.e(contextString, "HttpResponseException: " + e.getMessage());
	}
	@Override public void catchIOException(IOException e, JSONObject json) throws JSONException
	{
		e.printStackTrace();
		Log.e(contextString, "IOException: " + e.getMessage());
	}
	@Override public void catchJSONException(JSONException e, JSONObject json)
	{
		e.printStackTrace();
		Log.e(contextString, "JSONException: " + e.getMessage());
	}
	@Override public JSONObject convertResponseToJson(String response) throws JSONException
	{
		JSONObject json = new JSONObject();
		JSONArray jsonArray=new JSONArray(response);
		json.put("array", jsonArray);
		return json;
	}
	@Override
	public void onProcessResponseSuccess(Context context) {
//		Intent intent = new Intent(context, Activity.class);
//		context.startActivity(intent);			
	}
	@Override
	public void onProcessResponseError(Context context) {
	}
}