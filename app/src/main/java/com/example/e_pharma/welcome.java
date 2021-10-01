package com.example.e_pharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class welcome extends AppCompatActivity {
private Button cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
         getSupportActionBar().hide();


        setContentView(R.layout.activity_welcome);

        cont = findViewById(R.id.continueID);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contin();
            }
        });

    }


    public void contin(){
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

    }

}