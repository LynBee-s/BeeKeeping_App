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

public class HiveLocationAdapter extends RecyclerView.Adapter<HiveLocationAdapter.ViewHolder>{
        LayoutInflater inflater;
        ArrayList<Hives> data;
        HiveDB_Helper helper;
        HiveDB_Helper hiveDB_helper;
    public  HiveLocationAdapter(Context context){
        this.inflater=LayoutInflater.from(context);
    }
    public HiveLocationAdapter(ArrayList<Hives>data){this.data=data;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.hive_location,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HiveLocationAdapter.ViewHolder holder, int position) {
        holder.id6.setText(data.get(position).getID());
        holder.hiveid.setText(data.get(position).getHiveID());
        holder.hivelocate.setText(data.get(position).getLocation());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        Button updateloc;
        EditText id6,hiveid, hivelocate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hiveDB_helper=new HiveDB_Helper(itemView.getContext());
            helper= new HiveDB_Helper(itemView.getContext().getApplicationContext(), "LBDB.db", null, 1);
            id6=(EditText) itemView.findViewById(R.id.id6);
            hiveid=(EditText) itemView.findViewById(R.id.hiveid2);
            hivelocate=(EditText) itemView.findViewById(R.id.location2);
            updateloc=(Button) itemView.findViewById(R.id.updatehiveloc);

            updateloc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                    SQLiteDatabase db = helper.getReadableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMNID,id6.getText().toString());
                    values.put(Structure_BBDD.COLUMN2,hiveid.getText().toString());
                    values.put(Structure_BBDD.COLUMN7, hivelocate.getText().toString());

                    Toast.makeText(itemView.getContext(), "Hive location for hive #"+hiveid.getText()+" was successfully updated.",Toast.LENGTH_LONG).show();
                    String selection = Structure_BBDD.COLUMNID + " LIKE ?";
                    String[] selectionArgs = {id6.getText().toString()};
                    db.update(Structure_BBDD.TABLE1,
                            values,
                            selection,
                            selectionArgs);
                } catch (Exception e) {
                    Toast.makeText(itemView.getContext(), "ERROR",Toast.LENGTH_LONG).show();
                }
                }
            });
        }
    }
}
