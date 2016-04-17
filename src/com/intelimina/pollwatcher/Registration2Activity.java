package com.intelimina.pollwatcher;

import java.util.Date;

import holders.NavigationHolder;
import holders.LGUHolder;
import holders.UserHolder;
import models.Lgus;
import models.User;
import utils.DateHelper;
import utils.MyDialogHelper;
import utils.PrettyDateHelper;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Registration2Activity extends Activity {
	private EditText txtFname;	
	private EditText txtMi;	
	private EditText txtLname;	

	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration2);
		context=Registration2Activity.this;
		
		setupView();
	}
	private void setupView()
	{
		txtFname = (EditText) findViewById(R.id.fname);
		txtMi = (EditText) findViewById(R.id.mi);
		txtLname = (EditText) findViewById(R.id.lname);
	}
	public void back(View button){back();}
	public void back()
	{
		save();
		NavigationHolder.setDestination(NavigationHolder.RegistrationActivity);
		finish();
	}
	public void next(View button)
	{
		if(txtFname.getText().toString().isEmpty())
		{
			String message="Please enter your First Name";
			MyDialogHelper.popup(context, message);
        	return;
		}
		else if(txtMi.getText().toString().isEmpty())
		{
        	String message="Please enter your Middle Initial";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		else if(txtLname.getText().toString().isEmpty())
		{
        	String message="Please enter your Last Name";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		save();
		NavigationHolder.setDestination(NavigationHolder.Registration3Activity);
		finish();
	}
	public void save()
	{
		User user=UserHolder.getRegUser();
		user.setFname(txtFname.getText().toString());
		user.setMi(txtMi.getText().toString());
		user.setLname(txtLname.getText().toString());
	}
	public void fill(View button)
	{
		txtFname.setText("asdfgh");
		txtMi.setText("a");
		txtLname.setText("asdfgh");
	}
	@Override
	protected void onStart() {
		super.onStart();
		User user=UserHolder.getRegUser();
		txtFname.setText(user.getFname());
		txtMi.setText(user.getMi());
		txtLname.setText(user.getLname());


	}
	@Override
	public void onBackPressed()
	{
		back();
	}
}
