
package models.base;

import java.util.ArrayList;
import java.util.Date;

import models.Employee;
import models.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import utils.DateHelper;
import utils.ModelHelper;
import android.database.Cursor;

public class BaseEmployee extends Entity{
	//------------FIELDS-----------
	public static final String tablename="employees";
	//field names
	public static final String[] fields={
		"id"
		,"name"
			};
	//field types
	public static final String[] fieldtypes={
		"int(11)"
		,"varchar(25)"
			};
	//data types
	public static final Integer[] datatypes={
		ModelHelper.INTEGER
		,ModelHelper.STRING
			};
		//field validations
	//sample:
	//ModelHelper.REQUIRED,
	//ModelHelper.UNIQUE,
	//ModelHelper.REQUIRED+ModelHelper.UNIQUE,
	public static final Integer[] fieldvalidations={
		0//id
		,0//name
			};
	//field positions
	public static final int ID=0;
	public static final int NAME=1;
	//field labels
	public static final String[] fieldlabels={
		"Id"
		,"Name"
			};
	protected static final ModelHelper modelhelper=new ModelHelper(tablename,fields,fieldtypes,datatypes,fieldvalidations,fieldlabels);
	//-----------------------


	public BaseEmployee() {
	}
	public BaseEmployee(JSONObject values) {
		if(values==null)try{throw new Entity.NullValuesAssignmentException();}catch(Entity.NullValuesAssignmentException e){e.printStackTrace();}
		this.values=values;
	}
	public BaseEmployee(Cursor cursor) {
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

	@Override
	public JSONObject getValues() {
		return values;
	}
	public static void insert(JSONObject json) {
		modelhelper.insert(json);
	};	//static database methods
	public static ArrayList<Employee> select(String criteria) {
		ArrayList<Employee> list=new ArrayList<Employee>();
		for(JSONObject json:modelhelper.select(criteria))
			list.add(new Employee(json));
		return list;
	}
	public static Employee selectOne(String criteria) {
		JSONObject json=modelhelper.selectOne(criteria);
		if(json==null)return null;
		else return new Employee(json);
	}
	public static Employee getById(Integer id) {
		return new Employee(modelhelper.getById(id));
	}
	public static Integer count(String criteria) {
		return modelhelper.count(criteria);
	}
	public static Integer getLastInsertId() {
		return modelhelper.getLastInsertId();
	}	public static void delete(Employee item) {
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
