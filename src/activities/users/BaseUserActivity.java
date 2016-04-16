package activities.users;
import java.text.ParseException;

import models.User;
import utils.DatePickerHelper;
import utils.MyDialogHelper;
import utils.PrettyDateHelper;
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

public class BaseUserActivity extends Activity {
	static Context context;
	static public BaseUserActivity getInstance()
	{
		return (BaseUserActivity)context;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		context=BaseUserActivity.this;
		
		setupView();
	}
	LinearLayout layoutShow,layoutEdit;
	TextView lblUsername,errUsername;
	EditText txtUsername;
	TextView lblPassword,errPassword;
	EditText txtPassword;
	TextView lblSalt,errSalt;
	EditText txtSalt;
	TextView lblFname,errFname;
	EditText txtFname;
	TextView lblMi,errMi;
	EditText txtMi;
	TextView lblLname,errLname;
	EditText txtLname;
	TextView lblBday,errBday;
	EditText txtBday;
	TextView lblProvinceId,errProvinceId;
	EditText txtProvinceId;
	TextView lblAddress,errAddress;
	EditText txtAddress;
	TextView lblIsReg,errIsReg;
	EditText txtIsReg;
	TextView lblCityId,errCityId;
	EditText txtCityId;
	TextView lblEmail,errEmail;
	EditText txtEmail;
	TextView lblPhone,errPhone;
	EditText txtPhone;
	EditText edittexts[]=new EditText[User.fields.length];
	TextView errortexts[]=new TextView[User.fields.length];
	protected void setupView()
	{
		layoutShow = (LinearLayout) findViewById (R.id.layoutShow);
		layoutEdit = (LinearLayout) findViewById (R.id.layoutEdit);

		lblUsername = (TextView) findViewById (R.id.lblUsername);
		errUsername = (TextView) findViewById (R.id.errUsername);
		txtUsername = (EditText) findViewById (R.id.txtUsername);
		lblPassword = (TextView) findViewById (R.id.lblPassword);
		errPassword = (TextView) findViewById (R.id.errPassword);
		txtPassword = (EditText) findViewById (R.id.txtPassword);
		lblSalt = (TextView) findViewById (R.id.lblSalt);
		errSalt = (TextView) findViewById (R.id.errSalt);
		txtSalt = (EditText) findViewById (R.id.txtSalt);
		lblFname = (TextView) findViewById (R.id.lblFname);
		errFname = (TextView) findViewById (R.id.errFname);
		txtFname = (EditText) findViewById (R.id.txtFname);
		lblMi = (TextView) findViewById (R.id.lblMi);
		errMi = (TextView) findViewById (R.id.errMi);
		txtMi = (EditText) findViewById (R.id.txtMi);
		lblLname = (TextView) findViewById (R.id.lblLname);
		errLname = (TextView) findViewById (R.id.errLname);
		txtLname = (EditText) findViewById (R.id.txtLname);
		lblBday = (TextView) findViewById (R.id.lblBday);
		errBday = (TextView) findViewById (R.id.errBday);
		txtBday = (EditText) findViewById (R.id.txtBday);
		lblProvinceId = (TextView) findViewById (R.id.lblProvinceId);
		errProvinceId = (TextView) findViewById (R.id.errProvinceId);
		txtProvinceId = (EditText) findViewById (R.id.txtProvinceId);
		lblAddress = (TextView) findViewById (R.id.lblAddress);
		errAddress = (TextView) findViewById (R.id.errAddress);
		txtAddress = (EditText) findViewById (R.id.txtAddress);
		lblIsReg = (TextView) findViewById (R.id.lblIsReg);
		errIsReg = (TextView) findViewById (R.id.errIsReg);
		txtIsReg = (EditText) findViewById (R.id.txtIsReg);
		lblCityId = (TextView) findViewById (R.id.lblCityId);
		errCityId = (TextView) findViewById (R.id.errCityId);
		txtCityId = (EditText) findViewById (R.id.txtCityId);
		lblEmail = (TextView) findViewById (R.id.lblEmail);
		errEmail = (TextView) findViewById (R.id.errEmail);
		txtEmail = (EditText) findViewById (R.id.txtEmail);
		lblPhone = (TextView) findViewById (R.id.lblPhone);
		errPhone = (TextView) findViewById (R.id.errPhone);
		txtPhone = (EditText) findViewById (R.id.txtPhone);
		
		//these fields use EditTexts
		edittexts[1]=txtUsername;//String
		edittexts[2]=txtPassword;//String
		edittexts[3]=txtSalt;//String
		edittexts[4]=txtFname;//String
		edittexts[5]=txtMi;//String
		edittexts[6]=txtLname;//String
		edittexts[7]=txtBday;//Date
		edittexts[8]=txtProvinceId;//Integer
		edittexts[9]=txtAddress;//String
		edittexts[10]=txtIsReg;//Integer
		edittexts[11]=txtCityId;//Integer
		edittexts[12]=txtEmail;//String
		edittexts[13]=txtPhone;//String

		errortexts[1]=errUsername;//String
		errortexts[2]=errPassword;//String
		errortexts[3]=errSalt;//String
		errortexts[4]=errFname;//String
		errortexts[5]=errMi;//String
		errortexts[6]=errLname;//String
		errortexts[7]=errBday;//Date
		errortexts[8]=errProvinceId;//Integer
		errortexts[9]=errAddress;//String
		errortexts[10]=errIsReg;//Integer
		errortexts[11]=errCityId;//Integer
		errortexts[12]=errEmail;//String
		errortexts[13]=errPhone;//String

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
		String message="This will delete the enire user. Are you sure?";
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
		setItem(new User());
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
		
		for(int i=1;i<User.fields.length;i++)
		{
			//if errors are found, 
			//show them using the ErrorTexts 
			if(errormsgs[i]!=null)
			{
				errortexts[i].setText(User.fieldlabels[i]+": "+errormsgs[i]);
				errortexts[i].setTextColor(Color.parseColor("#ff0000"));				errorsfound=true;
			}
			//else hide the ErrorTexts
			else
			{
				errortexts[i].setText(User.fieldlabels[i]+": ");
				errortexts[i].setTextColor(Color.parseColor("#000000"));			}
		}
		//If errors exist, cancel save
		if(errorsfound)
		{
			return;
		}

		item.setUsername(txtUsername.getText().toString());
		item.setPassword(txtPassword.getText().toString());
		item.setSalt(txtSalt.getText().toString());
		item.setFname(txtFname.getText().toString());
		item.setMi(txtMi.getText().toString());
		item.setLname(txtLname.getText().toString());
		try {
			if(!txtBday.getText().toString().isEmpty())
			{
				item.setBday(PrettyDateHelper.toDate(txtBday.getText().toString()));
			}
		} catch (ParseException e) {
			MyDialogHelper.popup(context, "Invalid date: "+txtBday.getText().toString());
			return;
		}//Date
		item.setProvinceId(txtProvinceId.getText().toString().isEmpty()?null:Integer.valueOf(txtProvinceId.getText().toString()));
		item.setAddress(txtAddress.getText().toString());
		item.setIsReg(txtIsReg.getText().toString().isEmpty()?null:Integer.valueOf(txtIsReg.getText().toString()));
		item.setCityId(txtCityId.getText().toString().isEmpty()?null:Integer.valueOf(txtCityId.getText().toString()));
		item.setEmail(txtEmail.getText().toString());
		item.setPhone(txtPhone.getText().toString());
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
		lblUsername.setText(item.getUsername()==null?"":item.getUsername().toString());
		lblPassword.setText(item.getPassword()==null?"":item.getPassword().toString());
		lblSalt.setText(item.getSalt()==null?"":item.getSalt().toString());
		lblFname.setText(item.getFname()==null?"":item.getFname().toString());
		lblMi.setText(item.getMi()==null?"":item.getMi().toString());
		lblLname.setText(item.getLname()==null?"":item.getLname().toString());
		lblBday.setText(item.getBday()==null?"":PrettyDateHelper.toString(item.getBday()));
		lblProvinceId.setText(item.getProvinceId()==null?"":item.getProvinceId().toString());
		lblAddress.setText(item.getAddress()==null?"":item.getAddress().toString());
		lblIsReg.setText(item.getIsReg()==null?"":item.getIsReg().toString());
		lblCityId.setText(item.getCityId()==null?"":item.getCityId().toString());
		lblEmail.setText(item.getEmail()==null?"":item.getEmail().toString());
		lblPhone.setText(item.getPhone()==null?"":item.getPhone().toString());
	}
	private void populateEditMode()
	{
		txtUsername.setText(item.getUsername()==null?"":item.getUsername().toString());
		txtPassword.setText(item.getPassword()==null?"":item.getPassword().toString());
		txtSalt.setText(item.getSalt()==null?"":item.getSalt().toString());
		txtFname.setText(item.getFname()==null?"":item.getFname().toString());
		txtMi.setText(item.getMi()==null?"":item.getMi().toString());
		txtLname.setText(item.getLname()==null?"":item.getLname().toString());
		txtBday.setText(item.getBday()==null?"":PrettyDateHelper.toString(item.getBday()));
		txtBday.setFocusable(false);
		txtBday.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DatePickerHelper.genDatePicker(context,txtBday).show();
			}
		});

		txtProvinceId.setText(item.getProvinceId()==null?"":item.getProvinceId().toString());
		txtAddress.setText(item.getAddress()==null?"":item.getAddress().toString());
		txtIsReg.setText(item.getIsReg()==null?"":item.getIsReg().toString());
		txtCityId.setText(item.getCityId()==null?"":item.getCityId().toString());
		txtEmail.setText(item.getEmail()==null?"":item.getEmail().toString());
		txtPhone.setText(item.getPhone()==null?"":item.getPhone().toString());
	}

	static User item=null;

	public static User getItem() {
		return item;
	}
	public static void setItem(User _item) {
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
		return null;//ValidationHelper.validateAndGetErrorMsg(edittexts, User.tablename, User.fields, User.datatypes, User.fieldvalidations);
	}
}