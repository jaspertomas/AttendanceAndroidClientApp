package utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import models.Entity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ModelHelper {
//	public String TABLENAME=null;
//	public Class MODELCLASS=null;
//    protected abstract String getTable();
//    protected abstract Class getModelClass();

    public String tablename;
    public String[] fields;
    public String[] fieldtypes;
    
    //data types
    public static final int INTEGER=1;
    public static final int STRING=2;
    public static final int BOOLEAN=3;
    public static final int DOUBLE=4;
    public static final int DATE=5;
    public static final int DATETIME=6;
    public static final int DECIMAL=7;
    public static final int LONG=8;
    public static final int FLOAT=9;
    public static final int SHORT=10;
    public Integer[] datatypes;
    
    //field validations
    public static final Integer REQUIRED=1;
    public static final Integer UNIQUE=2;
    public Integer[] fieldvalidations;
    
    //labels
    public String[] fieldlabels;    
    
    public ModelHelper(String tablename, String[] fields, String[] fieldtypes, Integer[] datatypes, Integer[] fieldvalidations, String[] fieldlabels)
	{
		this.tablename=tablename;
		this.fields=fields;
		this.fieldtypes=fieldtypes;
		this.datatypes=datatypes;
		this.fieldvalidations=fieldvalidations;
		this.fieldlabels=fieldlabels;
	}

    //database connection
    private static SQLiteDatabase db;
    private static void initIfNecessary()
    {
    		if(db==null)
    	        db = MyDatabaseHelper.getInstance().getWritableDatabase();
    }
    public JSONObject toJSON(Cursor c)
    {
	    	try{
	        JSONObject values=new JSONObject();
	        for(int i=0;i<fields.length;i++)
	        {
		        	switch(datatypes[i])
		        	{
		        	case INTEGER:
			            values.put(fields[i], c.getInt(c.getColumnIndex(fields[i])));//Integer
			            break;
		        	case STRING:
			            values.put(fields[i], c.getString(c.getColumnIndex(fields[i])));//Integer
			            break;
		        	case BOOLEAN:
			            values.put(fields[i], c.getShort(c.getColumnIndex(fields[i]))==1?true:false);//Integer
			            break;
		        	case DOUBLE:
		                values.put(fields[i], c.getDouble(c.getColumnIndex(fields[i])));//Integer
		                break;
		        	case DATE:
		                values.put(fields[i], DateHelper.toDate(c.getString(c.getColumnIndex(fields[i]))));//Date
		                break;
		        	case DATETIME:
		                values.put(fields[i], DateTimeHelper.toDate(c.getString(c.getColumnIndex(fields[i]))));//DateTime
		                break;
		        	case DECIMAL:
		                values.put(fields[i], BigDecimal.valueOf(c.getDouble(c.getColumnIndex(fields[i]))));//Decimal
		                break;
		        	case LONG:
			            values.put(fields[i], c.getLong(c.getColumnIndex(fields[i])));//long
			            break;
		        	case FLOAT:
		                values.put(fields[i], c.getFloat(c.getColumnIndex(fields[i])));//float
		                break;
		        	case SHORT:
		                values.put(fields[i], c.getShort(c.getColumnIndex(fields[i])));//float
		                break;
		        	}
	        }
	        return values;
	    	}catch(JSONException e){e.printStackTrace();return null;}
    }
	public ArrayList<JSONObject> select(String criteria) {
		initIfNecessary();
		ArrayList<JSONObject> items = new ArrayList<JSONObject>();
		Cursor cursor = db.rawQuery("SELECT * FROM "+tablename+" "+criteria, null);
		while (cursor.moveToNext()) {
			items.add(toJSON(cursor));
		}
		cursor.close();
		return items;
	}
	public JSONObject selectOne(String criteria){
		ArrayList<JSONObject> items=select(criteria);
		for(JSONObject item:items)return item;
		return null;
	}
	public Integer count(String criteria) {
		initIfNecessary();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM "+tablename+" "+criteria, null);
		cursor.moveToFirst();
		Integer result=cursor.getInt(0);
		cursor.close();
		return result;
	}
	public Integer getLastInsertId() {
		initIfNecessary();
		Cursor cursor = db.rawQuery("SELECT last_insert_rowid() FROM "+tablename+" ", null);
		cursor.moveToFirst();
		Integer result=cursor.getInt(0);
		cursor.close();
		return result;
	}
    public JSONObject getById(Integer id) {
        return selectOne(" where id = '"+id.toString()+"'");
    }
    public void save(Entity entity)
    {
            if(entity.getId()==null || entity.getId()==0 || getById(entity.getId())==null)
            		entity.setId(insert(entity));
            else
                update(entity);
    }
    public int insert(Entity entity) {
		initIfNecessary();
		try {
			return Long.valueOf(db.insert(tablename, null, getContentValues(entity))).intValue();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    }

    public int insert(JSONObject attributes) {
    		initIfNecessary();
        try {
			return Long.valueOf(db.insert(tablename, null, getContentValues(attributes))).intValue();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    }

    public int update(String id, JSONObject attributes) throws JSONException {
		initIfNecessary();
        String[] whereArgs = new String[] { id };
        return db.update(tablename, getContentValues(attributes), "id = ?", whereArgs);
    }
    /*
    public void update(String id, ContentValues values) {
		initIfNecessary();
		String where = "id=?";
		String[] whereArgs = new String[] {String.valueOf(id)};
		db.update(tablename, values, where, whereArgs);    	
    }*/
    public void update(Entity entity) {
		try {
			initIfNecessary();
			String where = "id=?";
			String[] whereArgs = new String[] {String.valueOf(entity.getId())};
			db.update(tablename, getContentValues(entity), where, whereArgs);
		} catch (JSONException e) {
			e.printStackTrace();
		}    	
    }
    public int deleteAll(){
        return db.delete(tablename, null, null);
    }
    /*
    public E find(String id)
    throws ReflectiveOperationException {
        SQLiteDatabase db = this.getDb();
        Cursor cursor = db.query(this.getTable(), null, "id = ?", new String[] { id }, null, null, null, "1");

        Class[] constructorSignature = new Class[] { Cursor.class };
        Constructor constructor = this.getModelClass().getConstructor(constructorSignature);

        while (cursor.moveToNext()) {
            return (E) constructor.newInstance(cursor);
        }
        return null;
    }
    
    public E first() 
    throws ReflectiveOperationException {
        SQLiteDatabase db = this.getDb();
        Cursor cursor = db.query(this.getTable(), null, null, null, null, null, null, "1");

        Class[] constructorSignature = new Class[] { Cursor.class };
        Constructor constructor = this.getModelClass().getConstructor(constructorSignature);

        while (cursor.moveToNext()) {
            return (E) constructor.newInstance(cursor);
        }
        return null;
    }
    
    public HashMap<String, E> where(String where) 
    throws ReflectiveOperationException { // "id = 1"
        // needed? we're not writing to db
        db = this.getDb();
        
        Cursor cursor = db.query(this.getTable(), null, where, null, null, null, null, null);

        Class[] constructorSignature = new Class[] { Cursor.class };
        Constructor constructor = this.getModelClass().getConstructor(constructorSignature);

        items = new HashMap<String, E>();
        while (cursor.moveToNext()) {
            items.put(cursor.getString(0), (E) constructor.newInstance(cursor) );
        }
        return items;
    }
    */
    public void delete(Integer id) {
		initIfNecessary();
        String[] whereArgs = new String[] { id.toString() };
        db.delete(tablename, "id = ?", whereArgs);
    };

    public void delete(Entity item) {
    		delete(item.getId());
    }

    private static ContentValues getContentValues(JSONObject attributes) throws JSONException {
        ContentValues values = new ContentValues();
        Iterator keys = attributes.keys();
        String key;

        while (keys.hasNext()) {
            key = keys.next().toString();
            values.put(key, attributes.getString(key));
        }

        return values;
    }
    private static ContentValues getContentValues(Entity entity) throws JSONException {
    		return getContentValues(entity.getValues());
    }
    private String getCreateTableQuery()
    {
            return "CREATE TABLE IF NOT EXISTS "+tablename+" ("+implodeFieldsWithTypes()+" );";
    }
    private String implodeFieldsWithTypes()
    {
            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(fields[i].contentEquals(fields[0]))//fields[0] being the primary key
                            output+=fields[i]+" INTEGER PRIMARY KEY";
                    else
                            output+=","+fields[i]+" "+fieldtypes[i];
            }
            return output;
    }	
    private String getDeleteTableQuery()
    {
            return "DROP TABLE IF EXISTS "+tablename;
    }
	public void createTable()
	{
		initIfNecessary();
		db.execSQL(getCreateTableQuery());
	}
	public void deleteTable()
	{
		initIfNecessary();
		db.execSQL(getDeleteTableQuery());
	}
	
	public Integer jsonGetInteger(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return values.getInt(key);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void jsonPutInteger(JSONObject values, String key, Integer value) {
		try {
			values.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
    
	public Boolean jsonGetBoolean(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return values.getBoolean(key);
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutBoolean(JSONObject values, String key, Boolean value) {
		try {
			values.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Double jsonGetDouble(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return values.getDouble(key);
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutDouble(JSONObject values, String key, Double value) {
		try {
			values.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Long jsonGetLong(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return values.getLong(key);
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutLong(JSONObject values, String key, Long value) {
		try {
			values.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String jsonGetString(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return values.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutString(JSONObject values, String key, String value) {
		try {
			values.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Date jsonGetDate(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return DateHelper.toDate(values.getString(key));
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutDate(JSONObject values, String key, Date value) {
		try {
			values.put(key, DateTimeHelper.toString(value));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Date jsonGetDateTime(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return DateTimeHelper.toDate(values.getString(key));
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutDateTime(JSONObject values, String key, Date value) {
		try {
			values.put(key, DateTimeHelper.toString(value));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	//all the following functions need testing
	public BigDecimal jsonGetDecimal(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return BigDecimal.valueOf(values.getDouble(key));
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutDecimal(JSONObject values, String key, BigDecimal value) {
		try {
			values.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Short jsonGetShort(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return Short.valueOf(values.getString(key));
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutShort(JSONObject values, String key, Short value) {
		try {
			values.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Float jsonGetFloat(JSONObject values, String key) {
		try {
			if(values.isNull(key))return null;
			else return Float.valueOf(values.getString(key));
		} catch (JSONException e) {
			e.printStackTrace();return null;
		}
	}
	public void jsonPutFloat(JSONObject values, String key, Float value) {
		try {
			values.put(key, value);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
