package com.example.attendance;

import holders.PictureHolder;

import java.io.File;
import java.util.Date;

import utils.DateTimeHelper;
import utils.MyBitmapHelper;
import utils.MyPhotoSaver;
import utils.ShowCamera;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class CameraActivity extends Activity {
	public static String DEBUG_TAG="CameraActivity";

	Button btnCapture,btnSave;
	FrameLayout preview;
	
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		context=CameraActivity.this;
		
		pic = (ImageView) findViewById(R.id.imageView1);
		preview = (FrameLayout) findViewById(R.id.camera_preview);
		btnCapture = (Button) findViewById(R.id.button_capture);
		btnSave = (Button) findViewById(R.id.button_save);
		
		btnSave.setEnabled(false);
		PictureHolder.setDatetimestring("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}

	private Camera cameraObject;
	private ShowCamera showCamera;
	private ImageView pic;

	//this returns back camera if both cameras are available
//	public static Camera isCameraAvailiable() {
//		Camera object = null;
//		try {
//			object = Camera.open();
//		} catch (Exception e) {
//			Log.e("CameraActivity","Camera is not available");
//		}
//		return object;
//	}

	//this returns front camera
	private Camera isCameraAvailiable() {
	    int cameraCount = 0;
	    Camera cam = null;
	    Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
	    cameraCount = Camera.getNumberOfCameras();
	    for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
	        Camera.getCameraInfo(camIdx, cameraInfo);
	        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
	            try {
	                cam = Camera.open(camIdx);
	            } catch (RuntimeException e) {
	                Log.e(DEBUG_TAG, "Camera failed to open: " + e.getLocalizedMessage());
	            }
	        }
	    }

	    return cam;
	}	
	
	private PictureCallback capturedIt = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			
			Date date=new Date();
			PictureHolder.setDatetimestring(DateTimeHelper.toString(date));
			bitmap=MyBitmapHelper.drawTextToBitmap(CameraActivity.this, bitmap, PictureHolder.getDatetimestring());
			if (bitmap == null) {
				Toast.makeText(getApplicationContext(), "An error has occured. Picture not taken.",Toast.LENGTH_SHORT).show();
			} else {
				data=MyBitmapHelper.getByteArrayFromBitmap(bitmap);

				//get picture directory
			    File pictureFileDir = MyPhotoSaver.getDir(CameraActivity.this);

			    //if can't get picture directory, do nothing
			    if (!pictureFileDir.exists() && !pictureFileDir.mkdirs()) {

			      Log.d(DEBUG_TAG, "Can't create directory to save image.");
			      Toast.makeText(CameraActivity.this, "Can't create directory to save image.",
			          Toast.LENGTH_LONG).show();
			    }
			    else
			    {
					Boolean saved=MyPhotoSaver.save(data,pictureFileDir,CameraActivity.this);
					if(saved)
					{
//					      Toast.makeText(CameraActivity.this, "Image saved", Toast.LENGTH_LONG).show();
							pic.setImageBitmap(bitmap);
					}else{
					      Toast.makeText(CameraActivity.this, "Image could not be saved.", Toast.LENGTH_LONG).show();
					}
			    }
				
			}
			//reset preview window
			preview.removeAllViews();
			preview.addView(showCamera);
			
			//enable save button
			btnSave.setEnabled(true);
		}
	};

	public void capture(View view) {
		if(cameraObject!=null)
		{
			cameraObject.takePicture(null, null, capturedIt);
		}
		else
		{
			Toast.makeText(getApplicationContext(), "No camera available",Toast.LENGTH_SHORT).show();
		}
	}
	public void save(View view) {
		finish();
	}

//	@Override
//	protected void onDestroy() {
//		cameraObject.release();
//		super.onDestroy();
//	}
	@Override
	protected void onStart() {
		super.onStart();
		cameraObject = isCameraAvailiable();
		if(cameraObject==null)
		{
			btnCapture.setEnabled(false);
			btnSave.setEnabled(false);
			
			//show popup saying camera not available
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setTitle("Sorry, camera not available.");
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
			    @Override
			    public void onClick(DialogInterface dialog, int which) {
			    	finish();
			    }
			});
			builder.setCancelable(false);
			builder.show();		
		}
		else
		{
			showCamera = new ShowCamera(this, cameraObject);
			preview.addView(showCamera);
			btnCapture.setEnabled(true);
			btnSave.setEnabled(false);
		}
	}
	@Override
	protected void onStop() {
		if(cameraObject!=null)
			cameraObject.release();
		super.onStop();
	}
}
