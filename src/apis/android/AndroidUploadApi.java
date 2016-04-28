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
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import models.Record;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.FileHelper;
import utils.MyDialogHelper;
import utils.MyPhotoSaver;
import utils.NetworkHelper;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.intelimina.pollwatcher.CameraActivity;
import com.intelimina.pollwatcher.Constants;
import com.intelimina.pollwatcher.DashboardActivity;
import com.intelimina.pollwatcher.Registration4Activity;

public class AndroidUploadApi extends BaseAndroidUploadApi{
	public AndroidUploadApi(Context context) {
		super(context);
	}

	String contextString="AndroidUploadApi";
	public String getContextString(){return contextString;}

	public static void demo(Context context)
	{
		if(!NetworkHelper.isNetworkAvailable(context))
		{
			final DashboardActivity instance= DashboardActivity.getInstance();
			instance.runOnUiThread(new Runnable() {
			    public void run() {
			    	MyDialogHelper.popup(instance, "Please connect to the internet");
			    }
			});		
			return;
		}
		
		Integer count=Record.count("");
		if(count==0)
		{
			DashboardActivity.getInstance().runOnUiThread(new Runnable() {
			    public void run() {
			        Toast.makeText(DashboardActivity.getInstance(), "Upload Success", Toast.LENGTH_LONG).show();
			        DashboardActivity.getInstance().onUploadSuccess();
			    }
			});		
			return;
		}
		
		AndroidUploadApi api = new AndroidUploadApi(context);
		api.setAccessToken(Constants.accessToken1);//possible values:
		
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
					
					//move file to external folder
					Log.i("filename",MyPhotoSaver.getDir(context)+ File.separator +record.getFilename());
					File file=new File(MyPhotoSaver.getDir(context)+ File.separator +record.getFilename());
					if(file.exists())
					{
						//use this to move files to /DCIM/Camera
						String newfilename=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
								+File.separator
								+"Camera"
								+File.separator
								+record.getFilename();
						
						//use this to move files to /Pictures/Pollwatcher
//						String newfilename=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//								+File.separator
//								+"PollWatcher"
//								+File.separator
//								+record.getFilename();
//						Log.i("renameto",newfilename);
						File newfile=new File(newfilename);
						newfile.getParentFile().mkdirs();
//						file.renameTo(newfile);
						try {
							FileHelper.copy(file, newfile);
							file.delete();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//delete record
					record.delete();
				}
				
				final Context finalContext=context;
				DashboardActivity.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
				    	demo(finalContext);
				    }
				});		
			}
			else
			{
				DashboardActivity.getInstance().runOnUiThread(new Runnable() {
				    public void run() {
				        Toast.makeText(DashboardActivity.getInstance(), error, Toast.LENGTH_LONG).show();
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
			for(Record record:Record.select(" limit 5"))
			{
		        String imagepath = MyPhotoSaver.getDir(context).getPath()+"/"+record.getFilename();
		        File file=new File(imagepath);
		        if(file.exists())
		        	record.getValues().put("image_encoded", imageFileToString(imagepath));
		        else
		        	record.getValues().put("image_encoded","");
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
