package holders;

public class NavigationHolder {
	static Integer destination=0;
	public static final Integer MainActivity=0;
	public static final Integer ChooseUserActivity=1;
	public static final Integer ChooseVehicleActivity=2;
	public static final Integer DashboardActivity=3;
	public static final Integer EditProfileActivity=4;
	public static final Integer LguListActivity=5;
	public static final Integer LoginActivity=6;
	public static final Integer RegistrationActivity=7;
	public static final Integer ViewUpdatesActivity=8;
	public static final Integer VehicleRegistrationActivity=9;
	public static final Integer VehicleDashboardActivity=10;
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
