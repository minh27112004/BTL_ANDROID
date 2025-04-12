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
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.btl_android.ChiTietSanPhamActivity;
import com.example.btl_android.DAO.GioHangDAO;
import com.example.btl_android.DAO.SanPhamTrangChuDAO;
import com.example.btl_android.DTO.DanhSachSanPhamDTO;
import com.example.btl_android.DTO.GioHangDTO;
import com.example.btl_android.DTO.SanPhamTrangChuUserDTO;
import com.example.btl_android.R;

public class AdapterDanhSachSanPham extends RecyclerView.Adapter<AdapterDanhSachSanPham.ViewHolder> {

    ArrayList<DanhSachSanPhamDTO> list;
    private Context context;
    private List<GioHangDTO> listGioHang;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    GioHangDAO gioHangDAO;
    private List<SanPhamTrangChuUserDTO> listAllSp;
    private SanPhamTrangChuDAO sanPhamTrangChuDAO;

    public AdapterDanhSachSanPham(ArrayList<DanhSachSanPhamDTO> list, Context context) {
        this.list = list;
        this.context = context;
        this.sanPhamTrangChuDAO = new SanPhamTrangChuDAO(context);
        this.gioHangDAO = new GioHangDAO(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rau_danh_sach_san_pham_use, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int currentPosition = holder.getAdapterPosition();
        listAllSp = sanPhamTrangChuDAO.getAll();
        DanhSachSanPhamDTO id = list.get(currentPosition);
        String nameImg = list.get(currentPosition).getImg_url();
        int resourceImg = ((Activity) context).getResources().getIdentifier(nameImg, "drawable", ((Activity) context).getPackageName());

        holder.imgdanhsachsp.setImageResource(resourceImg);
        holder.tvtendanhsachsanpham.setText(list.get(currentPosition).getTen_san_pham());
        holder.tvgiadanhsachsanpham.setText(decimalFormat.format(list.get(currentPosition).getDon_gia()) + " VND / 1kg");


        String base64 = list.get(position).getImg_url();
        try {
            byte[] imageBytes = Base64.decode(base64, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            if (bitmap != null) {
                holder.imgdanhsachsp.setImageBitmap(bitmap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        listGioHang = gioHangDAO.getAll();
        holder.ivGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenSp = id.getTen_san_pham();
                int giaSp = id.getDon_gia();
                String tenAnh = id.getImg_url();

                if((listAllSp.get(currentPosition).getSoLuongSp())<=0){
                    Toast.makeText(context, "Sản phẩm tạm hết!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Kiểm tra trùng lặp
                boolean isExist = false;
                for (GioHangDTO item : listGioHang) {
                    if (item.getTenSanPham().equals(tenSp)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    Toast.makeText(context, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                } else {
                    GioHangDTO objGioHang = new GioHangDTO();
                    objGioHang.setTenSanPham(tenSp);
                    objGioHang.setGiaSanPham(giaSp);
                    objGioHang.setImgSanPham(tenAnh);
                    objGioHang.setSoLuongSanPham(1);
                    objGioHang.setTongTienCuaSp(giaSp);

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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(((Activity) context), ChiTietSanPhamActivity.class);

                remenBerProduct(id.getTen_san_pham(), id.getDon_gia(), id.getImg_url(), id.getMo_ta());
                ((Activity) context).startActivity(intent);


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


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgdanhsachsp, ivGioHang;
        TextView tvtendanhsachsanpham, tvgiadanhsachsanpham;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgdanhsachsp = itemView.findViewById(R.id.imgDanhSachSanPham);
            tvtendanhsachsanpham = itemView.findViewById(R.id.tvTenDanhSachSanPham);
            tvgiadanhsachsanpham = itemView.findViewById(R.id.tvGiaSanPhamDanhSachSanPham);
            ivGioHang = itemView.findViewById(R.id.ivIconGioHangItemRau);
        }
    }


}
