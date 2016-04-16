package com.intelimina.pollwatcher;

import holders.NavigationHolder;
import holders.RegistrationHolder;
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
	
	static final int DATE_DIALOG_ID = 0;

	private EditText txtUname;	
	private EditText txtPasswd;	
	private EditText txtPasswd2;	
	private EditText txtFname;	
	private EditText txtMi;	
	private EditText txtLname;	
	private EditText txtProvince;	
	private EditText txtCity;	
	private EditText txtBday;	
	private EditText txtEmail;	
	private EditText txtPhone;	
	private EditText txtAddress;	
	private Button btnFakeSuccess,btnFill;
	
	private Lgu province;
	private Lgu city;

	private Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		context=RegistrationActivity.this;
		
		setupView();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//data no longer needed
		RegistrationHolder.reset();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}

	private void setupView()
	{
		RegistrationHolder.reset();

		txtUname = (EditText) findViewById(R.id.uname);
		txtPasswd = (EditText) findViewById(R.id.passwd);
		txtPasswd2 = (EditText) findViewById(R.id.passwd2);
		txtFname = (EditText) findViewById(R.id.fname);
		txtMi = (EditText) findViewById(R.id.mi);
		txtLname = (EditText) findViewById(R.id.lname);
		txtProvince = (EditText) findViewById(R.id.province);
		txtCity = (EditText) findViewById(R.id.city);
		txtBday = (EditText) findViewById(R.id.bday);
		txtEmail = (EditText) findViewById(R.id.email);
		txtPhone = (EditText) findViewById(R.id.phone);
		txtAddress = (EditText) findViewById(R.id.address);
		
		btnFakeSuccess = (Button) findViewById(R.id.btnFakeSuccess);
		btnFakeSuccess.setVisibility(View.GONE);
		
//		if(Constants.DEMO==true)
//		{
//			btnFill = (Button) findViewById(R.id.btnFill);
//			btnFill.setVisibility(View.GONE);
//		}
		
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
		txtBday.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	    		showDialog(DATE_DIALOG_ID);		
	        }
	    });
		
		//default values
		txtBday.setText(PrettyDateHelper.toString(DateHelper.getNullDate2000()));
		
		//setup temporary user record
		User user=UserHolder.getUser();
		if(user!=null)
		{
			txtUname.setText(user.getUsername());
			txtPasswd.setText("");
			txtPasswd2.setText("");
			txtFname.setText(user.getFname());
			txtMi.setText(user.getMi());
			txtLname.setText(user.getLname());
			Date bday=user.getBday();
			txtBday.setText(PrettyDateHelper.toString(bday));
			byear=DateHelper.getYear(bday);
			bmonth=DateHelper.getMonth(bday);
			bdayOfMonth=DateHelper.getDayOfMonth(bday);
			txtPhone.setText(user.getPhone());
			txtEmail.setText(user.getEmail());
			RegistrationHolder.setCity(Lgus.getById(user.getCityId()));
			txtCity.setText(RegistrationHolder.getCity().getName());
			RegistrationHolder.setProvince(Lgus.getById(user.getProvinceId()));
			txtProvince.setText(RegistrationHolder.getProvince().getName());
			txtAddress.setText(user.getAddress());
		}
		else
		{
			user=new User();
			user.setUsername(txtUname.getText().toString());
			user.setPassword(MyEncryptionHelper.encrypt(txtPasswd.getText().toString()));
			user.setFname(txtFname.getText().toString());
			user.setMi(txtMi.getText().toString());
			user.setLname(txtLname.getText().toString());
			user.setBday(DateHelper.getNullDate2000());
			user.setEmail(txtEmail.getText().toString());
			user.setPhone(txtPhone.getText().toString());
			RegistrationHolder.setCity(Lgus.getByName(" Other / Not Applicable"));
			user.setCityId(RegistrationHolder.getCity().getId());
			RegistrationHolder.setProvince(Lgus.getByName(" Other / Not Applicable"));
			user.setProvinceId(RegistrationHolder.getProvince().getId());
			user.setAddress(txtAddress.getText().toString());
			user.setIsReg(0);
			user.save();
			
			UserHolder.setUser(user);
		}
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
	public void register(View button)
	{
		try {
			save();
		} catch (ParseException e) {
	    	MyDialogHelper.popup(context, "Invalid Date");
		}
		
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
		else if(duplicateuser!=null && duplicateuser.getId()!=UserHolder.getUser().getId())
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
		else if(txtBday.getText().toString().isEmpty())
		{
        	String message="Please enter your Birthday";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		else if(txtEmail.getText().toString().isEmpty())
		{
        	String message="Please enter your Email Address";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txtEmail.getText().toString()).matches())
		{
        	String message="Please enter a valid Email Address";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		else if(txtPhone.getText().toString().isEmpty())
		{
        	String message="Please enter your Phone Number";
			MyDialogHelper.popup(context, message);
        	return;
		} 
		else if(txtProvince.getText().toString().isEmpty())
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
		
		//send data to server
		
		//temporarily fake registration success
		fakeSuccess();
	}
	public void fakeSuccess(View button){fakeSuccess();}
	public void fakeSuccess()
	{
		User user=UserHolder.getUser();
		user.setIsReg(1);
		user.save();

		//go to dashboard after this
		NavigationHolder.setDestination(NavigationHolder.DashboardActivity);
		
    	String message="Registration successful";
    	Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    	
    	finish();
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

	//-----date picker system----
	private Integer byear=0,bmonth=0,bdayOfMonth=0;
	public void setDate(int year, int month, int date) {
		this.byear=year;
		this.bmonth=month;
		this.bdayOfMonth=date;
//		String myString = OutputStringCalculator.getGivenDateString(year, month, date);
		txtBday.setText(PrettyDateHelper.toString(DateHelper.toDate(byear+"-"+bmonth+"-"+bdayOfMonth)));
//		textscrollview.fullScroll(ScrollView.FOCUS_UP);
	}
	//this receives the chosen date from the datepicker dialog 
	private DatePickerDialog.OnDateSetListener mDateSetListener =
	new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, 
				int monthOfYear, int dayOfMonth) {
			setDate(year, monthOfYear+1, dayOfMonth);
		}
	};
	//this creates the datepicker dialog
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			Calendar c = Calendar.getInstance(); 
			byear=c.get(c.YEAR);
			bmonth=c.get(c.MONTH)+1;
			bdayOfMonth=c.get(c.DATE);
			DatePickerDialog d=new DatePickerDialog(this,
					mDateSetListener,
					2000, 0, 1);
			
			d.getDatePicker().setMinDate(DateHelper.toDate(String.valueOf(byear-100)+"-"+bmonth+"-"+bdayOfMonth).getTime());
			d.getDatePicker().setMaxDate(DateHelper.toDate(byear+"-"+bmonth+"-"+bdayOfMonth).getTime());
			return d;
		}
		return null;
	}
	//-----end date picker system----

	public void save(View button)
	{
		try {
			save();
		} catch (ParseException e) {
	    	MyDialogHelper.popup(context, "Invalid Date");
		}
	}
	public void save() throws ParseException
	{
		User user=UserHolder.getUser();
		user.setUsername(txtUname.getText().toString());
		user.setPassword(MyEncryptionHelper.encrypt(txtPasswd.getText().toString()));
		user.setFname(txtFname.getText().toString());
		user.setMi(txtMi.getText().toString());
		user.setLname(txtLname.getText().toString());
		user.setBday(PrettyDateHelper.toDate(txtBday.getText().toString()));
		user.setEmail(txtEmail.getText().toString());
		user.setPhone(txtPhone.getText().toString());
		user.setProvinceId(RegistrationHolder.getProvince().getId());
		user.setCityId(RegistrationHolder.getCity().getId());
		user.setAddress(txtAddress.getText().toString());
		user.save();
	}
	public void fill(View button)
	{
		txtUname.setText("asdfgh");
		txtPasswd.setText("asdfgh");
		txtPasswd2.setText("asdfgh");
		txtFname.setText("asdfgh");
		txtMi.setText("a");
		txtLname.setText("asdfgh");
		txtBday.setText("Jan 1, 2000");
		byear=2000;bmonth=1;bdayOfMonth=1;
		txtPhone.setText("12345677");
		txtEmail.setText("a@a.a");
		RegistrationHolder.setCity(Lgus.getByName("Manila"));
		txtCity.setText(RegistrationHolder.getCity().getName());
		RegistrationHolder.setProvince(Lgus.getByName(" Other / Not Applicable"));
		txtProvince.setText(RegistrationHolder.getProvince().getName());
		txtAddress.setText("asdfgh");
		
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
		UserHolder.getUser().delete();
		UserHolder.reset();

		NavigationHolder.setDestination(NavigationHolder.ChooseUserActivity);
		finish();
	}
	@Override
	public void onBackPressed()
	{
		finish();
	}
	public void saveAndExit(View button)
	{
		try {
			save();
		} catch (ParseException e) {
	    	MyDialogHelper.popup(context, "Invalid Date");
		}
		finish();
	}
}
