package com.example.e_pharma.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.e_pharma.R;
import com.example.e_pharma.eventbus.MyUpdateCartEvent;
import com.example.e_pharma.listener.ICartLoadListener;
import com.example.e_pharma.listener.IRecyclerViewClickListener;
import com.example.e_pharma.model.CartModel;
import com.example.e_pharma.model.MedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyMedicineAdapter extends RecyclerView.Adapter<MyMedicineAdapter.MyMedicineViewHolder> {

    private Context context;
    private List<MedModel> medModelList;
    private ICartLoadListener iCartLoadListener;

    public MyMedicineAdapter(Context context, List<MedModel> medModelList, ICartLoadListener iCartLoadListener) {
        this.context = context;
        this.medModelList = medModelList;
        this.iCartLoadListener = iCartLoadListener;
    }

    @NonNull
    @Override
    public MyMedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyMedicineViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_medicine_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyMedicineViewHolder holder, int position) {
        Glide.with(context)
                .load(medModelList.get(position).getImage())
                .into(holder.imageView);
        holder.txtPrice.setText(new StringBuilder("$").append(medModelList.get(position).getPrice()));
        holder.txtName.setText(new StringBuilder().append(medModelList.get(position).getName()));

        holder.setListener((view, adapterPosition1) -> {
            addToCart(medModelList.get(position));
        });
    }

    private void addToCart(MedModel medModel) {
        DatabaseReference userCart = FirebaseDatabase
                .getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID");// Things may need to change here

        //
        //
        //
        //
        //
        //

        userCart.child(medModel.getKey())
        .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    CartModel cartModel = snapshot.getValue(CartModel.class);
                    cartModel.setQuantity(cartModel.getQuantity()+1);
                    Map<String, Object> updateData = new HashMap<>();
                    updateData.put("quantity", cartModel.getQuantity());
                    updateData.put("totalPrice", cartModel.getQuantity() * Float.parseFloat(cartModel.getPrice()));


                    userCart.child(medModel.getKey())
                            .updateChildren(updateData)
                            .addOnSuccessListener(aVoid -> {
                                iCartLoadListener.onCartLoadFailed("Add To Cart Success");
                            })
                            .addOnFailureListener(e -> iCartLoadListener.onCartLoadFailed(e.getMessage()));
                }
                else
                    {
                    CartModel cartModel = new CartModel();
                    cartModel.setName(medModel.getName());
                    cartModel.setImage(medModel.getImage());
                    cartModel.setKey(medModel.getKey());
                    cartModel.setPrice(medModel.getPrice());
                    cartModel.setQuantity(1);
                    cartModel.setTotalPrice(Float.parseFloat(medModel.getPrice()));

                    userCart.child(medModel.getKey()).setValue(cartModel)
                            .addOnSuccessListener(aVoid -> {
                                iCartLoadListener.onCartLoadFailed("Add To Cart Success");
                            })
                            .addOnFailureListener(e -> iCartLoadListener.onCartLoadFailed(e.getMessage()));
                }

               EventBus.getDefault().postSticky(new MyUpdateCartEvent());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                iCartLoadListener.onCartLoadFailed(error.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return medModelList.size();
    }

    public class MyMedicineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtPrice)
        TextView txtPrice;


        IRecyclerViewClickListener listener;

        public void setListener(IRecyclerViewClickListener listener) {
            this.listener = listener;
        }

        private Unbinder unbinder;
        public MyMedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onRecyclerClick(v,getAdapterPosition());
        }
    }


}
