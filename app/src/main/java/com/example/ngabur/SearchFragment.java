package com.example.ngabur;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;


public class SearchFragment extends Fragment {
    private EditText searchview;
    private LinearLayout jakarta;
    HotelList hotelList;
    String s;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        jakarta = view.findViewById(R.id.jakarta_label);
        searchview = view.findViewById(R.id.search_main1);


        jakarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HotelList.class);
                startActivity(intent);
            }
        }
        );


        searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotelList.class);
                startActivity(intent);
            }
        });




        return view;
    }
}