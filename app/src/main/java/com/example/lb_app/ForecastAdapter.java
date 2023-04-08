package com.example.lb_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.Calendar;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<Forecast> data;
    HiveDB_Helper helper;
    HiveDB_Helper hiveDB_helper;
    public  ForecastAdapter(Context context){
        this.inflater=LayoutInflater.from(context);
    }
    public  ForecastAdapter(ArrayList<Forecast> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastAdapter.ViewHolder holder, int position) {
        holder.date.setText((data.get(position).getDate()));
        holder.day.setText((data.get(position).getDay()));
        holder.temp.setText((data.get(position).getTemperature()));
        holder.cond.setText((data.get(position).getConditions()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date, day, temp, cond;
        Button btninsert;

        public ViewHolder(final View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date6);
            day = (TextView) itemView.findViewById(R.id.day6);
            temp = (TextView) itemView.findViewById(R.id.temperature6);
            cond = (TextView) itemView.findViewById(R.id.weather6);
            btninsert = (Button) itemView.findViewById(R.id.insertf);

            Context context = null;
            btninsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        String DateNow = MonthDay.now().toString() + "-" + calendar.get(Calendar.YEAR);

                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(Structure_BBDD.COLUMNCID, DateNow);
                        values.put(Structure_BBDD.COLUMNC2, day.getText().toString());
                        values.put(Structure_BBDD.COLUMNC3, temp.getText().toString());
                        values.put(Structure_BBDD.COLUMNC4, cond.getText().toString());
                        long newRowId = db.insert(Structure_BBDD.TABLE4, null, values);
                        Toast.makeText(itemView.getContext(), "The register was saved with ID: " + newRowId, Toast.LENGTH_LONG).show();
                        //Clear text from fields
                        date.setText("");
                        day.setText("");
                        temp.setText("");
                        cond.setText("");
                    } catch (Exception e) {
                        Toast.makeText(itemView.getContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }
}
