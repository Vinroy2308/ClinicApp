package com.example.medbay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medbay.Doctor1;
import com.example.medbay.PatientMod;
import com.example.medbay.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PatAdapter extends BaseAdapter {
    Context context;
    ArrayList<PatientMod> arrayList;

    public PatAdapter(Context context, ArrayList<PatientMod> arrayList) {
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
        convertView = inflater.inflate(R.layout.patient_row,null);
        TextView t_name = (TextView)convertView.findViewById(R.id.pat_name);
        TextView t_time = (TextView)convertView.findViewById(R.id.pat_app_time);
        TextView t_id = (TextView)convertView.findViewById(R.id.p_id);


        PatientMod pat = arrayList.get(position);
        t_id.setText(String.valueOf(pat.getId()));
        t_name.setText(pat.getPat_name());
        t_time.setText(pat.getAppointment_time());

        return convertView;
    }
}
