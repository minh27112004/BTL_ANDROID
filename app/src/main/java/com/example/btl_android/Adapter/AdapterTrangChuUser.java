package com.example.btl_android.Adapter;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import com.example.btl_android.ChiTietSanPhamActivity;
import com.example.btl_android.DAO.GioHangDAO;
import com.example.btl_android.DTO.GioHangDTO;
import com.example.btl_android.DTO.SanPhamTrangChuUserDTO;
import com.example.btl_android.R;

public class AdapterTrangChuUser extends RecyclerView.Adapter<AdapterTrangChuUser.ViewHolder> {

    Context context;
    List<SanPhamTrangChuUserDTO> list;
    private GioHangDAO gioHangDAO;
    private List<GioHangDTO> listGioHang;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

    public AdapterTrangChuUser(Context context, List<SanPhamTrangChuUserDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view1 = LayoutInflater.from(context).inflate(R.layout.item_san_pham_trang_chu, parent, false);

        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SanPhamTrangChuUserDTO id = list.get(position);
        holder.tvTenSanPham.setText(list.get(position).getTenSanPhamUser());
        holder.tvGiaSanPham.setText(decimalFormat.format(list.get(position).getGiaSanPhamUser()) + " VND / 1kg");

        String tenImg = list.get(position).getAnhSanPhamUser();
        int resourceId = context.getResources().getIdentifier(tenImg, "drawable", context.getPackageName());
        if (resourceId != 0) {
            holder.ivAnhSanPham.setImageResource(resourceId);
        }
        else{
            String base64 = list.get(position).getAnhSanPhamUser();
            try {
                byte[] imageBytes = Base64.decode(base64, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                if (bitmap != null) {
                    holder.ivAnhSanPham.setImageBitmap(bitmap);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        gioHangDAO = new GioHangDAO(context);
        listGioHang = gioHangDAO.getAll();
        holder.ivIconGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String tenSanPham = id.getTenSanPhamUser();
                int donGia = id.getGiaSanPhamUser();
                String tenAnh = id.getAnhSanPhamUser();

                // Kiểm tra trùng lặp
                boolean isExist = false;
                for (GioHangDTO item : listGioHang) {
                    if (item.getTenSanPham().equals(tenSanPham)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    Toast.makeText(context, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    GioHangDTO objGioHang = new GioHangDTO();
                    objGioHang.setTenSanPham(tenSanPham);
                    objGioHang.setGiaSanPham(donGia);
                    objGioHang.setImgSanPham(tenAnh);
                    objGioHang.setSoLuongSanPham(1);
                    objGioHang.setTongTienCuaSp(donGia);

                    long kq = gioHangDAO.addRow(objGioHang);
                    if (kq > 0) {
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        listGioHang.add(objGioHang);

                    } else {
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(((Activity) context), ChiTietSanPhamActivity.class);
                remenberItem(id.getTenSanPhamUser(), id.getGiaSanPhamUser(), id.getAnhSanPhamUser(), id.getMoTaSp());
                ((Activity) context).startActivity(intent);
            }
        });

    }

    private void remenberItem(String tenSanPhamUser, int giaSanPham, String anhSp, String moTaSp) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("PRODUCT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("tenSp", tenSanPhamUser);
        editor.putInt("doGia", giaSanPham);
        editor.putString("anhSp", anhSp);
        editor.putString("moTa", moTaSp);
        editor.apply();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivAnhSanPham, ivIconGioHang;
        TextView tvTenSanPham, tvGiaSanPham;
        CardView layoutItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layoutItemTrangChu);
            ivAnhSanPham = itemView.findViewById(R.id.imgSanPhamTrangChu);
            ivIconGioHang = itemView.findViewById(R.id.ivIconGioHangItem);
            tvGiaSanPham = itemView.findViewById(R.id.tvGiaSanPhamItemTrangChu);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPhamItemTrangChu);

        }
    }

}
