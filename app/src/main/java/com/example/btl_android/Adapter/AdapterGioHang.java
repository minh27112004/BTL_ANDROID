package com.example.btl_android.Adapter;

import static com.example.btl_android.Fragment.FragGioHangUser.tvTongTienGioHang;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import com.example.btl_android.DAO.GioHangDAO;
import com.example.btl_android.DAO.SanPhamTrangChuDAO;
import com.example.btl_android.DTO.GioHangDTO;
import com.example.btl_android.DTO.SanPhamTrangChuUserDTO;
import com.example.btl_android.R;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolder> {

    Context context;
    public static List<GioHangDTO> list;

    GioHangDAO gioHangDAO;
    SanPhamTrangChuDAO  sanPhamTrangChuDAO;

    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");


    public AdapterGioHang(Context context, List<GioHangDTO> list) {
        this.context = context;
        this.list = list;
        gioHangDAO = new GioHangDAO(context);
        sanPhamTrangChuDAO = new SanPhamTrangChuDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view1 = LayoutInflater.from(context).inflate(R.layout.item_gio_hang, parent, false);
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int currentPosition = holder.getAdapterPosition();
        GioHangDTO id = list.get(currentPosition);

        list = gioHangDAO.getAll();

        holder.tvTenSanPham.setText(list.get(currentPosition).getTenSanPham());
        holder.tvGiaMacDinh.setText(decimalFormat.format(list.get(currentPosition).getGiaSanPham()) + " VND / 1Kg");
        String tenImg = list.get(currentPosition).getImgSanPham();
        int resImg =
                (((Activity) context).getResources().getIdentifier(tenImg
                        , "drawable", ((Activity) context).getPackageName()));
        holder.ivSanPham.setImageResource(resImg);
        holder.tvTongTien.setText(decimalFormat.format(list.get(currentPosition).getTongTienCuaSp()) + " VND");
        holder.tvSoLuongSp.setText(list.get(currentPosition).getSoLuongSanPham() + "");

        String base64 = list.get(currentPosition).getImgSanPham();

        try {
            byte[] imageBytes = Base64.decode(base64, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            if (bitmap != null) {
                holder.ivSanPham.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Xoá sản phẩm trong gio hàng
        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_xac_nhan, null, false);

                builder.setView(view);

                AlertDialog dialog = builder.create();
                Window window = dialog.getWindow();
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                AppCompatButton btnXacNhan, btnHuy;
                TextView tvNoiDung;

                btnHuy = view.findViewById(R.id.btnHuyDialog);
                btnXacNhan = view.findViewById(R.id.btnXacNhanDialog);
                tvNoiDung = view.findViewById(R.id.tvNoiDungDialog);

                tvNoiDung.setText("Bạn có chắc chắn muốn xóa sản phẩm không?");

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnXacNhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int kq = gioHangDAO.deleteRow(id);

                        if (kq > 0) {

                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            list.clear();
                            list.addAll(gioHangDAO.getAll());
                            tinhTongTien();
                            notifyDataSetChanged();
                            dialog.dismiss();

                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });


                dialog.show();
            }
        });

        //Set thêm sản phẩm
        holder.tvCongSoLuongSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id.getSoLuongSanPham() < 10  &&  id.getSoLuongSanPham() < sanPhamTrangChuDAO.getSoLuongHienTai(id.getIdSanPham())) {


                    int soLuongSanPhamMoi = id.getSoLuongSanPham() + 1;
                    id.setSoLuongSanPham(soLuongSanPhamMoi);
                    holder.tvSoLuongSp.setText(soLuongSanPhamMoi + "");

                    //set lại giá sản phẩm khi số lượng sản phẩm thay đổi;
                    int giaMacDinh = id.getGiaSanPham();
                    long tongTien1SanPham = (long) giaMacDinh * soLuongSanPhamMoi;
                    id.setTongTienCuaSp((int) tongTien1SanPham);
                    holder.tvTongTien.setText(decimalFormat.format(tongTien1SanPham) + " VND");

                    gioHangDAO.updateRowSoLuong(id);
                    list.clear();
                    list.addAll(gioHangDAO.getAll());
                    tinhTongTien();

                }
                else{
                    Toast.makeText(v.getContext(),"Mỗi sản phẩm chỉ được chọn tối đa 10kg hoặc đã bị quá số lượng kho!!",Toast.LENGTH_SHORT ).show();
                    return;
                }

            }
        });
        //Set click trừ sản phẩm
        holder.tvTruSoLuongSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id.getSoLuongSanPham() > 1) {

                    //setThemSoLuongSanPham
                    int soLuongSanPhamMoi = id.getSoLuongSanPham() - 1;
                    id.setSoLuongSanPham(soLuongSanPhamMoi);
                    holder.tvSoLuongSp.setText(soLuongSanPhamMoi + "");

                    //set lại giá sản phẩm khi số lượng sản phẩm thay đổi;
                    int giaMacDinh = id.getGiaSanPham();
                    long tongTien1SanPham = (long) giaMacDinh * soLuongSanPhamMoi;
                    id.setTongTienCuaSp((int) tongTien1SanPham);
                    holder.tvTongTien.setText(decimalFormat.format(tongTien1SanPham) + " VND");
                    gioHangDAO.updateRowSoLuong(id);
                    list.clear();
                    list.addAll(gioHangDAO.getAll());
                    tinhTongTien();

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenSanPham, tvCongSoLuongSp, tvTruSoLuongSp, tvSoLuongSp;
        TextView tvGiaMacDinh, tvTongTien;
        ImageView ivSanPham, ivXoa;
        LinearLayout layoutDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenSanPham = itemView.findViewById(R.id.tvTenSanPhamItemGioHang);
            tvGiaMacDinh = itemView.findViewById(R.id.tvGiaSanPhamItemGioHang);
            tvTongTien = itemView.findViewById(R.id.tvTongTien1SanPhamItemGioHang);
            tvCongSoLuongSp = itemView.findViewById(R.id.tvThemSoLuongSanPhamItemGioHang);
            tvTruSoLuongSp = itemView.findViewById(R.id.tvTruSoLuongSanPhamItemGioHang);
            tvSoLuongSp = itemView.findViewById(R.id.tvSoLuongSanPhamCua1ItemGioHang);
            ivSanPham = itemView.findViewById(R.id.ivItemGioHang);
            layoutDelete = itemView.findViewById(R.id.layoutDelete);


        }
    }

    //Hàm tính tổng tiền
    private void tinhTongTien() {
        long tongTien = 0;
        for (int i = 0; i < list.size(); i++) {

            tongTien = tongTien + ((long) list.get(i).getGiaSanPham() * list.get(i).getSoLuongSanPham());

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTienGioHang.setText("Tổng tiền : " + decimalFormat.format(tongTien) + " VND");

    }

}
