package com.example.ngabur;

import static com.example.ngabur.R.id.Hotel_image;

import android.content.Context;
import android.content.Intent;
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

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context context;
    private List<DataClass> dataList;

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

    public Adapter(Context context, List<DataClass> datalist) {
        this.context = context;
        this.dataList = datalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(dataList.get(position).getHotel_photo()).into(holder.hotelImage);
//        Glide.with(context).load(dataList.get(position)).into(holder.foodImage);
        holder.hotelName.setText(dataList.get(position).getHotel_name());
        holder.hotelFacility.setText(dataList.get(position).getHotel_facility());
        holder.hotelbed.setText(dataList.get(position).getHotel_bed()+" bed");
        holder.hotelRating.setText(dataList.get(position).getHotel_rate());
        holder.hotelbreakfast.setText(dataList.get(position).getHotel_breakfast()+ "AM");
        holder.hotelLocation.setText(dataList.get(position).getHotel_location());
        holder.Hoteldescription.setText(dataList.get(position).getHotel_description());
        holder.Hotelprice.setText("Rp."+dataList.get(position).getHotel_price()+"/night");
        holder.hotelUid.setText(dataList.get(position).getHotel_uid());
        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHotel.class);

                hotel_uid = dataList.get(holder.getAdapterPosition()).getHotel_uid();
                hotel_description = dataList.get(holder.getAdapterPosition()).getHotel_description();
                hotel_price = dataList.get(holder.getAdapterPosition()).getHotel_price();
                hotel_location = dataList.get(holder.getAdapterPosition()).getHotel_location();
                hotel_breakfast = dataList.get(holder.getAdapterPosition()).getHotel_breakfast();
                hotel_rate = dataList.get(holder.getAdapterPosition()).getHotel_rate();
                hotel_photo = dataList.get(holder.getAdapterPosition()).getHotel_photo();
                hotel_bed = dataList.get(holder.getAdapterPosition()).getHotel_bed();
                hotel_facility = dataList.get(holder.getAdapterPosition()).getHotel_facility();
                hotel_name = dataList.get(holder.getAdapterPosition()).getHotel_name();

                DataClass hotel = new DataClass(hotel_name, hotel_facility, hotel_bed, hotel_photo, hotel_rate, hotel_breakfast, hotel_location, hotel_description, hotel_price, hotel_uid);

                intent.putExtra("hotelClass", hotel);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getHotel_photo());
                intent.putExtra("Name", dataList.get(holder.getAdapterPosition()).getHotel_name());
                intent.putExtra("Facility", dataList.get(holder.getAdapterPosition()).getHotel_facility());
                intent.putExtra("Bed", dataList.get(holder.getAdapterPosition()).getHotel_bed());
                intent.putExtra("Rating", dataList.get(holder.getAdapterPosition()).getHotel_rate());
                intent.putExtra("Breakfast", dataList.get(holder.getAdapterPosition()).getHotel_breakfast());
                intent.putExtra("Location", dataList.get(holder.getAdapterPosition()).getHotel_location());
                intent.putExtra("Description", dataList.get(holder.getAdapterPosition()).getHotel_description());
                intent.putExtra("Price", dataList.get(holder.getAdapterPosition()).getHotel_price());
                intent.putExtra("HotelID", dataList.get(holder.getAdapterPosition()).getHotel_uid());

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<DataClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }

    public void checkDataList(ArrayList<DataClass> checkList) {
        dataList = checkList;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView hotelName, hotelFacility, hotelbed ,hotelRating , hotelbreakfast , hotelLocation, Hotelprice, Hoteldescription, hotelUid;
        ImageView hotelImage;;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hotelImage = itemView.findViewById(Hotel_image);
            hotelName = itemView.findViewById(R.id.Hotel_name);
            hotelFacility = itemView.findViewById(R.id.Hotel_Facility);
            hotelbed = itemView.findViewById(R.id.Hotel_bed);
            cardView = itemView.findViewById(R.id.card_view);
            hotelRating = itemView.findViewById(R.id.hotel_rating);
            hotelbreakfast = itemView.findViewById(R.id.Hotel_breakfast);
            hotelLocation = itemView.findViewById(R.id.Hotel_location);
            Hotelprice = itemView.findViewById(R.id.priceTxt);
            Hoteldescription = itemView.findViewById(R.id.Hotel_description);
            hotelUid = itemView.findViewById(R.id.hotel_uid);

//            foodRating = itemView.findViewById(R.id.food_rating);



        }
    }

}
