package com.example.e_pharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
 Button Log;
 Button Signup;

TextView Username,logPassword;
//comment
    //aliBroti

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log = findViewById(R.id.loginID);
        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


    private Boolean validateUsername(){
        String val = Username.getText().toString();


        if(val.isEmpty()){
            Username.setError("Field cannot be empty");
            return false;
        }else{
            Username.setError(null);
            Username.setEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword(){
        String val = logPassword.getText().toString();

        if(val.isEmpty()){
            logPassword.setError("Field cannot be empty");
            return false;
        }
        else{
            logPassword.setError(null);
            logPassword.setEnabled(false);
            return true;
        }

    }


    
    public void loginUser(View view){
        if(!validateUsername() | !validatePassword()){
            return;
        }
        else{
            isUser();
        }
    }

    private void isUser() {
         String userEnteredUsername = Username.getText().toString().trim();
         String userEnteredPassword = logPassword.getText().toString().trim();

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
         Query checkUser = FirebaseDatabase.getInstance().getReference("user").orderByChild("logUsername").equalTo(userEnteredUsername);
//        Query checkUser =reference.orderByChild("logUsername").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot){

               if(dataSnapshot.exists()){
                   Toast.makeText(Login.this, dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
//                   Username.setError(null);
//                   Username.setEnabled(false);
//
//                   String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);
//                   if(passwordFromDB.equals(userEnteredPassword))
//
//                   {
//
//                       logPassword.setError(null);
//                       logPassword.setEnabled(false);
//
//
//
//                   Login();


//
//                   } else {
//                       MediaRouteButton progressBar = null;
//                       progressBar.setVisibility(View.GONE);
//                       logPassword.setError("Wrong Password");
//                       logPassword.requestFocus();
//                   }
               }

               else {
                   MediaRouteButton progressBar = null;
                   progressBar.setVisibility(View.GONE);
                   Username.setError("No such User exist");
                   Username.requestFocus();
               }

          }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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