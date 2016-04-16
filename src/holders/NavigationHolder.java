package holders;

public class NavigationHolder {
	static int destination=-1;
	public static final int MainActivity=0;
	public static final int RegistrationActivity=1;
	public static final int Registration2Activity=2;
	public static final int Registration3Activity=3;
	public static final int Registration4Activity=4;
	public static final int LoginActivity=6;
	public static final int ShutDown=100;
//	public static final int DashboardActivity=3;
//	public static final int EditProfileActivity=4;
//	public static final int LguListActivity=5;
	public static Integer getDestination() {
		return destination;
	}
	public static void setDestination(Integer destination) {
		NavigationHolder.destination = destination;
	}
	public static void reset() {
		destination=-1;
	}
	
}
