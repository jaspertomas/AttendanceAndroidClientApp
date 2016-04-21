package holders;

import models.Lgu;
import models.Region;

public class LGUHolder {
	static Region region;
	public static Region getRegion() {
		return region;
	}
	public static void setRegion(Region region) {
		LGUHolder.region = region;
	}

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
		region=null;
	}
	
}
