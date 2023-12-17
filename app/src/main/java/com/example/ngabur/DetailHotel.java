package com.example.ngabur;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class DetailHotel extends AppCompatActivity {

    TextView Hotelname;
    TextView detailFacility;
    TextView detailbed;
    TextView hotelrating;
    TextView detailbreakfast;
    TextView detaildescription;
    TextView price_Txt;
    TextView locationTxt;
    TextView detailUid;

    ImageView detailPhoto, bookmark;
    ImageView hotelback;
    Button buttonbook;
    FirebaseAuth auth;
    String name, user;

    int Price;
    DataClass hotel;
    private List<DataClass> dataList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        hotelback = findViewById(R.id.hotel_back);

        Hotelname = findViewById(R.id.Hotel_name);
        detailFacility = findViewById(R.id.detail_Facility);
        detailbed = findViewById(R.id.detail_bed);
        detailPhoto = findViewById(R.id.detail_photo);
        detailbreakfast = findViewById(R.id.detail_breakfast);
        hotelrating = findViewById(R.id.hotel_rating);
        detaildescription = findViewById(R.id.detail_description);
        price_Txt = findViewById(R.id.priceTxt);
        locationTxt = findViewById(R.id.locationTxt);
        buttonbook = findViewById(R.id.button_book);
        detailUid = findViewById(R.id.detail_uid);
//        bookmark = findViewById(R.id.bookmark);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Hotelname.setText(bundle.getString("Name"));
            detailFacility.setText(bundle.getString("Facility"));
            detailbed.setText(bundle.getString("Bed")+" bed");
            hotelrating.setText(bundle.getString("Rating"));
            detailbreakfast.setText(bundle.getString("Breakfast")+" AM");
            detaildescription.setText(bundle.getString("Description"));
            Price = bundle.getInt("Price");
            price_Txt.setText(String.valueOf(Price) +" /night");
            locationTxt.setText(bundle.getString("Location"));
            detailUid.setText(bundle.getString("HotelID")); //hotel id
            hotel = (DataClass) bundle.getSerializable("hotelClass");


            Glide.with(this).load(bundle.getString("Image")).into(detailPhoto);
        }

        if (detailFacility.getText().toString().contains(".. ")){
            detailFacility.setText(detailFacility.getText().toString().replace(".. ", "\n"));
        }

        if (detaildescription.getText().toString().contains(".. ")){
            detaildescription.setText(detaildescription.getText().toString().replace(".. ", "\n"));
        }


        hotelback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailHotel.this, Booking_Hotel_date.class);
                intent.putExtra("hotelClass", hotel);
                intent.putExtra("nameHotel", Hotelname.getText().toString());
                intent.putExtra("descHotel", detaildescription.getText().toString());
                intent.putExtra("priceHotel", Price);
                intent.putExtra("HotelID", detailUid.getText().toString());
                startActivity(intent);
            }
        });


//        bookmark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }




}