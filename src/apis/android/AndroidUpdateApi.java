/*controller: android
name: update
description: 
return type: employees (Array)
method: GET
path: /android/update
Parameters:
access_token

*/
package apis.android;
import org.json.JSONException;
import org.json.JSONObject;

import utils.UpdateHelper;
import android.content.Context;
import android.widget.Toast;

import com.itforhumanity.attendance.CameraActivity;

public class AndroidUpdateApi extends BaseAndroidUpdateApi{
	public AndroidUpdateApi(Context context) {
		super(context);
	}

	String contextString="AndroidUpdateApi";
	public String getContextString(){return contextString;}

	public static void demo(Context context)
	{
		AndroidUpdateApi api = new AndroidUpdateApi(context);
		api.setAccessToken("lalalala+hey+jude");
		api.setMessageLoading("Updating...");
		api.execute();
	}
	@Override public JSONObject convertResponseToJson(String response) throws JSONException
	{
		JSONObject json = new JSONObject(response);
		return json;
	}
	@Override
	public void processResponse(Context context, JSONObject json) {
		try {
			Boolean success=json.getBoolean("success");
			final String error;
			if(json.has("error"))error=json.getString("error");
			else error="Something's wrong";
			
			if(success)
			{
				UpdateHelper.process(json);
				CameraActivity.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
				        Toast.makeText(CameraActivity.getInstance(), "Update Success", Toast.LENGTH_LONG).show();
				        CameraActivity.getInstance().load();
				    }
				});		
			}
			else
			{
				CameraActivity.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
				        Toast.makeText(CameraActivity.getInstance(), error, Toast.LENGTH_LONG).show();
				    }
				});		
			}
			
		} catch (JSONException e){
			e.printStackTrace();
		}
	}
}
