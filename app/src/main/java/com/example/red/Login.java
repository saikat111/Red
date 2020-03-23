package com.example.red;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText email, pass;
    private Button login;
    private Button create;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email =findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        login =findViewById(R.id.l);
        create =findViewById(R.id.s);
        fAuth = FirebaseAuth.getInstance();
        l();
        c();

    }

   public void l() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String e = email.getText().toString().trim();
                String p = pass.getText().toString().trim();
                if (e.isEmpty()) {
                    email.setError("Email is Required.");

                }
                if (p.isEmpty()) {
                    pass.setError("password is Required.");
                }
                if (p.length() < 6) {
                    pass.setError("Password Must be 6 Characters");
                }
                fAuth.signInWithEmailAndPassword(e, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(Login.this,MainActivity.class);
                            startActivity(in);
                        } else {
                            Toast.makeText(getApplicationContext(), "try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });
 }
    public  void c(){
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(Login.this,SingUp.class);
                startActivity(inn);
            }
        });
    }

}
