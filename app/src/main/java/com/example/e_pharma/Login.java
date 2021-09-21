package com.example.e_pharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
private Button Log;
private Button Signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log = findViewById(R.id.loginID);
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        Signup = findViewById(R.id.signupID);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup();
            }
        });


    }


    public void Login(){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }



    public void Signup(){
        Intent i = new Intent(this,Register.class);
        startActivity(i);

    }




}