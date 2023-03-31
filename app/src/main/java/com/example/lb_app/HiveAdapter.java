package com.example.lb_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HiveAdapter extends RecyclerView.Adapter<HiveAdapter.ViewHolder>{
    LayoutInflater inflater;
    ArrayList<Hives> data;

    public  HiveAdapter(Context context){
        this.inflater=LayoutInflater.from(context);
    }
public  HiveAdapter(ArrayList<Hives> data) {
    this.data = data;
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.hiverec_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( HiveAdapter.ViewHolder holder, int position) {
        holder.tvid.setText(data.get(position).getID());
        holder.tvhid.setText(data.get(position).getHiveID());
        holder.tvdate.setText(data.get(position).getDate());
        holder.tvhivestat.setText(data.get(position).getHive_Stat());
        holder.tvframes.setText(data.get(position).getFrames());
        holder.tvpop.setText(data.get(position).getPopulation());
        holder.tvloc.setText(data.get(position).getLocation());
        holder.tvnotes.setText(data.get(position).getNotes());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvid,tvhid,tvdate,tvhivestat,tvframes,tvpop,tvloc,tvnotes;

        public ViewHolder(final View itemView) {
            super(itemView);
            tvid=(TextView) itemView.findViewById(R.id.rvid);
            tvhid=(TextView) itemView.findViewById(R.id.rvhid);
            tvdate=(TextView) itemView.findViewById(R.id.rvdate);
            tvframes=(TextView)itemView.findViewById(R.id.rvframes);
            tvhivestat=(TextView) itemView.findViewById(R.id.rvhivestat);
            tvpop=(TextView) itemView.findViewById(R.id.rvpop);
            tvloc=(TextView) itemView.findViewById(R.id.rvlocate);
            tvnotes=(TextView) itemView.findViewById(R.id.rvnote);
        }
    }
}
