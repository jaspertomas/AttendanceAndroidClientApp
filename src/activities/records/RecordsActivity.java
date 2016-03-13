package activities.records;

import android.os.Bundle;

import com.itforhumanity.attendance.R;

public class RecordsActivity extends BaseRecordsActivity {
	static public RecordsActivity getInstance()
	{
		return (RecordsActivity)context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_records);
		context=RecordsActivity.this;
		
		setupView();
	}
}