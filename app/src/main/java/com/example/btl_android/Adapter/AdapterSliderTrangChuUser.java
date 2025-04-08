package com.example.btl_android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.example.btl_android.DTO.SliderDTO;
import com.example.btl_android.R;

public class AdapterSliderTrangChuUser extends RecyclerView.Adapter<AdapterSliderTrangChuUser.ViewHolder> {

    private List<SliderDTO> list;

    public AdapterSliderTrangChuUser(List<SliderDTO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider,parent,false);

        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.ivAnhSlider.setImageResource(list.get(position).getImgSlider());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAnhSlider;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivAnhSlider = itemView.findViewById(R.id.ivSliderItem);


        }
    }

}
