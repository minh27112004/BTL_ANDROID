package com.example.btl_android.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.btl_android.ChiTietSanPhamActivity;
import com.example.btl_android.DTO.SanPhamRauAdminDTO;
import com.example.btl_android.R;

public class AdapterSanPhamCuAdmin extends RecyclerView.Adapter<AdapterSanPhamCuAdmin.ViewHolderCu> {
    ArrayList<SanPhamRauAdminDTO> list;
    Context context;
    private AdapterSanPhamRauAdmin.SanPhamAdminInterface listener;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public AdapterSanPhamCuAdmin(ArrayList<SanPhamRauAdminDTO> list, Context context, AdapterSanPhamRauAdmin.SanPhamAdminInterface listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }

    public AdapterSanPhamCuAdmin(ArrayList<SanPhamRauAdminDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public interface SanPhamAdminInterface {
        void updateSanPham(SanPhamRauAdminDTO dto);
    }

    @NonNull
    @Override
    public ViewHolderCu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rau_danh_sach_san_pham_admin, parent, false);
        return new ViewHolderCu(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCu holder, @SuppressLint("RecyclerView") int position) {

        SanPhamRauAdminDTO id = list.get(position);

        String nameImg = list.get(position).getImg_url();
        int resourceImg = ((Activity) context).getResources().getIdentifier(nameImg, "drawable", ((Activity) context).getPackageName());
        holder.imgDanhSachSanPhamAdmin.setImageResource(resourceImg);

        String base64 = list.get(position).getImg_url();
        try {
            byte[] imageBytes = Base64.decode(base64, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

            if (bitmap != null) {
                holder.imgDanhSachSanPhamAdmin.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.tvGiaSanPhamDanhSachSanPhamAdmin.setText(decimalFormat.format(list.get(position).getDon_gia()) + " VND / 1kg");
        holder.tvTenDanhSachSanPhamAdmin.setText(list.get(position).getTen_san_pham());

        holder.ivSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, SuaSanPhamAdmin.class);
//                context.startActivity(intent);
                listener.updateSanPham(list.get(position));

            }
        });

        //Xem chi tiết sản phâm
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                remenBerProduct(id.getTen_san_pham(), id.getDon_gia(), id.getImg_url(), id.getMo_ta());
                context.startActivity(new Intent(context, ChiTietSanPhamActivity.class));


            }
        });


    }

    private void remenBerProduct(String tenSanPham, int donGia, String imgUrl, String moTa) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PRODUCT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("tenSp", tenSanPham);
        editor.putInt("doGia", donGia);
        editor.putString("anhSp", imgUrl);
        editor.putString("moTa", moTa);

        editor.apply();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderCu extends RecyclerView.ViewHolder {
        ImageView imgDanhSachSanPhamAdmin, ivSua;
        TextView tvTenDanhSachSanPhamAdmin, tvGiaSanPhamDanhSachSanPhamAdmin;


        public ViewHolderCu(@NonNull View itemView) {
            super(itemView);

            imgDanhSachSanPhamAdmin = itemView.findViewById(R.id.imgDanhSachSanPhamAdmin);
            tvTenDanhSachSanPhamAdmin = itemView.findViewById(R.id.tvTenDanhSachSanPhamAdmin);
            tvGiaSanPhamDanhSachSanPhamAdmin = itemView.findViewById(R.id.tvGiaSanPhamDanhSachSanPhamAdmin);
            ivSua = itemView.findViewById(R.id.ivIconSuaItem);

        }
    }
}
