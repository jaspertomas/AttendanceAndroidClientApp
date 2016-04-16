package holders;

import models.User;

public class UserHolder {
	static User user=null;
	public static User getUser() {
		return user;
	}
	public static void setUser(User user) {
		UserHolder.user = user;
	}

//	static User chosenuser=null;
//	public static User getChosenUser() {
//		return chosenuser;
//	}
//	public static void setChosenUser(User user) {
//		UserHolder.chosenuser = user;
//	}
	
	public static void reset()
	{
		user=null;
//		chosenuser=null;
	}
}
