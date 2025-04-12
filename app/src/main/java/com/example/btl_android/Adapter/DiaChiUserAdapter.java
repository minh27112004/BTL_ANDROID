package com.example.btl_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_android.CapNhatDonDatActivity;
import com.example.btl_android.DTO.ThongTinKhachHangDTO;
import com.example.btl_android.R;

import java.util.List;

public class DiaChiUserAdapter extends RecyclerView.Adapter<DiaChiUserAdapter.DiaChiUserViewHolder> {

    private List<ThongTinKhachHangDTO> list;
    private Context context;

    public DiaChiUserAdapter(List<ThongTinKhachHangDTO> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DiaChiUserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_diachi, parent, false);
        return new DiaChiUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiaChiUserViewHolder holder, int position) {
        ThongTinKhachHangDTO item = list.get(position);
        holder.txtTenDiaChi.setText("Tên khách hàng: " + item.getTenKhachHang());
        holder.txtPhoneDiaChi.setText("Số điện thoại: " + item.getSoDienThoai());
        holder.txtDiaChi.setText("Địa chỉ: " + item.getDiaChi());


        holder.btnCapNhatDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentPosition = holder.getAdapterPosition();
                // Kiểm tra xem vị trí có hợp lệ không
                if (currentPosition != RecyclerView.NO_POSITION && currentPosition < list.size()) {
                    ThongTinKhachHangDTO currentItem = list.get(currentPosition);

                    String tenKhachHang = currentItem.getTenKhachHang();
                    String soDienThoai = currentItem.getSoDienThoai();
                    String diaChi = currentItem.getDiaChi();

                    Intent intent = new Intent(context, CapNhatDonDatActivity.class);
                    intent.putExtra("ten_khach_hang", tenKhachHang);
                    intent.putExtra("so_dien_thoai", soDienThoai);
                    intent.putExtra("dia_chi", diaChi);

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class DiaChiUserViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTenDiaChi, txtPhoneDiaChi, txtDiaChi;
        private AppCompatButton btnCapNhatDiaChi;

        public DiaChiUserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTenDiaChi = itemView.findViewById(R.id.txt_ten_dia_chi);
            txtPhoneDiaChi = itemView.findViewById(R.id.txt_phone_dia_chi);
            txtDiaChi = itemView.findViewById(R.id.txt_dia_chi);
            btnCapNhatDiaChi = itemView.findViewById(R.id.btnCapNhatDiaChi);
        }
    }
}