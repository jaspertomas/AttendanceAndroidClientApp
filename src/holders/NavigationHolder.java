package holders;

public class NavigationHolder {
	static Integer destination=0;
	public static final Integer ShutDown=-1;
	public static final Integer MainActivity=0;
	public static final Integer RegistrationActivity=1;
	public static final Integer Registration2Activity=2;
	public static final Integer Registration3Activity=3;
	public static final Integer Registration4Activity=4;
//	public static final Integer DashboardActivity=3;
//	public static final Integer EditProfileActivity=4;
//	public static final Integer LguListActivity=5;
//	public static final Integer LoginActivity=6;
	public static Integer getDestination() {
		return destination;
	}
	public static void setDestination(Integer destination) {
		NavigationHolder.destination = destination;
	}
	public static void reset() {
		// TODO Auto-generated method stub
		destination=-1;
	}
	
}
