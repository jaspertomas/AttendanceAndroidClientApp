package com.itforhumanity.attendance;

import models.Setting;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServerSettingActivity extends Activity {
	EditText txtServerIp;
	Button btnExit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_server_setting);
		
		setupView();
	}
	public void setupView()
	{
	    txtServerIp = (EditText) findViewById(R.id.serverIp);
	    btnExit = (Button) findViewById(R.id.btnExit);
	}
	@Override
	protected void onResume() {
		super.onResume();
	    Setting serverIp=Setting.getByName("server_ip_raw");
	    if(serverIp!=null)
	    {
	    	txtServerIp.setText(serverIp.getValue());
	    	btnExit.setVisibility(View.GONE);
	    }
	    else
	    	btnExit.setVisibility(View.VISIBLE);//if server is set, no need for exit button
	}
	public void setServerIp(View view)
	{
	    if(txtServerIp.getText().toString().isEmpty())
	    {
			Toast.makeText(this, "Please enter an IP Address", Toast.LENGTH_LONG).show();
			return;
	    }
		
	    Setting serverIpRaw=Setting.getByName("server_ip_raw");
	    if(serverIpRaw==null)
	    {
			serverIpRaw=new Setting();
			serverIpRaw.setName("server_ip_raw");
	    }
	    serverIpRaw.setValue(txtServerIp.getText().toString());
	    serverIpRaw.save();

	    Setting serverIp=Setting.getByName("server_ip");
	    if(serverIp==null)
	    {
	    	serverIp=new Setting();
	    	serverIp.setName("server_ip");
	    }
	    serverIp.setValue("http://"+txtServerIp.getText().toString()+"/attendance/web/index.php");
	    serverIp.save();
	    Constants.initServerUrl();
		Toast.makeText(this, "IP Address set to "+"http://"+txtServerIp.getText().toString()+"/web/index.php", Toast.LENGTH_LONG).show();
		
		finish();
	}	
	public void exit(View view) {
	    Setting serverIp=Setting.getByName("server_ip");
	    if(serverIp==null)
	    	CameraActivity.getInstance().setShutdown(true);
		finish();
	}
	
}
