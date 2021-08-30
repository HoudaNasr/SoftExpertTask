package com.example.softexperttast.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.softexperttast.R;
import com.example.softexperttast.data.models.Datum;

import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {
    private List<Datum> carsList;
    private Context context;
    public CarsAdapter(List<Datum> carsList) {
        this.carsList = carsList;
    }



    public class CarsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCar;
        private TextView tvCarName , tvCarState , tvCarConsutionYear;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCar = itemView.findViewById(R.id.car_image);
            tvCarName = itemView.findViewById(R.id.brand_text_view);
            tvCarState = itemView.findViewById(R.id.isUsed_text_view);
            tvCarConsutionYear = itemView.findViewById(R.id.consultion_year_text_view);

        }
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cars_layout, parent, false);
        CarsViewHolder catsViewHolder = new CarsViewHolder(v);
        context = parent.getContext();
        return catsViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        Datum car = carsList.get(position);

        holder.tvCarName.setText(car.getBrand());
        if (car.getConstractionYear() != null){
            holder.tvCarConsutionYear.setText(car.getConstractionYear() + "");

        }
        if (car.getImageUrl() != null){
            Glide.with(context).load(car.getImageUrl()).into(holder.imgCar);
        }
        holder.tvCarState.setText(car.getIsUsed() + "");
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

}
