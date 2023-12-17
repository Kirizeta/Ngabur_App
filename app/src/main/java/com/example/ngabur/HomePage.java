package com.example.ngabur;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codebyashish.autoimageslider.AutoImageSlider;
import com.codebyashish.autoimageslider.Enums.ImageScaleType;
import com.codebyashish.autoimageslider.ExceptionsClass;
import com.codebyashish.autoimageslider.Models.ImageSlidesModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HomePage extends Fragment {
    SearchHotel searchFragment = new SearchHotel();
    private RecyclerView.Adapter adapterFoodList;
    private TextView textName, textView, textMember;
    private ImageView photo;
    private String names, photos, members;
    private EditText search;
    private FirebaseAuth auth;
    private LinearLayout jakarta, jogja, bali, padang, surabaya, malang, semarang, more;
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private HotelListAdapter adapter;
    private List<DataClass> dataList;
    private ValueEventListener eventListener;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        jakarta = view.findViewById(R.id.jakarta_label);
        jogja = view.findViewById(R.id.jogja_label);
        bali = view.findViewById(R.id.bali_label);
        padang = view.findViewById(R.id.padang_label);
        surabaya = view.findViewById(R.id.surabaya_label);
        malang = view.findViewById(R.id.malang_label);
        textName = view.findViewById(R.id.name_home);
        semarang = view.findViewById(R.id.semarang_label);
        search = view.findViewById(R.id.searchHome);
        photo = view.findViewById(R.id.home_photo);
        textView = view.findViewById(R.id.view_all);
        more = view.findViewById(R.id.view_label);
        textMember = view.findViewById(R.id.member_home);


        auth = FirebaseAuth.getInstance();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HotelList.class);
                startActivity(intent);
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HotelList.class);
                startActivity(intent);
            }
        });



        String id = auth.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Registered Users");
        reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDetails readWriteUserDetails = snapshot.getValue(ReadWriteUserDetails.class);
                if (readWriteUserDetails != null) {
                    names = readWriteUserDetails.name;
                    photos = readWriteUserDetails.photo;
                    members = readWriteUserDetails.member;

                    textName.setText(names);
                    Glide.with(getContext()).load(photos).into(photo);
                    textMember.setText(members);

                    if(textMember.getText().toString().contains("VIP")) {
                        LinearLayout.LayoutParams slider = new LinearLayout.LayoutParams(0, 0);
                        AutoImageSlider autoImageSlider = (AutoImageSlider) view.findViewById(R.id.autoImageSlider);
                        autoImageSlider.setLayoutParams(slider);

                    }
                    else {
                        AutoImageSlider autoImageSlider = view.findViewById(R.id.autoImageSlider);
                        ArrayList<ImageSlidesModel> autoImageList = new ArrayList<>();
                        try {
                            autoImageList.add(new ImageSlidesModel("https://cms.hig.id//upload/cms/HIG_BRImo_640.jpg", ImageScaleType.FIT));
                            autoImageList.add(new ImageSlidesModel("https://img.okezone.com/content/2023/08/16/406/2865667/promo-hotel-mulai-dari-rp100-ribu-aja-di-mister-aladin-yuk-liburan-SGK0c47knd.jpg", ImageScaleType.FIT));
                            autoImageList.add(new ImageSlidesModel("https://bankmega.com/media/filer_public/1f/8c/1f8c9a62-fd2a-43cb-afba-c0e6de07e5e5/0-banner-bm-new-intercontinental.jpg", ImageScaleType.FIT));
                            autoImageSlider.setImageList(autoImageList, ImageScaleType.FIT);

                            autoImageSlider.setDefaultAnimation();

                        } catch (ExceptionsClass e) {
                            throw new RuntimeException(e);
                        }

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = view.findViewById(R.id.hoteluserlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));

        dataList = new ArrayList<>();

        adapterFoodList = new HotelListAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapterFoodList);

        database = FirebaseDatabase.getInstance().getReference("Hotels");

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
                adapterFoodList.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        jakarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Jakarta");
                startActivity(intent);
            }
        }
        );

        jogja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Yogyakarta");
                startActivity(intent);
            }
        }
        );

        bali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Bali");
                startActivity(intent);
            }
        }
        );

        padang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Padang");
                startActivity(intent);
            }
        }
        );

        surabaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Surabaya");
                startActivity(intent);
            }
        }
        );

        malang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Malang");
                startActivity(intent);
            }
        }
        );

        semarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Semarang");
                startActivity(intent);
            }
        }
        );

        return view;
    }

}