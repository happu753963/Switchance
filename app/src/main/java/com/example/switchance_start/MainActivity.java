package com.example.switchance_start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.switchance_start.model.Constant;
import com.example.switchance_start.register.LogInActivity;
import com.example.switchance_start.register.RegisterActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btn_register;
    Button btn_logIn;
//    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //設定firebase

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReferenceFromUrl(Constant.DB_URL);
//        myRef.child("messenge").setValue("haha");

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
            }
        });
//        }else{
//            Intent intent = new Intent();
//            intent.setClass(MainActivity.this, logo.class);
//            startActivity(intent);
    }
}
//}
