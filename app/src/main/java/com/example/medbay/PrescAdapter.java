package com.example.medbay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PrescAdapter extends BaseAdapter {
    Context context;
    ArrayList<PrescModel> arrayList;

    public PrescAdapter(Context context, ArrayList<PrescModel> arrayList) {
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
        convertView = inflater.inflate(R.layout.patient_doc_list,null);
        TextView med = convertView.findViewById(R.id.med_name);
        TextView dos = convertView.findViewById(R.id.med_dosage);
        TextView day = convertView.findViewById(R.id.med_days);

        PrescModel mod = arrayList.get(position);
        med.setText(mod.getMedicine());
        dos.setText(mod.getDosage());
        day.setText(mod.getDays());


        return convertView;
    }
}
