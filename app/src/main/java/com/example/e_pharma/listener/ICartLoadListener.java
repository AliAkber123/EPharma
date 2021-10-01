package com.example.e_pharma.listener;

import com.example.e_pharma.model.CartModel;
import com.example.e_pharma.model.MedModel;

import java.util.List;

public interface ICartLoadListener {
    void onCartLoadSuccess(List<CartModel> cartModelListModel);
    void onCartLoadFailed(String message);
}
