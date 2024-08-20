package com.example.cakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txvappname,txvsub;
    Button btnAdmSignUp,btnUserSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txvappname=findViewById(R.id.txvappname);
        txvsub=findViewById(R.id.txvsub);
        btnAdmSignUp=findViewById(R.id.btnAdmSignUp);
        btnUserSignUp=findViewById(R.id.btnUserSignUp);
        SharedPreference preference= new SharedPreference();


        btnAdmSignUp.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent (getApplicationContext (),SignUpActivity.class);
                startActivity (intent);
            }
        });

        btnUserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

    }
}