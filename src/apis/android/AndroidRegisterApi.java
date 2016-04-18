/*controller: android
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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import models.Record;
import models.User;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.MyPhotoSaver;
import utils.UrlHelper;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.Toast;

import com.intelimina.pollwatcher.Constants;
import com.intelimina.pollwatcher.Registration4Activity;

public class AndroidRegisterApi extends BaseAndroidRegisterApi{
	
	private static User user;
	
	public AndroidRegisterApi(Context context) {
		super(context);
	}

	String contextString="AndroidRegisterApi";
	public String getContextString(){return contextString;}

	public static void demo(Context context,User user)
	{
		AndroidRegisterApi.user=user;
		AndroidRegisterApi api = new AndroidRegisterApi(context);
		api.setAccessToken(Constants.accessToken1);//possible values:
		
		api.setMessageLoading("Connecting to Server...");
		api.execute();
	}
	@Override public JSONObject convertResponseToJson(String response) throws JSONException
	{
//		Log.i("response",response);
		return new JSONObject(response);
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
				//save user
				user.setSfGuardUserId(json.getInt("sf_guard_user_id"));
				user.setPassword(json.getString("password"));
				user.setSalt(json.getString("salt"));
				user.setIsReg(1);
				user.save();
				
				Registration4Activity.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
						Registration4Activity.getInstance().onSubmitSuccess();
				    }
				});		
			}
			else
			{
				Registration4Activity.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
						Registration4Activity.getInstance().onSubmitFailure(error);
				    }
				});		
			}
			
		} catch (JSONException e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void formulateData(Context context, JSONObject json, HttpPost post) {
		JSONObject mainobj=new JSONObject();
		try {
			if(accessToken!=null)mainobj.put("access_token", accessToken.toString());
			
			mainobj.put("user", user.getValues());
//			Log.i("request",mainobj.toString());
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
		String url=Constants.SERVER_URL+"/android/register";
		return UrlHelper.escapeUrl(url);
	}
	
	
	
//---------utilities---------------------------------------------------

	
	public String imageFileToString(String imagepath)
	{
        BitmapFactory.Options options0 = new BitmapFactory.Options();
        options0.inSampleSize = 2;
        // options.inJustDecodeBounds = true;
        options0.inScaled = false;
        options0.inDither = false;
        options0.inPreferredConfig = Bitmap.Config.ARGB_8888;

        Bitmap bmp = BitmapFactory.decodeFile(imagepath);

        ByteArrayOutputStream baos0 = new ByteArrayOutputStream();

        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos0);
        byte[] imageBytes0 = baos0.toByteArray();

        return Base64.encodeToString(imageBytes0, Base64.DEFAULT);	
        }

}
