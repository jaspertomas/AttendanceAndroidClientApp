package com.intelimina.pollwatcher;

import holders.NavigationHolder;
import holders.UserHolder;
import models.User;
import utils.MyInitializer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	Context context;
	private ImageView imageView;	
	
	static MainActivity instance;
	public static MainActivity getInstance() {
		return instance;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=MainActivity.this;
		instance=MainActivity.this;
		
		MyInitializer.initialize(context);

		setupView();
	}
	private void setupView()
	{
		
		imageView = (ImageView) findViewById(R.id.imageView);
	}
	public void takePicture(View button)
	{
		Intent intent = new Intent(context, CameraActivity.class);
		startActivity(intent);
	}
//	public void savePicture()
//	{
//		//if action is neither adpic nor odopic, do nothing
//		//if(PictureHolder.getAction()==0)return;
//		
//		//if datetimestring is not set, then no picture taken
//		//do nothing
//		String datetimestring=PictureHolder.getDatetimestring();
//		if(datetimestring.isEmpty())return;
//		
//		String filename=datetimestring.replace(" ", "-").replace(":", "-")+".jpg";
//
//	    File pictureFileDir = MyPhotoSaver.getDir(context);
//	    File picturefile=new File(pictureFileDir,"temp.jpg");
//	    File newpicturefile=new File(pictureFileDir,filename);
//	    if(picturefile.exists())
//	    {
//	    	picturefile.renameTo(newpicturefile);
//	    	
//			//Update update=VehicleHolder.getUpdate();
//	    	
//    		//if there is a previous picture, delete it
////    	    File oldpicturefile=new File(pictureFileDir,update.getAdPicFilename());
////    	    if(oldpicturefile.exists())oldpicturefile.delete();
////    		
////    		//save filename to update
////    		update.setAdPicFilename(filename);
////    		update.save();
//
//    		//show image
//	        imageView.setImageBitmap(BitmapFactory.decodeFile(newpicturefile.getAbsolutePath()));
//	    }
//	}
//	
	@Override
	protected void onStart() {
		super.onStart();
		
		switch(NavigationHolder.getDestination())
		{
			case NavigationHolder.ShutDown:
			{
				NavigationHolder.reset();finish();break;
			}
			case NavigationHolder.RegistrationActivity:
			{
				Intent intent = new Intent(context, RegistrationActivity.class);startActivity(intent);break;
			}
			case NavigationHolder.Registration2Activity:
			{
				Intent intent = new Intent(context, Registration2Activity.class);startActivity(intent);break;
			}
			case NavigationHolder.Registration3Activity:
			{
				Intent intent = new Intent(context, Registration3Activity.class);startActivity(intent);break;
			}
			case NavigationHolder.Registration4Activity:
			{
				Intent intent = new Intent(context, Registration4Activity.class);startActivity(intent);break;
			}
			case NavigationHolder.LoginActivity:
			{
				Intent intent = new Intent(context, LoginActivity.class);startActivity(intent);break;
			}
			//no navigation directive
			//normal operation
			default:
			{
				//if user is set, that means newly registered or logged in
				//if not, that means logged out or not yet registered
				if(UserHolder.getUser()==null)
				{
					Integer usercount=User.count(" where is_reg=1");
					//if no registered users in database, register
					if(usercount==0)
					{
						Intent intent = new Intent(context, RegistrationActivity.class);
						startActivity(intent);
					}
					//else log in
					else
					{
						Intent intent = new Intent(context, LoginActivity.class);
						startActivity(intent);
					}
				}
			}
		}
	}
	
//	@Override
//	protected void onResume() {
//		super.onResume();
//		
//	}
//	@Override
//	protected void onPause() {
//		super.onPause();
//	}
}
