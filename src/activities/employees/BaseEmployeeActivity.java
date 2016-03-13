package activities.employees;
import models.Employee;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itforhumanity.attendance.R;

public class BaseEmployeeActivity extends Activity {
	static Context context;
	static public BaseEmployeeActivity getInstance()
	{
		return (BaseEmployeeActivity)context;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.employee, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee);
		context=BaseEmployeeActivity.this;
		
		setupView();
	}
	LinearLayout layoutShow,layoutEdit;
	TextView lblName,errName;
	EditText txtName;
	EditText edittexts[]=new EditText[Employee.fields.length];
	TextView errortexts[]=new TextView[Employee.fields.length];
	protected void setupView()
	{
		layoutShow = (LinearLayout) findViewById (R.id.layoutShow);
		layoutEdit = (LinearLayout) findViewById (R.id.layoutEdit);

		lblName = (TextView) findViewById (R.id.lblName);
		errName = (TextView) findViewById (R.id.errName);
		txtName = (EditText) findViewById (R.id.txtName);
		
		//these fields use EditTexts
		edittexts[1]=txtName;//String

		errortexts[1]=errName;//String

		initializeData();
	}
	protected void initializeData()
	{
		populateShowMode();
		populateEditMode();
		
		if(isOperationEdit())
		{
			setEditMode();
		}
	}
	
	public void edit(View button){edit();}
	public void edit()
	{
		setEditMode();
	}
	public void delete(View button){delete();}
	public void delete()
	{
		String message="This will delete the enire employee. Are you sure?";
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(message);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
				item.delete();
				finish();
		    }
		});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    }
		});

		builder.show();		
		
	}
	public void save(View button){
		save();
	}
	//cancel edit
	public void cancel(View button){
		setShowMode();
	}
	//return to list
	public void back(View button){
		finish();
	}
	public void addNew(View button){
		setItem(new Employee());
		initializeData();
	}
	public void saveAndAddNew(View button){
		save();
		addNew(button);
	}
	public void save()
	{
		//validate
		Boolean errorsfound=false;
		String[] errormsgs=validateAndGetErrorMsg();
		
		for(int i=1;i<Employee.fields.length;i++)
		{
			//if errors are found, 
			//show them using the ErrorTexts 
			if(errormsgs[i]!=null)
			{
				errortexts[i].setText(Employee.fieldlabels[i]+": "+errormsgs[i]);
				errortexts[i].setTextColor(Color.parseColor("#ff0000"));				errorsfound=true;
			}
			//else hide the ErrorTexts
			else
			{
				errortexts[i].setText(Employee.fieldlabels[i]+": ");
				errortexts[i].setTextColor(Color.parseColor("#000000"));			}
		}
		//If errors exist, cancel save
		if(errorsfound)
		{
			return;
		}

		item.setName(txtName.getText().toString());
		item.save();
		populateShowMode();
		setShowMode();
	}
	public void setShowMode()
	{
		layoutShow.setVisibility(LinearLayout.VISIBLE);
		layoutEdit.setVisibility(LinearLayout.GONE);
	}
	public void setEditMode()
	{
		layoutEdit.setVisibility(LinearLayout.VISIBLE);
		layoutShow.setVisibility(LinearLayout.GONE);
	}
	private void populateShowMode()
	{
		lblName.setText(item.getName()==null?"":item.getName().toString());
	}
	private void populateEditMode()
	{
		txtName.setText(item.getName()==null?"":item.getName().toString());
	}

	static Employee item=null;

	public static Employee getItem() {
		return item;
	}
	public static void setItem(Employee _item) {
		item = _item;
	}

	static Integer operation=0;
	static final Integer SHOW=0;
	static final Integer EDIT=2;

	public static boolean isOperationEdit() {
		return operation == EDIT;
	}
	public static boolean isOperationShow() {
		return operation == SHOW;
	}

	public static void setOperationEdit() {
		operation = EDIT;
	}
	public static void setOperationShow() {
		operation = SHOW;
	}
	protected String[] validateAndGetErrorMsg()
	{
		return null;//ValidationHelper.validateAndGetErrorMsg(edittexts, Employee.tablename, Employee.fields, Employee.datatypes, Employee.fieldvalidations);
	}
}