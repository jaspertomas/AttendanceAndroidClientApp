package activities.records;
import java.text.ParseException;

import models.Record;
import utils.DateTimePickerHelper;
import utils.MyDialogHelper;
import utils.PrettyDateTimeHelper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.intelimina.pollwatcher.R;

public class BaseRecordActivity extends Activity {
	static Context context;
	static public BaseRecordActivity getInstance()
	{
		return (BaseRecordActivity)context;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		context=BaseRecordActivity.this;
		
		setupView();
	}
	LinearLayout layoutShow,layoutEdit;
	TextView lblSfGuardUserId,errSfGuardUserId;
	EditText txtSfGuardUserId;
	TextView lblDatetime,errDatetime;
	EditText txtDatetime;
	TextView lblRecordType,errRecordType;
	EditText txtRecordType;
	TextView lblDescription,errDescription;
	EditText txtDescription;
	TextView lblNotes,errNotes;
	EditText txtNotes;
	TextView lblFilename,errFilename;
	EditText txtFilename;
	EditText edittexts[]=new EditText[Record.fields.length];
	TextView errortexts[]=new TextView[Record.fields.length];
	protected void setupView()
	{
		layoutShow = (LinearLayout) findViewById (R.id.layoutShow);
		layoutEdit = (LinearLayout) findViewById (R.id.layoutEdit);

		lblSfGuardUserId = (TextView) findViewById (R.id.lblSfGuardUserId);
		errSfGuardUserId = (TextView) findViewById (R.id.errSfGuardUserId);
		txtSfGuardUserId = (EditText) findViewById (R.id.txtSfGuardUserId);
		lblDatetime = (TextView) findViewById (R.id.lblDatetime);
		errDatetime = (TextView) findViewById (R.id.errDatetime);
		txtDatetime = (EditText) findViewById (R.id.txtDatetime);
		lblRecordType = (TextView) findViewById (R.id.lblRecordType);
		errRecordType = (TextView) findViewById (R.id.errRecordType);
		txtRecordType = (EditText) findViewById (R.id.txtRecordType);
		lblDescription = (TextView) findViewById (R.id.lblDescription);
		errDescription = (TextView) findViewById (R.id.errDescription);
		txtDescription = (EditText) findViewById (R.id.txtDescription);
		lblNotes = (TextView) findViewById (R.id.lblNotes);
		errNotes = (TextView) findViewById (R.id.errNotes);
		txtNotes = (EditText) findViewById (R.id.txtNotes);
		lblFilename = (TextView) findViewById (R.id.lblFilename);
		errFilename = (TextView) findViewById (R.id.errFilename);
		txtFilename = (EditText) findViewById (R.id.txtFilename);
		
		//these fields use EditTexts
		edittexts[1]=txtSfGuardUserId;//Integer
		edittexts[2]=txtDatetime;//DateTime
		edittexts[3]=txtRecordType;//String
		edittexts[4]=txtDescription;//String
		edittexts[5]=txtNotes;//String
		edittexts[6]=txtFilename;//String

		errortexts[1]=errSfGuardUserId;//Integer
		errortexts[2]=errDatetime;//DateTime
		errortexts[3]=errRecordType;//String
		errortexts[4]=errDescription;//String
		errortexts[5]=errNotes;//String
		errortexts[6]=errFilename;//String

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
		String message="This will delete the enire record. Are you sure?";
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
		setItem(new Record());
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
		
		for(int i=1;i<Record.fields.length;i++)
		{
			//if errors are found, 
			//show them using the ErrorTexts 
			if(errormsgs[i]!=null)
			{
				errortexts[i].setText(Record.fieldlabels[i]+": "+errormsgs[i]);
				errortexts[i].setTextColor(Color.parseColor("#ff0000"));				errorsfound=true;
			}
			//else hide the ErrorTexts
			else
			{
				errortexts[i].setText(Record.fieldlabels[i]+": ");
				errortexts[i].setTextColor(Color.parseColor("#000000"));			}
		}
		//If errors exist, cancel save
		if(errorsfound)
		{
			return;
		}

		item.setSfGuardUserId(txtSfGuardUserId.getText().toString().isEmpty()?null:Integer.valueOf(txtSfGuardUserId.getText().toString()));
		try {
			if(!txtDatetime.getText().toString().isEmpty())
			{
				item.setDatetime(PrettyDateTimeHelper.toDate(txtDatetime.getText().toString()));
			}
		} catch (ParseException e) {
			MyDialogHelper.popup(context, "Invalid date/time: "+txtDatetime.getText().toString());
			return;
		}//DateTime
		item.setRecordType(txtRecordType.getText().toString());
		item.setDescription(txtDescription.getText().toString());
		item.setNotes(txtNotes.getText().toString());
		item.setFilename(txtFilename.getText().toString());
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
		lblSfGuardUserId.setText(item.getSfGuardUserId()==null?"":item.getSfGuardUserId().toString());
		lblDatetime.setText(item.getDatetime()==null?"":PrettyDateTimeHelper.toString(item.getDatetime()));
		lblRecordType.setText(item.getRecordType()==null?"":item.getRecordType().toString());
		lblDescription.setText(item.getDescription()==null?"":item.getDescription().toString());
		lblNotes.setText(item.getNotes()==null?"":item.getNotes().toString());
		lblFilename.setText(item.getFilename()==null?"":item.getFilename().toString());
	}
	private void populateEditMode()
	{
		txtSfGuardUserId.setText(item.getSfGuardUserId()==null?"":item.getSfGuardUserId().toString());
		txtDatetime.setText(item.getDatetime()==null?"":PrettyDateTimeHelper.toString(item.getDatetime()));
		txtDatetime.setFocusable(false);
		txtDatetime.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DateTimePickerHelper.genDatePicker(context,txtDatetime).show();
			}
		});

		txtRecordType.setText(item.getRecordType()==null?"":item.getRecordType().toString());
		txtDescription.setText(item.getDescription()==null?"":item.getDescription().toString());
		txtNotes.setText(item.getNotes()==null?"":item.getNotes().toString());
		txtFilename.setText(item.getFilename()==null?"":item.getFilename().toString());
	}

	static Record item=null;

	public static Record getItem() {
		return item;
	}
	public static void setItem(Record _item) {
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
		return null;//ValidationHelper.validateAndGetErrorMsg(edittexts, Record.tablename, Record.fields, Record.datatypes, Record.fieldvalidations);
	}
}