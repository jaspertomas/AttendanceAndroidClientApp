package models;

import java.util.ArrayList;

import models.base.BaseRecord;

import org.json.JSONObject;

import utils.MyDatabaseHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class Record extends BaseRecord{
	public Record()
	{
	}
	public Record(JSONObject values) {
		super(values);
	}
	public Record(Cursor cursor) {
		super(cursor);
	}
	public static void selectIdsAndNames(String criteria,		
			ArrayList<Integer> itemIds,
			ArrayList<String> itemNames
			) {
		itemNames.clear();
		itemIds.clear();
		SQLiteDatabase db = MyDatabaseHelper.getInstance()
				.getWritableDatabase();

		Cursor cursor = db.rawQuery("SELECT id, id FROM records "+criteria, null);
		while (cursor.moveToNext()) {
			itemIds.add(cursor.getInt(cursor.getColumnIndex("id")));
			itemNames.add(cursor.getString(cursor.getColumnIndex("id")));
		}
		cursor.close();
	}
}