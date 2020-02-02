package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class StartupListAdapter extends RecyclerView.Adapter<StartupListAdapter.ViewHolder> {
   Context context;
    List<StartupData> startupData;
    public StartupListAdapter(List startupData) {
        super();

        this.startupData=startupData;
    }

    @NonNull
    @Override
    public StartupListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.startup_list_item,parent,false);
        ViewHolder holder=new ViewHolder(v);
        context=parent.getContext();
        Log.d("kun", "onCreateViewHolder: ");
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setData(startupData.get(position).getCompanyName(),startupData.get(position).getNetWorth());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,NewsFeed.class);
                intent.putExtra("Name",startupData.get(position).getCompanyName());
                context.startActivity(intent);
            }
        });
        Log.d("kun", "onBindViewHolder: "+startupData.get(position).getCompanyName());
    }


    @Override
    public int getItemCount() {
        return startupData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,worth;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.stname);
            worth=itemView.findViewById(R.id.stworth);
        }
        public void setData(String Name,String Worth)
        {
            name.setText("Name :"+Name);
            worth.setText("Net Worth: "+Worth+" Billion");
        }
        public void add(List<StartupData> chats)
        {
            startupData.addAll(chats);
            notifyDataSetChanged();
        }
    }
}