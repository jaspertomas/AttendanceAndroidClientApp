package models;

import java.util.ArrayList;

import models.base.BaseUser;

import org.json.JSONObject;

import utils.MyDatabaseHelper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class User extends BaseUser{
	public User()
	{
	}
	public User(JSONObject values) {
		super(values);
	}
	public User(Cursor cursor) {
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

		Cursor cursor = db.rawQuery("SELECT id, username FROM users "+criteria, null);
		while (cursor.moveToNext()) {
			itemIds.add(cursor.getInt(cursor.getColumnIndex("id")));
			itemNames.add(cursor.getString(cursor.getColumnIndex("username")));
		}
		cursor.close();
	}
}