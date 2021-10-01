package com.example.e_pharma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.e_pharma.adapter.MyMedicineAdapter;
import com.example.e_pharma.listener.ICartLoadListener;
import com.example.e_pharma.listener.IMedLoadListener;
import com.example.e_pharma.model.CartModel;
import com.example.e_pharma.model.MedModel;
import com.example.e_pharma.utils.SpaceItemDecoration;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cart extends AppCompatActivity implements IMedLoadListener, ICartLoadListener {
ImageView back;
    @BindView(R.id.recycler_drink)
    RecyclerView recycleDrink;
    @BindView(R.id.cartLayoutID)
    RelativeLayout cartLayout;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.btnCart)
    FrameLayout btnCart;


    IMedLoadListener medLoadListener;
    ICartLoadListener cartLoadListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();


        setContentView(R.layout.activity_cart);


        back = findViewById(R.id.notification_background);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });




        init();
    LoadDrinkFromFirebase();
    }

    private void LoadDrinkFromFirebase() {
        List<MedModel> medModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Drink")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists())
                        {
                            for (DataSnapshot medSnapshot :snapshot.getChildren())
                            {
                                MedModel medModel =medSnapshot.getValue(MedModel.class);
                                medModel.setKey(medSnapshot.getKey());
                                medModels.add(medModel);
                            }
                            medLoadListener.onMedLoadSuccess(medModels);
                        }
                        else
                            medLoadListener.onMedLoadFailed("Can't find medicine");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
medLoadListener.onMedLoadFailed(error.getMessage());
                    }
                });
    }

    private void init(){
        ButterKnife.bind(this);

        medLoadListener = this;
        cartLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recycleDrink.setLayoutManager(gridLayoutManager);
        recycleDrink.addItemDecoration(new SpaceItemDecoration());
    }

    @Override
    public void onMedLoadSuccess(List<MedModel> medModelList) {
MyMedicineAdapter adapter = new MyMedicineAdapter(this,medModelList);
recycleDrink.setAdapter(adapter);
    }

    @Override
    public void onMedLoadFailed(String message) {
        Snackbar.make(cartLayout,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelListModel) {

    }

    @Override
    public void onCartLoadFailed(String message) {

    }



    public void back(){
        Intent i = new Intent(this,Medicine.class);
        startActivity(i);

    }

}