package com.example.red;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Info extends AppCompatActivity {
    private Button done;
   private EditText e1,e2,e3;
   private CheckBox c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
   private FirebaseAuth fAuth;



    String userID ;
    String relation = "";
    String sex ="";
    String choice ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        fAuth = FirebaseAuth.getInstance();

        done = findViewById(R.id.done);
        e1 = findViewById(R.id.nickname);
        e2 = findViewById(R.id.country);
        e3 = findViewById(R.id.age);

        c1 = (CheckBox) findViewById(R.id.single);
        c2 = (CheckBox)findViewById(R.id.relation);
        c3 = (CheckBox)findViewById(R.id.married);
        c4 = (CheckBox)findViewById(R.id.divorced);
        c5 = (CheckBox)findViewById(R.id.male);
        c6 = (CheckBox)findViewById(R.id.female);
        c7 = (CheckBox)findViewById(R.id.other);
        c8 = (CheckBox)findViewById(R.id.cmale);
        c9 = (CheckBox)findViewById(R.id.cfemale);
        c10 = (CheckBox)findViewById(R.id.gay);
        c11 = (CheckBox)findViewById(R.id.lesbian);
        c12 = (CheckBox)findViewById(R.id.transgender);
        create();
    }
    public void create(){
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = e1.getText().toString().trim();
                String country = e2.getText().toString().trim();
                String age = e3 .getText().toString().trim();

                   if (c1.isChecked()) {
                        relation = "singel";
                    }
                    if (c2.isChecked()) {
                        relation = "in a relation";
                    }
                    if (c3.isChecked()) {
                        relation = "married";
                    }
                    if (c4.isChecked()) {
                        relation = "Divorce";
                    }
                    if (c5.isChecked()) {
                        sex = "male";
                    }
                    if (c6.isChecked()) {
                        sex = "female";
                    }
                    if (c7.isChecked()) {
                        sex = "other";
                    }
                    if (c8.isChecked()) {
                        choice = "male";
                    }
                    if (c9.isChecked()) {
                        choice = "female";
                    }
                    if (c10.isChecked()) {
                        choice = "Gay";
                    }
                    if (c11.isChecked()) {
                        choice = "Lesbian";
                    }
                    if (c12.isChecked()) {
                        choice = "Transgender";
                    }



                userID = fAuth.getCurrentUser().getUid();

                HashMap<String,Object> user = new HashMap<>();
                user.put("Name",nickname);
                user.put("Country",country);
                user.put("Age",age);
                user.put("sex", sex);
                user.put("Relation status", relation);
                user.put("Choice",choice);
                user.put("profilePic"," ");


                //Intent intent = new Intent(Info.this,MainActivity.class);
               // startActivity(intent);
                FirebaseDatabase.getInstance().getReference().child("Profiles").child(userID)
                        .setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"profile updated",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Info.this, Pic.class);
                                startActivity(intent);
                            }
                        });



            }
        });

    }
}
