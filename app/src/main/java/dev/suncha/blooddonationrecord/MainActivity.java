package dev.suncha.blooddonationrecord;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    ListView lv;
    Context context;
    ImageView topimageview;
    TextView proud_tv;

    String[] itemname ={
            "Add new entry",
            "View previous entries"
    };

    Integer[] imageId={
            R.drawable.add_circle,
            R.drawable.view_list
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        lv=(ListView) findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(this, itemname,imageId));

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int height = size.y;

        topimageview=(ImageView)findViewById(R.id.topimage);
        topimageview.getLayoutParams().height=height/4;

        proud_tv=(TextView)findViewById(R.id.proud_text);
        proud_tv.getLayoutParams().height=height/4;

        final Intent donationDetails=new Intent(this,getDonationDetails.class);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(context, "You Clicked " + itemname[position], Toast.LENGTH_SHORT).show();
                switch(position){
                    case 0:
                        startActivity(donationDetails);
                        break;


                }
            }
        });
    }

}
