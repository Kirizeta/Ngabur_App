package com.example.ngabur;

import static com.example.ngabur.R.id.Hotel_image;
import static com.example.ngabur.R.id.Hotel_image_hst;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyViewHolder> {

    private Context context;
    private List<Transaction> transactions;

    String hotel_name;
    String hotel_facility;
    String hotel_bed;
    String hotel_photo;
    String hotel_rate;
    String hotel_breakfast;
    String hotel_location;
    String hotel_description;
    int hotel_price;
    String hotel_uid;

    public AdapterHistory(Context context, List<Transaction> transactions) {
        this.context = context;
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemhistory, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(transactions.get(position).getHotel().getHotel_photo()).into(holder.hotelImage);
        holder.hotelName.setText(transactions.get(position).getHotel().getHotel_name());
        holder.hotelbed.setText(transactions.get(position).getHotel().getHotel_bed()+" bed");
        holder.hotelRating.setText(transactions.get(position).getHotel().getHotel_rate());
        holder.hotelbreakfast.setText(transactions.get(position).getHotel().getHotel_breakfast()+ "AM");
        holder.hotelLocation.setText(transactions.get(position).getHotel().getHotel_location());
        holder.hotelprice.setText("Rp."+transactions.get(position).getTotalharga());
        holder.dateCekin.setText(transactions.get(position).getCheckInDate());
        holder.dateCekot.setText(transactions.get(position).getCheckOutDate());
//        holder.cardView.setOnClickListener(new View.OnClickListener() {

//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, DetailHotel.class);
//
//                hotel_uid = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_uid();
//                hotel_description = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_description();
//                hotel_price = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_price();
//                hotel_location = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_location();
//                hotel_breakfast = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_breakfast();
//                hotel_rate = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_rate();
//                hotel_photo = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_photo();
//                hotel_bed = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_bed();
//                hotel_facility = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_facility();
//                hotel_name = transactions.get(holder.getAdapterPosition()).getHotel().getHotel_name();
//
//                DataClass hotel = new DataClass(hotel_name, hotel_facility, hotel_bed, hotel_photo, hotel_rate, hotel_breakfast, hotel_location, hotel_description, hotel_price, hotel_uid);
//
//                intent.putExtra("hotelClass", hotel);
//                intent.putExtra("Image", transactions.get(holder.getAdapterPosition()).getHotel_photo());
//                intent.putExtra("Name", transactions.get(holder.getAdapterPosition()).getHotel_name());
//                intent.putExtra("Facility", transactions.get(holder.getAdapterPosition()).getHotel_facility());
//                intent.putExtra("Bed", transactions.get(holder.getAdapterPosition()).getHotel_bed());
//                intent.putExtra("Rating", transactions.get(holder.getAdapterPosition()).getHotel_rate());
//                intent.putExtra("Breakfast", transactions.get(holder.getAdapterPosition()).getHotel_breakfast());
//                intent.putExtra("Location", transactions.get(holder.getAdapterPosition()).getHotel_location());
//                intent.putExtra("Description", transactions.get(holder.getAdapterPosition()).getHotel_description());
//                intent.putExtra("Price", transactions.get(holder.getAdapterPosition()).getHotel_price());
//                intent.putExtra("HotelID", transactions.get(holder.getAdapterPosition()).getHotel_uid());
//
//                context.startActivity(intent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public void searchDataList(ArrayList<Transaction> transactionArrayList){
        transactionArrayList = transactionArrayList;
        notifyDataSetChanged();
    }

    public void checkDataList(ArrayList<Transaction> transactionArrayList) {
        transactionArrayList = transactionArrayList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView hotelName, dateCekin, dateCekot ,hotelbed, hotelRating , hotelbreakfast , hotelLocation, hotelprice;
        ImageView hotelImage;;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hotelImage = itemView.findViewById(Hotel_image_hst);
            hotelName = itemView.findViewById(R.id.Hotel_name_hst);
            hotelbed = itemView.findViewById(R.id.Hotel_bed_hst);
            cardView = itemView.findViewById(R.id.card_view_history);
            hotelRating = itemView.findViewById(R.id.hotel_rating_hst);
            hotelbreakfast = itemView.findViewById(R.id.Hotel_breakfast_hst);
            hotelLocation = itemView.findViewById(R.id.Hotel_location_hst);
            hotelprice = itemView.findViewById(R.id.priceTxt_hst);
            dateCekin = itemView.findViewById(R.id.dateCheckIn_hst);
            dateCekot = itemView.findViewById(R.id.dateCheckOut_hst);

        }
    }

}
