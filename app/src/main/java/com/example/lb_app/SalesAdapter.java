package com.example.lb_app;

import static com.example.lb_app.Structure_BBDD.TABLE2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder>{
    ArrayList<Sales> data;
    HiveDB_Helper hiveDB_helper;
    private float TotalI;
    private float AmtI;
    private float PriceI;

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
        Button btnupdate,btndelete;
        HiveDB_Helper helper;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hiveDB_helper=new HiveDB_Helper(itemView.getContext());
            helper= new HiveDB_Helper(itemView.getContext().getApplicationContext());
            btnupdate= itemView.findViewById(R.id.btnupdate5);
            btndelete= itemView.findViewById(R.id.delete1);

            id4= itemView.findViewById(R.id.id4);
            transid4= itemView.findViewById(R.id.transid4);
            date4= itemView.findViewById(R.id.date4);
            descrip4= itemView.findViewById(R.id.descrip4);
            amt4= itemView.findViewById(R.id.amt4);
            price4= itemView.findViewById(R.id.price4);
            total4= itemView.findViewById(R.id.total4);
            notes4= itemView.findViewById(R.id.notes4);

            btndelete.setOnClickListener(v -> {
                try {
                    SQLiteDatabase db = helper.getWritableDatabase();
                    String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
                    String[] selectionArgs = {id4.getText().toString()};
                    AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
                    builder.setMessage("Are you sure you would like to delete this register?").setPositiveButton("yes", (dialogInterface, i) -> {
                        db.delete(TABLE2, selection, selectionArgs);
                        Toast.makeText(itemView.getContext(), "The register has been deleted", Toast.LENGTH_LONG).show();
                        id4.setText("");
                        transid4.setText("");
                        date4.setText("");
                        descrip4.setText("");
                        amt4.setText("");
                        price4.setText("");
                        total4.setText("");
                        notes4.setText("");
                    })
                            .setNegativeButton("No", (dialog, which) -> {

                            }).show();
                } catch (Exception e) {
                    Toast.makeText(itemView.getContext(), "ERROR", Toast.LENGTH_LONG).show();
                }
            });

            btnupdate.setOnClickListener(v -> {
                try {
                    PriceI= Float.parseFloat(price4.getText().toString());
                    AmtI=Float.parseFloat(amt4.getText().toString());
                    TotalI=PriceI*AmtI;
                    SQLiteDatabase db = helper.getReadableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(Structure_BBDD.COLUMNAID,id4.getText().toString() );
                    values.put(Structure_BBDD.COLUMNA2, transid4.getText().toString());
                    values.put(Structure_BBDD.COLUMNA3, date4.getText().toString());
                    values.put(Structure_BBDD.COLUMNA4, descrip4.getText().toString());
                    values.put(Structure_BBDD.COLUMNA5, amt4.getText().toString());
                    values.put(Structure_BBDD.COLUMNA6, price4.getText().toString());
                    values.put(Structure_BBDD.COLUMNA7, TotalI);
                    values.put(Structure_BBDD.COLUMNA8, notes4.getText().toString());
                    String selection = Structure_BBDD.COLUMNAID + " LIKE ?";
                    String[] selectionArgs = {id4.getText().toString()};

                    db.update(TABLE2, values, selection, selectionArgs);
                    Toast.makeText(itemView.getContext(), "Register "+id4.getText()+" was successfully updated.",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(itemView.getContext(), "ERROR",Toast.LENGTH_LONG).show();
                }
            });


        }
    }
}
