package com.example.C_Learning;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Page001 extends AppCompatActivity {

    private Button log01,reg01;
    private EditText mail01,pass01;
    private TextView FPass;
    private Dialog loadingDialog;
    private FirebaseAuth firebaseAuth;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page001);

        log01=findViewById(R.id.loginbtn66);
        mail01=findViewById(R.id.emailinput);
        pass01=findViewById(R.id.passwordinput);
        reg01=findViewById(R.id.regbtnn66);
        FPass =findViewById(R.id.forgetpass);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        loadingDialog= new Dialog(this);
        loadingDialog.setContentView(R.layout.loading);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.button_shape_rounded));
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT );
        loadingDialog.setCancelable(false);



        log01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.show();
                validate(mail01.getText().toString(),pass01.getText().toString());
            }


        });

        reg01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qt= new Intent(Page001.this,Reg_page.class);
                startActivity(qt);
            }


        });
        FPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent qrt= new Intent(Page001.this,FPass.class);
                startActivity(qrt);
            }


        });

    }
    private void validate(String emailo, String passo)
    {
        firebaseAuth.signInWithEmailAndPassword(emailo , passo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful())
                {
                    checkEmailVerification();

                }
                else {
                    Toast.makeText(Page001.this, "Error SignIn account!", Toast.LENGTH_SHORT).show();
                    loadingDialog.dismiss();
                }


            }
        });

    }

    private void checkEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getInstance().getCurrentUser();
        Boolean emailflag = firebaseUser.isEmailVerified();

        // startActivity(new Intent(StudentLogin.this, StudentPage.class));

        if(emailflag){
            finish();
            startActivity(new Intent(Page001.this, HomePage.class));
        }else{
            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
            loadingDialog.dismiss();
            firebaseAuth.signOut();
        }
    }

}
