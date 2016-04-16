package activities.users;

import android.os.Bundle;

import com.intelimina.pollwatcher.R;

public class UsersActivity extends BaseUsersActivity {
	static public UsersActivity getInstance()
	{
		return (UsersActivity)context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_users);
		context=UsersActivity.this;
		
		setupView();
	}
}