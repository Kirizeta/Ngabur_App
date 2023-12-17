package com.example.ngabur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Booking_Hotel_date extends AppCompatActivity {

    TextView selectedDate;
    Button nextBtn, backBtn;
    CalendarView cv;
    Spinner adult, child, days;
    AutoCompleteTextView room;
    String selection;

    ImageView back;

    String namaHotel;
    String descHotel;
    int price;
    String CheckInDate, CheckOutDate;
    int quantityDay;
    DataClass hotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_hotel_date);

        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Integer[] daysQuant = new Integer[]{1, 2, 3, 4, 5};
        String[] rooms = new String[]{"Standard", "Deluxe", "Presidency"};

        nextBtn = findViewById(R.id.btn_next);
        back = findViewById(R.id.Hotel_back1);
        cv = findViewById(R.id.calender_book);
        selectedDate = findViewById(R.id.tv_date);
        adult = findViewById(R.id.spin_adult);
        child = findViewById(R.id.spin_child);
        room = findViewById(R.id.Room);
        days = findViewById(R.id.spin_days);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapterRoom = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, rooms);
        ArrayAdapter<Integer> adapterdaysQ = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, daysQuant);

        adult.setAdapter(adapter);
        child.setAdapter(adapter);
        days.setAdapter(adapterdaysQ);
        room.setAdapter(adapterRoom);

        Date today = Calendar.getInstance().getTime();//getting date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");//formating according to my need
        String date = formatter.format(today);
        selectedDate.setText(date);

        cv.setFirstDayOfWeek(2);

        cv.setMinDate(cv.getDate());

        long now = System.currentTimeMillis() - 1000;
        cv.setMaxDate(now+(1000*60*60*24*7));

        days.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
//                get quantity Day
                quantityDay = daysQuant[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                quantityDay = daysQuant[0];
            }
        });

        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView arg0, int year, int month,
                                            int date) {
//                Toast.makeText(getApplicationContext(),date+ "/"+month+"/"+year,4000).show();
                CheckInDate = String.format("%d/%d/%d", date, month+1, year);
                CheckOutDate = String.format("%d/%d/%d", date + quantityDay, month+1, year);
                selectedDate.setText(CheckInDate);

            }

        });





        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        room.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selection = (String)adapterView.getItemAtPosition(i);
//                Toast.makeText(Booking_Hotel_date.this, selection, Toast.LENGTH_SHORT).show();
            }
        });

        room.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selection = (String)adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selection = (String)adapterView.getItemAtPosition(0);
            }
        });


        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            namaHotel = bundle.getString("nameHotel");
            descHotel = bundle.getString("descHotel");
            price = bundle.getInt("priceHotel");
            hotel = (DataClass) bundle.getSerializable("hotelClass");
            // minta hotel id
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Booking_Hotel_date.this, BookingHotel.class);
                intent.putExtra("hotelClass", hotel);
                intent.putExtra("CheckInDate", CheckInDate);
                intent.putExtra("CheckOutDate", CheckOutDate);
                intent.putExtra("room", selection);
                intent.putExtra("HotelName", namaHotel);
                intent.putExtra("HotelDesc", descHotel);
                intent.putExtra("PriceHotel", price );
                // kirim hotel id
                startActivity(intent);
            }
        });
    }
}