package com.intelimina.pollwatcher;

import holders.LGUHolder;
import holders.NavigationHolder;
import holders.UserHolder;
import models.Record;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import apis.android.AndroidUploadApi;

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
	@Override
	public void onBackPressed()
	{
		NavigationHolder.setDestination(NavigationHolder.ShutDown);
		finish();
	}
	public void reportResults(View button)
	{
		LGUHolder.reset();
		Intent intent = new Intent(context, ReportResultsActivity.class);
		startActivity(intent);
	}
	public void reportVoteBuying(View button)
	{
		Intent intent = new Intent(context, ReportVoteBuyingActivity.class);
		startActivity(intent);
	}
	public void reportIncident(View button)
	{
		Intent intent = new Intent(context, ReportIncidentActivity.class);
		startActivity(intent);
	}
	public void launchCamera(View button)
	{
		 Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
		 startActivityForResult(intent, 0); 
 //	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//	        startActivityForResult(takePictureIntent, 1);
//	    }
	}
	public void upload(View button)
	{
		Integer count=Record.count("");
		if(count==0)
			Toast.makeText(context, "No records to upload",Toast.LENGTH_LONG).show();
	    else
			AndroidUploadApi.demo(context);
	}
}
