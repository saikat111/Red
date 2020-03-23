package com.example.red;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SingUp extends AppCompatActivity {
    private Button s;
    private EditText e,p;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        s = (Button)findViewById(R.id.sing);
        e = (EditText) findViewById(R.id.es);
        p = (EditText) findViewById(R.id.rs);
        mAuth = FirebaseAuth.getInstance();
        cr();
    }
    public void cr(){
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ee = e.getText().toString().trim();
                String pp = p.getText().toString().trim();
                if (ee.isEmpty()) {
                    e.setError("Email is Required.");

                }
                if (pp.isEmpty()) {
                    p.setError("password is Required.");
                }
                if (p.length() < 6) {
                    p.setError("Password Must be 6 Characters");
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(ee).matches()){
                    e.setError("Wrong email");
                }
                mAuth.createUserWithEmailAndPassword(ee,pp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent in = new Intent(SingUp.this,Info.class);
                            startActivity(in);

                        }
                    }
                });


            }
        });
    }
}
