package com.intelimina.pollwatcher;

import holders.NavigationHolder;
import holders.RegistrationHolder;
import holders.UserHolder;
import utils.MyDialogHelper;
import models.Lgu;
import models.Lgus;
import models.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Registration4Activity extends Activity {
	private EditText txtProvince;	
	private EditText txtCity;	
	private EditText txtAddress;	

	private Lgu province;
	private Lgu city;

	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration4);
		context=Registration4Activity.this;
		
		setupView();
	}
	private void setupView()
	{
		txtProvince = (EditText) findViewById(R.id.province);
		txtCity = (EditText) findViewById(R.id.city);
		txtAddress = (EditText) findViewById(R.id.address);
		
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
	public void submit(View button)
	{
		if(txtProvince.getText().toString().isEmpty())
		{
        	String message="Please choose your Province";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		else if(txtCity.getText().toString().isEmpty())
		{
        	String message="Please choose your City";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		else if(txtAddress.getText().toString().isEmpty())
		{
        	String message="Please enter your Address";
			MyDialogHelper.popup(context, message);
        	return;
		} 

		save();
		//send data to server
		//!!!todo
	}
	public void save()
	{
		NavigationHolder.setDestination(NavigationHolder.Registration3Activity);
		finish();
		User user=UserHolder.getRegUser();
		user.setProvinceId(RegistrationHolder.getProvince().getId());
		user.setCityId(RegistrationHolder.getCity().getId());
		user.setAddress(txtAddress.getText().toString());
	}
	public void onSubmitSuccess()
	{
		RegistrationHolder.reset();
		NavigationHolder.setDestination(NavigationHolder.MainActivity);
		finish();
	}
	public void onSubmitFailure(String message)
	{
		MyDialogHelper.popup(context, message);
	}
	@Override
	protected void onStart() {
		super.onStart();

		Lgu tempcity=RegistrationHolder.getCity();
		if(tempcity!=null)
		{
			this.city=tempcity;
			txtCity.setText(city.getName());
		}
		else
		{
			txtCity.setText("");
		}

		Lgu tempprovince=RegistrationHolder.getProvince();
		if(tempprovince!=null)
		{
			this.province=tempprovince;
			txtProvince.setText(province.getName());
		}
		else
		{
			txtProvince.setText("");
		}
	
	}
	public void fill(View button)
	{
		RegistrationHolder.setCity(Lgus.getByName("Manila"));
		txtCity.setText(RegistrationHolder.getCity().getName());
		RegistrationHolder.setProvince(Lgus.getByName(" Other / Not Applicable"));
		txtProvince.setText(RegistrationHolder.getProvince().getName());
		txtAddress.setText("asdfgh");
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
