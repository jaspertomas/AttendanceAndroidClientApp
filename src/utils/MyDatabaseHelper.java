package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import models.Employee;
import models.Record;
import models.Setting;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelper extends SQLiteOpenHelper {
	//set this to true if you want the initial database
	//to be copied from the assets folder
	//instead of just being a blank database
	private static boolean copydbfromassets=false;
	
	private static String DB_PATH = ""; 
	private final Context context;
	private static String contextstring = "MyDatabaseHelper"; // Tag just for the LogCat window
	private static final String DB_NAME = "database.db";

	private static final int latestVersion=1;
	private static MyDatabaseHelper instance;
	public static MyDatabaseHelper getInstance()
	{
		try {
			if(instance==null)
				instance=new MyDatabaseHelper(MyApplicationContextHolder.getAppContext());
			return instance;
		} catch (IOException e) {
			Log.e(contextstring,"Error creating database");
			return null;
		}
	}
	private MyDatabaseHelper(Context context) throws IOException 
	{
	    super(context, DB_NAME, null, latestVersion);
	    this.context = context;

	    //if copydbfromassets is true
		//the database will be copied from the assets folder
		//instead of just being a blank database
	    if(copydbfromassets)
	    {
			if(android.os.Build.VERSION.SDK_INT >= 17){
				DB_PATH = context.getApplicationInfo().dataDir + "/databases/";         
			}
			else
			{
				DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
			}
			createDataBase();
	    }
	}   
	private void createDataBase() throws IOException
	{
	    //If database not exists copy it from the assets

	    boolean mDataBaseExist = checkDataBase();
	    if(!mDataBaseExist)
	    {
	        this.getReadableDatabase();
	        this.close();
	        try 
	        {
	            //Copy the database from assests
	            copyDataBase();
	            Log.e(contextstring, "createDatabase database created");
	        } 
	        catch (IOException mIOException) 
	        {
	            throw new Error("ErrorCopyingDataBase");
	        }
	    }
	}
    //Check that the database exists here: /data/data/your package/databases/Da Name
    private boolean checkDataBase()
    {
        File dbFile = new File(DB_PATH + DB_NAME);
        //Log.v("dbFile", dbFile + "   "+ dbFile.exists());
        return dbFile.exists();
    }

    //Copy the database from assets
    private void copyDataBase() throws IOException
    {
        InputStream mInput = context.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer))>0)
        {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

	@Override
	public void onCreate(SQLiteDatabase database) {
//		database.execSQL("");
//		onUpgrade( database,  1,  latestVersion);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		if(oldVersion<1)
		{
		}
	}
	public static void createTables(Context context)
	{
		try {
			if(instance==null)
				instance=new MyDatabaseHelper(MyApplicationContextHolder.getAppContext());

			//Put table creator functions here
			//Employees.deleteTable();
			Employee.createTable();
			//Record.deleteTable();
			Record.createTable();
			//Setting.deleteTable();
			Setting.createTable();

		} catch (IOException e) {
			Log.e(contextstring,"Error creating database");
		}
	}
}
