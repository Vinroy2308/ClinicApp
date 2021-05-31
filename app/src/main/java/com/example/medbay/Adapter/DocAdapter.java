package com.example.medbay.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.medbay.Doctor1;
import com.example.medbay.Doctor2;
import com.example.medbay.R;
import com.example.medbay.database.Medbay;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DocAdapter extends BaseAdapter {

    Context context;
    ArrayList<Doctor2> arrayList;

    public DocAdapter(Context context, ArrayList<Doctor2> arrayList) {
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
        convertView = inflater.inflate(R.layout.doc_row,null);
        TextView t_id = (TextView)convertView.findViewById(R.id.spec_id_txt);
        TextView t_name = (TextView)convertView.findViewById(R.id.spec_name_txt);
        TextView s_name = (TextView)convertView.findViewById(R.id.special_txt);


        Doctor2 doc = arrayList.get(position);
        t_id.setText(String.valueOf(doc.getId()));
        t_name.setText(doc.getName());
        s_name.setText(doc.getSpecialize());

        return convertView;

    }
}
