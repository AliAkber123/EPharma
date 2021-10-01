package com.example.e_pharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
private Button medicine;
private Button rep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_main);

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