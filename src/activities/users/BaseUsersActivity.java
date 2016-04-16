package activities.users;

import java.util.ArrayList;
import java.util.List;

import models.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.intelimina.pollwatcher.R;

public class BaseUsersActivity extends Activity {
	static Context context;
	static public BaseUsersActivity getInstance()
	{
		return (BaseUsersActivity)context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);
		context=BaseUsersActivity.this;
		
		setupView();
	}

	@Override
	protected void onStart() {
		super.onStart();
		load();
	}

	TextView lblWelcome;
	List<String> itemTitles;
    ListView listView;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.users, menu);
		return true;
	}

	//ArrayList<User> list=new ArrayList<User>();
	ArrayAdapter<String> listAdapter;
	protected void setupView()
	{
        lblWelcome = (TextView) findViewById (R.id.lblWelcome);
		
        listView = (ListView) findViewById (R.id.list);
		itemTitles= new ArrayList<String>();
        
		//=====read tracks table and add results to listview=======
		
        if (listView != null) {
        	
		listAdapter=new ArrayAdapter<String>(context,
			android.R.layout.simple_list_item_1, itemTitles);
		
		listView.setAdapter(listAdapter);


		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) 
			{
				UserActivity.setOperationShow();
				UserActivity.setItem(getItemAtPosition(position));
				Intent intent = new Intent(BaseUsersActivity.this, UserActivity.class);
				startActivity(intent);
			}});
		}
		load();
	}
	
	public void create(View button){create();}
	public void create()
	{
		UserActivity.setOperationEdit();
		UserActivity.setItem(new User());
        Intent intent = new Intent(BaseUsersActivity.this, UserActivity.class);
        startActivity(intent);
	}
	public void logout(View button){logout();}
	public void logout()
	{
	}

	public void load() {
        String criteria=" ";
        loadListItems(criteria);

		itemTitles.clear();
        for(String name:getListItemLabels())
        {
        	itemTitles.add(name);
        }
        listAdapter.notifyDataSetChanged();
        listView.invalidate();
		
        lblWelcome.setText(String.valueOf(itemTitles.size())+" items found");
	}

	public static void loadListItems(String criteria)
	{
		listItemLabels.clear();
		listItemIds.clear();
		//load ids and names into these arrays
		User.selectIdsAndNames(criteria,listItemIds,listItemLabels);
	}
	
	static ArrayList<String> listItemLabels=new ArrayList<String>();
	static ArrayList<Integer> listItemIds=new ArrayList<Integer>();

	public static ArrayList<String> getListItemLabels() {
		return listItemLabels;
	}

	public static void setListItemLabels(ArrayList<String> _listItemLabels) {
		listItemLabels = _listItemLabels;
	}

	public static ArrayList<Integer> getListItemIds() {
		return listItemIds;
	}

	public static void setListItemIds(ArrayList<Integer> _listItemIds) {
		listItemIds = _listItemIds;
	}
	public static User getItemAtPosition(Integer position) {
		return User.getById(listItemIds.get(position));
	}
	public void back(View view) {
		super.onBackPressed();
	}
//	public void onBackPressed() {
//		super.onBackPressed();
//	}
	
}