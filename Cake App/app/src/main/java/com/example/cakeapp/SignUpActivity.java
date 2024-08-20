package com.example.cakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    EditText edtAdminUsername,edtAdminEmail,edtAdminPassword,edtConfirmAdminPassword;
    Button btnAdminSignUp,btnLoginAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        edtAdminUsername=findViewById(R.id.edtAdminUsername);
        edtAdminEmail = findViewById(R.id.edtAdminEmail);
        edtAdminPassword = findViewById(R.id.edtAdminPassword);
        edtConfirmAdminPassword=findViewById(R.id.edtConfirmAdminPassword);
        btnAdminSignUp=findViewById(R.id.btnAdminSignUp);
        btnLoginAdmin=findViewById(R.id.btnLoginAdmin);


        SharedPreference preference= new SharedPreference();

        btnAdminSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.SaveString(getApplicationContext(),
                        edtAdminUsername.getText().toString(),SharedPreference.Admin_Key_Name);
                preference.SaveInt(getApplicationContext(),
                        Integer.valueOf(edtAdminPassword.getText().toString()),SharedPreference.Admin_Key_PIN);
                Intent intent= new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
                SignUpActivity.this.finish();
            }
        });

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.SaveString(getApplicationContext(),
                        edtAdminUsername.getText().toString(),SharedPreference.Admin_Key_Name);
                preference.SaveInt(getApplicationContext(),
                        Integer.valueOf(edtAdminPassword.getText().toString()),SharedPreference.Admin_Key_PIN);
                Intent intent= new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
                SignUpActivity.this.finish();
            }
        });


    }
}