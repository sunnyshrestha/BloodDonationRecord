package dev.suncha.blooddonationrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sunny on 1/19/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "donationRecord";
    static final String TABLE_DONATIONS = "donations";

    static final String KEY_ID = "id";
    static final String KEY_DATE = "date";
    static final String KEY_LOCATION = "location";
    static final String KEY_ORGANISERS = "organisers";
    static final String KEY_REMINDER = "reminder";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DONATION_RECORDS_TABLE = "CREATE TABLE " + TABLE_DONATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_DATE + " TEXT," +
                KEY_LOCATION + " TEXT," + KEY_ORGANISERS + " TEXT," + KEY_REMINDER + " TEXT" + ")";
        db.execSQL(DONATION_RECORDS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONATIONS);
        onCreate(db);
    }

    void addRecord(donationRecord donation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, donation.get_date());
        values.put(KEY_LOCATION, donation.get_organisers());
        values.put(KEY_REMINDER, donation.get_reminder());

        db.insert(TABLE_DONATIONS, null, values);
        db.close();
    }

    //Getting a single record
    donationRecord getRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_DONATIONS, new String[]{KEY_ID, KEY_DATE,
                        KEY_LOCATION, KEY_ORGANISERS, KEY_REMINDER}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        donationRecord record = new donationRecord(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return record;
    }

    //Getting all records
    public List<donationRecord> getAllRecords() {
        List<donationRecord> recordList = new ArrayList<donationRecord>();

        String selectQuery = "SELECT * FROM " + TABLE_DONATIONS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                donationRecord record = new donationRecord();
                record.set_id(Integer.parseInt(cursor.getString(0)));
                record.set_date(cursor.getString(1));
                record.set_location(cursor.getString(2));
                record.set_organisers(cursor.getString(3));
                record.set_reminder(cursor.getString(4));
                recordList.add(record);
            } while (cursor.moveToNext());
        }
        return recordList;
    }

    public int updateRecord(donationRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, record.get_date());
        values.put(KEY_LOCATION, record.get_location());
        values.put(KEY_ORGANISERS, record.get_organisers());
        values.put(KEY_REMINDER, record.get_reminder());

        return db.update(TABLE_DONATIONS, values, KEY_ID + " =?",
                new String[]{String.valueOf(record.get_id())});
    }

    public void deleteRecord(donationRecord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DONATIONS, KEY_ID + " =?",
                new String[]{String.valueOf(record.get_id())});
        db.close();
    }

    public int getRecordsCount() {
        String countQuery = "SELECT * FROM " + TABLE_DONATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}
