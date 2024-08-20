package com.example.cakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView edtUserUsername,edtUserPassword;
    Button btnLoginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserUsername=findViewById(R.id.edtUserUsername);
        edtUserPassword=findViewById(R.id.edtUserPassword);
        btnLoginUser=findViewById(R.id.btnLoginUser);

        SharedPreference preference= new SharedPreference();
        String name= preference.GetString(getApplicationContext(),SharedPreference.Key_Name);

        btnLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Pin = preference.GetInt(getApplicationContext(),SharedPreference.Key_PIN);
                if (Integer.valueOf(edtUserPassword.getText().toString()) == Pin)
                {
                    preference.SaveBoolean(getApplicationContext(),
                            true,SharedPreference.Key_Status);
                Intent intent= new Intent(getApplicationContext(), DisplayCakeActivity.class);
                startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Incorrect Pin",Toast.LENGTH_LONG).show();
            }
        });

    }
}