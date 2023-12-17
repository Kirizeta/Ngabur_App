package com.example.ngabur;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HotelList extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    Adapter adapter;
    List<DataClass> dataList;
    ValueEventListener eventListener;
    SearchView searchView;
    ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serch_hotel_list);

        recyclerView = findViewById(R.id.categoryuserlist);
        searchView = findViewById(R.id.search_main_hotes);
        searchView.clearFocus();

        back = findViewById(R.id.hotel_back);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HotelList.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(HotelList.this);
        builder.setCancelable(false);
        builder.setView(R.layout.seaching_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        dataList = new ArrayList<>();

        adapter = new Adapter(HotelList.this, dataList);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance().getReference("Hotels");
        dialog.show();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        eventListener = database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot :snapshot.getChildren()){
                        if(dataSnapshot.exists()){
                            for(DataSnapshot shot : dataSnapshot.getChildren()){
                                DataClass dataClass = shot.getValue(DataClass.class);
                                dataList.add(dataClass);
                                Collections.shuffle(dataList);
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }

        });

    }


    public String searchList(String text) {
        ArrayList<DataClass> searchList = new ArrayList<>();

        for (DataClass dataClass : dataList) {
            if (dataClass.getHotel_name().toLowerCase().contains(text.toLowerCase())) {
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);

        return null;
    }
}

