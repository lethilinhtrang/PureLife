package com.haqyna.purelife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    Button button_do_C, button_do_F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        button_do_C = findViewById(R.id.button_do_C);
        button_do_F = findViewById(R.id.button_do_F);

        button_do_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kieuDo = "C";
                Intent intent = new Intent();
                intent.putExtra("kieuDo", kieuDo);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        button_do_F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kieuDo = "F";
                Intent intent = new Intent();
                intent.putExtra("kieuDo", kieuDo);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}