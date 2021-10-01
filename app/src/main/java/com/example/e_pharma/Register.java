package com.example.e_pharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {


TextView Username,regPhone,regEmail,regPassword;
Button reg;

FirebaseDatabase rootNode;
DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_register);

        reg         = findViewById(R.id.registerID);
        Username = findViewById(R.id.editTextTextPersonName2);
        regPhone    = findViewById(R.id.editTextPhone);
        regEmail    = findViewById(R.id.editTextTextEmailAddress);
        regPassword = findViewById(R.id.editTextTextPassword3);



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("user");

                //Get all values
                String name = Username.getText().toString();
                String email = regEmail.getText().toString();
                String phone = regPhone.getText().toString();
                String password = regPassword.getText().toString();



                Registerhelper rh = new Registerhelper(name , email, phone, password);

                reference.child(phone).setValue(rh);

                reg();
            }
        });


    }

    public void reg(){
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }

}