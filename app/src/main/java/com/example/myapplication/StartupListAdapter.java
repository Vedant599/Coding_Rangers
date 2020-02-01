package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StartupListAdapter extends RecyclerView.Adapter<StartupListAdapter.Holder> {
    Context context;
    List<StartupData> startupData;
    public StartupListAdapter(Context context, List startupData) {
        super();
        this.context=context;
        this.startupData=startupData;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.startup_list_item,parent,false);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(startupData.get(position).getCompanyName());
        holder.worth.setText(startupData.get(position).getNetWorth());
        Log.d("kun", "onBindViewHolder: "+startupData.get(position).getCompanyName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView name,worth;
        public Holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.stname);
            worth=itemView.findViewById(R.id.stworth);

        }
    }
}