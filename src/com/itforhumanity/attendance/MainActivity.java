package com.itforhumanity.attendance;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	Context context;
	private ImageView imageView;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=MainActivity.this;
		
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
//	@Override
//	protected void onStart() {
//		super.onStart();
//		
//		//save picture file name to Update object if new picture exists
//		savePicture();
//	}
}
