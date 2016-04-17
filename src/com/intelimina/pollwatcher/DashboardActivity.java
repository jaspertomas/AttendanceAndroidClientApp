package com.intelimina.pollwatcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends Activity {
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		context=DashboardActivity.this;
	}
	public void takePicture(View button)
	{
		Intent intent = new Intent(context, CameraActivity.class);
		startActivity(intent);
	}
}
