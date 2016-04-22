package com.intelimina.pollwatcher;

import holders.LGUHolder;
import holders.PictureHolder;
import holders.UserHolder;

import java.io.File;

import models.Lgu;
import models.Record;
import models.Region;

import org.json.JSONException;
import org.json.JSONObject;

import utils.MyDialogHelper;
import utils.MyPhotoSaver;
import utils.StringHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ReportResultsActivity extends Activity {
	private EditText txtRegion,txtProvince,txtCity,txtPrecinct;	
	private Lgu province,city;
	private Region region;

	Context context;
	String[] ps={
			"Binay"
			,"Santiago"
			,"Duterte"
			,"Poe"
			,"Roxas"
			};
	String[] vps={
			"Honasan"
			,"Marcos"
			,"Cayetano"
			,"Escudero"
			,"Robredo"
			};

	String[] psfull={
			"Jejomar Binay"
			,"Miriam Defensor Santiago"
			,"Rodrigo Duterte"
			,"Grace Poe"
			,"Mar Roxas"
			};
	String[] vpsfull={
			"Gringo Honasan"
			,"Bongbong Marcos"
			,"Alan Peter Cayetano"
			,"Francis Escudero"
			,"Leni Robredo"
			};

	private ImageView imageView;	
	private EditText txtp1,txtp2,txtp3,txtp4,txtp5,txtvp1,txtvp2,txtvp3,txtvp4,txtvp5,txtNotes;
	private TextView lblp1,lblp2,lblp3,lblp4,lblp5,lblvp1,lblvp2,lblvp3,lblvp4,lblvp5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_results);
		context=ReportResultsActivity.this;

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
		lblp1.setText(psfull[0]);
		lblp2.setText(psfull[1]);
		lblp3.setText(psfull[2]);
		lblp4.setText(psfull[3]);
		lblp5.setText(psfull[4]);
		lblvp1.setText(vpsfull[0]);
		lblvp2.setText(vpsfull[1]);
		lblvp3.setText(vpsfull[2]);
		lblvp4.setText(vpsfull[3]);
		lblvp5.setText(vpsfull[4]);

		txtRegion = (EditText) findViewById(R.id.region);
		txtProvince = (EditText) findViewById(R.id.province);
		txtCity = (EditText) findViewById(R.id.city);
		txtPrecinct = (EditText) findViewById(R.id.txtPrecinct);
		
		txtRegion.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	regionList();
	        }
	    });
		txtProvince.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	provinceList();
	        }
	    });
		txtCity.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	cityList();
	        }
	    });
	}
	@Override
	protected void onStart() {
		super.onStart();
		savePicture();
		
		Region tempregion=LGUHolder.getRegion();
		if(tempregion!=null)
		{
			this.region=tempregion;
			txtRegion.setText(region.getName());
			
			txtProvince.setEnabled(true);
			txtCity.setEnabled(true);

			Lgu tempcity=LGUHolder.getCity();
			if(tempcity!=null)
			{
				this.city=tempcity;
				txtCity.setText(city.getName());
			}

			Lgu tempprovince=LGUHolder.getProvince();
			if(tempprovince!=null)
			{
				this.province=tempprovince;
				txtProvince.setText(province.getName());
			}
		}
		else
		{
			txtProvince.setEnabled(false);
			txtCity.setEnabled(false);
		}
		
	}
	public void cancel(View button)
	{
		finish();
	}
	public void save(View button)
	{
		if(PictureHolder.getPictureFile()==null)
		{
        	String message="Please take a picture of the election results";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		
		//-----LGU Validation------------------
		if(txtRegion.getText().toString().isEmpty())
		{
        	String message="Please choose your Region";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtProvince.getText().toString().isEmpty())
		{
        	String message="Please choose your Province";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtCity.getText().toString().isEmpty())
		{
        	String message="Please choose your City";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtPrecinct.getText().toString().isEmpty())
		{
        	String message="Please enter your Precinct Number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		//-------vote count validation-----------------
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
		try {
			JSONObject candidatedata=new JSONObject();
			candidatedata.put(ps[0], Integer.valueOf(txtp1.getText().toString()));
			candidatedata.put(ps[1], Integer.valueOf(txtp2.getText().toString()));
			candidatedata.put(ps[2], Integer.valueOf(txtp3.getText().toString()));
			candidatedata.put(ps[3], Integer.valueOf(txtp4.getText().toString()));
			candidatedata.put(ps[4], Integer.valueOf(txtp5.getText().toString()));
			candidatedata.put(vps[0], Integer.valueOf(txtvp1.getText().toString()));
			candidatedata.put(vps[1], Integer.valueOf(txtvp2.getText().toString()));
			candidatedata.put(vps[2], Integer.valueOf(txtvp3.getText().toString()));
			candidatedata.put(vps[3], Integer.valueOf(txtvp4.getText().toString()));
			candidatedata.put(vps[4], Integer.valueOf(txtvp5.getText().toString()));
			JSONObject locationdata=new JSONObject();
			locationdata.put("province_id", Integer.valueOf(LGUHolder.getProvince().getId().toString()));
			locationdata.put("city_id", Integer.valueOf(LGUHolder.getCity().getId().toString()));
			locationdata.put("precinct_no", Integer.valueOf(txtPrecinct.getText().toString()));
			JSONObject jsondata=new JSONObject();
			jsondata.put("candidatedata", candidatedata);
			jsondata.put("locationdata", locationdata);
			
			Record record=new Record();
			record.setJsondata(jsondata.toString());
			record.setFilename(PictureHolder.getFilename());
			record.setDatetime(new java.util.Date());
			record.setDescription(txtNotes.getText().toString());
			record.setRecordType("electionresults");
			record.setSfGuardUserId(UserHolder.getUser().getSfGuardUserId());
			record.save();
			Toast.makeText(context, "Report successfully saved.",Toast.LENGTH_LONG).show();
			finish();
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
	public void regionList()
	{
		Intent intent = new Intent(context, RegionListActivity.class);
		intent.setAction("region");
		startActivity(intent);
	}
	public void provinceList()
	{
		Intent intent = new Intent(context, LguListActivity.class);
		intent.setAction("province");
		startActivity(intent);
	}
	public void cityList()
	{
		Intent intent = new Intent(context, LguListActivity.class);
		intent.setAction("city");
		startActivity(intent);
	}
	
}
