package personal.rowan.baseapplication.data.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import personal.rowan.baseapplication.data.db.table.SampleTable;
import personal.rowan.baseapplication.data.model.SampleObject;

public class DatabaseManager
		extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "ApplicationDatabase";
	// increment version number between releases after adding any new tables or modifying existing table schema
	private static final int DATABASE_VERSION = 2;

	private static DatabaseManager instance;

	private DatabaseManager(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		getWritableDatabase();
	}

	public static DatabaseManager getInstance(Context context) {
		if (instance == null) {
			instance = new DatabaseManager(context);
		}

		return instance;
	}

	// call db.execSQL for all tables implemented by application
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(new SampleTable().generateSQL());
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public List<SampleObject> loadSampleObjects() {
		List<SampleObject> items = new ArrayList<>();

		try {
			Cursor cursor = getWritableDatabase().query(
					SampleTable.class.getSimpleName(),
					null, null, null, null, null, null);

			if (cursor.moveToFirst()) {
				do {
					items.add(SampleTable.toObject(cursor));
				} while (cursor.moveToNext());
			}

			cursor.close();
		} catch(SQLiteException e) {
			Log.e("DBError", "loadSampleObjects");
		}

		return items;
	}

	public SampleObject loadSampleObjectById(String id) {
		SampleObject object = null;

		try {
			Cursor cursor = getWritableDatabase().query(
					SampleTable.class.getSimpleName(),
					null,
					SampleTable.ID + "=?",
					new String[] {id},
					null, null, null);

			if (cursor.moveToFirst()) {
				do {
					object = SampleTable.toObject(cursor);
				} while (cursor.moveToNext());
			}

			cursor.close();
		} catch(SQLiteException e) {
			e.printStackTrace();
		}

		return object;
	}

	public List<SampleObject> loadSampleObjectsByTitle(String title) {
		List<SampleObject> items = new ArrayList<>();

		try {
			Cursor cursor = getWritableDatabase().query(
					SampleTable.class.getSimpleName(),
					null,
					SampleTable.SAMPLE_OBJECT_TITLE + "=?",
					new String[] {title},
					null, null, null);

			if (cursor.moveToFirst()) {
				do {
					items.add(SampleTable.toObject(cursor));
				} while (cursor.moveToNext());
			}

			cursor.close();
		} catch(SQLiteException e) {
			e.printStackTrace();
		}

		return items;
	}

	public List<SampleObject> loadSampleObjectsByDetail(String detail) {
		List<SampleObject> items = new ArrayList<>();

		try {
			Cursor cursor = getWritableDatabase().query(
					SampleTable.class.getSimpleName(),
					null,
					SampleTable.SAMPLE_OBJECT_DETAIL + "=?",
					new String[] {detail},
					null, null, null);

			if (cursor.moveToFirst()) {
				do {
					items.add(SampleTable.toObject(cursor));
				} while (cursor.moveToNext());
			}

			cursor.close();
		} catch(SQLiteException e) {
			e.printStackTrace();
		}

		return items;
	}

	public void saveSampleObject(SampleObject sampleObject) {
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();

		try {
			insertOrReplaceValue(SampleTable.toContentValues(sampleObject), SampleTable.class.getSimpleName());

			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("DBError", "saveSampleObject");
		} finally {
			db.endTransaction();
		}
	}

	public void saveSampleObjects(List<SampleObject> sampleObjects) {
		SQLiteDatabase db = getWritableDatabase();
		db.beginTransaction();

		try {
			for(SampleObject sampleObject : sampleObjects) {
				insertOrReplaceValue(SampleTable.toContentValues(sampleObject), SampleTable.class.getSimpleName());
			}

			db.setTransactionSuccessful();
		} catch (Exception e) {
			Log.e("DBError", "saveSampleObjects");
		} finally {
			db.endTransaction();
		}
	}

	public void deleteSampleObject(String sampleObjectId) {
		deleteRow(SampleTable.class.getSimpleName(), sampleObjectId);
	}

	private void insertOrReplaceValue(ContentValues values, String tableName) {
		getWritableDatabase().replace(tableName, null, values);
	}

	private void deleteRow(String tableName, String id) {
		getWritableDatabase().delete(tableName, "_id=?", new String[] {id});
	}

}
