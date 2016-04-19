package com.intelimina.pollwatcher;

import holders.NavigationHolder;
import holders.LGUHolder;
import holders.UserHolder;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import models.Lgu;
import models.Lgus;
import models.User;
import utils.DateHelper;
import utils.MyDialogHelper;
import utils.MyEncryptionHelper;
import utils.PrettyDateHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends Activity {
	
	private EditText txtUname;	
	private EditText txtPasswd;	
	private EditText txtPasswd2;	
	
	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		context=RegistrationActivity.this;
		
		setupView();
	}
//	@Override
//	protected void onDestroy() {
//		super.onDestroy();
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}

	private void setupView()
	{

		txtUname = (EditText) findViewById(R.id.uname);
		txtPasswd = (EditText) findViewById(R.id.passwd);
		txtPasswd2 = (EditText) findViewById(R.id.passwd2);
	}
	public void next(View button)
	{
		User duplicateuser=User.getByUsername(txtUname.getText().toString());
		
		//validate
		if(txtUname.getText().toString().isEmpty())
		{
        	String message="Please enter a User Name";
			MyDialogHelper.popup(context, message);
        	return;
		}
		if(txtUname.getText().toString().length()<6)
		{
        	String message="Username is too short (6 letters or longer please)";
			MyDialogHelper.popup(context, message);
        	return;
		}
		else if(txtUname.getText().toString().length()>50)
		{
        	String message="Your Username is too long (50 letters or shorter please)";
			MyDialogHelper.popup(context, message);
        	return;
		}
		//else if username is taken (by another User record)
		//say so
		else if(duplicateuser!=null && duplicateuser.getId()!=UserHolder.getRegUser().getId())
		{
        	String message="Username "+txtUname.getText().toString()+" is already taken.";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		if(txtPasswd.getText().toString().isEmpty())
		{
        	String message="Please enter a Password";
			MyDialogHelper.popup(context, message);
        	return;
		}
		if(txtPasswd2.getText().toString().isEmpty())
		{
        	String message="Please retype your Password";
			MyDialogHelper.popup(context, message);
        	return;
		}
		if(!txtPasswd2.getText().toString().contentEquals(txtPasswd.getText().toString()))
		{
        	String message="Passwords do not match";
			MyDialogHelper.popup(context, message);
        	return;
		}

		save();
		NavigationHolder.setDestination(NavigationHolder.Registration2Activity);
		finish();
	}

	public void save()
	{
		User user=UserHolder.getRegUser();
		user.setUsername(txtUname.getText().toString());
		user.setPhone(txtUname.getText().toString());
		user.setPassword(txtPasswd.getText().toString());
	}
	public void fill(View button)
	{
		txtUname.setText("asdfgh");
		txtPasswd.setText("asdfgh");
		txtPasswd2.setText("asdfgh");
		
	}

	public void cancel(View button){cancel();}
	public void cancel()
	{
		String message="Are you sure you want to cancel?";
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(message);

		// Set up the buttons
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    	reallyCancel();
		    }
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    }
		});
		builder.show();
	}
	public void reallyCancel()
	{
		//city and province data no longer needed
		LGUHolder.reset();
		NavigationHolder.setDestination(NavigationHolder.ShutDown);
		finish();
	}
	@Override
	public void onBackPressed()
	{
		cancel();
//		MainActivity.getInstance().setShutdown(true);
//		finish();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		User user=UserHolder.getRegUser();
			txtUname.setText(user.getUsername());
			txtPasswd.setText(user.getPassword());
			txtPasswd2.setText(user.getPassword());
	}
}
