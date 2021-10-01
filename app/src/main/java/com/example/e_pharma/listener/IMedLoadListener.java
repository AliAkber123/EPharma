package com.example.e_pharma.listener;

import com.example.e_pharma.model.MedModel;

import java.util.List;

public interface IMedLoadListener {
    void onMedLoadSuccess(List<MedModel> medModelList);
    void onMedLoadFailed(String message);
}
