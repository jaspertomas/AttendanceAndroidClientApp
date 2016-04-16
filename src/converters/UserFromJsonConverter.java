package converters;

import models.User;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.DateHelper;
import utils.DateTimeHelper;

public class UserFromJsonConverter {
	public static User convert(JSONObject json)
	{
		User user=new User();
		try {
			if(json.has("id")&&!json.isNull("id"))user.setId(json.getInt("id"));
			if(json.has("username")&&!json.isNull("username"))user.setUsername(json.getString("username"));
			if(json.has("password")&&!json.isNull("password"))user.setPassword(json.getString("password"));
			if(json.has("salt")&&!json.isNull("salt"))user.setSalt(json.getString("salt"));
			if(json.has("fname")&&!json.isNull("fname"))user.setFname(json.getString("fname"));
			if(json.has("mi")&&!json.isNull("mi"))user.setMi(json.getString("mi"));
			if(json.has("lname")&&!json.isNull("lname"))user.setLname(json.getString("lname"));
			if(json.has("bday")&&!json.isNull("bday"))user.setBday(DateHelper.toDate(json.getString("bday")));
			else user.setBday(DateHelper.getNullDate());
			if(json.has("province_id")&&!json.isNull("province_id"))user.setProvinceId(json.getInt("province_id"));
			if(json.has("address")&&!json.isNull("address"))user.setAddress(json.getString("address"));
			if(json.has("is_reg")&&!json.isNull("is_reg"))user.setIsReg(json.getInt("is_reg"));
			if(json.has("city_id")&&!json.isNull("city_id"))user.setCityId(json.getInt("city_id"));
			if(json.has("email")&&!json.isNull("email"))user.setEmail(json.getString("email"));
			if(json.has("phone")&&!json.isNull("phone"))user.setPhone(json.getString("phone"));
			return user;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList<User> convertList(JSONArray json) {
		try {
			ArrayList<User> list = new ArrayList<User>();
			for (int i = 0; i < json.length(); i++) {
				list.add(UserFromJsonConverter.convert(json.getJSONObject(i)));
			}
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
