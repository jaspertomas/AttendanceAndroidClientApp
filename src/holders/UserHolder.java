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

	
	static User regUser=null;
	public static User getRegUser() {
		return regUser;
	}
	public static void setRegUser(User regUser) {
		UserHolder.regUser = regUser;
	}
	
}
