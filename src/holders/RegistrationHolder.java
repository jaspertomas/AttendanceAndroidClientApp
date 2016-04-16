package holders;

import models.Lgu;

public class RegistrationHolder {
	static Lgu city;
	public static Lgu getCity() {
		return city;
	}
	public static void setCity(Lgu city) {
		RegistrationHolder.city = city;
	}

	static Lgu province;
	public static Lgu getProvince() {
		return province;
	}
	public static void setProvince(Lgu province) {
		RegistrationHolder.province = province;
	}
	
	public static void reset()
	{
		city=null;
		province=null;
	}
	
}
