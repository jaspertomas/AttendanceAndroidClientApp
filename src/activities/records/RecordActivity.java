package activities.records;

import android.os.Bundle;

import com.itforhumanity.attendance.R;

public class RecordActivity extends BaseRecordActivity {
	static public RecordActivity getInstance()
	{
		return (RecordActivity)context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		context=RecordActivity.this;
		
		setupView();
	}
	@Override
	protected String[] validateAndGetErrorMsg(){
		String errormsg="";//temporary placeholder variable
		
		//run standard validation process
		String[] errormsgs=super.validateAndGetErrorMsg();		

		//custom validations here 
		//add your error messages to variable errormsgs 
		/*for example:
		if(txtName.getText().toString().isEmpty())
		{
			errormsg="Name cannot be empty";
			//add errmsg properly to list of errors for field 'name'
			//multiple error messages are separated by newline character
			errormsgs[Classname.NAME]=(errormsgs[i]==null ? errormsg : errormsgs[i]+"\n"+errormsg);
		}
		*/
		return errormsgs;
	}
}