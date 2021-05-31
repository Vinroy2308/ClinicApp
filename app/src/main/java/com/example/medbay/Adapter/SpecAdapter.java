package com.example.medbay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medbay.Doctor1;
import com.example.medbay.R;

import java.util.ArrayList;

public class SpecAdapter extends BaseAdapter {

    Context context;
    ArrayList<Doctor1> arrayList;
    public SpecAdapter(Context context, ArrayList<Doctor1> arrayList) {
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
            convertView = inflater.inflate(R.layout.my_row,null);
            TextView t_id = (TextView)convertView.findViewById(R.id.spec_id_txt);
            TextView t_name = (TextView)convertView.findViewById(R.id.spec_name_txt);


            Doctor1 doc = arrayList.get(position);
            t_id.setText(String.valueOf(doc.getId()));
            t_name.setText(doc.getName());

        return convertView;
    }
}
