package com.example.switchance_start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.switchance_start.register.LogInActivity;
import com.example.switchance_start.register.RegisterActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //設定firebase
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef=database.getInstance().getReferenceFromUrl("https://switchance-e8900.firebaseio.com/ex2");
        myRef.child("messenge").setValue("haha");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_register = (Button)findViewById(R.id.btn_register);
        Button btn_logIn= (Button)findViewById(R.id.btn_logIn);

        btn_register.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
//                MainActivity.this.finish();
            }
        });


        btn_logIn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LogInActivity.class);
                startActivity(intent);
//                MainActivity.this.finish();
            }
        });
    }



}
