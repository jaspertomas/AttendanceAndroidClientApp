package com.intelimina.pollwatcher;

import holders.LGUHolder;

import java.util.ArrayList;
import java.util.List;

import utils.MyDialogHelper;

import models.Lgus;
import models.Region;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LguListActivity extends Activity {
//	private static LguListActivity instance;
//	public static LguListActivity getInstance()
//	{
//		return instance;
//	}

	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lgu_list);
//		instance=this;
		context=LguListActivity.this;
		
		setupView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lgu_list, menu);
		return true;
	}

	List<String> itemTitles;
	public void setupView()
	{
		
        ListView listView = (ListView) findViewById (R.id.list_view);
        
		//=====read tracks table and add results to listview=======
		ArrayList<models.Lgu> list;
		Region region=LGUHolder.getRegion();
		Integer region_id=0;
		if(region!=null)region_id=region.getId();
		
		if(getIntent().getAction().contentEquals("province"))
			//4 means province
			list=models.Lgus.select(" where type =4 and (region_id="+region_id+" or region_id is null) order by name");
		else
			list=models.Lgus.select(" where type !=4 and (region_id="+region_id+" or region_id is null) order by name");
		itemTitles= new ArrayList<String>(list.size());

//		itemTitles.add("---"+tablelabel+"---");
        for(models.Lgu item:list)
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

    					if(getIntent().getAction().contentEquals("province"))
    						LGUHolder.setProvince(Lgus.getByName(selectedFromList));
    					else
    						LGUHolder.setCity(Lgus.getByName(selectedFromList));
    						
    					finish();
    			    }});	
        
        }	   
	}
	@Override
	protected void onStart() {
		super.onStart();
		//if list contains no items, it's probably because a region has not been chosen yet.
		if(itemTitles.size()==0)
			MyDialogHelper.popup(context, "Please choose a region first");
	}

}
