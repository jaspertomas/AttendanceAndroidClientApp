package models;

import java.util.ArrayList;
import java.util.Date;

import utils.StandardDateHelper;
import android.database.Cursor;

public class Region {
    //------------FIELDS-----------
    public static final String tablename="regions";
    //field names
    public static String[] fields={
            "id"
            ,"name"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"varchar(20)"
            };
    //-----------------------

    public Integer id;
    public String name;

    public Region() {
    }
    public Region(Cursor c) {
        id=c.getInt(c.getColumnIndex("id"));
        name=c.getString(c.getColumnIndex("name"));
    }

//	public String getUuid()
//	{
//		return id.toString()+"-";
//	}

    public Integer getId() {
            return id;
    }

    public void setId(Integer id) {
            this.id = id;
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(name);

            return values;
    }
    public void delete()
    {
            Regions.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    id=Regions.insert(this);
            else
                    Regions.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
