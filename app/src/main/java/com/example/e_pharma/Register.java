package com.example.e_pharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private Button register;
    private EditText FullName,Email,Password,Phone;
    private ProgressBar progressBar;
    //TextView Username,regPhone,regEmail,regPassword;
//Button reg;
//
//FirebaseDatabase rootNode;
//DatabaseReference reference;
//
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
//
//
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();



        register=(Button)findViewById(R.id.registerID);
        register.setOnClickListener(this);

        FullName=(EditText) findViewById(R.id.editTextTextPersonName2);
        Email=(EditText) findViewById(R.id.editTextTextEmailAddress);
        Password=(EditText) findViewById(R.id.editTextTextPassword3);
        Phone=(EditText)findViewById(R.id.editTextPhone);

        progressBar=(ProgressBar) findViewById(R.id.progressBar);
//
//        reg         = findViewById(R.id.registerID);
//        Username = findViewById(R.id.editTextTextPersonName2);
//        regPhone    = findViewById(R.id.editTextPhone);
//        regEmail    = findViewById(R.id.editTextTextEmailAddress);
//        regPassword = findViewById(R.id.editTextTextPassword3);
//
//
//
//        reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("user");
//
//                //Get all values
//                String name = Username.getText().toString();
//                String email = regEmail.getText().toString();
//                String phone = regPhone.getText().toString();
//                String password = regPassword.getText().toString();
//
//
//
//                Registerhelper rh = new Registerhelper(name , email, phone, password);
//
//                reference.child(phone).setValue(rh);
//
//                reg();
//            }
//        });
//
//
//    }
//
//    public void reg(){
//        Intent i = new Intent(this,Login.class);
//        startActivity(i);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
//          case R.id.banner:
//           startActivity(new Intent(this,MainActivity.class));
//           break;

            case R.id.registerID:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        String email=Email.getText().toString().trim();
        String password=Password.getText().toString().trim();
        String fullName=FullName.getText().toString().trim();
        String phone=Phone.getText().toString().trim();

        if(fullName.isEmpty()){
            FullName.setError("Full name is required!!!");
            FullName.requestFocus();
            return;
        }



        if(phone.isEmpty()){
            Phone.setError("Phone number is required!!!");
            Phone.requestFocus();
            return;
        }

        if(email.isEmpty()){
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Email.setError("Please provide the valid email!!!");
            Email.requestFocus();
            return;
        }
        if(password.isEmpty()){
            Password.setError("Password is required!!!");
            Password.requestFocus();
            return;
        }

        if(password.length()<6){
            Password.setError("Min password length should be six characters");
            Password.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Registerhelper user=new Registerhelper(fullName,email,phone,password);

                            FirebaseDatabase.getInstance().getReference("user")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Register.this,"User has been registered successfully!!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);

                                    }else{
                                        Toast.makeText(Register.this,"Failed to register!Try Again!",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(Register.this,"Failed to register!Try Again!",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

//    private class OnCompletionListener<T> {
//    }



}