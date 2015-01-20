package dev.suncha.blooddonationrecord;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

//http://androidsolution4u.blogspot.com/2013/09/android-populate-listview-from-sqlite.html
public class ViewPreviousRecords extends ActionBarActivity {
    private DatabaseHandler mHelper;
    private SQLiteDatabase dataBase;

    private ArrayList<String> _id = new ArrayList<String>();
    private ArrayList<String> _date= new ArrayList<String>();
    private ArrayList<String> _location=new ArrayList<String>();
    private ArrayList<String> _reminder=new ArrayList<String>();
    private ArrayList<String> _organisers=new ArrayList<String>();

    private ListView donationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_previous_records);

        donationList=(ListView)findViewById(R.id.list);
        mHelper=new DatabaseHandler(this);
        displayData();
    }

    private void displayData() {
        dataBase=mHelper.getWritableDatabase();
        Cursor mCursor=dataBase.rawQuery("SELECT*FROM "+DatabaseHandler.TABLE_DONATIONS,null);

        _id.clear();
        _date.clear();
        _location.clear();
        _organisers.clear();
        _reminder.clear();

        if(mCursor.moveToFirst()){
            do{
                _id.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.KEY_ID)));
                _location.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.KEY_LOCATION)));
                _organisers.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.KEY_ORGANISERS)));
                _reminder.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHandler.KEY_REMINDER)));
            }while(mCursor.moveToNext());
        }
        DisplayAdapter displayAdapter=new DisplayAdapter(ViewPreviousRecords.this,_id,_date,_location,_organisers,_reminder);
        donationList.setAdapter(displayAdapter);
        mCursor.close();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_previous_records, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
