package converters;

import java.util.ArrayList;

import models.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EmployeeFromJsonConverter {
	public static Employee convert(JSONObject json)
	{
		Employee employee=new Employee();
		try {
			if(json.has("id")&&!json.isNull("id"))employee.setId(json.getInt("id"));
			if(json.has("name")&&!json.isNull("name"))employee.setName(json.getString("name"));
			return employee;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList<Employee> convertList(JSONArray json) {
		try {
			ArrayList<Employee> list = new ArrayList<Employee>();
			for (int i = 0; i < json.length(); i++) {
				list.add(EmployeeFromJsonConverter.convert(json.getJSONObject(i)));
			}
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
