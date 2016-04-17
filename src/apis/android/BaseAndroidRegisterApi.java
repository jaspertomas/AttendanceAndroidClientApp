/*usage:
AndroidUploadApi api = new AndroidUploadApi(context);
api.setAccessToken("");//possible values:
api.setRecords("");//possible values:
api.setMessageLoading("message here...");
api.execute();

controller: android
name: upload
description: 
return type:  (None)
method: POST
path: /android/upload
Parameters:
access_token
records

*/
package apis.android;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import utils.UrlHelper;
import android.content.Context;
import android.util.Log;
import apis.BaseApi;

import com.intelimina.pollwatcher.Constants;

public class BaseAndroidRegisterApi extends BaseApi{
	String contextString="BaseAndroidUploadApi";
	public String getContextString(){return contextString;}
	String accessToken;//options: []
	String records;//options: []
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public void setRecords(String records) {
		this.records = records;
	}

	public BaseAndroidRegisterApi(Context context) {
		super(context);
	}

	@Override
	public void formulateData(Context context, JSONObject json, HttpPost post) {
		JSONObject mainobj=new JSONObject();
		try {
			if(accessToken!=null)mainobj.put("access_token", accessToken.toString());
			if(records!=null)mainobj.put("records", records.toString());
			StringEntity se = new StringEntity(mainobj.toString());
			post.setEntity(se);
			
     // setup the request headers
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-Type", "application/json");
     
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override public String getUrl() {
		String url=Constants.SERVER_URL+"/android/upload";
		return UrlHelper.escapeUrl(url);	}
	@Override public Integer getMethod() {
		return BaseApi.METHOD_POST;
	}
	@Override
	public void processResponse(Context context, JSONObject json) {
		onProcessResponseSuccess(context);
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
		return new JSONObject();
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