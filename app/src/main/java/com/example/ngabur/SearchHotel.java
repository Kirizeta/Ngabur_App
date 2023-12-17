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

import java.util.List;


public class SearchHotel extends Fragment {
//    private SearchView searchview;
    private EditText searchview;
    private LinearLayout jakarta;
    private LinearLayout bandung;
    private LinearLayout padang;
    private LinearLayout bali;
    private LinearLayout jogja;
    private LinearLayout surabaya;
    private LinearLayout semarang;
    private LinearLayout ntt;
    private LinearLayout malang;
    private LinearLayout aceh;
    private LinearLayout palembang;
    private LinearLayout ntb;
    private List<DataClass> dataList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        jakarta = view.findViewById(R.id.jakarta_label);
        bandung = view.findViewById(R.id.bandung_label);
        searchview = view.findViewById(R.id.search_main1);
        padang = view.findViewById(R.id.padang_label);
        bali = view.findViewById(R.id.bali_label);
        jogja = view.findViewById(R.id.jogja_label);
        surabaya = view.findViewById(R.id.surabaya_label);
        semarang = view.findViewById(R.id.semarang_label);
        ntt = view.findViewById(R.id.ntt_label);
        malang = view.findViewById(R.id.malang_label);
        aceh = view.findViewById(R.id.aceh_label);
        palembang = view.findViewById(R.id.palembang_label);
        ntb = view.findViewById(R.id.ntb_label);

        searchview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HotelList.class);
                startActivity(intent);
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

        bandung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Bandung");
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

        bali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Bali");
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

        surabaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                intent.putExtra("Category", "Surabaya");
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

        ntt.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                                          intent.putExtra("Category", "NTT");
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

        aceh.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                                          intent.putExtra("Category", "Aceh");
                                          startActivity(intent);
                                      }
                                  }
        );

        palembang.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                                          intent.putExtra("Category", "Palembang");
                                          startActivity(intent);
                                      }
                                  }
        );

        ntb.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          Intent intent = new Intent(getActivity(), CategoryHotelList.class);
                                          intent.putExtra("Category", "NTB");
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