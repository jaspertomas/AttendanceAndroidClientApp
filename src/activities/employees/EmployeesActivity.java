package activities.employees;

import android.os.Bundle;

import com.itforhumanity.attendance.R;

public class EmployeesActivity extends BaseEmployeesActivity {
	static public EmployeesActivity getInstance()
	{
		return (EmployeesActivity)context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employees);
		context=EmployeesActivity.this;
		
		setupView();
	}
}