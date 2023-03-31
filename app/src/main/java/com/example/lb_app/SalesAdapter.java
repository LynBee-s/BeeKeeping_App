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

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<Sales> data;
    HiveDB_Helper helper;
   HiveDB_Helper hiveDB_helper;
public SalesAdapter(Context context){this.inflater=LayoutInflater.from(context);}
public SalesAdapter(ArrayList<Sales>data){this.data=data;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.sales_list,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesAdapter.ViewHolder holder, int position) {
        holder.id4.setText(data.get(position).getID());
        holder.transid4.setText(data.get(position).getTrans_ID());
        holder.date4.setText(data.get(position).getDate());
        holder.descrip4.setText(data.get(position).getDescription());
        holder.amt4.setText(data.get(position).getAmount());
        holder.price4.setText(data.get(position).getPrice());
        holder.total4.setText(data.get(position).getTotal());
        holder.notes4.setText(data.get(position).getNotes());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        EditText id4,transid4,date4,descrip4,amt4,price4,total4,notes4;
        Button btnupdate;
        HiveDB_Helper helper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hiveDB_helper=new HiveDB_Helper(itemView.getContext());
            helper= new HiveDB_Helper(itemView.getContext().getApplicationContext(), "LBDB.db", null, 1);
            btnupdate=(Button) itemView.findViewById(R.id.btnupdate5);
            id4=(EditText) itemView.findViewById(R.id.id4);
            transid4=(EditText) itemView.findViewById(R.id.transid4);
            date4=(EditText) itemView.findViewById(R.id.date4);
            descrip4=(EditText) itemView.findViewById(R.id.descrip4);
            amt4=(EditText) itemView.findViewById(R.id.amt4);
            price4=(EditText)itemView.findViewById(R.id.price4);
            total4=(EditText) itemView.findViewById(R.id.total4);
            notes4=(EditText) itemView.findViewById(R.id.notes4);

            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        SQLiteDatabase db = helper.getReadableDatabase();
// New value for one column
                        ContentValues values = new ContentValues();
                        values.put(Structure_BBDD.COLUMNAID,id4.getText().toString() );
                        values.put(Structure_BBDD.COLUMNA2, transid4.getText().toString());
                        values.put(Structure_BBDD.COLUMNA3, date4.getText().toString());
                        values.put(Structure_BBDD.COLUMNA4, descrip4.getText().toString());
                        values.put(Structure_BBDD.COLUMNA5, amt4.getText().toString());
                        values.put(Structure_BBDD.COLUMNA6, price4.getText().toString());
                        values.put(Structure_BBDD.COLUMNA7, total4.getText().toString());
                        values.put(Structure_BBDD.COLUMNA8, notes4.getText().toString());
                        String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
                        String[] selectionArgs = {id4.getText().toString()};
                        int count = db.update(
                                Structure_BBDD.TABLE2,
                                values,
                                selection,
                                selectionArgs);
                        Toast.makeText(itemView.getContext(), "Register "+id4.getText()+" was successfully updated.",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(itemView.getContext(), "ERROR",Toast.LENGTH_LONG).show();
                    }
                }
            });


        }
    }
}
