package personal.rowan.baseapplication.data.db.table;

import android.content.ContentValues;
import android.database.Cursor;

import personal.rowan.baseapplication.data.db.CleanCursor;
import personal.rowan.baseapplication.data.db.Column;
import personal.rowan.baseapplication.data.model.SampleObject;

public class SampleTable
		extends Table {

	public static final String SAMPLE_OBJECT_TITLE = "sampleObjectTitle";
	public static final String SAMPLE_OBJECT_DETAIL = "sampleObjectDetail";

	@Override
	protected void onCreateColumns() {
		addColumn(new Column(SAMPLE_OBJECT_TITLE, Column.ColumnType.TEXT));
		addColumn(new Column(SAMPLE_OBJECT_DETAIL, Column.ColumnType.TEXT));
	}

	public static SampleObject toObject(Cursor cursor) {
		CleanCursor cleanCursor = new CleanCursor(cursor);

		String objectId = cleanCursor.getString(ID);
		String objectTitle = cleanCursor.getString(SAMPLE_OBJECT_TITLE);
		String objectDetail = cleanCursor.getString(SAMPLE_OBJECT_DETAIL);

		return new SampleObject(objectId, objectTitle, objectDetail);
	}

	public static ContentValues toContentValues(SampleObject object) {
		ContentValues contentValues = new ContentValues();

		contentValues.put(ID, object.getId().replace("-", ""));
		contentValues.put(SAMPLE_OBJECT_TITLE, object.getTitle());
		contentValues.put(SAMPLE_OBJECT_DETAIL, object.getDetail());

		return contentValues;
	}

}
