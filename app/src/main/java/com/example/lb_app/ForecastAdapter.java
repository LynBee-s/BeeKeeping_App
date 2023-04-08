package com.example.lb_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {
    LayoutInflater inflater;
    ArrayList<Forecast> data;
    public  ForecastAdapter(Context context){
        this.inflater=LayoutInflater.from(context);
    }
    public  ForecastAdapter(ArrayList<Forecast> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public ForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        TextView date,day,temp,cond;
        public ViewHolder(final View itemView) {
            super(itemView);
            date=(TextView) itemView.findViewById(R.id.rvid);
            day=(TextView) itemView.findViewById(R.id.rvhid);
            temp=(TextView) itemView.findViewById(R.id.rvdate);
            cond=(TextView)itemView.findViewById(R.id.rvframes);

        }
    }
}
