package com.example.btl_android.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.example.btl_android.Adapter.AdapterGioHang;
import com.example.btl_android.DAO.DonDatUserDAO;
import com.example.btl_android.DAO.GioHangDAO;
import com.example.btl_android.DAO.SanPhamTrangChuDAO;
import com.example.btl_android.DTO.DonDatUserDTO;
import com.example.btl_android.DTO.GioHangDTO;
import com.example.btl_android.DTO.SanPhamTrangChuUserDTO;
import com.example.btl_android.MainActivity;
import com.example.btl_android.R;


public class FragGioHangUser extends Fragment {

    List<GioHangDTO> listGioHangFrag;
    GioHangDAO gioHangDAO;
    RecyclerView recyclerView;
    AdapterGioHang adapterGioHang;
    @SuppressLint("StaticFieldLeak")
    public static TextView tvTongTienGioHang;
    public AppCompatButton btnDatHang;
    public DonDatUserDAO donDatUserDAO;
    List<DonDatUserDTO> list;
    List<SanPhamTrangChuUserDTO> listSanPham;
    SanPhamTrangChuDAO sanPhamTrangChuDAO;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_gio_hang_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvTongTienGioHang = view.findViewById(R.id.tvTongTienGioHang);
        btnDatHang = view.findViewById(R.id.btnDatHangGioHang);

        sanPhamTrangChuDAO = new SanPhamTrangChuDAO(getContext());
        listSanPham = sanPhamTrangChuDAO.getAll();

        //Khởi tạo dao và đẩy thông tin lên recyclerView
        gioHangDAO = new GioHangDAO(getContext());
        listGioHangFrag = gioHangDAO.getAll();
        recyclerView = view.findViewById(R.id.rcvGioHang);
        adapterGioHang = new AdapterGioHang(getContext(), listGioHangFrag);
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();

        //Gọi hàm tính tổng tiền
        tinhTongTien();

        //Gọi hàm đặt hàng
        datHang();


