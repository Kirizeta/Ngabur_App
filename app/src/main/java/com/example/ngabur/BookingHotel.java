package com.example.ngabur;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingHotel extends AppCompatActivity {

    TextView chekin, chekout, room, desc, harga, namahotel;
    EditText nama, telp, email;
    Button nextPay;
    String Checkin, Chekout, roomType, DescHotel, HotelID;
    int priceHotel;

    AutoCompleteTextView Payment;
    DataClass hotel;
    String pay;

    TextView namaDialog, hargaDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_hotel);

        String[] typePay = new String[]{"Credit", "Debit", "M-Bangking"};

        chekin = findViewById(R.id.dateCheckIn);
        chekout = findViewById(R.id.dateCheckOut);
        room = findViewById(R.id.tv_room);
        desc = findViewById(R.id.tv_desc);
        harga = findViewById(R.id.tv_harga);
        nama = findViewById(R.id.et_name);
        telp = findViewById(R.id.et_telp);
        email = findViewById(R.id.et_email);
        nextPay = findViewById(R.id.next_btn_to_pay);
        Payment = findViewById(R.id.typePay);
        namahotel = findViewById(R.id.HotelName);

        ArrayAdapter<String> adapterPay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, typePay);

        Payment.setAdapter(adapterPay);

        Payment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pay = typePay[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                pay = typePay[0];
            }
        });


        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need
        String dateToday = formatter.format(today);


        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            Checkin = bundle.getString("CheckInDate");
            chekin.setText(Checkin);

            Chekout = bundle.getString("CheckOutDate");
            chekout.setText(Chekout);

            roomType = bundle.getString("room");
            room.setText(roomType);

            DescHotel = bundle.getString("HotelDesc");
            desc.setText(DescHotel);

            priceHotel = bundle.getInt("PriceHotel");
            harga.setText(String.valueOf(priceHotel));

            hotel = (DataClass) bundle.getSerializable("hotelClass");
//            HotelID = bundle.getString("hotelID");

            namahotel.setText(hotel.getHotel_name());

        }


        nextPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().equals("")){
                    Toast.makeText(BookingHotel.this, "Name musnt be empty", Toast.LENGTH_SHORT).show();
                }
                if (telp.getText().equals("")){
                    Toast.makeText(BookingHotel.this, "Name musnt be empty", Toast.LENGTH_SHORT).show();
                }
                if (email.getText().equals("")){
                    Toast.makeText(BookingHotel.this, "Name musnt be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    String key = database.getReference("Registered Users").child(auth.getUid()).push().getKey();

                    Transaction tr = new Transaction(key, dateToday, Checkin, Chekout, roomType, hotel, pay, "UnPaid", priceHotel);

                    database.getReference("Registered Users").child(auth.getUid()).child("transaction").child(key).setValue(tr);

                    AlertDialog.Builder builder = new AlertDialog.Builder(BookingHotel.this);
                    View dialogView = getLayoutInflater().inflate(R.layout.finish_booking, null);
                    builder.setView(dialogView);
                    AlertDialog dialog = builder.create();
                    namaDialog = dialogView.findViewById(R.id.nama_hotel);
                    namaDialog.setText(hotel.getHotel_name());

                    hargaDialog = dialogView.findViewById(R.id.price_hotel);
                    hargaDialog.setText(String.valueOf(priceHotel));
                    dialog.getWindow().setBackgroundDrawableResource(R.drawable.ungu_round);

                    dialogView.findViewById(R.id.accessHistory).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(BookingHotel.this, HistoryActivity.class);
                            startActivity(intent);
                        }
                    });
                    dialog.show();
                }
            }
        });

    }
}