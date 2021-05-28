package com.example.medbay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList spec_id, spec_name;

    CustomAdapter(Context context,ArrayList spec_id, ArrayList spec_name) {
        this.context = context;
        this.spec_id = spec_id;
        this.spec_name = spec_name;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CustomAdapter.MyViewHolder holder, int position) {
        holder.spec_id_txt.setText(String.valueOf(spec_id.get(position)));
        holder.spec_name_txt.setText(String.valueOf(spec_name.get(position)));
    }

    @Override
    public int getItemCount() {
        return spec_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView spec_id_txt, spec_name_txt;
        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            spec_id_txt = itemView.findViewById(R.id.spec_id);
            spec_name_txt = itemView.findViewById(R.id.spec_name);
        }
    }
}
