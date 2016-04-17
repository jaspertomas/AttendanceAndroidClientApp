package com.intelimina.pollwatcher;

import holders.NavigationHolder;
import holders.UserHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DashboardActivity extends Activity {
	TextView welcome;

	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		context=DashboardActivity.this;

		setupView();
	}
	private void setupView()
	{
		welcome = (TextView) findViewById (R.id.welcome);
        welcome.setText("Welcome "+UserHolder.getUser().toString());
	}
	public void takePicture(View button)
	{
		Intent intent = new Intent(context, CameraActivity.class);
		startActivity(intent);
	}
	@Override
	public void onBackPressed()
	{
		NavigationHolder.setDestination(NavigationHolder.ShutDown);
		finish();
	}
	public void reportResults(View button)
	{
		Intent intent = new Intent(context, ReportResultsActivity.class);
		startActivity(intent);
	}
}
