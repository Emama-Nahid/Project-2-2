package com.example.C_Learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private Button strtquz01,bmark01 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        firebaseAuth = FirebaseAuth.getInstance();


        strtquz01=findViewById(R.id.strquz);
        bmark01=findViewById(R.id.bmark001);

        strtquz01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qrtt1= new Intent(HomePage.this,CatagoryActivity.class);
                startActivity(qrtt1);


            }

        });
        bmark01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bttk= new Intent(HomePage.this,BookmarkActivity.class);
                startActivity(bttk);


            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:{
                firebaseAuth.signOut();
                finish();
                Intent lggt= new Intent(HomePage.this,Page001.class);
                startActivity(lggt);


            }
        }
        return super.onOptionsItemSelected(item);
    }
}
