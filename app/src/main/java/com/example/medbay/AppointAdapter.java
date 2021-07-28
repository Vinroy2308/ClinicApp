package com.example.medbay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AppointAdapter extends BaseAdapter {
    Context context;
    ArrayList<AppointModel> arrayList;

    public AppointAdapter(Context context, ArrayList<AppointModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.appoint_list_layout,null);
        TextView name = convertView.findViewById(R.id.dc_name_tv);
        TextView date = convertView.findViewById(R.id.ap_date_tv);
        TextView time = convertView.findViewById(R.id.ap_time_tv);

        AppointModel model = arrayList.get(position);
        name.setText(model.getName());
        date.setText(model.getAppDate());
        time.setText(model.getAppTime());


        return convertView;
    }
}
