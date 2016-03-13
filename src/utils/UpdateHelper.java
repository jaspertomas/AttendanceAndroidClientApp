package utils;

import models.Employee;

import org.json.JSONException;
import org.json.JSONObject;

import converters.EmployeeFromJsonConverter;

public class UpdateHelper {
	public static void process(JSONObject json) throws JSONException
	{
		Employee.deleteAll();
//		Setting.deleteAll();
					
		for(Employee item:EmployeeFromJsonConverter.convertList(json.getJSONArray("employees")))item.save();
//		for(Setting item:SettingFromJsonConverter.convertList(json.getJSONArray("settings")))item.save();
	}
}
