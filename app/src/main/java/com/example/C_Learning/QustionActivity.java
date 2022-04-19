package com.example.C_Learning;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class QustionActivity extends AppCompatActivity {


    public static final String FILE_NAME = "C_Learning";
    public static final String KEY_NAME = "Qustions";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    private TextView Qustion,noIndicator;
    private FloatingActionButton bookmarkbtn;
    private LinearLayout optionContainer;
    private Button sharebtn,nextbnt;
    private int count=0;
    private  List<QustionModel> list;
    private int position=0;
    private int score=0;
    private String category;
    private int setNo;
    private Dialog loadingDialog;

    private List<QustionModel> bookmarkList;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Gson gson;
    private int matchedQustionPosition;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qustion);
        Toolbar toolbarp01 = findViewById(R.id.toolbar003);

        setSupportActionBar(toolbarp01);

        Qustion=findViewById(R.id.textView2p);
        noIndicator=findViewById(R.id.textView3p);
        bookmarkbtn=findViewById(R.id.Bookmark001);
        optionContainer=findViewById(R.id.linearLayout2);
        sharebtn=findViewById(R.id.buttonp05);
        nextbnt=findViewById(R.id.buttonp06);

        preferences=getSharedPreferences(FILE_NAME , Context.MODE_PRIVATE);
        editor = preferences.edit();
        gson= new Gson();

        getBookmarks();
        bookmarkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(modelMatch()){
                    bookmarkList.remove(matchedQustionPosition);
                    bookmarkbtn.setImageDrawable(getDrawable(R.drawable.bookmark_border));
                }else {
                    bookmarkList.add(list.get(position));
                    bookmarkbtn.setImageDrawable(getDrawable(R.drawable.bookmark));
                }

            }
        });

        category = getIntent().getStringExtra("category");
        setNo = getIntent().getIntExtra("setNo",1);


        loadingDialog= new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.button_shape_rounded));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT );
        loadingDialog.setCancelable(false);


        list = new ArrayList<>();

        loadingDialog.show();
        myRef.child("Sets").child(category).child("qustions").orderByChild("SetNo").equalTo(setNo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1: snapshot.getChildren()){
                    list.add(snapshot1.getValue(QustionModel.class));
                }
                if(list.size() > 0){

                    for(int i=0;i< 4 ;i++){
                        optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View v) {
                                checkAnswer((Button) v);

                            }
                        });
                    }
                    playAnim(Qustion,0,list.get(position).getQustion());

                    nextbnt.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onClick(View v) {
                            nextbnt.setEnabled(false);
                            nextbnt.setAlpha(0.7f);
                            enableoption(true);
                            position++;
                            if(position ==list.size()){
                                Intent scoreIntent = new Intent(QustionActivity.this,ScoreActivity.class);
                                scoreIntent.putExtra("score",score);
                                scoreIntent.putExtra("total",list.size());
                                startActivity(scoreIntent);
                                finish();
                                return;
                            }
                            count=0;
                            playAnim(Qustion,0,list.get(position).getQustion() );

                        }
                    });
                }else{
                    finish();
                    Toast.makeText(QustionActivity.this, "No Qustions", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(QustionActivity.this,error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        storebookmarks();
    }


    private void playAnim(final View view, final int value, final String data ){

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

                if(value ==0 && count < 4 ){
                    String  option="";
                    if(count == 0){
                        option = list.get(position).getOptionA();
                    }else if(count == 1){
                        option = list.get(position).getOptionB();

                    }else if(count == 2){
                        option = list.get(position).getOptionC();

                    }else if(count == 3){
                        option = list.get(position).getOptionD();

                    }
                    playAnim(optionContainer.getChildAt(count),0,option);
                    count++;
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animation) {

                if(value==0){
                    try{
                        ((TextView)view).setText(data);
                        noIndicator.setText(position+1+"/"+list.size());
                        if(modelMatch()){
                            bookmarkbtn.setImageDrawable(getDrawable(R.drawable.bookmark));
                        }else {
                            bookmarkbtn.setImageDrawable(getDrawable(R.drawable.bookmark_border));
                        }

                    }catch (ClassCastException ex) {
                        ((Button)view).setText(data);

                    }
                    view.setTag(data);
                    playAnim(view,1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedoption){
        enableoption(false);
        nextbnt.setEnabled(true);
        nextbnt.setAlpha(1);
        if(selectedoption.getText().toString().equals(list.get(position).getCorrectANS())){
            //correct
          selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
          score++;

        }else {
            //incorrect
            selectedoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
           Button correctoption =(Button) optionContainer.findViewWithTag(list.get(position).getCorrectANS());
            correctoption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));


        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableoption(boolean enable){
        for(int i=0;i< 4 ;i++){
             optionContainer.getChildAt(i).setEnabled(enable);
             if (enable){
                 optionContainer.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
             }
        }
    }

    private void getBookmarks(){
      String json =  preferences.getString(KEY_NAME,"");

      Type type = new TypeToken<List<QustionModel>>(){}.getType();

      bookmarkList = gson.fromJson(json,type);

      if (bookmarkList == null){
          bookmarkList = new ArrayList<>();
      }

    }

    private Boolean modelMatch() {
        boolean mached = false;
        int i=0;
        for (QustionModel model : bookmarkList) {
            if (model.getQustion().equals(list.get(position).getQustion())
                    && model.getCorrectANS().equals(list.get(position).getCorrectANS())
                    && model.getSetNo() == list.get(position).getSetNo()) {
                mached = true;
                matchedQustionPosition=i;
            }
            i++;
        }

        return mached;
    }

    private void storebookmarks(){
        String json = gson.toJson(bookmarkList);

        editor.putString(KEY_NAME,json);
        editor.commit();
    }

}
