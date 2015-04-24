//package com.icecub3.recofood;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Random;
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DBHelper extends SQLiteOpenHelper {
//
//	private static final String DATABASE_NAME = "AbKyaKhau";
//	private static final int DATABASE_VERSION = 1;
//
//	public DBHelper(Context context) {
//		super(context, DATABASE_NAME, null, DATABASE_VERSION);
//		// TODO Auto-generated constructor stub
//	}
//
//	private static final String TABLE_NAME = "Food";
//	private static final String KEY_ID = "id";
//	private static final String KEY_DISH = "dish";
//	private static final String KEY_LAST_DATE = "last_date";
//	private static final String[] KEY_COLUMNS = { KEY_ID, KEY_DISH,
//			KEY_LAST_DATE };
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		// TODO Auto-generated method stub
//		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID
//				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_DISH + " TEXT, "
//				+ KEY_LAST_DATE + " TEXT )";
//		db.execSQL(CREATE_TABLE);
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		// TODO Auto-generated method stub
//		String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
//		db.execSQL(DROP_TABLE);
//
//		onCreate(db);
//	}
//
//	public void addDish(Dishes dish) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_DISH, dish.getDishName());
//		values.put(KEY_LAST_DATE, dish.getLastDate());
//		db.insert(TABLE_NAME, null, values);
//
//		db.close();
//
//	}
//
//	public Dishes getDish(int id) {
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		Cursor cursor = db.query(TABLE_NAME, KEY_COLUMNS, KEY_ID + "=?",
//				new String[] { String.valueOf(id) }, null, null, null, null);
//
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		Dishes dish = new Dishes();
//		dish.setId(Integer.parseInt(cursor.getString(0)));
//		dish.setDishName(cursor.getString(1));
//		dish.setLastDate(cursor.getString(2));
//
//		return dish;
//	}
//
//	public Dishes getDishObjByName(String dish_name) {
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		Cursor cursor = db.query(TABLE_NAME, KEY_COLUMNS, KEY_DISH + "=?",
//				new String[] { dish_name }, null, null, null, null);
//
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		Dishes dish = new Dishes();
//		dish.setId(Integer.parseInt(cursor.getString(0)));
//		dish.setDishName(cursor.getString(1));
//		dish.setLastDate(cursor.getString(2));
//
//		return dish;
//	}
//
//	public List<Dishes> getAllDishes() {
//		List<Dishes> dishes = new LinkedList<Dishes>();
//
//		String query = "SELECT * FROM " + TABLE_NAME;
//
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(query, null);
//
//		Dishes dish = null;
//		if (cursor.moveToFirst()) {
//			do {
//				dish = new Dishes();
//				dish.setId(Integer.parseInt(cursor.getString(0)));
//				dish.setDishName(cursor.getString(1));
//				dish.setLastDate(cursor.getString(2));
//
//				dishes.add(dish);
//			} while (cursor.moveToNext());
//		}
//
//		return dishes;
//	}
//
//	public List<String> getAllDishesNames() {
//		List<String> dishNames = new LinkedList<String>();
//		String query = "SELECT " + KEY_DISH + " FROM " + TABLE_NAME + " ORDER BY " + KEY_DISH 
//				+ " ASC";
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(query, null);
//
//		String dishName = "";
//		if (cursor.moveToFirst()) {
//			do {
//				dishName = new String();
//				dishName = cursor.getString(0);
//				dishNames.add(dishName);
//			} while (cursor.moveToNext());
//		}
//		return dishNames;
//	}
//
//	public int updateDishName(Dishes dish) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_DISH, dish.getDishName());
//		values.put(KEY_LAST_DATE, dish.getLastDate());
//
//		return db.update(TABLE_NAME, values, KEY_ID + "=?",
//				new String[] { String.valueOf(dish.getId()) });
//	}
//
//	public void deleteDish(Dishes dish) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		db.delete(TABLE_NAME, KEY_ID + "=?",
//				new String[] { String.valueOf(dish.getId()) });
//		db.close();
//	}
//
//	public String getMaxId() {
//		String max = "CHOP!";
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery("SELECT MAX(" + KEY_ID + ") FROM "
//				+ TABLE_NAME + " ORDER BY " + KEY_ID + " DESC", null);
//
//		if (cursor != null) {
//			cursor.moveToFirst();
//			max = cursor.getString(0);
//			cursor.close();
//
//			// if (max == null) {
//			// addDish(new Dishes(1, "Nothing"));
//			// Cursor new_cursor = db.rawQuery("SELECT MAX(" + KEY_ID
//			// + ") FROM " + TABLE_NAME + " ORDER BY " + KEY_ID
//			// + " DESC", null);
//			// new_cursor.moveToFirst();
//			// max = new_cursor.getString(0);
//			// }
//
//		}
//
//		return max;
//	}
//
//	// public String getIdNothing() {
//	// SQLiteDatabase db = this.getReadableDatabase();
//	// String id = "";
//	// Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM " + TABLE_NAME +
//	// " WHERE "
//	// + KEY_DISH + "='Nothing' ORDER BY " + KEY_ID + " ASC", null);
//	//
//	// if(cursor!=null) {
//	// cursor.moveToFirst();
//	// id = cursor.getString(0);
//	// }
//	// return id;
//	// }
//
////	public int returnRandomValidId() {
////		int max = Integer.parseInt(getMaxId());
////		int id = getRandomNumber(max);
////		String res="";
////		SQLiteDatabase db = this.getReadableDatabase();
////		
////		/*Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
////					+ " WHERE " + KEY_ID + " ='" + id + "'", null);
////		res=cursor.getString(0);
////		
////		if(res==null) {
////			returnRandomValidId(); //try again till it gives a correct value
////		}
////
////
////		return Integer.parseInt(res); */
////		
////		Cursor cursor = db.rawQuery("SELECT " + KEY_ID + " FROM " 
////				+ TABLE_NAME + " WHERE " + KEY_ID + "='"
////				+ id + "'", null);
////		
////		if(cursor!=null) {
////			res = cursor.getString(0);
////		}
////		if(res == null) {
////			id = returnRandomValidId();
////		}
////		return id;
////
////	}
////
////	public int getRandomNumber(int max) {
////		Random r = new Random();
////		int number = r.nextInt(max) + 1;
////
////		if (number > max)
////			number = max;
////
////		return number;
////	}
////	
////	public int getRandomId() {
////		SQLiteDatabase db = this.getReadableDatabase();
////		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY RANDOM() LIMIT 1", null);
////		int x=-1;
////		
////		if(cursor!=null) {
////			x= Integer.parseInt(cursor.getString(0));
////		}
////		
////		return x;
////	}
//
//	public int getRandomId() {
//		SQLiteDatabase db = this.getReadableDatabase();
//		//Cursor cursor = db.query(TABLE_NAME, new String[] {KEY_ID}, null, null, null, null, " RANDOM() ", "1");
//		Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME
//				+ " ORDER BY RANDOM()", null);
//		int x=-1;	
//		if(cursor!=null) {
//			cursor.moveToFirst();
//			x= Integer.parseInt(cursor.getString(0));
//		}
//		
//		return x;
//	}
//}

package com.icecub3.recofood;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "RecoFood";
	private static final int DATABASE_VERSION = 1;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	private static final String TABLE_NAME = "Food";
	private static final String KEY_ID = "id";
	private static final String KEY_ITEM = "item";
	private static final String KEY_YES = "countYes";
	private static final String KEY_NO = "countNo";
	private static final String KEY_PROB_YES = "probYes";
	private static final String KEY_DISP_SESSION = "dispThisSession";
	private static final String[] KEY_COLUMNS = { KEY_ID, KEY_ITEM, KEY_YES,
			KEY_NO, KEY_PROB_YES, KEY_DISP_SESSION };

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " + KEY_ID
				+ " INTEGER PRIMARY KEY, " + KEY_ITEM + " TEXT, " + KEY_YES
				+ " INTEGER, " + KEY_NO + " INTEGER, " + KEY_PROB_YES
				+ " INTEGER, " + KEY_DISP_SESSION + " INTEGER " + " )";
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(DROP_TABLE);

		onCreate(db);
	}

	public void addItem(Items item) {

		// initialize values to be forced
		item.setCountYes(10);
		item.setCountNo(0);
		item.setProbYes(calcProbYes(item.getCountYes(), item.getCountNo()));
		item.setDispThisSession(0);

		// Proceed with insert on DB
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ITEM, item.getItem());
		values.put(KEY_YES, item.getCountYes());
		values.put(KEY_NO, item.getCountNo());
		values.put(KEY_PROB_YES, item.getProbYes());
		values.put(KEY_DISP_SESSION, item.getDispThisSession());

		db.insert(TABLE_NAME, null, values);
		db.close();
	}

	public Items getItem(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, KEY_COLUMNS, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Items item = new Items();
		item.setId(Integer.parseInt(cursor.getString(0)));
		item.setItem(cursor.getString(1));
		item.setCountYes(Integer.parseInt(cursor.getString(2)));
		item.setCountNo(Integer.parseInt(cursor.getString(3)));
		item.setProbYes(Integer.parseInt(cursor.getString(4)));
		item.setDispThisSession(Integer.parseInt(cursor.getString(5)));

		return item;
	}

	public Items getItemObjByName(String item_name) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_NAME, KEY_COLUMNS, KEY_ITEM + "=?",
				new String[] { item_name }, null, null, null, null);

		if (cursor != null)
			cursor.moveToFirst();

		Items item = new Items();
		item.setId(Integer.parseInt(cursor.getString(0)));
		item.setItem(cursor.getString(1));
		item.setCountYes(Integer.parseInt(cursor.getString(2)));
		item.setCountNo(Integer.parseInt(cursor.getString(3)));
		item.setProbYes(Integer.parseInt(cursor.getString(4)));
		item.setDispThisSession(Integer.parseInt(cursor.getString(5)));
		return item;

	}

	public List<Items> getAllItems() {
		List<Items> items = new LinkedList<Items>();

		String query = "SELECT * FROM " + TABLE_NAME;

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);

		Items item = null;

		if (cursor.moveToFirst()) {
			do {
				item = new Items();
				item.setId(Integer.parseInt(cursor.getString(0)));
				item.setItem(cursor.getString(1));
				item.setCountYes(Integer.parseInt(cursor.getString(2)));
				item.setCountNo(Integer.parseInt(cursor.getString(3)));
				item.setProbYes(Integer.parseInt(cursor.getString(4)));
				item.setDispThisSession(Integer.parseInt(cursor.getString(5)));
			} while (cursor.moveToNext());
		}

		return items;
	}

	public List<String> getAllItemNames() {
		List<String> itemNames = new LinkedList<String>();

		String query = "SELECT " + KEY_ITEM + " FROM " + TABLE_NAME
				+ " ORDER BY " + KEY_ITEM + " ASC";
		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);

		String itemName = "";

		if (cursor.moveToFirst()) {
			do {
				itemName = new String();
				itemName = cursor.getString(0);
				itemNames.add(itemName);
			} while (cursor.moveToNext());
		}

		return itemNames;
	}

	public int updateItemName(Items item) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ITEM, item.getItem());
		values.put(KEY_YES, item.getCountYes());
		values.put(KEY_NO, item.getCountNo());
		values.put(KEY_PROB_YES, item.getProbYes());

		return db.update(TABLE_NAME, values, KEY_ID + "=?",
				new String[] { String.valueOf(item.getId()) });
	}

	public void deleteItem(Items item) {
		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE_NAME, KEY_ID + "=?",
				new String[] { String.valueOf(item.getId()) });
		db.close();
	}

	public int updateItemProb(Items item, boolean accepted) {
		if (accepted == true) {
			item.setCountYes(item.getCountYes() + 1);
		} else if (accepted = false) {
			item.setCountNo(item.getCountNo() + 1);
		}
		item.setProbYes(calcProbYes(item.getCountYes(), item.getCountNo()));
		item.setDispThisSession(1); // Update will only be called on yes/no

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ITEM, item.getItem());
		values.put(KEY_YES, item.getCountYes());
		values.put(KEY_NO, item.getCountNo());
		values.put(KEY_PROB_YES, item.getProbYes());
		values.put(KEY_DISP_SESSION, item.getDispThisSession());

		return db.update(TABLE_NAME, values, KEY_ID + "=?",
				new String[] { String.valueOf(item.getId()) });
	}

	public Items getSuggestedItem() {
		SQLiteDatabase db = this.getReadableDatabase();
		int min, max, count, total;

		String queryItemCount = "SELECT " + KEY_ID + " FROM " + TABLE_NAME
				+ " WHERE " + KEY_DISP_SESSION + " =FALSE ORDER BY " + KEY_ID
				+ " DESC";
		Cursor maxCursor = db.rawQuery(queryItemCount, null);
		if (maxCursor != null)
			maxCursor.moveToFirst();
		int maxId = Integer.parseInt(maxCursor.getString(0));
		if(maxId > 0) {
			String queryProbSum = "SELECT SUM(" + KEY_PROB_YES + ") FROM "
					+ TABLE_NAME + " WHERE " + KEY_DISP_SESSION + " =FALSE";
			Cursor sumCursor = db.rawQuery(queryProbSum, null);
			if (sumCursor != null)
				sumCursor.moveToFirst();
			total = Integer.parseInt(sumCursor.getString(0));
	
			min = 0;
			max = maxId;
	
			while (min < max) {
				String limitedSumQuery = "SELECT SUM(" + KEY_PROB_YES + ") FROM "
						+ TABLE_NAME + " WHERE " + KEY_DISP_SESSION
						+ " =FALSE AND " + KEY_ID + " <= "
						+ String.valueOf((int) Math.ceil((max + min) / 2));
				Cursor limitedSumCursor = db.rawQuery(limitedSumQuery, null);
				if (limitedSumCursor != null)
					limitedSumCursor.moveToFirst();
				int temp = Integer.parseInt(limitedSumCursor.getString(0));
	
				if (temp > total)
					min = (int) Math.ceil((min + max) / 2);
				else if (temp < total)
					max = (int) Math.ceil((min + max) / 2);
				else
					min = max = (int) Math.ceil((min + max) / 2);
			}
	
			String countQuery = "SELECT COUNT(" + KEY_ID + ") FROM " + TABLE_NAME
					+ " WHERE " + KEY_DISP_SESSION + " =FALSE AND " + KEY_ID + " ="
					+ String.valueOf(min);
			Cursor countNewCursor = db.rawQuery(countQuery, null);
			if (countNewCursor != null)
				countNewCursor.moveToFirst();
			int count1 = Integer.parseInt(countNewCursor.getString(0));
			while (count1 == 0) {
				min -= 1;
				countQuery = "SELECT COUNT(" + KEY_ID + ") FROM " + TABLE_NAME
						+ " WHERE " + KEY_DISP_SESSION + " =FALSE AND " + KEY_ID
						+ " =" + String.valueOf(min);
				countNewCursor = db.rawQuery(countQuery, null);
				if (countNewCursor != null)
					countNewCursor.moveToFirst();
				count1 = Integer.parseInt(countNewCursor.getString(0));
	
			}
	
			Cursor cursor = db.query(TABLE_NAME, KEY_COLUMNS, KEY_ID + "=?",
					new String[] { String.valueOf(min) }, null, null, null, null);
	
			if (cursor != null)
				cursor.moveToFirst();
	
			Items item = new Items();
			item.setId(Integer.parseInt(cursor.getString(0)));
			item.setItem(cursor.getString(1));
			item.setCountYes(Integer.parseInt(cursor.getString(2)));
			item.setCountNo(Integer.parseInt(cursor.getString(3)));
			item.setProbYes(Integer.parseInt(cursor.getString(4)));
			item.setDispThisSession(Integer.parseInt(cursor.getString(5)));
			return item;
		}
		else if(maxId<=0) {
			Items item = new Items();
			return item;
		}
		Items item = new Items();
		return item;
	}
	
	public void clearSessionDisp()
	{
		String query = "UPDATE " + TABLE_NAME + " SET " + KEY_DISP_SESSION + "=0";
		SQLiteDatabase db = this.getWritableDatabase();
		
		db.rawQuery(query,null);
		
	}
	
	

	public int calcProbYes(int y, int n) {
		return (int) (10000 * y / (y + n));
	}

}