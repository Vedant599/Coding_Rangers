package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAct extends AppCompatActivity {
    String TAG="Database";
    public static List<StartupData> startList=new ArrayList<>();
    RecyclerView recyclerView;
    private Button CRM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        CRM=findViewById(R.id.CRM);
        CRM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DatabaseAct.this,Main2Activity.class));
            }
        });
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference("Startups");
        recyclerView = findViewById(R.id.startuplist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(DatabaseAct.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot d: dataSnapshot.getChildren())
               {
                   StartupData startupData=d.getValue(StartupData.class);
                   startList.add(startupData);
                  // Toast.makeText(DatabaseAct.this, "HI", Toast.LENGTH_SHORT).show();
               }
               //Toast.makeText(DatabaseAct.this, "HEllo", Toast.LENGTH_SHORT).show();
               StartupListAdapter startupListAdapter=new StartupListAdapter(startList);
               recyclerView.setAdapter(startupListAdapter);
               startupListAdapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
              // Toast.makeText(DatabaseAct.this, "HI", Toast.LENGTH_SHORT).show();
           }

       });








            }

}