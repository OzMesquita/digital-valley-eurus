package com.example.encontrosuniversitarios.view.fragment;

public interface CheckInCheckOutListener {
    void onSuccess(String message);
    void onCheckedInOnDifferentRoom(int previousRoom);
}
