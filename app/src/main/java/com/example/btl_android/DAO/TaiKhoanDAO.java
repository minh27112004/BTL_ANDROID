package com.example.btl_android.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.btl_android.DBHelper.MyDBHelper;
import com.example.btl_android.DTO.TaiKhoanDTO;

public class TaiKhoanDAO {

    private final MyDBHelper myDBHelper;
    private final SQLiteDatabase db;
    private final Context context;

    public TaiKhoanDAO(Context context){
        this.context = context;
        myDBHelper = new MyDBHelper(context);
        db = myDBHelper.getWritableDatabase();

    }


    //Hàm thêm tài khoản
    public long addRow(TaiKhoanDTO objTaiKhoan){

        ContentValues values = new ContentValues();
        values.put("ten_dang_nhap",objTaiKhoan.getTenDangNhap());
        values.put("mat_khau",objTaiKhoan.getMatKhau());

        return db.insert("tb_tai_khoan",null,values);

    }



    //Hàm đổi mật khẩu
    public int updateRow(TaiKhoanDTO objTaiKhoan){

        ContentValues values = new ContentValues();
        values.put("mat_khau",objTaiKhoan.getMatKhau());

        return db.update("tb_tai_khoan",values,"id_tai_khoan=?"
                ,new String[]{objTaiKhoan.getIdTaiKhoan()+""});


    }
    public int updateMatKhau(String tenDangNhap,String matKhau){

        ContentValues values = new ContentValues();
        values.put("mat_khau",matKhau);

        return db.update("tb_tai_khoan",values,"ten_dang_nhap=?",new String[]{tenDangNhap});

    }

    //Ham cap nhật thông tin người dùng
    public int updateThongTin(TaiKhoanDTO objTaiKhoan) {
        ContentValues values = new ContentValues();
        values.put("tenUser", objTaiKhoan.getTenUser());
        values.put("email", objTaiKhoan.getEmail());
        values.put("soDienThoai", objTaiKhoan.getSoDienThoai());
        values.put("gioiTinh", objTaiKhoan.getGioiTinh());
        values.put("ngaySinh", objTaiKhoan.getNgaySinh());

        return db.update("tb_tai_khoan", values, "id_tai_khoan=?",
                new String[]{objTaiKhoan.getIdTaiKhoan()+""});
    }

    //Ham lay thong tin ngươi dùng

    public List<TaiKhoanDTO> getAllThongTin() {
        List<TaiKhoanDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_tai_khoan", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int idTk = cursor.getInt(0);
                String tenUser = cursor.getString(1);
                String email = cursor.getString(2);
                String soDienThoai = cursor.getString(3);
                String gioiTinh = cursor.getString(4);
                String ngaySinh = cursor.getString(5);

                TaiKhoanDTO objTaiKhoan = new TaiKhoanDTO();
                objTaiKhoan.setIdTaiKhoan(idTk);
                objTaiKhoan.setTenUser(tenUser);
                objTaiKhoan.setEmail(email);
                objTaiKhoan.setSoDienThoai(soDienThoai);
                objTaiKhoan.setGioiTinh(gioiTinh);
                objTaiKhoan.setNgaySinh(ngaySinh);

                list.add(objTaiKhoan);
                cursor.moveToNext();
            }
        }
        return list;
    }

    // Ham lấy thông tin để hiển thị lên EditText
    public TaiKhoanDTO getThongTinTheoTenDangNhap(String tenDangNhap) {
        SQLiteDatabase db = myDBHelper.getReadableDatabase();
        Cursor cursor = db.query("tb_tai_khoan", null, "ten_dang_nhap=?", new String[]{tenDangNhap}, null, null, null);
        TaiKhoanDTO userInfo = null;
        if (cursor != null && cursor.moveToFirst()){
            int idTaiKhoan = cursor.getInt(cursor.getColumnIndexOrThrow("id_tai_khoan"));
            String tenUser = cursor.getString(cursor.getColumnIndexOrThrow("tenUser"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String soDienThoai = cursor.getString(cursor.getColumnIndexOrThrow("soDienThoai"));
            String gioiTinh = cursor.getString(cursor.getColumnIndexOrThrow("gioiTinh"));
            String ngaySinh = cursor.getString(cursor.getColumnIndexOrThrow("ngaySinh"));
            userInfo = new TaiKhoanDTO(idTaiKhoan, tenUser, email, soDienThoai, gioiTinh, ngaySinh);
        }
        cursor.close();
//        db.close();
        return userInfo;
    }



    //Hàm getAll tài khoản
    public List<TaiKhoanDTO> getAll(){

        List<TaiKhoanDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_tai_khoan",null);

        if (cursor != null && cursor.getCount() > 0) {

            cursor.moveToFirst();
            while (!cursor.isAfterLast()){

                int id = cursor.getInt(0);
                int idKhach = cursor.getInt(1);
                String tenDangNhap = cursor.getString(2);
                String matKhau = cursor.getString(3);

                TaiKhoanDTO objTaiKhoan = new TaiKhoanDTO();
                objTaiKhoan.setIdTaiKhoan(id);
                objTaiKhoan.setIdKhachHang(idKhach);
                objTaiKhoan.setTenDangNhap(tenDangNhap);
                objTaiKhoan.setMatKhau(matKhau);

                list.add(objTaiKhoan);

                cursor.moveToNext();


            }

        }


        return list;

    }



    //Hàm check tài khoản login
    public  Boolean checkTaiKhoan(String tenTaiKhoan,String matKhau){

        Cursor cursor =
                db.rawQuery("SELECT * FROM tb_tai_khoan WHERE ten_dang_nhap=? AND mat_khau=?"
                        ,new String[]{tenTaiKhoan,matKhau});

        if (cursor.getCount() > 0){

            return true;

        }else {

            return false;

        }


    }

    //Hàm check tên tài khoản để quên mật khẩu
    public Boolean checkUserName(String tenTaiKhoan){

        Cursor cursor =
                db.rawQuery("SELECT * FROM tb_tai_khoan WHERE ten_dang_nhap=?"
                            ,new String[]{tenTaiKhoan});

        if (cursor.getCount() > 0) {

            return true;

        }else {

            return false;

        }

    }

    //Hàm check mật khẩu
    public Boolean checkPass(String passOld){
        Cursor cursor = db.rawQuery("SELECT * FROM tb_tai_khoan WHERE mat_khau=?", new String[]{passOld});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public static boolean isValidPassword(String password) {
        // Kiểm tra nếu password rỗng hoặc null
        if (password == null || password.isEmpty()) {
            return false;
        }

        // Kiểm tra ký tự đầu tiên có phải chữ cái in hoa không
        if (!Character.isUpperCase(password.charAt(0))) {
            return false;
        }

        // Kiểm tra xem có chứa ít nhất một ký tự đặc biệt không
        String specialCharacters = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        for (char c : password.toCharArray()) {
            if (specialCharacters.indexOf(c) != -1) {
                return true; // Tìm thấy ký tự đặc biệt, trả về true
            }
        }

        // Không tìm thấy ký tự đặc biệt
        return false;
    }



}
