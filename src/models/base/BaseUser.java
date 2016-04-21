
package models.base;

import java.util.ArrayList;
import java.util.Date;

import models.User;
import models.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import utils.DateHelper;
import utils.ModelHelper;
import android.database.Cursor;

public class BaseUser extends Entity{
	//------------FIELDS-----------
	public static final String tablename="users";
	//field names
	public static final String[] fields={
		"id"
		,"username"
		,"password"
		,"salt"
		,"fname"
		,"mi"
		,"lname"
		,"bday"
		,"province_id"
		,"address"
		,"is_reg"
		,"city_id"
		,"email"
		,"phone"
		,"sf_guard_user_id"
			};
	//field types
	public static final String[] fieldtypes={
		"int(11)"
		,"varchar(50)"
		,"varchar(50)"
		,"varchar(50)"
		,"varchar(100)"
		,"varchar(1)"
		,"varchar(100)"
		,"varchar(10)"
		,"int(11)"
		,"varchar(255)"
		,"int(11)"
		,"int(11)"
		,"varchar(100)"
		,"varchar(100)"
		,"int(11)"
			};
	//data types
	public static final Integer[] datatypes={
		ModelHelper.INTEGER
		,ModelHelper.STRING
		,ModelHelper.STRING
		,ModelHelper.STRING
		,ModelHelper.STRING
		,ModelHelper.STRING
		,ModelHelper.STRING
		,ModelHelper.DATE
		,ModelHelper.INTEGER
		,ModelHelper.STRING
		,ModelHelper.INTEGER
		,ModelHelper.INTEGER
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
		,0//username
		,0//password
		,0//salt
		,0//fname
		,0//mi
		,0//lname
		,0//bday
		,0//province_id
		,0//address
		,0//is_reg
		,0//city_id
		,0//email
		,0//phone
		,0//sf_guard_user_id
			};
	//field positions
	public static final int ID=0;
	public static final int USERNAME=1;
	public static final int PASSWORD=2;
	public static final int SALT=3;
	public static final int FNAME=4;
	public static final int MI=5;
	public static final int LNAME=6;
	public static final int BDAY=7;
	public static final int PROVINCE_ID=8;
	public static final int ADDRESS=9;
	public static final int IS_REG=10;
	public static final int CITY_ID=11;
	public static final int EMAIL=12;
	public static final int PHONE=13;
	public static final int SF_GUARD_USER_ID=14;
	//field labels
	public static final String[] fieldlabels={
		"Id"
		,"Username"
		,"Password"
		,"Salt"
		,"Fname"
		,"Mi"
		,"Lname"
		,"Bday"
		,"Province Id"
		,"Address"
		,"Is Reg"
		,"City Id"
		,"Email"
		,"Phone"
		,"Sf Guard User Id"
			};
	protected static final ModelHelper modelhelper=new ModelHelper(tablename,fields,fieldtypes,datatypes,fieldvalidations,fieldlabels);
	//-----------------------


	public BaseUser() {
	}
	public BaseUser(JSONObject values) {
		if(values==null)try{throw new Entity.NullValuesAssignmentException();}catch(Entity.NullValuesAssignmentException e){e.printStackTrace();}
		this.values=values;
	}
	public BaseUser(Cursor cursor) {
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

	public String getUsername() {
		return modelhelper.jsonGetString(values, "username");
	}
	public void setUsername(String username) {
		modelhelper.jsonPutString(values, "username", username);
	}

	public String getPassword() {
		return modelhelper.jsonGetString(values, "password");
	}
	public void setPassword(String password) {
		modelhelper.jsonPutString(values, "password", password);
	}

	public String getSalt() {
		return modelhelper.jsonGetString(values, "salt");
	}
	public void setSalt(String salt) {
		modelhelper.jsonPutString(values, "salt", salt);
	}

	public String getFname() {
		return modelhelper.jsonGetString(values, "fname");
	}
	public void setFname(String fname) {
		modelhelper.jsonPutString(values, "fname", fname);
	}

	public String getMi() {
		return modelhelper.jsonGetString(values, "mi");
	}
	public void setMi(String mi) {
		modelhelper.jsonPutString(values, "mi", mi);
	}

	public String getLname() {
		return modelhelper.jsonGetString(values, "lname");
	}
	public void setLname(String lname) {
		modelhelper.jsonPutString(values, "lname", lname);
	}

	public Date getBday() {
		return modelhelper.jsonGetDate(values, "bday");
	}
	public void setBday(Date bday) {
		modelhelper.jsonPutDate(values, "bday", bday);
	}

	public Integer getProvinceId() {
		return modelhelper.jsonGetInteger(values, "province_id");
	}
	public void setProvinceId(Integer province_id) {
		modelhelper.jsonPutInteger(values, "province_id", province_id);
	}

	public String getAddress() {
		return modelhelper.jsonGetString(values, "address");
	}
	public void setAddress(String address) {
		modelhelper.jsonPutString(values, "address", address);
	}

	public Integer getIsReg() {
		return modelhelper.jsonGetInteger(values, "is_reg");
	}
	public void setIsReg(Integer is_reg) {
		modelhelper.jsonPutInteger(values, "is_reg", is_reg);
	}

	public Integer getCityId() {
		return modelhelper.jsonGetInteger(values, "city_id");
	}
	public void setCityId(Integer city_id) {
		modelhelper.jsonPutInteger(values, "city_id", city_id);
	}

	public String getEmail() {
		return modelhelper.jsonGetString(values, "email");
	}
	public void setEmail(String email) {
		modelhelper.jsonPutString(values, "email", email);
	}

	public String getPhone() {
		return modelhelper.jsonGetString(values, "phone");
	}
	public void setPhone(String phone) {
		modelhelper.jsonPutString(values, "phone", phone);
	}

	public Integer getSfGuardUserId() {
		return modelhelper.jsonGetInteger(values, "sf_guard_user_id");
	}
	public void setSfGuardUserId(Integer sf_guard_user_id) {
		modelhelper.jsonPutInteger(values, "sf_guard_user_id", sf_guard_user_id);
	}

	@Override
	public JSONObject getValues() {
		return values;
	}
	public static void insert(JSONObject json) {
		modelhelper.insert(json);
	};	//static database methods
	public static ArrayList<User> select(String criteria) {
		ArrayList<User> list=new ArrayList<User>();
		for(JSONObject json:modelhelper.select(criteria))
			list.add(new User(json));
		return list;
	}
	public static User selectOne(String criteria) {
		JSONObject json=modelhelper.selectOne(criteria);
		if(json==null)return null;
		else return new User(json);
	}
	public static User getById(Integer id) {
        JSONObject result=modelhelper.getById(id);
        if(result!=null)return new User(result);
        else return null;
	}
	public static User getByUserId(Integer id) {
        JSONObject result=modelhelper.selectOne(" where user_id="+id.toString());
        if(result!=null)return new User(result);
        else return null;
	}
	public static User getByUsername(String name) {
        JSONObject result=modelhelper.selectOne(" where username=\""+name+"\"");
        if(result!=null)return new User(result);
        else return null;
    }
	public static Integer count(String criteria) {
		return modelhelper.count(criteria);
	}
	public static Integer getLastInsertId() {
		return modelhelper.getLastInsertId();
	}	public static void delete(User item) {
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
		return getFname()+" "+getLname()+" ("+getUsername()+")";
	}
	public static void deleteAll() {
		modelhelper.deleteAll();
	};	
}
