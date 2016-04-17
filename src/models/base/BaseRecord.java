
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
		,"sf_guard_user_id"
		,"datetime"
		,"record_type"
		,"description"
		,"notes"
		,"filename"
			};
	//field types
	public static final String[] fieldtypes={
		"int(11)"
		,"int(11)"
		,"varchar(0)"
		,"varchar(30)"
		,"varchar(255)"
		,"varchar(255)"
		,"varchar(255)"
			};
	//data types
	public static final Integer[] datatypes={
		ModelHelper.INTEGER
		,ModelHelper.INTEGER
		,ModelHelper.DATETIME
		,ModelHelper.STRING
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
		,0//sf_guard_user_id
		,0//datetime
		,0//record_type
		,0//description
		,0//notes
		,0//filename
			};
	//field positions
	public static final int ID=0;
	public static final int SF_GUARD_USER_ID=1;
	public static final int DATETIME=2;
	public static final int RECORD_TYPE=3;
	public static final int DESCRIPTION=4;
	public static final int NOTES=5;
	public static final int FILENAME=6;
	//field labels
	public static final String[] fieldlabels={
		"Id"
		,"Sf Guard User Id"
		,"Datetime"
		,"Record Type"
		,"Description"
		,"Notes"
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

	public Integer getSfGuardUserId() {
		return modelhelper.jsonGetInteger(values, "sf_guard_user_id");
	}
	public void setSfGuardUserId(Integer sf_guard_user_id) {
		modelhelper.jsonPutInteger(values, "sf_guard_user_id", sf_guard_user_id);
	}

	public Date getDatetime() {
		return modelhelper.jsonGetDate(values, "datetime");
	}
	public void setDatetime(Date datetime) {
		modelhelper.jsonPutDate(values, "datetime", datetime);
	}

	public String getRecordType() {
		return modelhelper.jsonGetString(values, "record_type");
	}
	public void setRecordType(String record_type) {
		modelhelper.jsonPutString(values, "record_type", record_type);
	}

	public String getDescription() {
		return modelhelper.jsonGetString(values, "description");
	}
	public void setDescription(String description) {
		modelhelper.jsonPutString(values, "description", description);
	}

	public String getNotes() {
		return modelhelper.jsonGetString(values, "notes");
	}
	public void setNotes(String notes) {
		modelhelper.jsonPutString(values, "notes", notes);
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
