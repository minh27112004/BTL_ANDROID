package com.example.btl_android.Adapter;

import static com.example.btl_android.Fragment.FragGioHangUser.listSanPham;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import com.example.btl_android.ChiTietDonDatAdminActivity;
import com.example.btl_android.DAO.DonDatUserDAO;
import com.example.btl_android.DAO.SanPhamTrangChuDAO;
import com.example.btl_android.DAO.TrangChuAdminDAO;
import com.example.btl_android.DTO.DonDatUserDTO;
import com.example.btl_android.R;

public class DonDatAdminAdapter extends RecyclerView.Adapter<DonDatAdminAdapter.DanhSachDonDatViewHolder> {

    private List<DonDatUserDTO> list;
    Context context;
    DonDatUserDAO donDatUserDAO;

    public DonDatAdminAdapter(List<DonDatUserDTO> list, Context context) {
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
    public DanhSachDonDatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_danh_sach_don_admin, parent, false);
        return new DanhSachDonDatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhSachDonDatViewHolder holder, int position) {

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


        /*holder.btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.txtTrangThaiAdmin.setText("Đang giao hàng");
                id.setTrangThai("Đang giao hàng");
                int kq = donDatUserDAO.updateTrangThai(id);

                list = donDatUserDAO.donDat();
                setData(list);

                if (kq > 0) {
                    Toast.makeText(context, "Đang giao hàng", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Thât bại", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        holder.btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                DonDatUserDTO donHang = list.get(position);
                TrangChuAdminDAO sanPhamDAO = new TrangChuAdminDAO(context);

                try {
                    String[] cacSanPham = donHang.getTenSanPham().split("\n");

                    for (String sanPhamStr : cacSanPham) {
                        if (sanPhamStr.trim().isEmpty()) continue;


                        String[] parts = sanPhamStr.split(",");
                        if (parts.length < 2) continue;

                        String tenSanPham = parts[0].substring(2).trim(); // Bỏ dấu "- " ở đầu
                        int soLuong = Integer.parseInt(parts[1].replaceAll("[^0-9]", "")); // Lấy số từ "Số lượng: 2Kg"


                        int idSanPham = sanPhamDAO.layIdSanPhamTheoTen(tenSanPham);

                        if (idSanPham > 0) {

                            boolean capNhatThanhCong = sanPhamDAO.capNhatSoLuong(idSanPham, soLuong);
                            if (!capNhatThanhCong) {
                                Toast.makeText(context, "Không đủ số lượng cho " + tenSanPham, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }

                    // Cập nhật trạng thái đơn hàng
                    donHang.setTrangThai("Đang giao hàng");
                    int kq = donDatUserDAO.updateTrangThai(donHang);

                    if (kq > 0) {
                        list = donDatUserDAO.donDat();
                        setData(list);
                        Toast.makeText(context, "Xác nhận đơn hàng thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Cập nhật trạng thái thất bại", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {

                    Toast.makeText(context, "Lỗi xử lý đơn hàng", Toast.LENGTH_SHORT).show();
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

    public class DanhSachDonDatViewHolder extends RecyclerView.ViewHolder {
        TextView txtTrangThaiAdmin, txtTenKhachHangAdmin, txtDanhSachSanPhamAdmin, txtThoiGianAdmin, txtTongTienAdmin;
        Button btnXacNhan;

        public DanhSachDonDatViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTrangThaiAdmin = itemView.findViewById(R.id.txtTrangThaiDonDatAdmin);
            txtTenKhachHangAdmin = itemView.findViewById(R.id.txtTenKhachHangDonDatAdmin);
            txtDanhSachSanPhamAdmin = itemView.findViewById(R.id.txtDanhSachSanPhamAdmin);
            txtThoiGianAdmin = itemView.findViewById(R.id.txtThoiGianItemDonDatAdmin);
            txtTongTienAdmin = itemView.findViewById(R.id.txtTongTienItemDonDatAdmin);
            btnXacNhan = itemView.findViewById(R.id.btnXacNhanDonAdmin);

        }
    }
}
