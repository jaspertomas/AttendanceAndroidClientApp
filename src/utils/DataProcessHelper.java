package utils;

import javax.security.auth.Subject;

import models.Employee;
import converters.EmployeeFromJsonConverter;

import org.json.JSONException;
import org.json.JSONObject;

public class DataProcessHelper {
	public static void process(JSONObject json) throws JSONException
	{
		Employee.deleteAll();
//		Setting.deleteAll();
					
		for(Employee item:EmployeeFromJsonConverter.convertList(json.getJSONArray("employees")))item.save();
//		for(Setting item:SettingFromJsonConverter.convertList(json.getJSONArray("settings")))item.save();
	}
}
