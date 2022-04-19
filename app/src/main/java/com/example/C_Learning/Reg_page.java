package com.example.C_Learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Reg_page extends AppCompatActivity {

    private Button reg02;
    private EditText fname01,uname01,pass01,pass02,mail02;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_page);


        fname01=findViewById(R.id.fnameinput);
        uname01=findViewById(R.id.unameinput);
        pass01=findViewById(R.id.password);
        pass02=findViewById(R.id.conpassword);
        mail02=findViewById(R.id.mailinput);
        reg02= findViewById(R.id.regbtnn);
        firebaseAuth = FirebaseAuth.getInstance();

        reg02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_email = mail02.getText().toString().trim();
                String user_password = pass01.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(user_email , user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful())
                        {
                            // Toast.makeText(StudentReg.this,"Account Created!!!",Toast.LENGTH_SHORT).show();
                            // Intent ft= new Intent(StudentReg.this,StudentLogin.class);
                            // startActivity(ft);
                            sendEmaliVerification();


                        }
                        else
                            Toast.makeText(Reg_page.this, "Error creating account!",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });





    }

    private void sendEmaliVerification()
    {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        //sendUserData();
                        Toast.makeText(Reg_page.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(Reg_page.this, Page001.class));
                    } else {
                        Toast.makeText(Reg_page.this, "Verification mail hasn't been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
