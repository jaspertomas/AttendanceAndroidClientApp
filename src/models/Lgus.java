package models;

import java.util.ArrayList;
import java.util.Date;

import utils.MyDatabaseHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Lgus {
    //------------FIELDS-----------
    public static final String tablename=Lgu.tablename;
    public static String[] fields=Lgu.fields;
    public static String[] fieldtypes=Lgu.fieldtypes;
    //-----------------------
    //-------------------------TABLE FUNCTIONS---------------------

    //-----------getter functions----------
    public static Lgu getById(Integer id) {
            ArrayList<Lgu> map=select(" where id = '"+id.toString()+"'");
            for(Lgu item:map)return item;
            return null;
    }
    public static Lgu getByName(String name)
    {
            ArrayList<Lgu> items=select(" where name = '"+name+"'");
            for(Lgu item:items)return item;
            return null;
    }	
    public static ArrayList<Lgu> selectByName(String name)
    {
            return select(" where name = '"+name+"'");
    }	
    public static Lgu getByType(Integer type)
    {
            ArrayList<Lgu> items=select(" where type = '"+type.toString()+"'");
            for(Lgu item:items)return item;
            return null;
    }	
    public static ArrayList<Lgu> selectByType(Integer type)
    {
            return select(" where type = '"+type.toString()+"'");
    }	
    public static Lgu getByRegion(String region)
    {
            ArrayList<Lgu> items=select(" where region = '"+region+"'");
            for(Lgu item:items)return item;
            return null;
    }	
    public static ArrayList<Lgu> selectByRegion(String region)
    {
            return select(" where region = '"+region+"'");
    }	
    public static Lgu getByRegionId(Integer region_id)
    {
            ArrayList<Lgu> items=select(" where region_id = '"+region_id.toString()+"'");
            for(Lgu item:items)return item;
            return null;
    }	
    public static ArrayList<Lgu> selectByRegionId(Integer region_id)
    {
            return select(" where region_id = '"+region_id.toString()+"'");
    }	
    //-----------database functions--------------

	public static void delete(Lgu item)
	{
		SQLiteDatabase db = MyDatabaseHelper.getInstance().getWritableDatabase();
		db.execSQL("delete from "+tablename+" where id = '"+item.getId()+"';");
		//db.close()
	}
	public static void delete(Integer id)
	{
		SQLiteDatabase db = MyDatabaseHelper.getInstance().getWritableDatabase();
		db.execSQL("delete from "+tablename+" where id = '"+id+"';");
		//db.close()
	}
	public static Integer insert(Lgu item)
	{
		SQLiteDatabase db = MyDatabaseHelper.getInstance().getWritableDatabase();
		
		if(fieldtypes[0].contains("int"))
		{
			db.execSQL("INSERT INTO "+tablename+" ("+implodeFields(false)+")VALUES (" 
					+implodeValues(item, false)
					+");");
		}
		else
		if(fieldtypes[0].contains("varchar"))
		{
			db.execSQL("INSERT INTO "+tablename+" ("+implodeFields(true)+")VALUES (" 
					+implodeValues(item, true)
					+");");
		}

            //fetch last insert id
		Cursor cursor = db.rawQuery("SELECT last_insert_rowid() FROM "+tablename+" ", null);
		cursor.moveToFirst();
		Integer result=cursor.getInt(0);
		cursor.close();

		//db.close()
		return result;
	}
	public static void update(Lgu item)
	{
		SQLiteDatabase db = MyDatabaseHelper.getInstance().getWritableDatabase();

		db.execSQL(
		"update "+tablename+" set "+implodeFieldsWithValues(item,false)+" where id = '"+item.getId()
		+"';");
		//db.close()
	}
	public static ArrayList<Lgu> select(String criteria) {
		ArrayList<Lgu> items = new ArrayList<Lgu>();
		SQLiteDatabase db = MyDatabaseHelper.getInstance()
				.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT * FROM "+tablename+" "+criteria, null);
		items = new ArrayList<Lgu>();
		while (cursor.moveToNext()) {
			items.add(new Lgu(cursor));
		}
		cursor.close();
		//db.close()
		return items;
	}
	public static Integer count(String criteria) {
		SQLiteDatabase db = MyDatabaseHelper.getInstance().getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT count(*) FROM "+tablename+" "+criteria, null);
		cursor.moveToFirst();
		Integer result=cursor.getInt(0);
		cursor.close();
		//db.close()
		return result;
	}
	public static Integer getLastInsertId() {
		SQLiteDatabase db = MyDatabaseHelper.getInstance().getWritableDatabase();
		Cursor cursor = db.rawQuery("SELECT last_insert_rowid() FROM "+tablename+" ", null);
		cursor.moveToFirst();
		Integer result=cursor.getInt(0);
		cursor.close();
		//db.close()
		return result;
	}

    //-----------database helper functions--------------
    public static String implodeValues(Lgu item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(withId);
            String output="";
            for(String value:values)
            {
                    if(!output.isEmpty())
                            output+=",";
                    output+=(value!=null?"'"+value+"'":"null");
            }
            return output;
    }
    public static String implodeFields(boolean withId)
    {
            String output="";
            for(String field:fields)
            {
                    if(!withId && field.contentEquals("id"))continue;
                    if(!output.isEmpty())
                            output+=",";
                    output+=field;
            }
            return output;
    }
    public static String implodeFieldsWithValues(Lgu item,boolean withId)
    {
            ArrayList<String> values=item.implodeFieldValuesHelper(true);//get entire list of values; whether the id is included will be dealt with later.

            if(values.size()!=fields.length)
            {
                    System.err.println("Lgus:implodeFieldsWithValues(): ERROR: values length does not match fields length");
            }

            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(!withId && fields[i].contentEquals("id"))continue;
                    if(!output.isEmpty())
                            output+=",";
                    output+=fields[i]+"="+(values.get(i)!=null?"'"+values.get(i)+"'":"null");
            }
            return output;
    }	
    public static String implodeFieldsWithTypes()
    {
            String output="";
            for(int i=0;i<fields.length;i++)
            {
                    if(fields[i].contentEquals(fields[0]))//fields[0] being the primary key
                            output+=fields[i]+" "+fieldtypes[i]+" PRIMARY KEY";
                    else
                            output+=","+fields[i]+" "+fieldtypes[i];
            }
            return output;
    }	
    public static String createTable()
    {
            return "CREATE TABLE IF NOT EXISTS "+tablename+" ("+implodeFieldsWithTypes()+" );";
    }
    public static String deleteTable()
    {
            return "DROP TABLE IF EXISTS "+tablename;
    }
}
