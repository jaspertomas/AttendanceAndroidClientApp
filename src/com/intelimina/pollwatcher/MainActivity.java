package com.intelimina.pollwatcher;

import holders.NavigationHolder;
import holders.LGUHolder;
import holders.UserHolder;
import models.Lgus;
import models.User;
import utils.DateHelper;
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
	@Override
	public void onBackPressed()
	{
		nav();
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
		nav();
	}
	private void nav()
	{
		Log.i("NavigationHelper Destination",String.valueOf(NavigationHolder.getDestination()));
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
						//setup temporary user record
						LGUHolder.reset();
						
						User user=new User();
						user.setUsername("");
						user.setPassword("");
						user.setFname("");
						user.setMi("");
						user.setLname("");
						user.setBday(DateHelper.getNullDate2000());
						user.setEmail("");
						user.setPhone("");
						LGUHolder.setCity(Lgus.getByName(" Other / Not Applicable"));
						user.setCityId(LGUHolder.getCity().getId());
						LGUHolder.setProvince(Lgus.getByName(" Other / Not Applicable"));
						user.setProvinceId(LGUHolder.getProvince().getId());
						user.setAddress("");
						user.setIsReg(0);
						UserHolder.setRegUser(user);
						
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
				//else go to dashboard activity
				else
				{
					Intent intent = new Intent(context, DashboardActivity.class);
					startActivity(intent);
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
