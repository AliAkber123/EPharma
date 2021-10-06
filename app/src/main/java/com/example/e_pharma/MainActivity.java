package com.example.e_pharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.PrimitiveIterator;

public class MainActivity extends AppCompatActivity {
private Button medicine;
private Button rep;
private Button logOUT;
private Button about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_main);


        logOUT=(Button)findViewById(R.id.logoutID);
        logOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,Login.class));
            }
        });


        about=(Button)findViewById(R.id.aboutID);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,About.class));
            }
        });


        medicine = findViewById(R.id.medicine);
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicine();
            }
        });


        rep=findViewById(R.id.rep);
        rep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep();
            }
        });


    }

    public void medicine(){
        Intent i = new Intent(this,Medicine.class);
        startActivity(i);

    }

    public void rep(){
        Intent intent = new Intent(this,Reports.class);
        startActivity(intent);

    }

}