package com.example.encontrosuniversitarios.view.fragment;

public interface CheckInCheckOutListener {
    void onSuccess(String message);
    void onCheckedInOnDifferentRoom(String message);
    void onInvalidQRCode(String message);
    void onFailure(String message);
}
