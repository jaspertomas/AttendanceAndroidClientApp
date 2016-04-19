package com.intelimina.pollwatcher;

import holders.PictureHolder;
import holders.UserHolder;

import java.io.File;

import models.Record;

import org.json.JSONException;
import org.json.JSONObject;

import utils.DateTimeHelper;
import utils.MyDialogHelper;
import utils.MyPhotoSaver;
import utils.StringHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ReportVoteBuyingActivity extends Activity {
	Context context;

	private ImageView imageView;	
	private EditText txtNotes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_vote_buying);
		context=ReportVoteBuyingActivity.this;

		MyPhotoSaver.reset();
		PictureHolder.reset();
		setupView();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		MyPhotoSaver.reset();
		PictureHolder.reset();
	}
	private void setupView()
	{
		imageView = (ImageView) findViewById(R.id.imageView);
		txtNotes = (EditText) findViewById(R.id.txtNotes);

	}
	@Override
	protected void onStart() {
		super.onStart();
		savePicture();
	}
	public void cancel(View button)
	{
		finish();
	}
	public void save(View button)
	{
		if(PictureHolder.getPictureFile()==null)
		{
        	String message="Please take a picture of the vote buying";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		
		//--------validation complete---------

		//save data to database
		Record record=new Record();
		record.setJsondata("");
		record.setFilename(PictureHolder.getFilename());
		record.setDatetime(new java.util.Date());
		record.setDescription(txtNotes.getText().toString());
		record.setRecordType("votebuying");
		record.setSfGuardUserId(UserHolder.getUser().getSfGuardUserId());
		record.save();
		finish();
	}
	public void takePicture(View button)
	{
		takePicture();
	}
	//this launches CameraActivity
	public void takePicture()
	{
//		PictureHolder.setAction(PictureHolder.ACTION_ODOPIC);
		Intent intent = new Intent(context, CameraActivity.class);
		startActivity(intent);
	}
	//this automatically runs after coming back from CameraActivity
	//it saves the picture taken by CameraActivity, if it exists
	public void savePicture()
	{
		//if action is neither adpic nor odopic, do nothing
		//if(PictureHolder.getAction()==0)return;
		
		//if there's a picture in MyPhotoSaver, 
		if(MyPhotoSaver.getPictureFile()!=null)
		{
			File picturefile=MyPhotoSaver.getPictureFile();
			String filename=MyPhotoSaver.getDateTimeStringToFilename();
			
			//then rename it from temp.jpg to a name with the datestring
			File newfile=new File(MyPhotoSaver.getDir(context)+ File.separator +filename);
	    	picturefile.renameTo(newfile);
			//or not
	    	//use this if files are not saved to temp.jpg, and need no renaming
//			File newfile=picturefile;
	    	
	    	//and move it to PictureHolder
	    	PictureHolder.setPictureFile(newfile);
	    	//along with additional data
	    	PictureHolder.setFilename(filename);
		}
		
		//if there's a picture in PictureHolder
		//whether or not it's newly taken
		if(PictureHolder.getPictureFile()!=null)
	    {
    		//show image
	        imageView.setImageBitmap(BitmapFactory.decodeFile(PictureHolder.getPictureFile().getAbsolutePath()));
	    }
	}
}
