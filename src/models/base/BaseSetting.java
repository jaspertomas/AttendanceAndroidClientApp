
package models.base;

import java.util.ArrayList;
import java.util.Date;

import models.Setting;
import models.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import utils.DateHelper;
import utils.ModelHelper;
import android.database.Cursor;

public class BaseSetting extends Entity{
	//------------FIELDS-----------
	public static final String tablename="settings";
	//field names
	public static final String[] fields={
		"id"
		,"name"
		,"value"
		,"priority"
			};
	//field types
	public static final String[] fieldtypes={
		"int(11)"
		,"varchar(50)"
		,"varchar(50)"
		,"int(11)"
			};
	//data types
	public static final Integer[] datatypes={
		ModelHelper.INTEGER
		,ModelHelper.STRING
		,ModelHelper.STRING
		,ModelHelper.INTEGER
			};
		//field validations
	//sample:
	//ModelHelper.REQUIRED,
	//ModelHelper.UNIQUE,
	//ModelHelper.REQUIRED+ModelHelper.UNIQUE,
	public static final Integer[] fieldvalidations={
		0//id
		,0//name
		,0//value
		,0//priority
			};
	//field positions
	public static final int ID=0;
	public static final int NAME=1;
	public static final int VALUE=2;
	public static final int PRIORITY=3;
	//field labels
	public static final String[] fieldlabels={
		"Id"
		,"Name"
		,"Value"
		,"Priority"
			};
	protected static final ModelHelper modelhelper=new ModelHelper(tablename,fields,fieldtypes,datatypes,fieldvalidations,fieldlabels);
	//-----------------------


	public BaseSetting() {
	}
	public BaseSetting(JSONObject values) {
		if(values==null)try{throw new Entity.NullValuesAssignmentException();}catch(Entity.NullValuesAssignmentException e){e.printStackTrace();}
		this.values=values;
	}
	public BaseSetting(Cursor cursor) {
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

	public String getName() {
		return modelhelper.jsonGetString(values, "name");
	}
	public void setName(String name) {
		modelhelper.jsonPutString(values, "name", name);
	}

	public String getValue() {
		return modelhelper.jsonGetString(values, "value");
	}
	public void setValue(String value) {
		modelhelper.jsonPutString(values, "value", value);
	}

	public Integer getPriority() {
		return modelhelper.jsonGetInteger(values, "priority");
	}
	public void setPriority(Integer priority) {
		modelhelper.jsonPutInteger(values, "priority", priority);
	}

	@Override
	public JSONObject getValues() {
		return values;
	}
	public static void insert(JSONObject json) {
		modelhelper.insert(json);
	};	//static database methods
	public static ArrayList<Setting> select(String criteria) {
		ArrayList<Setting> list=new ArrayList<Setting>();
		for(JSONObject json:modelhelper.select(criteria))
			list.add(new Setting(json));
		return list;
	}
	public static Setting selectOne(String criteria) {
		JSONObject json=modelhelper.selectOne(criteria);
		if(json==null)return null;
		else return new Setting(json);
	}
	public static Setting getById(Integer id) {
		return new Setting(modelhelper.getById(id));
	}
	public static Setting getByName(String name) {
        return selectOne(" where name = '"+name+"'");
	}
	public static Integer count(String criteria) {
		return modelhelper.count(criteria);
	}
	public static Integer getLastInsertId() {
		return modelhelper.getLastInsertId();
	}	public static void delete(Setting item) {
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
		return getName().toString();
	}
	public static void deleteAll() {
		modelhelper.deleteAll();
	};	
}
