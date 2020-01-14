package com.example.switchance_start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.switchance_start.model.Constant;
import com.example.switchance_start.register.LogInActivity;
import com.example.switchance_start.register.RegisterActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    Button btn_register;
    Button btn_logIn;
//    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //設定firebase
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.DB_URL);
        myRef.child("messenge").setValue("haha");


//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user == null) {
            setContentView(R.layout.activity_main);

            btn_register = (Button) findViewById(R.id.btn_register);
            btn_logIn = (Button) findViewById(R.id.btn_logIn);

            btn_register.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
//                MainActivity.this.finish();
                }
            });

            btn_logIn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, LogInActivity.class);
                    startActivity(intent);
//                MainActivity.this.finish();
                }
            });
//        }else{
//            Intent intent = new Intent();
//            intent.setClass(MainActivity.this, logo.class);
//            startActivity(intent);
        }
    }
//}
