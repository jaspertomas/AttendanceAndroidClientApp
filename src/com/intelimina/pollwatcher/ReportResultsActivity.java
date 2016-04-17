package com.intelimina.pollwatcher;

import holders.UserHolder;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ReportResultsActivity extends Activity {
	private ImageView imageView;	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report_results);

		setupView();
	}
	private void setupView()
	{
		imageView = (ImageView) findViewById(R.id.imageView);
	}
}
