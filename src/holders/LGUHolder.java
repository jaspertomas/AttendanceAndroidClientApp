package holders;

import models.Lgu;

public class LGUHolder {
	static Lgu city;
	public static Lgu getCity() {
		return city;
	}
	public static void setCity(Lgu city) {
		LGUHolder.city = city;
	}

	static Lgu province;
	public static Lgu getProvince() {
		return province;
	}
	public static void setProvince(Lgu province) {
		LGUHolder.province = province;
	}
	
	public static void reset()
	{
		city=null;
		province=null;
	}
	
}
