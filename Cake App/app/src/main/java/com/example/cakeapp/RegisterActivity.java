package com.example.cakeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    TextView edtUserUserSignUp,edtUserEmail, edtPasswordUser, edtUserConfirmPassword;
    Button btnSignUpUser,btnUserLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);
        edtUserUserSignUp= findViewById(R.id.edtUserUserSignUp);
        edtUserEmail = findViewById(R.id.edtUserEmail);
        edtPasswordUser = findViewById(R.id.edtUserPassword);
        edtUserConfirmPassword = findViewById(R.id.edtUserConfirmPassword);
        btnSignUpUser = findViewById(R.id.btnSignUpUser);
        btnUserLogin = findViewById(R.id.btnUserLogin);

        SharedPreference preference= new SharedPreference();

        btnSignUpUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.SaveString(getApplicationContext(),
                        edtUserUserSignUp.getText().toString(),SharedPreference.Key_Name);
                preference.SaveInt(getApplicationContext(),
                        Integer.valueOf(edtPasswordUser.getText().toString()),SharedPreference.Key_PIN);
                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
//                SignUpActivity.this.finish();
            }
        });


        btnUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.SaveString(getApplicationContext(),
                        edtUserUserSignUp.getText().toString(),SharedPreference.Key_Name);
                preference.SaveString(getApplicationContext(),
                        edtUserEmail.getText().toString(),SharedPreference.Key_Email);
                preference.SaveInt(getApplicationContext(),
                        Integer.valueOf(edtPasswordUser.getText().toString()),SharedPreference.Key_PIN);
                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
            }
        });
    }
}