        super.onViewCreated(view, savedInstanceState);
    }

    private void datHang() {

        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTraShowBottomSheet()) {

                    openShowBottomSheet();

                }

            }
        });


    }

    private boolean kiemTraShowBottomSheet() {

        if (listGioHangFrag.size() == 0) {

            Toast.makeText(getContext(), "Giỏ hàng không có sản phẩm"
                    , Toast.LENGTH_SHORT).show();
            return false;

        }


        return true;

    }

    private void openShowBottomSheet() {

        //Khởi tạo view Cho bottom sheet
        @SuppressLint("InflateParams") View view1 =
                LayoutInflater.from(requireContext())
                        .inflate(R.layout.layout_bottom_sheet_gio_hang_user
                                , null, false);

        BottomSheetDialog sheetDialog = new BottomSheetDialog(requireContext());
        sheetDialog.setContentView(view1);
        //Khởi tạo các view
        TextView tvThucDon, tvTongTien, tvThoiGian;
        EditText edTenNguoiNhan, edSoDienThoai, edDiaChi;
        AppCompatButton btnHuy, btnXacNhan;
        //Ánh xạ
        tvThucDon = view1.findViewById(R.id.tvThucPham);
        edTenNguoiNhan = view1.findViewById(R.id.edTenNguoiNhanBottomSheet);
        edDiaChi = view1.findViewById(R.id.edDiaChiBottomSheet);
        edSoDienThoai = view1.findViewById(R.id.edSoDienThoaiBottomSheet);
        btnHuy = view1.findViewById(R.id.btnHuy);
        btnXacNhan = view1.findViewById(R.id.btnXacNhanBottomSheet);
        tvTongTien = view1.findViewById(R.id.tvTongTienThanhToan);
        tvThoiGian = view1.findViewById(R.id.tvThoiGianBottomSheet);
        //Khởi tạo dao
        donDatUserDAO = new DonDatUserDAO(getContext());
        Log.d("TAG", "openShowBottomSheet: ====> "+MainActivity.currentAccount.getTenUser());
        list = donDatUserDAO.getAll().stream().filter(it -> it.getTenKhachHang().equals(MainActivity.currentAccount.getTenUser())).collect(Collectors.toList());


        //Lấy tên đăng nhập của tài khoản
        String tenDangNhap = requireActivity().getIntent().getStringExtra("tenDangNhap");

        //Dùng for lồng để lấy ra thông tin của các sản pham có trong gio hàng để đặt
        String hoaDon = "";
        int idSp = 0;

        for (int i = 0; i < AdapterGioHang.list.size(); i++) {
            for (int j = 0; j < listSanPham.size(); j++) {

                if (listSanPham.get(j).getTenSanPhamUser().equals(AdapterGioHang.list.get(i).getTenSanPham())) {

                    DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                    hoaDon += "- " + listSanPham.get(j).getTenSanPhamUser()
                            + ", Số lượng: " + AdapterGioHang.list.get(i).getSoLuongSanPham() + "Kg" +
                            ", " + "Đơn giá: " + decimalFormat.format(AdapterGioHang.list.get(i).getTongTienCuaSp()) + " VND" + "\n";
                    idSp += listSanPham.get(j).getIdSanPhamUser();
                    break;

                }
            }
        }


        tvThucDon.setText(hoaDon);//set những món có trong giỏ hàng
        edTenNguoiNhan.setText(tenDangNhap);// lấy  tên đăng nhập làm tên người nhận
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTien.setText("Tổng tiền thanh toán: " + decimalFormat.format(tinhTongTienBottomSheet()) + " VND");//Lấy tổng tiền
        //Lấy giờ và ngày đặt
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String ngayDat = simpleDateFormat.format(calendar.getTime());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH:mm");
        String gioDat = simpleDateFormat1.format(calendar.getTime());

        //set ngày và giờ đặt cho textview Trong bottom sheet
        tvThoiGian.setText("Thời gian: " + gioDat + " " + ngayDat);


        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetDialog.dismiss();
            }
        });
        //tạo biến finalHoaDon gán bằng với hóa đơn
        String finalHoaDon = hoaDon;
        int finalIdSp = idSp;
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (kiemTra()) {
                    String tenKhach = edTenNguoiNhan.getText().toString();
                    String soDienThoai = edSoDienThoai.getText().toString();
                    String diaChi = edDiaChi.getText().toString();

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_xac_nhan, null, false);
                    builder.setView(view);

                    AlertDialog dialog = builder.create();
                    Window window = dialog.getWindow();
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                    AppCompatButton btnXacNhan, btnHuy;

                    btnHuy = view.findViewById(R.id.btnHuyDialog);
                    btnXacNhan = view.findViewById(R.id.btnXacNhanDialog);

                    btnHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    btnXacNhan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                    DonDatUserDTO objDonDat = new DonDatUserDTO();
                    objDonDat.setIdSanPham(finalIdSp);
                    objDonDat.setTenKhachHang(tenKhach);
                    objDonDat.setTenSanPham(finalHoaDon);
                    objDonDat.setSoDienThoai(soDienThoai);
                    objDonDat.setDiaChi(diaChi);
                    objDonDat.setNgayDat(ngayDat);
                    objDonDat.setTrangThai("Đã đặt");
                    objDonDat.setTongTien(tinhTongTienBottomSheet());

                            long kq = donDatUserDAO.addRow(objDonDat);

                            if (kq > 0) {

                                Toast.makeText(getContext(), "Đặt thành công", Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(donDatUserDAO.getAll().stream().filter(it -> it.getTenKhachHang().equals(MainActivity.currentAccount.getTenUser())).collect(Collectors.toList()));
                                gioHangDAO.deleteAllDataGioHang();
                                AdapterGioHang.list.clear();
                                listGioHangFrag.clear();
                                tinhTongTien();
                                sheetDialog.dismiss();
                                dialog.dismiss();
                            } else {

                                Toast.makeText(getContext(), "Đặt thất bại", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

                    dialog.show();


                }
            }

            private boolean kiemTra() {

                if (edDiaChi.getText().toString().equals("") || edSoDienThoai.getText().toString().equals("")) {

                    Toast.makeText(getContext(), "Mời nhập thông tin cá nhân", Toast.LENGTH_SHORT).show();
                    return false;

                }

                String checkPhoneNumber = "^0[0-9]{9}$";
                if (!edSoDienThoai.getText().toString().matches(checkPhoneNumber)) {

                    Toast.makeText(getContext(), "Số điện thoại không hợp lệ", Toast.LENGTH_SHORT).show();
                    return false;

                }

                if (edDiaChi.getText().toString().length() < 5) {

                    Toast.makeText(getContext(), "Địa chỉ phải trên 5 ký tự"
                            , Toast.LENGTH_SHORT).show();
                    return false;

                }
                return true;


            }
        });

        sheetDialog.show();


    }

    //Hàm tính tổng tiền
    private void tinhTongTien() {
        int tongTien = 0;
        for (int i = 0; i < listGioHangFrag.size(); i++) {

            tongTien = tongTien + (listGioHangFrag.get(i).getGiaSanPham() * listGioHangFrag.get(i).getSoLuongSanPham());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvTongTienGioHang.setText("Tổng tiền : " + decimalFormat.format(tongTien) + " VND");

    }

    public int tinhTongTienBottomSheet() {
        int tongTienButtonSheet = 0;
        for (int i = 0; i < AdapterGioHang.list.size(); i++) {

            tongTienButtonSheet = tongTienButtonSheet + AdapterGioHang.list.get(i).getTongTienCuaSp();
        }
        return tongTienButtonSheet;
    }


}
