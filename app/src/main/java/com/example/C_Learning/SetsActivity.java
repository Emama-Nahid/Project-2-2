package com.example.C_Learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.GridView;

public class SetsActivity extends AppCompatActivity {

    private GridView gridView01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sets);
        Toolbar toolbar01 = findViewById(R.id.toolbar002);
        setSupportActionBar(toolbar01);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        gridView01=findViewById(R.id.gridview001);

         Grid_Adapter adapter=new Grid_Adapter(getIntent().getIntExtra("sets",0),getIntent().getStringExtra("title"));
         gridView01.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId() == android.R.id.home) {
           finish();
       }
        return super.onOptionsItemSelected(item);
    }
}
