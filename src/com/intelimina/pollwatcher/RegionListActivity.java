package com.intelimina.pollwatcher;

import holders.LGUHolder;

import java.util.ArrayList;
import java.util.List;

import models.Regions;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RegionListActivity extends Activity {
//	private static LguListActivity instance;
//	public static LguListActivity getInstance()
//	{
//		return instance;
//	}

	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_region_list);
//		instance=this;
		context=RegionListActivity.this;
		
		setupView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.region_list, menu);
		return true;
	}

	public void setupView()
	{
		
		List<String> itemTitles;
        ListView listView = (ListView) findViewById (R.id.list_view);
        
		//=====read tracks table and add results to listview=======
		ArrayList<models.Region> list;
		
		list=models.Regions.select("");
		itemTitles= new ArrayList<String>(list.size());

//		itemTitles.add("---"+tablelabel+"---");
        for(models.Region item:list)
        {
        	itemTitles.add(item.getName());
        }

        if (listView != null) {
            listView.setAdapter(new ArrayAdapter<String>(context,
              android.R.layout.simple_list_item_1, itemTitles));


            listView.setOnItemClickListener(new OnItemClickListener() {
    			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
    			    {
    					String selectedFromList = (parent.getItemAtPosition(position).toString());

						LGUHolder.setRegion(Regions.getByName(selectedFromList));
    						
    					finish();
    			    }});	
        
        }	   
	}
}
