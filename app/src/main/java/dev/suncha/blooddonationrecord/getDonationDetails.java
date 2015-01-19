package dev.suncha.blooddonationrecord;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class getDonationDetails extends ActionBarActivity {


    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    ImageView topimageview;
    TextView proud_tv;
    Button pick_date,saveButton;
    Switch reminderswitch;
    String reminder="";

    EditText display_date,display_location,display_organisers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_entry_layout);

        pick_date = (Button) findViewById(R.id.pick_date);
        reminderswitch=(Switch)findViewById(R.id.reminderswitch);

        final DatabaseHandler db = new DatabaseHandler(this);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        topimageview = (ImageView) findViewById(R.id.topimage);
        topimageview.getLayoutParams().height = height / 4;

        proud_tv = (TextView) findViewById(R.id.proud_text);
        proud_tv.getLayoutParams().height = height / 4;

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        display_date = (EditText) findViewById(R.id.button_pick_date);
        display_organisers=(EditText)findViewById(R.id.et_organisers);
        display_location=(EditText)findViewById(R.id.et_location);

        //display_date.setText(new StringBuilder().append(day).append("/")
        //       .append(month+1).append("/").append(year));
        showDate(year, month + 1, day);

        reminderswitch.setChecked(false);
        reminderswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    reminder="yes";
                }else{
                    reminder="no";
                }
            }
        });

        saveButton=(Button)findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display_date.getText().toString().matches("")||display_location.getText().toString().matches("")||display_organisers.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(),R.string.warning,Toast.LENGTH_SHORT).show();
                }else{
                    db.addRecord(new donationRecord(display_date.getText().toString(),display_location.getText().toString(),display_organisers.getText().toString(),reminder));

                }
            }
        });

    }

    private void showDate(int year, int month, int day) {
        display_date.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }


    //@Override
    @SuppressWarnings("deprecation")
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener
            = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2 + 1, arg3);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_donation_details, menu);
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
