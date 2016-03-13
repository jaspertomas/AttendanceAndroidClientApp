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
import java.io.UnsupportedEncodingException;

import models.Record;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.MyPhotoSaver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.itforhumanity.attendance.CameraActivity;

public class AndroidUploadApi extends BaseAndroidUploadApi{
	public AndroidUploadApi(Context context) {
		super(context);
	}

	String contextString="AndroidUploadApi";
	public String getContextString(){return contextString;}

	public static void demo(Context context)
	{
		AndroidUploadApi api = new AndroidUploadApi(context);
		api.setAccessToken("lalalala hey jude");//possible values:
		
		api.setMessageLoading("Uploading...");
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
				//delete successfully uploaded records
				JSONArray recordIds=json.getJSONArray("record_ids");
				String id;
				Record record;
				for(int i=0;i<recordIds.length();i++)
				{
					id=recordIds.getString(i);
					record=Record.selectOne(" where filename ='"+id+"' ");
					record.delete();
				}
				
				CameraActivity.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
				        Toast.makeText(CameraActivity.getInstance(), "Upload Success", Toast.LENGTH_SHORT).show();
				    }
				});		
			}
			else
			{
				CameraActivity.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
				        Toast.makeText(CameraActivity.getInstance(), error, Toast.LENGTH_SHORT).show();
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
			
			JSONArray array=new JSONArray();
			for(Record record:Record.select(""))
			{
		        String imagepath = MyPhotoSaver.getDir(context).getPath()+"/"+record.getFilename();
				record.getValues().put("image_encoded", imageFileToString(imagepath));
				array.put(record.getValues());
			}
			mainobj.put("records", array);
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
