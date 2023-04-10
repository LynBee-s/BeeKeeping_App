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

public class ExpenditureAdapter extends RecyclerView.Adapter<ExpenditureAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<Expenditure> data;
    HiveDB_Helper hiveDB_helper;
    private float TotalI;
    private float AmtI;
    private float PriceI;

    public ExpenditureAdapter(Context context){
        this.inflater=LayoutInflater.from(context);
    }
    public ExpenditureAdapter(ArrayList<Expenditure>data){this.data=data;}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.exp_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenditureAdapter.ViewHolder holder, int position) {
        holder.id5.setText(data.get(position).getID());
        holder.transid5.setText(data.get(position).getTrans_ID());
        holder.date5.setText(data.get(position).getDate());
        holder.descrip5.setText(data.get(position).getDescription());
        holder.amt5.setText(data.get(position).getAmount());
        holder.price5.setText(data.get(position).getPrice());
        holder.total5.setText(data.get(position).getTotal());
        holder.notes5.setText(data.get(position).getNotes());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        EditText id5,transid5,date5,descrip5,amt5,price5,total5,notes5;
        Button btnupdate2;
        HiveDB_Helper helper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hiveDB_helper=new HiveDB_Helper(itemView.getContext());
            helper= new HiveDB_Helper(itemView.getContext().getApplicationContext(), "LBDB.db", null, 1);

            btnupdate2=(Button) itemView.findViewById(R.id.updateexp);

            id5=(EditText) itemView.findViewById(R.id.id5);
            transid5=(EditText) itemView.findViewById(R.id.transid5);
            date5=(EditText) itemView.findViewById(R.id.date5);
            descrip5=(EditText) itemView.findViewById(R.id.descrip5);
            amt5=(EditText) itemView.findViewById(R.id.amt5);
            price5=(EditText)itemView.findViewById(R.id.price5);
            total5=(EditText) itemView.findViewById(R.id.total5);
            notes5=(EditText) itemView.findViewById(R.id.notes5);

            btnupdate2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        PriceI= Float.parseFloat(price5.getText().toString());
                        AmtI=Float.parseFloat(amt5.getText().toString());
                        TotalI=PriceI*AmtI;
                        SQLiteDatabase db = helper.getReadableDatabase();
// New value for one column
                        ContentValues values = new ContentValues();
                        values.put(Structure_BBDD.COLUMNBID,id5.getText().toString() );
                        values.put(Structure_BBDD.COLUMNB2, transid5.getText().toString());
                        values.put(Structure_BBDD.COLUMNB3, date5.getText().toString());
                        values.put(Structure_BBDD.COLUMNB4, descrip5.getText().toString());
                        values.put(Structure_BBDD.COLUMNB5, amt5.getText().toString());
                        values.put(Structure_BBDD.COLUMNB6, price5.getText().toString());
                        values.put(Structure_BBDD.COLUMNB7, TotalI);
                        values.put(Structure_BBDD.COLUMNB8, notes5.getText().toString());
                        String selection = Structure_BBDD.COLUMNBID + " LIKE ?";
                        String[] selectionArgs = {id5.getText().toString()};
                        int count = db.update(
                                Structure_BBDD.TABLE3,
                                values,
                                selection,
                                selectionArgs);
                        Toast.makeText(itemView.getContext(), "Register "+id5.getText()+" was successfully updated.",Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(itemView.getContext(), "ERROR",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }
}
