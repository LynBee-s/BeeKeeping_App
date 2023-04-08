package com.example.lb_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
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

    public ForecastAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public ForecastAdapter(ArrayList<Forecast> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast, parent, false);
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
        TextView date, temp;
        EditText day, cond;
        Button btnUpdate;

        public ViewHolder(final View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date6);
            day = (EditText) itemView.findViewById(R.id.day6);
            temp = (EditText) itemView.findViewById(R.id.temperature6);
            cond = (EditText) itemView.findViewById(R.id.weather6);
            btnUpdate = (Button) itemView.findViewById(R.id.insertf);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        SQLiteDatabase db = helper.getReadableDatabase();
// New value for one column
                        ContentValues values = new ContentValues();

                        //values.put(Structure_BBDD.COLUMNCID, date.getText().toString());
                        values.put(Structure_BBDD.COLUMNC2, day.getText().toString());
                        values.put(Structure_BBDD.COLUMNC3, temp.getText().toString());
                        values.put(Structure_BBDD.COLUMNC4, cond.getText().toString());
                        String selection = Structure_BBDD.COLUMNCID + " LIKE ?";
                        String[] selectionArgs = {date.getText().toString()};
                        int count = db.update(
                                Structure_BBDD.TABLE4,
                                values,
                                selection,
                                selectionArgs);

                        Toast.makeText(itemView.getContext(), "Weather forecast for" + date.getText() + " has been updated.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(itemView.getContext(), "ERROR: Unable to update weather forecast. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }
        }
    }

