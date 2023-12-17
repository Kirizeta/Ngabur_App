package com.example.ngabur;

import static android.content.Intent.getIntent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoryList extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference database;
    AdapterHistory adapter;
    DataClass dataClass;
    List<Transaction> listTr;
    ValueEventListener eventListener;
    ImageView back;

    FragmentManager fragmentManager;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create, container, false);

        recyclerView = view.findViewById(R.id.categoryuserlist_hst);

        back = view.findViewById(R.id.Hotel_back_hst);
        
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NavbarMenu.class);
                startActivity(intent);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(view.getContext());
        builder.setCancelable(false);
        builder.setView(R.layout.seaching_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        listTr = new ArrayList<>();

        adapter = new AdapterHistory(view.getContext(), listTr);
        recyclerView.setAdapter(adapter);

//        Bundle bundle = getIntent().getExtras();
//
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference db = firebaseDatabase.getReference("Registered Users").child(auth.getUid()).child("transaction");
        dialog.show();

        eventListener = db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listTr.clear();
                for (DataSnapshot itemSnapshot : snapshot.getChildren()) {
                    Transaction transaction = itemSnapshot.getValue(Transaction.class);
                    if (transaction != null) {
                        listTr.add(transaction);
                    }
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
            }

        });


        return view;
    }
}