package com.example.C_Learning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private TextView score, total;
    private Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score=findViewById(R.id.score);
        total=findViewById(R.id.total);
        doneBtn=findViewById(R.id.done_btn);

        score.setText(String.valueOf(getIntent().getIntExtra("score",0)));
        total.setText("Out Of "+String.valueOf(getIntent().getIntExtra("total",0)));


        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
