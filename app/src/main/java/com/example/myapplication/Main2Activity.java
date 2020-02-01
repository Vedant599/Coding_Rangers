package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
private Button Pie,Bar,Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Pie=findViewById(R.id.ButtonPie);
        Bar=findViewById(R.id.ButtonBar);
        Database=findViewById(R.id.ButtonDatabase);
        Pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Main2Activity.this, "Entered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main2Activity.this, CRMActivity.class);
                intent.putExtra("method","pie");
                startActivity(intent);
            }
        });
        Bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CRMActivity.class);
                intent.putExtra("method","bars");
                startActivity(intent);
            }
        });
        Database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, DatabaseAct.class);
                startActivity(intent);
            }
        });
    }
}
