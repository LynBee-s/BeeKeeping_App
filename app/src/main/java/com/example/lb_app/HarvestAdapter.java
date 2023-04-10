package com.example.lb_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class HarvestAdapter extends RecyclerView.Adapter<HarvestAdapter.ViewHolder>{
        LayoutInflater inflater;
        ArrayList<Harvest> data;
        HiveDB_Helper hiveDB_helper;
        public HarvestAdapter(Context context){this.inflater=LayoutInflater.from(context);}
        public HarvestAdapter(ArrayList<Harvest>data){this.data=data;}
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.harvest_rec,parent,false);
                return new ViewHolder(view);
                }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id8.setText(data.get(position).getID());
        holder.hiveid8.setText(data.get(position).getHive_ID());
        holder.date8.setText(data.get(position).getDate());
        holder.amt8.setText(data.get(position).getAmount_H());
        holder.other8.setText(data.get(position).getOther());
        holder.amtt8.setText(data.get(position).getAmount_O());
        holder.notes8.setText(data.get(position).getNotes());

    }
        @Override
        public int getItemCount() {
                return data.size();
                }
        public  class ViewHolder extends RecyclerView.ViewHolder {
            EditText id8,hiveid8,date8,amt8,other8,amtt8,notes8;
            Button btnupdate8;
            HiveDB_Helper helper;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                hiveDB_helper=new HiveDB_Helper(itemView.getContext());
                helper= new HiveDB_Helper(itemView.getContext().getApplicationContext(), "LBDB.db", null, 1);

                btnupdate8=(Button) itemView.findViewById(R.id.btnupdate8);

                id8=(EditText) itemView.findViewById(R.id.id7);
                hiveid8=(EditText) itemView.findViewById(R.id.hiveid7);
                date8=(EditText) itemView.findViewById(R.id.date7);
                amt8=(EditText) itemView.findViewById(R.id.amount7);
                other8=(EditText) itemView.findViewById(R.id.other7);
                amtt8=(EditText)itemView.findViewById(R.id.amount8);
                notes8=(EditText) itemView.findViewById(R.id.notes8);

                btnupdate8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            SQLiteDatabase db = helper.getReadableDatabase();
        // New value for one column
                            ContentValues values = new ContentValues();
                            values.put(Structure_BBDD.COLUMNCID,id8.getText().toString() );
                            values.put(Structure_BBDD.COLUMNC2, hiveid8.getText().toString());
                            values.put(Structure_BBDD.COLUMNC3, date8.getText().toString());
                            values.put(Structure_BBDD.COLUMNC4, amt8.getText().toString());
                            values.put(Structure_BBDD.COLUMNC5, other8.getText().toString());
                            values.put(Structure_BBDD.COLUMNC6, amtt8.getText().toString());
                            values.put(Structure_BBDD.COLUMNC7, notes8.getText().toString());
                            String selection = Structure_BBDD.COLUMNBID + " LIKE ?";
                            String[] selectionArgs = {id8.getText().toString()};
                            int count = db.update(
                                    Structure_BBDD.TABLE4,
                                    values,
                                    selection,
                                    selectionArgs);
                            Toast.makeText(itemView.getContext(), "Register "+id8.getText()+" was successfully updated.",Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(itemView.getContext(), "ERROR",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }
        }
