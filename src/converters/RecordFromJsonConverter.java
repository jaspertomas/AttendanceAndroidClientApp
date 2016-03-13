package converters;

import models.Record;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import utils.DateHelper;
import utils.DateTimeHelper;

public class RecordFromJsonConverter {
	public static Record convert(JSONObject json)
	{
		Record record=new Record();
		try {
			if(json.has("id")&&!json.isNull("id"))record.setId(json.getInt("id"));
			if(json.has("employee_name")&&!json.isNull("employee_name"))record.setEmployeeName(json.getString("employee_name"));
			if(json.has("datetime")&&!json.isNull("datetime"))record.setDatetime(json.getString("datetime"));
			if(json.has("filename")&&!json.isNull("filename"))record.setFilename(json.getString("filename"));
			return record;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static ArrayList<Record> convertList(JSONArray json) {
		try {
			ArrayList<Record> list = new ArrayList<Record>();
			for (int i = 0; i < json.length(); i++) {
				list.add(RecordFromJsonConverter.convert(json.getJSONObject(i)));
			}
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
