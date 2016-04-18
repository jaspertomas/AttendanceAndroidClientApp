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

public class ReportResultsActivity extends Activity {
	Context context;
	String[] ps={
			"Binay"
			,"Santiago"
			,"Duterte"
			,"Poe"
			,"Roxas"
			};
	String[] vps={
			"Honasan "
			,"Marcos"
			,"Cayetano"
			,"Escudero"
			,"Robredo "
			};
/*
	String[] ps={
			"Jejomar Binay"
			,"Miriam Defensor Santiago"
			,"Rodrigo Duterte"
			,"Grace Poe"
			,"Mar Roxas"
			};
	String[] vps={
			"Gringo Honasan "
			,"Bongbong Marcos"
			,"Alan Peter Cayetano"
			,"Francis Escudero"
			,"Leni Robredo "
			};
 * */	
	private ImageView imageView;	
	private EditText txtp1,txtp2,txtp3,txtp4,txtp5,txtvp1,txtvp2,txtvp3,txtvp4,txtvp5;
	private TextView lblp1,lblp2,lblp3,lblp4,lblp5,lblvp1,lblvp2,lblvp3,lblvp4,lblvp5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_results);
		context=ReportResultsActivity.this;

		PictureHolder.reset();
		setupView();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		PictureHolder.reset();
	}
	private void setupView()
	{
		imageView = (ImageView) findViewById(R.id.imageView);
		txtvp1 = (EditText) findViewById(R.id.txtvp5);
		txtvp2 = (EditText) findViewById(R.id.txtvp4);
		txtvp3 = (EditText) findViewById(R.id.txtvp3);
		txtvp4 = (EditText) findViewById(R.id.txtvp2);
		txtvp5 = (EditText) findViewById(R.id.txtvp1);
		txtp1 = (EditText) findViewById(R.id.txtp5);
		txtp2 = (EditText) findViewById(R.id.txtp4);
		txtp3 = (EditText) findViewById(R.id.txtp3);
		txtp4 = (EditText) findViewById(R.id.txtp2);
		txtp5 = (EditText) findViewById(R.id.txtp1);
		lblvp1 = (TextView) findViewById(R.id.lblvp5);
		lblvp2 = (TextView) findViewById(R.id.lblvp4);
		lblvp3 = (TextView) findViewById(R.id.lblvp3);
		lblvp4 = (TextView) findViewById(R.id.lblvp2);
		lblvp5 = (TextView) findViewById(R.id.lblvp1);
		lblp1 = (TextView) findViewById(R.id.lblp5);
		lblp2 = (TextView) findViewById(R.id.lblp4);
		lblp3 = (TextView) findViewById(R.id.lblp3);
		lblp4 = (TextView) findViewById(R.id.lblp2);
		lblp5 = (TextView) findViewById(R.id.lblp1);
		lblp1.setText(ps[0]);
		lblp2.setText(ps[1]);
		lblp3.setText(ps[2]);
		lblp4.setText(ps[3]);
		lblp5.setText(ps[4]);
		lblvp1.setText(vps[0]);
		lblvp2.setText(vps[1]);
		lblvp3.setText(vps[2]);
		lblvp4.setText(vps[3]);
		lblvp5.setText(vps[4]);
	}
	@Override
	protected void onStart() {
		super.onStart();
		savePicture();
	}
	public void save(View button)
	{
		if(PictureHolder.getPictureFile()==null)
		{
        	String message="Please take a picture of the election results";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		
		if(txtp5.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+ps[4];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtp4.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+ps[3];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtp3.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+ps[2];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtp2.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+ps[1];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtp1.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+ps[0];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtvp5.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+vps[4];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtvp4.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+vps[3];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtvp3.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+vps[2];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtvp2.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+vps[1];
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtvp1.getText().toString().isEmpty())
		{
        	String message="Please enter vote count for "+vps[0];
			MyDialogHelper.popup(context, message);
        	return;
		} 

		
		if(!StringHelper.isNumeric(txtp5.getText().toString()))
		{
        	String message="Vote count for "+ps[4]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtp4.getText().toString()))
		{
        	String message="Vote count for "+ps[3]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtp3.getText().toString()))
		{
        	String message="Vote count for "+ps[2]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtp2.getText().toString()))
		{
        	String message="Vote count for "+ps[1]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtp1.getText().toString()))
		{
        	String message="Vote count for "+ps[0]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtvp5.getText().toString()))
		{
        	String message="Vote count for "+vps[4]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtvp4.getText().toString()))
		{
        	String message="Vote count for "+vps[3]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtvp3.getText().toString()))
		{
        	String message="Vote count for "+vps[2]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtvp2.getText().toString()))
		{
        	String message="Vote count for "+vps[1]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(!StringHelper.isNumeric(txtvp1.getText().toString()))
		{
        	String message="Vote count for "+vps[0]+" must be a number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		//--------validation complete---------

		//save data to database
		JSONObject jsondata=new JSONObject();
		try {
			jsondata.put(ps[0], Integer.valueOf(txtp1.getText().toString()));
			jsondata.put(ps[1], Integer.valueOf(txtp2.getText().toString()));
			jsondata.put(ps[2], Integer.valueOf(txtp3.getText().toString()));
			jsondata.put(ps[3], Integer.valueOf(txtp4.getText().toString()));
			jsondata.put(ps[4], Integer.valueOf(txtp5.getText().toString()));
			jsondata.put(vps[0], Integer.valueOf(txtvp1.getText().toString()));
			jsondata.put(vps[1], Integer.valueOf(txtvp2.getText().toString()));
			jsondata.put(vps[2], Integer.valueOf(txtvp3.getText().toString()));
			jsondata.put(vps[3], Integer.valueOf(txtvp4.getText().toString()));
			jsondata.put(vps[4], Integer.valueOf(txtvp5.getText().toString()));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Record record=new Record();
		record.setJsondata(jsondata.toString());
		record.setFilename(PictureHolder.getFilename());
		record.setDatetime(new java.util.Date());
//		record.setDescription(txtDescription.getText().toString());
		record.setRecordType("electionresults");
		record.setSfGuardUserId(UserHolder.getUser().getSfGuardUserId());
//		record.save();
		Log.i("",jsondata.toString());
	}
	public void takePicture(View button)
	{
		takePicture();
	}
	public void takePicture()
	{
//		PictureHolder.setAction(PictureHolder.ACTION_ODOPIC);
		Intent intent = new Intent(context, CameraActivity.class);
		startActivity(intent);
	}

	public void savePicture()
	{
		//if action is neither adpic nor odopic, do nothing
		//if(PictureHolder.getAction()==0)return;
		
		//if there's a picture in MyPhotoSaver, 
		if(MyPhotoSaver.getPictureFile()!=null)
		{
			File picturefile=MyPhotoSaver.getPictureFile();
			String filename=MyPhotoSaver.getDateTimeString()+".jpg";
			
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
