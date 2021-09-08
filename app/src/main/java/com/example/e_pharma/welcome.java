package com.example.e_pharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcome extends AppCompatActivity {
private Button cont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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