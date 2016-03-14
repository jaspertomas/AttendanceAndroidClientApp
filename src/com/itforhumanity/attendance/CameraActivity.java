package com.itforhumanity.attendance;

import holders.PictureDataHolder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Employee;
import models.Record;
import utils.DateTimeHelper;
import utils.MyBitmapHelper;
import utils.MyInitializer;
import utils.MyPhotoSaver;
import utils.ShowCamera;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import apis.android.AndroidUpdateApi;
import apis.android.AndroidUploadApi;

public class CameraActivity extends Activity {
	static CameraActivity instance;
	public static CameraActivity getInstance() {
		return instance;
	}
	public static String DEBUG_TAG="CameraActivity";

//	Button btnCapture,btnSave;
	FrameLayout preview;
	
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);
		context=CameraActivity.this;
		instance=CameraActivity.this;

		MyInitializer.initialize(context);
		
		setupView();
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.action_settings:
		{
			Intent intent = new Intent(CameraActivity.this, ServerSettingActivity.class);
			startActivity(intent);
			return true;
		}
		case R.id.action_update:
		{
//			Intent intent = new Intent(CameraActivity.this, ScheduleMapActivity.class);
//			startActivity(intent);
//			releaseCamera();
			AndroidUpdateApi.demo(context);
			return true;
		}
		case R.id.action_upload:
		{
			Integer count=Record.count("");
			if(count==0)
				Toast.makeText(CameraActivity.this, "No records to upload",Toast.LENGTH_LONG).show();
		    else
				AndroidUploadApi.demo(context);
			return true;
		}
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private Camera cameraObject;
	private ShowCamera showCamera;
	private ImageView pic;

	//this returns back camera if both cameras are available
	public static Camera isCameraAvailiable() {
		Camera object = null;
		try {
			object = Camera.open();
		} catch (Exception e) {
			Log.e("CameraActivity","Camera is not available");
		}
		return object;
	}

	//this returns front camera
	private Camera isFrontCameraAvailiable() {
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
	                Log.e(DEBUG_TAG, "Front Camera failed to open: " + e.getLocalizedMessage());
	            }
	        }
	    }

	    return cam;
	}	
	
	private PictureCallback capturedIt = new PictureCallback() {

		@Override
		public void onPictureTaken(byte[] data, Camera camera) {

			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			
			bitmap=MyBitmapHelper.drawTextToBitmap(CameraActivity.this, bitmap, DateTimeHelper.toString(new Date()));
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
//			btnSave.setEnabled(true);
		}
	};

//	@Override
//	protected void onDestroy() {
//		cameraObject.release();
//		super.onDestroy();
//	}
	@Override
	protected void onResume() {
		super.onResume();
		
		//if shutdown = true
		if(shutdown)
		{
			finish();
		}
		//else if server url is not set
		else if(Constants.getServerUrl()==null)
		{
			Intent intent = new Intent(context, ServerSettingActivity.class);
			startActivity(intent);
		}
		//else load camera
		else
		{
			loadCamera();
		}

	}
	@Override
	protected void onPause() {
		releaseCamera();
		super.onPause();
	}
	Boolean shutdown=false;
	public void setShutdown(Boolean shutdown) {
		this.shutdown = shutdown;
	}
	public void releaseCamera()
	{
		if(cameraObject!=null)
			cameraObject.release();
	}
	public void loadCamera()
	{
		cameraObject = isFrontCameraAvailiable();
		if(cameraObject==null)
		{
//			btnCapture.setEnabled(false);
//			btnSave.setEnabled(false);
			
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
//			btnCapture.setEnabled(true);
//			btnSave.setEnabled(false);
		}
	}
	
	//-------list -------------

	@Override
	protected void onStart() {
		super.onStart();
		load();
	}

	List<String> itemTitles;
    ListView listView;
    
	//ArrayList<Employee> list=new ArrayList<Employee>();
	ArrayAdapter<String> listAdapter;
	protected void setupView()
	{
		pic = (ImageView) findViewById(R.id.imageView1);
		preview = (FrameLayout) findViewById(R.id.camera_preview);
//		btnCapture = (Button) findViewById(R.id.button_capture);
//		btnSave = (Button) findViewById(R.id.button_save);
//		
//		btnSave.setEnabled(false);
		
		listView = (ListView) findViewById (R.id.list);
		itemTitles= new ArrayList<String>();
        
		//=====read tracks table and add results to listview=======
		
        if (listView != null) {
        	
		listAdapter=new ArrayAdapter<String>(context,
			android.R.layout.simple_list_item_1, itemTitles);
		
		listView.setAdapter(listAdapter);


		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				if(cameraObject!=null)
				{
					String employeename=getItemAtPosition(position).getName();
					String datetimestring=DateTimeHelper.toString(new Date());
					String filename=datetimestring.replace(" ", "-").replace(":", "-")+"-"+employeename+".jpg";
					PictureDataHolder.setFilename(filename);
					
					cameraObject.takePicture(null, null, capturedIt);
					
					//if datetimestring is not set, then no picture taken
					//do nothing
					
//				    File pictureFileDir = MyPhotoSaver.getDir(context);
//				    File picturefile=new File(pictureFileDir,"temp.jpg");
//				    File newpicturefile=new File(pictureFileDir,filename);
//				    if(picturefile.exists())
				    {
//				    	picturefile.renameTo(newpicturefile);
				    	
						Record record=new Record();
						record.setDatetime(datetimestring);
						record.setEmployeeName(employeename);
			    		record.setFilename(filename);
			    		record.save();
				    	
						Toast.makeText(getApplicationContext(), "Picture saved successfully.",Toast.LENGTH_SHORT).show();
				    }
				    //else picture file not found
				    //this should never happen
//				    else
//				    {
//						Toast.makeText(getApplicationContext(), "An error has occured. Picture not saved.",Toast.LENGTH_SHORT).show();
//				    }
				}
				else
				{
					Toast.makeText(getApplicationContext(), "No camera available",Toast.LENGTH_SHORT).show();
				}
			}});
		}
		load();
	}
	
	public void load() {
        String criteria=" order by name";
        loadListItems(criteria);

		itemTitles.clear();
        for(String name:getListItemLabels())
        {
        	itemTitles.add(name);
        }
        listAdapter.notifyDataSetChanged();
        listView.invalidate();
	}

	public static void loadListItems(String criteria)
	{
		listItemLabels.clear();
		listItemIds.clear();
		//load ids and names into these arrays
		Employee.selectIdsAndNames(criteria,listItemIds,listItemLabels);
	}
	
	static ArrayList<String> listItemLabels=new ArrayList<String>();
	static ArrayList<Integer> listItemIds=new ArrayList<Integer>();

	public static ArrayList<String> getListItemLabels() {
		return listItemLabels;
	}

	public static void setListItemLabels(ArrayList<String> _listItemLabels) {
		listItemLabels = _listItemLabels;
	}

	public static ArrayList<Integer> getListItemIds() {
		return listItemIds;
	}

	public static void setListItemIds(ArrayList<Integer> _listItemIds) {
		listItemIds = _listItemIds;
	}
	public static Employee getItemAtPosition(Integer position) {
		return Employee.getById(listItemIds.get(position));
	}	
	
}
