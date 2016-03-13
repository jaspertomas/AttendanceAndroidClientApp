
package models.base;

import java.util.ArrayList;
import java.util.Date;

import models.Record;
import models.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import utils.DateHelper;
import utils.ModelHelper;
import android.database.Cursor;

public class BaseRecord extends Entity{
	//------------FIELDS-----------
	public static final String tablename="records";
	//field names
	public static final String[] fields={
		"id"
		,"employee_name"
		,"datetime"
		,"filename"
			};
	//field types
	public static final String[] fieldtypes={
		"int(11)"
		,"varchar(25)"
		,"varchar(20)"
		,"varchar(30)"
			};
	//data types
	public static final Integer[] datatypes={
		ModelHelper.INTEGER
		,ModelHelper.STRING
		,ModelHelper.STRING
		,ModelHelper.STRING
			};
		//field validations
	//sample:
	//ModelHelper.REQUIRED,
	//ModelHelper.UNIQUE,
	//ModelHelper.REQUIRED+ModelHelper.UNIQUE,
	public static final Integer[] fieldvalidations={
		0//id
		,0//employee_name
		,0//datetime
		,0//filename
			};
	//field positions
	public static final int ID=0;
	public static final int EMPLOYEE_NAME=1;
	public static final int DATETIME=2;
	public static final int FILENAME=3;
	//field labels
	public static final String[] fieldlabels={
		"Id"
		,"Employee Name"
		,"Datetime"
		,"Filename"
			};
	protected static final ModelHelper modelhelper=new ModelHelper(tablename,fields,fieldtypes,datatypes,fieldvalidations,fieldlabels);
	//-----------------------


	public BaseRecord() {
	}
	public BaseRecord(JSONObject values) {
		if(values==null)try{throw new Entity.NullValuesAssignmentException();}catch(Entity.NullValuesAssignmentException e){e.printStackTrace();}
		this.values=values;
	}
	public BaseRecord(Cursor cursor) {
		JSONObject temp=modelhelper.toJSON(cursor);
		if(temp==null)try{throw new Entity.NullValuesAssignmentException();}catch(Entity.NullValuesAssignmentException e){e.printStackTrace();}
		this.values=temp;
	}

	public Integer getId() {
		return modelhelper.jsonGetInteger(values, "id");
	}
	public void setId(Integer id) {
		modelhelper.jsonPutInteger(values, "id", id);
	}

	public String getEmployeeName() {
		return modelhelper.jsonGetString(values, "employee_name");
	}
	public void setEmployeeName(String employee_name) {
		modelhelper.jsonPutString(values, "employee_name", employee_name);
	}

	public String getDatetime() {
		return modelhelper.jsonGetString(values, "datetime");
	}
	public void setDatetime(String datetime) {
		modelhelper.jsonPutString(values, "datetime", datetime);
	}

	public String getFilename() {
		return modelhelper.jsonGetString(values, "filename");
	}
	public void setFilename(String filename) {
		modelhelper.jsonPutString(values, "filename", filename);
	}

	@Override
	public JSONObject getValues() {
		return values;
	}
	public static void insert(JSONObject json) {
		modelhelper.insert(json);
	};	//static database methods
	public static ArrayList<Record> select(String criteria) {
		ArrayList<Record> list=new ArrayList<Record>();
		for(JSONObject json:modelhelper.select(criteria))
			list.add(new Record(json));
		return list;
	}
	public static Record selectOne(String criteria) {
		JSONObject json=modelhelper.selectOne(criteria);
		if(json==null)return null;
		else return new Record(json);
	}
	public static Record getById(Integer id) {
		return new Record(modelhelper.getById(id));
	}
	public static Integer count(String criteria) {
		return modelhelper.count(criteria);
	}
	public static Integer getLastInsertId() {
		return modelhelper.getLastInsertId();
	}	public static void delete(Record item) {
		modelhelper.delete(item);
	};
	public static void delete(Integer id) {
		modelhelper.delete(id);
	};	public static void createTable()
	{
		modelhelper.createTable();
	}
	public static void deleteTable()
	{
		modelhelper.deleteTable();
	}	//database methods
	@Override
	public void insert() {
		modelhelper.insert(this);
	}
	@Override
	public void update() {
		modelhelper.update(this);
	}
	@Override
	public void delete()
	{
		modelhelper.delete(this);
	}
	@Override
	public void save()
	{
		modelhelper.save(this);
	}
	@Override
	public String toString()
	{
		return getId().toString();
	}
	public static void deleteAll() {
		modelhelper.deleteAll();
	};	
}
