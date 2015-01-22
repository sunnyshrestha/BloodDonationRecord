package dev.suncha.blooddonationrecord;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sunny on 1/20/2015.
 */
public class DisplayAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> _id;
    private ArrayList<String> _date;
    private ArrayList<String> _location;
    private ArrayList<String> _organisers;
    private ArrayList<String> _reminder;

    public DisplayAdapter(Context c, ArrayList<String> _id, ArrayList<String> _date, ArrayList<String> _location, ArrayList<String> _organisers, ArrayList<String> _reminder) {
        this.mContext = c;
        this._id = _id;
        this._date = _date;
        this._location = _location;
        this._organisers = _organisers;
        this._reminder = _reminder;
    }

    @Override
    public int getCount() {
        return _id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewHolder mHolder;
        LayoutInflater layoutInflater;

        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.listcell, null);
            mHolder = new ListViewHolder();

            WindowManager wm=(WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;

            mHolder.tv_id = (TextView) convertView.findViewById(R.id.tv_id);
            mHolder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            mHolder.tv_location = (TextView) convertView.findViewById(R.id.tv_location);
            mHolder.tv_organisers = (TextView) convertView.findViewById(R.id.tv_organisers);
            mHolder.tv_reminder = (TextView) convertView.findViewById(R.id.tv_reminder);

            mHolder.tv_id.getLayoutParams().width=width/10;
            mHolder.tv_reminder.getLayoutParams().width=width/10;
            mHolder.tv_organisers.getLayoutParams().width=width/4;
            mHolder.tv_location.getLayoutParams().width=width/4;
            mHolder.tv_date.getLayoutParams().width=width/4;
            mHolder.tv_id.getLayoutParams().width=width/10;

            convertView.setTag(mHolder);
        } else {
            mHolder = (ListViewHolder) convertView.getTag();
        }


        mHolder.tv_id.setText(_id.get(position));
        mHolder.tv_date.setText(_date.get(position));
        mHolder.tv_location.setText(_location.get(position));
        mHolder.tv_organisers.setText(_organisers.get(position));
        mHolder.tv_reminder.setText(_reminder.get(position));

        return convertView;
    }

    public class ListViewHolder {
        TextView tv_id;
        TextView tv_date;
        TextView tv_location;
        TextView tv_organisers;
        TextView tv_reminder;
    }
}
