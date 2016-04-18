package com.intelimina.pollwatcher;

import holders.PictureDataHolder;
import holders.PictureHolder;

import java.io.File;

import utils.MyDialogHelper;
import utils.MyPhotoSaver;
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
	
	private ImageView imageView;	
	private EditText txtp1,txtp2,txtp3,txtp4,txtp5,txtvp1,txtvp2,txtvp3,txtvp4,txtvp5;
	private TextView lblp1,lblp2,lblp3,lblp4,lblp5,lblvp1,lblvp2,lblvp3,lblvp4,lblvp5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_results);
		context=ReportResultsActivity.this;

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
	public void submit(View button)
	{
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

//		Log.i(ps[0],String.valueOf(txtp1.getText()));
//		Log.i(ps[1],String.valueOf(txtp2.getText()));
//		Log.i(ps[2],String.valueOf(txtp3.getText()));
//		Log.i(ps[3],String.valueOf(txtp4.getText()));
//		Log.i(ps[4],String.valueOf(txtp5.getText()));
//		Log.i(vps[0],String.valueOf(txtvp1.getText()));
//		Log.i(vps[1],String.valueOf(txtvp2.getText()));
//		Log.i(vps[2],String.valueOf(txtvp3.getText()));
//		Log.i(vps[3],String.valueOf(txtvp4.getText()));
//		Log.i(vps[4],String.valueOf(txtvp5.getText()));
//		save();

		//send data to server
//		AndroidRegisterApi.demo(context, UserHolder.getRegUser());
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
		
		//if datetimestring is not set, then no picture taken
		//do nothing
		String datetimestring=PictureHolder.getDatetimestring();
		if(datetimestring.isEmpty())return;
		
	    File pictureFileDir = MyPhotoSaver.getDir(context);
	    String filename=PictureDataHolder.getFilename();
	    File picturefile=new File(pictureFileDir,filename);
	    if(picturefile.exists())
	    {
	    	//picturefile.renameTo(newpicturefile);
	    	
//			Update update=VehicleHolder.getUpdate();
//	    	
//    	    File oldpicturefile=new File(pictureFileDir,update.getOdoPicFilename());
//    	    if(oldpicturefile.exists())oldpicturefile.delete();
//    		
//    		//save filename to update
//    		update.setOdoPicFilename(filename);
//    		update.save();

    		//show image
	        imageView.setImageBitmap(BitmapFactory.decodeFile(picturefile.getAbsolutePath()));
//			Toast.makeText(getApplicationContext(), "Picture saved successfully.",Toast.LENGTH_SHORT).show();
	    }
	    //else picture file not found
	    //this should never happen
	    else
	    {
//			Toast.makeText(getApplicationContext(), "An error has occured. Picture not saved.",Toast.LENGTH_SHORT).show();
	    }
	}
}
