package com.intelimina.pollwatcher;

import holders.NavigationHolder;
import holders.LGUHolder;
import holders.UserHolder;
import models.Lgu;
import models.Lgus;
import models.User;
import utils.MyDialogHelper;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import apis.android.AndroidRegisterApi;

public class Registration4Activity extends Activity {
	private EditText txtProvince;	
	private EditText txtCity;	
	private EditText txtAddress;	

	private Lgu province;
	private Lgu city;

	private Context context;
	static Registration4Activity instance;
	public static Registration4Activity getInstance() {
		return instance;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration4);
		context=Registration4Activity.this;
		instance=Registration4Activity.this;
		
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
	public void back(View button){back();}
	public void back()
	{
		save();
		NavigationHolder.setDestination(NavigationHolder.Registration3Activity);
		finish();
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
		AndroidRegisterApi.demo(context, UserHolder.getRegUser());
	}
	public void save()
	{
		User user=UserHolder.getRegUser();
		user.setProvinceId(LGUHolder.getProvince().getId());
		user.setCityId(LGUHolder.getCity().getId());
		user.setAddress(txtAddress.getText().toString());
	}
	public void onSubmitSuccess()
	{
		MyDialogHelper.popup(context, "Registration Successful");
		LGUHolder.reset();
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

		Lgu tempcity=LGUHolder.getCity();
		if(tempcity!=null)
		{
			this.city=tempcity;
			txtCity.setText(city.getName());
		}
		else
		{
			txtCity.setText("");
		}

		Lgu tempprovince=LGUHolder.getProvince();
		if(tempprovince!=null)
		{
			this.province=tempprovince;
			txtProvince.setText(province.getName());
		}
		else
		{
			txtProvince.setText("");
		}
	
		User user=UserHolder.getRegUser();
//		RegistrationHolder.setCity(Lgus.getById(user.getCityId()));
//		txtCity.setText(RegistrationHolder.getCity().getName());
//		RegistrationHolder.setProvince(Lgus.getById(user.getProvinceId()));
//		txtProvince.setText(RegistrationHolder.getProvince().getName());
		txtAddress.setText(user.getAddress());
		
	}
	public void fill(View button)
	{
		LGUHolder.setCity(Lgus.getByName("Manila"));
		txtCity.setText(LGUHolder.getCity().getName());
		LGUHolder.setProvince(Lgus.getByName(" Other / Not Applicable"));
		txtProvince.setText(LGUHolder.getProvince().getName());
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
	@Override
	public void onBackPressed()
	{
		back();
	}
}
