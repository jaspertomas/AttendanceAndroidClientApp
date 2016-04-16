package models;

import java.util.ArrayList;
import java.util.Date;

import utils.StandardDateHelper;
import android.database.Cursor;

public class Lgu {
    //------------FIELDS-----------
    public static final String tablename="lgus";
    //field names
    public static String[] fields={
            "id"
            ,"name"
            ,"type"
            ,"region"
            ,"region_id"
            };
    //field types
    public static String[] fieldtypes={
            "int(11)"
            ,"varchar(50)"
            ,"int(11)"
            ,"varchar(50)"
            ,"int(11)"
            };
    //-----------------------

    public Integer id;
    public String name;
    public Integer type;
    public String region;
    public Integer region_id;

    public Lgu() {
    }
    public Lgu(Cursor c) {
        id=c.getInt(c.getColumnIndex("id"));
        name=c.getString(c.getColumnIndex("name"));
        type=c.getInt(c.getColumnIndex("type"));
        region=c.getString(c.getColumnIndex("region"));
        region_id=c.getInt(c.getColumnIndex("region_id"));
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

    public Integer getType() {
            return type;
    }

    public void setType(Integer type) {
            this.type = type;
    }

    public String getRegion() {
            return region;
    }

    public void setRegion(String region) {
            this.region = region;
    }

    public Integer getRegionId() {
            return region_id;
    }

    public void setRegionId(Integer region_id) {
            this.region_id = region_id;
    }


    //database functions
    public ArrayList<String> implodeFieldValuesHelper(boolean withId)
    {
            ArrayList<String> values=new ArrayList<String>(); 

            //add values for each field here
            if(withId)values.add(id!=null?id.toString():null);
            values.add(name);
            values.add(type!=null?type.toString():null);
            values.add(region);
            values.add(region_id!=null?region_id.toString():null);

            return values;
    }
    public void delete()
    {
            Lgus.delete(this);
    }
    public void save()
    {
            if(id==null || id==0)
                    id=Lgus.insert(this);
            else
                    Lgus.update(this);
    }
    @Override
    public String toString()
    {
            return id.toString();
    }
}
