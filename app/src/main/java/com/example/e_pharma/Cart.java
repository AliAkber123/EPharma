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
import com.example.e_pharma.eventbus.MyUpdateCartEvent;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    protected void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onStop()
    {
        if(EventBus.getDefault().hasSubscriberForEvent(MyUpdateCartEvent.class))
            EventBus.getDefault().removeStickyEvent(MyUpdateCartEvent.class);
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onUpdateCart(MyUpdateCartEvent event){

        countCartItem();
    }



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
    LoadMedicinesFromFirebase();
    countCartItem();
    }

    private void LoadMedicinesFromFirebase() {
        List<MedModel> medModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Medicines")
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


        btnCart.setOnClickListener(v -> startActivity(new Intent(this,CartActivity.class)));

    }

    @Override
    public void onMedLoadSuccess(List<MedModel> medModelList) {
MyMedicineAdapter adapter = new MyMedicineAdapter(this,medModelList,cartLoadListener);
recycleDrink.setAdapter(adapter);
    }

    @Override
    public void onMedLoadFailed(String message) {
        Snackbar.make(cartLayout,message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onCartLoadSuccess(List<CartModel> cartModelList) {
      int cartSum = 0;
      for(CartModel cartModel: cartModelList)
          cartSum += cartModel.getQuantity();
      badge.setNumber(cartSum);

    }

    @Override
    public void onCartLoadFailed(String message) {
Snackbar.make(cartLayout,message,Snackbar.LENGTH_LONG).show();
    }

@Override
protected void onResume(){
        super.onResume();
        countCartItem();
}

    private void countCartItem() {
        List<CartModel> cartModels = new ArrayList<>();
        FirebaseDatabase
                .getInstance().getReference("Cart").child("UNIQUE_USER_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot cartSnapshot: snapshot.getChildren())
                        {
                            CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                            cartModel.setKey(cartSnapshot.getKey());
                            cartModels.add(cartModel);


                        }
                        cartLoadListener.onCartLoadSuccess(cartModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    cartLoadListener.onCartLoadFailed(error.getMessage());
                    }
                });
    }

    public void back(){
        Intent i = new Intent(this,Medicine.class);
        startActivity(i);

    }

}