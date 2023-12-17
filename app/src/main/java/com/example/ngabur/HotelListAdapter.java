package com.example.ngabur;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HotelListAdapter extends RecyclerView.Adapter<HotelListAdapter.ViewHolder> {
    List<DataClass> items;
    Context context;

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

    public HotelListAdapter(Context context, List<DataClass> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_hotel_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.titleTxt.setText(items.get(position).getHotel_name());
        holder.locationTxt.setText(items.get(position).getHotel_location());
        holder.ScoreTxt.setText("" + items.get(position).getHotel_rate());
        holder.price.setText("" + items.get(position).getHotel_price());
//        Glide.with(context).load(items.get(position)).into(holder.pic);
        Picasso.get().load(items.get(position).getHotel_photo()).centerCrop().fit().into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailHotel.class);
                hotel_uid = items.get(holder.getAdapterPosition()).getHotel_uid();
                hotel_description = items.get(holder.getAdapterPosition()).getHotel_description();
                hotel_price = items.get(holder.getAdapterPosition()).getHotel_price();
                hotel_location = items.get(holder.getAdapterPosition()).getHotel_location();
                hotel_breakfast = items.get(holder.getAdapterPosition()).getHotel_breakfast();
                hotel_rate = items.get(holder.getAdapterPosition()).getHotel_rate();
                hotel_photo = items.get(holder.getAdapterPosition()).getHotel_photo();
                hotel_bed = items.get(holder.getAdapterPosition()).getHotel_bed();
                hotel_facility = items.get(holder.getAdapterPosition()).getHotel_facility();
                hotel_name = items.get(holder.getAdapterPosition()).getHotel_name();

                DataClass hotel = new DataClass(hotel_name, hotel_facility, hotel_bed, hotel_photo, hotel_rate, hotel_breakfast, hotel_location, hotel_description, hotel_price, hotel_uid);

                intent.putExtra("hotelClass", hotel);
                intent.putExtra("HotelID", items.get(holder.getAdapterPosition()).getHotel_uid());
                intent.putExtra("Image", items.get(holder.getAdapterPosition()).getHotel_photo());
                intent.putExtra("Name", items.get(holder.getAdapterPosition()).getHotel_name());
                intent.putExtra("Facility", items.get(holder.getAdapterPosition()).getHotel_facility());
                intent.putExtra("Bed", items.get(holder.getAdapterPosition()).getHotel_bed());
                intent.putExtra("Rating", items.get(holder.getAdapterPosition()).getHotel_rate());
                intent.putExtra("Breakfast", items.get(holder.getAdapterPosition()).getHotel_breakfast());
                intent.putExtra("Location", items.get(holder.getAdapterPosition()).getHotel_location());
                intent.putExtra("Description", items.get(holder.getAdapterPosition()).getHotel_description());
                intent.putExtra("Price", items.get(holder.getAdapterPosition()).getHotel_price());

                context.startActivity(intent);
            }
        });

//        int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getRecipe_photo(), "drawable", holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId)
//                .transform(new GranularRoundedCorners(30, 30, 0, 0))
//                .into(holder.pic);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(holder.itemView.getContext(), DetailActivity.class);
//                intent.putExtra("object",items.get(position));
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, locationTxt, ScoreTxt, price;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.hotel_name1);
            locationTxt = itemView.findViewById(R.id.hotel_location1);
            ScoreTxt = itemView.findViewById(R.id.hotel_rating1);
            pic = itemView.findViewById(R.id.hotel_image1);
            price = itemView.findViewById(R.id.hotel_price1);

        }
    }
}
