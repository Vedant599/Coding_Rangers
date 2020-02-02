package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.ContentHandler;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter <NewsAdapter.NHolder>{
    Context context;
    List news;
    NewsAdapter(Context context,List n){
        this.context=context;
        news=n;
    }
    @NonNull
    @Override
    public NHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);

        return new NHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull NHolder holder, int position) {
        holder.textView.setText((CharSequence) news.get(position));

    }
    @Override
    public int getItemCount() {
        return news.size();
    }
    class NHolder extends RecyclerView.ViewHolder {
        TextView textView;
        NHolder(View view){
            super(view);
            textView=view.findViewById(R.id.newsdesc);
        }
    }
}
