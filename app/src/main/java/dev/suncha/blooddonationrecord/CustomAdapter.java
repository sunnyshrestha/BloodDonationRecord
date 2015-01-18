package dev.suncha.blooddonationrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sunny on 1/18/2015.
 */
public class CustomAdapter extends BaseAdapter {

    String[] title;
    Context context;
    Integer[] image;
    private static LayoutInflater inflater=null;

    public CustomAdapter(MainActivity mainActivity, String[] itemname, Integer[] imageId) {
        title=itemname;
        context=mainActivity;
        image=imageId;

        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    public class Holder{
        TextView tv;
        ImageView img;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position,View view,ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView=inflater.inflate(R.layout.row_list,null);
        holder.tv=(TextView)rowView.findViewById(R.id.Itemname);
        holder.img=(ImageView)rowView.findViewById(R.id.icon);

        holder.tv.setText(title[position]);
        holder.img.setImageResource(image[position]);

        return rowView;
    }
}
