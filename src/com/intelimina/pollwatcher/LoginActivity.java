package com.intelimina.pollwatcher;

import holders.NavigationHolder;
import holders.UserHolder;
import models.Setting;
import models.User;
import utils.MyDialogHelper;
import utils.MyEncryptionHelper;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private EditText txtPasswd;	
	private TextView txtUname;	
	//private CheckBox chkRememberMe;	

	Context context;
	User user;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		context=LoginActivity.this;
		
		setupView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	private void setupView()
	{
		txtPasswd = (EditText) findViewById(R.id.passwd);
		txtUname = (TextView) findViewById(R.id.uname);
		//chkRememberMe = (CheckBox) findViewById(R.id.chkRememberMe);

		//remember me: prepopulate username
		user=User.selectOne(" where is_reg=1");
		if(user!=null)
		txtUname.setText(user.getUsername());
	}
	public void login(View button)
	{
		if(MyEncryptionHelper.encrypt(txtPasswd.getText().toString()).contentEquals(user.getPassword()))
		{
//			Setting remember_me=Setting.getByName("remember_me");
//			if(chkRememberMe.isChecked())
//			{
//				remember_me.setValue(user.getId().toString());
//				remember_me.save();
//			}
//			else
//			{
//				remember_me.setValue("0");
//				remember_me.save();
//			}
			
			UserHolder.setUser(user);
			//Go to dashboard after this
			NavigationHolder.setDestination(NavigationHolder.MainActivity);
			finish();
		}
		else
		{
			txtPasswd.setText("");
			String message="Invalid password";
			MyDialogHelper.popup(context, message);
		}
		
	}
	public void cancel(View button)
	{
		NavigationHolder.setDestination(NavigationHolder.ShutDown);
		finish();
	}
	@Override
	public void onBackPressed()
	{
		NavigationHolder.setDestination(NavigationHolder.ShutDown);
		finish();
	}

}
