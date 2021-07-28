package com.example.medbay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medbay.DocPatient;
import com.example.medbay.R;

import java.util.List;

public class DocPatientAdapter extends BaseAdapter {

    Context context;
    List<DocPatientModel> docpatList;

    public DocPatientAdapter(Context context, List<DocPatientModel> docpatList) {
        this.context = context;
        this.docpatList = docpatList;
    }


    @Override
    public int getCount() {
        return this.docpatList.size();
    }

    @Override
    public Object getItem(int position) {
        return docpatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.patient_doc_list, null);
        TextView med_name = (TextView)convertView.findViewById(R.id.med_name);
        TextView dosage = (TextView)convertView.findViewById(R.id.med_dosage);
        TextView day = (TextView)convertView.findViewById(R.id.med_days);

        DocPatientModel dc = docpatList.get(position);
        med_name.setText(dc.getMedicine());
        dosage.setText(dc.getDosage());
        day.setText(dc.getDays());

        return convertView;
    }
}
