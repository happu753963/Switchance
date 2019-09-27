package com.example.switchance_start.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.switchance_start.R;

public class IconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_avtivity);

        ImageButton btn_back= (ImageButton)findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(IconActivity.this, RegisterActivity.class);
                startActivity(intent);
                IconActivity.this.finish();
            }
        });
    }
    public void onClickIcon(View view){
        int id = view.getId();
        Intent intent = new Intent();
        switch (id) {
            case R.id.img_chameleon:
//                Toast.makeText(this,"1",Toast.LENGTH_LONG).show();
                intent.putExtra("image",R.drawable.chameleon);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.img_chicken:
//                Toast.makeText(this,"2",Toast.LENGTH_LONG).show();
                intent.putExtra("image",R.drawable.chicken);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.img_fox:
//                Toast.makeText(this,"3",Toast.LENGTH_LONG).show();
                intent.putExtra("image",R.drawable.fox);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.img_goose:
//                Toast.makeText(this,"4",Toast.LENGTH_LONG).show();
                intent.putExtra("image",R.drawable.goose);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.img_lion:
//                Toast.makeText(this,"5",Toast.LENGTH_LONG).show();
                intent.putExtra("image",R.drawable.lion);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.img_hedgehog:
//                Toast.makeText(this,"6",Toast.LENGTH_LONG).show();
                intent.putExtra("image",R.drawable.hedgehog);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }


}
