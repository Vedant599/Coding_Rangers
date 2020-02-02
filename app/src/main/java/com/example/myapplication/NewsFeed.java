package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.sax.SAXTransformerFactory;

public class NewsFeed extends AppCompatActivity {
    ArrayList<String> news;
    String TAG="kun";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        Intent intent=getIntent();

        String name=intent.getStringExtra("Name");
        recyclerView=findViewById(R.id.news);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Startups");

        news = new ArrayList();
        ref.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot d: dataSnapshot.child("News").getChildren()){
                    news.add(d.getValue(String.class));
                }
                Log.d(TAG, "onDataChange: \n"+news);
                recyclerView.setLayoutManager(new LinearLayoutManager(NewsFeed.this));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new NewsAdapter(NewsFeed.this,news));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }}
