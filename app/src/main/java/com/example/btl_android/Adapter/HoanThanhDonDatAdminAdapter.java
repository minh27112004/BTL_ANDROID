package com.example.btl_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import com.example.btl_android.ChiTietDonDatAdminActivity;
import com.example.btl_android.DAO.DonDatUserDAO;
import com.example.btl_android.DTO.DonDatUserDTO;
import com.example.btl_android.R;

public class HoanThanhDonDatAdminAdapter extends RecyclerView.Adapter<HoanThanhDonDatAdminAdapter.HoanThanhDonDatViewHolder> {
    private List<DonDatUserDTO> list;
    Context context;
    DonDatUserDAO donDatUserDAO;

    public HoanThanhDonDatAdminAdapter(List<DonDatUserDTO> list, Context context) {
        this.list = list;
        this.context = context;
        this.donDatUserDAO = new DonDatUserDAO(context);
    }

    public void setData(List<DonDatUserDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HoanThanhDonDatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_sach_don_admin, parent, false);
        return new HoanThanhDonDatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HoanThanhDonDatViewHolder holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");

        DonDatUserDTO id = list.get(position);

        holder.txtTongTienAdmin.setText("Tổng tiền: " + decimalFormat.format(list.get(position).getTongTien()) + " VND");
        holder.txtThoiGianAdmin.setText("Thời gian: " + list.get(position).getNgayDat());
        holder.txtDanhSachSanPhamAdmin.setText("" + list.get(position).getTenSanPham());
        holder.txtTenKhachHangAdmin.setText("Tên khách hàng: " + list.get(position).getTenKhachHang());
        holder.txtTrangThaiAdmin.setText(list.get(position).getTrangThai());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietDonDatAdminActivity.class);
                intent.putExtra("idHoaDon", id.getId());
                intent.putExtra("tenKhach", id.getTenKhachHang());
                intent.putExtra("soDienThoai", id.getSoDienThoai());
                intent.putExtra("diaChi", id.getDiaChi());
                intent.putExtra("tenSanPham", id.getTenSanPham());
                intent.putExtra("tongTien", id.getTongTien());
                intent.putExtra("ngayDat", id.getNgayDat());
                intent.putExtra("trangThai", id.getTrangThai());
                context.startActivity(intent);
            }
        });

        if (list.get(position).getTrangThai().equals("Đã thanh toán")) {

            holder.btnXacNhan.setVisibility(View.GONE);
            holder.view.setVisibility(View.GONE);

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HoanThanhDonDatViewHolder extends RecyclerView.ViewHolder {

        TextView txtTrangThaiAdmin, txtTenKhachHangAdmin, txtDanhSachSanPhamAdmin, txtThoiGianAdmin, txtTongTienAdmin;
        Button btnXacNhan;
        View view;

        public HoanThanhDonDatViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTrangThaiAdmin = itemView.findViewById(R.id.txtTrangThaiDonDatAdmin);
            txtTenKhachHangAdmin = itemView.findViewById(R.id.txtTenKhachHangDonDatAdmin);
            txtDanhSachSanPhamAdmin = itemView.findViewById(R.id.txtDanhSachSanPhamAdmin);
            txtThoiGianAdmin = itemView.findViewById(R.id.txtThoiGianItemDonDatAdmin);
            txtTongTienAdmin = itemView.findViewById(R.id.txtTongTienItemDonDatAdmin);
            btnXacNhan = itemView.findViewById(R.id.btnXacNhanDonAdmin);
            view = itemView.findViewById(R.id.lendView);
        }
    }
}
