package com.example.C_Learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.C_Learning.QustionActivity.FILE_NAME;
import static com.example.C_Learning.QustionActivity.KEY_NAME;

public class BookmarkActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private List<QustionModel> bookmarkList;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);
        Toolbar toolbar=findViewById(R.id.toolbar05);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Bookmarks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.rv02);
        preferences=getSharedPreferences(FILE_NAME , Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson= new Gson();

        getBookmarks();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);


        BookmarkAdapter adapter = new BookmarkAdapter(bookmarkList);
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        storebookmarks();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()== android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    private void getBookmarks(){
        String json =  preferences.getString(KEY_NAME,"");

        Type type = new TypeToken<List<QustionModel>>(){}.getType();

        bookmarkList = gson.fromJson(json,type);

        if (bookmarkList == null){
            bookmarkList = new ArrayList<>();
        }

    }
    private void storebookmarks(){
        String json = gson.toJson(bookmarkList);

        editor.putString(KEY_NAME,json);
        editor.commit();
    }


}
