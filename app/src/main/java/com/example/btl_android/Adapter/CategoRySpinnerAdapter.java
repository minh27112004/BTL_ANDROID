package com.example.btl_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import com.example.btl_android.DTO.Category;
import com.example.btl_android.R;

public class CategoRySpinnerAdapter extends ArrayAdapter<Category> {


    public CategoRySpinnerAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_themsp, parent, false);
        TextView txtSelected = convertView.findViewById(R.id.txt_selected);

        Category category = this.getItem(position);
        if (category != null) {
            txtSelected.setText(category.getName());
        }

        return convertView;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_spinner, parent, false);
        TextView txtCategory = convertView.findViewById(R.id.txtCategorySpinner);

        Category category = this.getItem(position);
        if (category != null) {
            txtCategory.setText(category.getName());
        }

        return convertView;
    }
}
