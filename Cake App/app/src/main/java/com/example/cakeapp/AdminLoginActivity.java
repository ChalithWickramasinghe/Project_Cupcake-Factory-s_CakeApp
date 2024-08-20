package com.example.cakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    EditText edtAdminName,edtAdmPassword;
    Button btnAdmLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        edtAdminName=findViewById(R.id.edtUserUsername);
        edtAdmPassword=findViewById(R.id.edtAdmPassword);
        btnAdmLogin=findViewById(R.id.btnLoginUser);

        SharedPreference preference= new SharedPreference();
        String name= preference.GetString(getApplicationContext(),SharedPreference.Admin_Key_Name);

        btnAdmLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int Pin = preference.GetInt(getApplicationContext(), SharedPreference.Admin_Key_PIN);
                if (Integer.valueOf(edtAdmPassword.getText().toString()) == Pin)
                {
                    preference.SaveBoolean(getApplicationContext(),
                            true,SharedPreference.Key_Status);

                Intent intent= new Intent(getApplicationContext(), CakeActivity.class);
                startActivity(intent);

            }
                else
                    Toast.makeText(getApplicationContext(),"Incorrect Pin", Toast.LENGTH_LONG).show();
            }
        });
    }
}