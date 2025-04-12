package com.example.btl_android.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.btl_android.ChiTietDonDatUserActivity;
import com.example.btl_android.DTO.DonDatUserDTO;
import com.example.btl_android.R;

public class AdapterDonDatUser extends RecyclerView.Adapter<AdapterDonDatUser.ViewHolder> {


    Context context;
    List<DonDatUserDTO> list;

    public AdapterDonDatUser(Context context, List<DonDatUserDTO> list) {
        this.context = context;
        if (list == null){
            this.list = new ArrayList<>();
        }else {
            this.list = list;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.item_don_dat_user, parent, false);

        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        DonDatUserDTO id = list.get(position);

        holder.tvTongTienDonDat.setText("Tổng tiền : " + decimalFormat.format(list.get(position).getTongTien()) + " VND");
        holder.tvThoiGian.setText("Ngày đặt : " + list.get(position).getNgayDat());
        holder.tvDanhSachSp.setText("" + list.get(position).getTenSanPham());
        holder.tvTenKhach.setText("Tên khách hàng : " + list.get(position).getTenKhachHang());
        holder.tvTrangThai.setText(list.get(position).getTrangThai());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                remenberDonDat(
                        id.getId(), id.getTenKhachHang(), id.getSoDienThoai(), id.getDiaChi(),
                        id.getTenSanPham(), id.getTongTien(), id.getNgayDat(), id.getTrangThai()

                );
                Intent intent = new Intent(((Activity) context), ChiTietDonDatUserActivity.class);
                intent.putExtra("idHoaDon", id.getId());
                intent.putExtra("tenKhach", id.getTenKhachHang());
                intent.putExtra("soDienThoai", id.getSoDienThoai());
                intent.putExtra("diaChi", id.getDiaChi());
                intent.putExtra("tenSanPham", id.getTenSanPham());
                intent.putExtra("tongTien", id.getTongTien());
                intent.putExtra("ngayDat", id.getNgayDat());
                intent.putExtra("trangThai", id.getTrangThai());
                ((Activity) context).startActivity(intent);
            }
        });


    }

    private void remenberDonDat(int id, String tenKhachHang, String soDienThoai, String diaChi
            , String tenSanPham, int tongTien, String ngayDat, String trangThai) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("DONDAT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("idDonDat", id);
        editor.putString("tenKhachHang", tenKhachHang);
        editor.putString("soDienThoai", soDienThoai);
        editor.putString("diaChi", diaChi);
        editor.putString("tenSanPham", tenSanPham);
        editor.putInt("tongTien", tongTien);
        editor.putString("ngayDat", ngayDat);
        editor.putString("trangThai", trangThai);

        editor.apply();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTongTienDonDat;
        TextView tvTenKhach, tvDanhSachSp, tvThoiGian, tvTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTongTienDonDat = itemView.findViewById(R.id.tvTongTienItemDonDatUser);
            tvDanhSachSp = itemView.findViewById(R.id.tvDanhSachSanPham);
            tvThoiGian = itemView.findViewById(R.id.tvThoiGianItemDonDatUser);
            tvTenKhach = itemView.findViewById(R.id.tvTenKhach);
            tvTrangThai = itemView.findViewById(R.id.tvTrangThaiDonDat);

        }
    }

}
