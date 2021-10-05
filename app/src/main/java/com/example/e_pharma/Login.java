//package com.example.e_pharma;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.MediaRouteButton;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class Login extends AppCompatActivity {
// Button Log;
// Button Signup;
//
//TextView Username,logPassword;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
//
//
//        setContentView(R.layout.activity_login);
//
//        Log = findViewById(R.id.loginID);
//        Log.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Login();
//            }
//        });
//
//
//
//        Signup = findViewById(R.id.signupID);
//        Signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Signup();
//            }
//        });
//
//
//    }
//
//
//    private Boolean validateUsername(){
//        String val = Username.getText().toString();
//
//
//        if(val.isEmpty()){
//            Username.setError("Field cannot be empty");
//            return false;
//        }else{
//            Username.setError(null);
//            Username.setEnabled(false);
//            return true;
//        }
//    }
//
//
//    private Boolean validatePassword(){
//        String val = logPassword.getText().toString();
//
//        if(val.isEmpty()){
//            logPassword.setError("Field cannot be empty");
//            return false;
//        }
//        else{
//            logPassword.setError(null);
//            logPassword.setEnabled(false);
//            return true;
//        }
//
//    }
//
//
//
//    public void loginUser(View view){
//        if(!validateUsername() | !validatePassword()){
//            return;
//        }
//        else{
//            isUser();
//        }
//    }
//
//    private void isUser() {
//         String userEnteredUsername = Username.getText().toString().trim();
//         String userEnteredPassword = logPassword.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("user");
//         Query checkUser = FirebaseDatabase.getInstance().getReference("user").orderByChild("logUsername").equalTo(userEnteredUsername);
//      //  Query checkUser =reference.orderByChild("logUsername").equalTo(userEnteredUsername);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot dataSnapshot){
//
//               if(dataSnapshot.exists()){
//                   Toast.makeText(Login.this, dataSnapshot.getKey(), Toast.LENGTH_SHORT).show();
//                  Username.setError(null);
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
//
//
//                   } else {
//                       MediaRouteButton progressBar = null;
//                       progressBar.setVisibility(View.GONE);
//                       logPassword.setError("Wrong Password");
//                       logPassword.requestFocus();
//                 }
//               }
//
//               else {
//                   MediaRouteButton progressBar = null;
//                   progressBar.setVisibility(View.GONE);
//                   Username.setError("No such User exist");
//                   Username.requestFocus();
//               }
//
//          }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });
//
//    }
//
//
//
//    public void Login(){
//        Intent i = new Intent(this,MainActivity.class);
//        startActivity(i);
//
//    }
//
//
//
//    public void Signup(){
//        Intent i = new Intent(this,Register.class);
//        startActivity(i);
//
//    }
//
//
//
//
//}




package com.example.e_pharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity implements View.OnClickListener {
    //variables
    Button logIN;
    Button signUP;

    //TextView Username,logPassword;
    //private TextView register;
    private EditText Email, Password;
    // private Button signIn;
    private FirebaseAuth mAuth;
    private ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
       this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       getSupportActionBar().hide();



        setContentView(R.layout.activity_login);

        signUP = (Button) findViewById(R.id.signupID);
        signUP.setOnClickListener(this);

        logIN = (Button) findViewById(R.id.loginID);
        logIN.setOnClickListener(this);

        Email =(EditText)findViewById(R.id.editTextTextEmailAddress2) ;
        Password=(EditText)findViewById(R.id.editTextTextPassword);

        progress =(ProgressBar)findViewById(R.id.progressBar) ;



        mAuth =FirebaseAuth.getInstance();

//
//        Log = findViewById(R.id.loginID);
//        Log.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Login();
//            }
//        });
//
//
//        Signup = findViewById(R.id.signupID);
//        Signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Signup();
//            }
//        });
//
//        editTextEmail = (EditText) findViewById(R.id.editTextTextPersonName);
//        editTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
//
//        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//        mAuth=FirebaseAuth.getInstance();
//
//
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signupID:
                startActivity( new Intent(this,Register.class));
                break;

            case R.id.loginID:;
                Login();
                break;
        }
    }



    public void Login(){


        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();



        if(email.isEmpty())
        {
            Email.setError("E-mail is required");
            Email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please enter a valid email");
            Email.requestFocus();
            return;
        }


        if(password.isEmpty())
        {
            Password.setError("Password is required");
            Password.requestFocus();
            return;
        }

        if(password.length()<6){
            Password.setError("Minimum password length is 6 characters");
            Password.requestFocus();
            return;

        }

        progress.setVisibility(View.VISIBLE);





        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to user profile
                    startActivity(new Intent(Login.this,MainActivity.class));

                }else{

                     Toast.makeText(Login.this,"Failed to login!Please check yours credentials",Toast.LENGTH_LONG).show();
                }
            }
        });








//        Intent i = new Intent(this,MainActivity.class);
//        startActivity(i);

    }



//    public void Signup(){
//        Intent i = new Intent(this,Register.class);
//        startActivity(i);
//
//    }
//
//    private void userLogin(){
//        String email=editTextEmail.getText().toString().trim();
//        String password=editTextPassword.getText().toString().trim();
//
//        if(email.isEmpty()) {
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            editTextEmail.setError("Please Enter a Valid Email");
//            editTextEmail.requestFocus();
//            return;
//
//        }
//
//        if(password.isEmpty()) {
//            editTextPassword.setError("Password is required");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        if(password.length()<6){
//            editTextPassword.setError("Minimun password length is 6 characters");
//            editTextPassword.requestFocus();
//            return;
//
//        }
//
//        progressBar.setVisibility(View.VISIBLE);
//
//        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    //redirect to user profile
//                    startActivity(new Intent(Login.this,MainActivity.class));
//
//                }else{
//
//                    // Toast.makeText(MainActivity.this,"Failed to login!Please check ypurs credentials",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//    }


}




































