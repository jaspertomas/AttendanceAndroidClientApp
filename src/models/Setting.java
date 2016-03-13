package models;

import java.util.ArrayList;

import models.base.BaseSetting;

import org.json.JSONObject;

import utils.MyDatabaseHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class Setting extends BaseSetting{
	public Setting()
	{
	}
	public Setting(JSONObject values) {
		super(values);
	}
	public Setting(Cursor cursor) {
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

		Cursor cursor = db.rawQuery("SELECT id, name FROM settings "+criteria, null);
		while (cursor.moveToNext()) {
			itemIds.add(cursor.getInt(cursor.getColumnIndex("id")));
			itemNames.add(cursor.getString(cursor.getColumnIndex("name")));
		}
		cursor.close();
	}
}