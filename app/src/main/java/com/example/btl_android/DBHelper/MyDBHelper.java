package com.example.btl_android.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHelper extends SQLiteOpenHelper {

    public  static  final  String DB_NAME = "QuanLy";
    public  static  final  int DB_VERSION = 1;

    public MyDBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sqlLoaiSanPham = "CREATE TABLE tb_loai_san_pham (\n" +
                "    id_san_pham       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    ten_loai_san_pham TEXT    NOT NULL\n" +
                ");\n";
        db.execSQL(sqlLoaiSanPham);

        String sqlSanPham = "CREATE TABLE tb_san_pham (\n" +
                "    id_san_pham      INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    id_loai_san_pham INTEGER REFERENCES tb_loai_san_pham (id_san_pham),\n" +
                "    ten_san_pham     TEXT    NOT NULL,\n" +
                "    don_gia          INTEGER NOT NULL,\n" +
                "    img_url          TEXT NOT NULL,\n" +
                "    mo_ta            TEXT    NOT NULL,\n" +
                "    so_luong         INTEGER ,\n" +
                "    nhacungcap         TEXT NOT NULL,\n" +
                "    loai         TEXT \n" +
                ");\n";
        db.execSQL(sqlSanPham);
        db.execSQL("insert into tb_san_pham values (2,2,'Quả bơ',17000,'img_qua_bo','Quả bơ là một loại trái cây có giá trị dinh dưỡng cao, chứa nhiều chất dinh dưỡng, bao gồm:\n" +
                "\n" +
                "Chất béo không bão hòa đơn: Đây là loại chất béo tốt cho sức khỏe, giúp giảm nguy cơ mắc bệnh tim mạch, đột quỵ, và tiểu đường. Vitamin C: Có tác dụng tăng cường hệ miễn dịch, chống oxy hóa. Vitamin E: Có tác dụng chống oxy hóa, bảo vệ tế bào. Kali: Có tác dụng điều hòa huyết áp. Magie: Có tác dụng tốt cho xương khớp. Folate: Có tác dụng tốt cho phụ nữ mang thai. Chất xơ: Có tác dụng hỗ trợ tiêu hóa, giảm cholesterol.',100,'Nguyễn Toàn','Quả')");
        db.execSQL("insert into tb_san_pham values (1,2,'Quả táo đỏ',13000,'img_qua_tao','Quả táo là một loại quả có giá trị dinh dưỡng cao. Quả táo chứa nhiều vitamin và khoáng chất, bao gồm: Vitamin C: có tác dụng tăng cường hệ miễn dịch, chống oxy hóa, Vitamin A: có tác dụng tốt cho mắt, da, và tóc, Vitamin E: có tác dụng chống oxy hóa, bảo vệ tế bào, Kali: có tác dụng điều hòa huyết áp, Chất xơ: có tác dụng hỗ trợ tiêu hóa, giảm cholesterol',100,'Nguyễn Công','Quả')");
        db.execSQL("insert into tb_san_pham values (3,2,'Rau cải thảo',15000,'img_cai_thao_trang_chu','Rau cải thảo là một loại rau có giá trị dinh dưỡng cao, chứa nhiều chất dinh dưỡng, bao gồm: Vitamin C: Có tác dụng tăng cường hệ miễn dịch, chống oxy hóa. Vitamin A: Có tác dụng tốt cho mắt, da, và tóc. Vitamin K: Có tác dụng đông máu, giúp ngăn ngừa chảy máu. Sắt: Có tác dụng tạo máu, giúp phòng ngừa thiếu máu. Chất xơ: Có tác dụng hỗ trợ tiêu hóa, giảm cholesterol.',100,'Nguyễn Duy','Rau')");
        db.execSQL("insert into tb_san_pham values (4,2,'Nho hồng nhật',18000,'img_qua_nho','Quả nho là một loại quả có giá trị dinh dưỡng cao, chứa nhiều chất dinh dưỡng, bao gồm:\n" +
                "\n" +
                "Vitamin C: Có tác dụng tăng cường hệ miễn dịch, chống oxy hóa.\n" +
                "Vitamin A: Có tác dụng tốt cho mắt, da, và tóc.\n" +
                "Vitamin K: Có tác dụng đông máu, giúp ngăn ngừa chảy máu.\n" +
                "Kali: Có tác dụng điều hòa huyết áp.\n" +
                "Magie: Có tác dụng tốt cho xương khớp.\n" +
                "Folate: Có tác dụng tốt cho phụ nữ mang thai.\n" +
                "Chất xơ: Có tác dụng hỗ trợ tiêu hóa, giảm cholesterol.\n" +
                "Khoáng chất: Nho có chứa nhiều khoáng chất cần thiết cho cơ thể, như: kali, magie, sắt, kẽm,...',5,'Nguyễn Nam','Quả')");
        db.execSQL("insert into tb_san_pham values (5,2,'Dâu tây',12000,'img_dau_tay','Quả nho là một loại quả có giá trị dinh dưỡng cao, chứa nhiều chất dinh dưỡng, bao gồm:\n" +
                "\n" +
                "Vitamin C: Có tác dụng tăng cường hệ miễn dịch, chống oxy hóa.\n" +
                "Vitamin A: Có tác dụng tốt cho mắt, da, và tóc.\n" +
                "Vitamin K: Có tác dụng đông máu, giúp ngăn ngừa chảy máu.\n" +
                "Kali: Có tác dụng điều hòa huyết áp.\n" +
                "Magie: Có tác dụng tốt cho xương khớp.\n" +
                "Folate: Có tác dụng tốt cho phụ nữ mang thai.\n" +
                "Chất xơ: Có tác dụng hỗ trợ tiêu hóa, giảm cholesterol.\n" +
                "Khoáng chất: Nho có chứa nhiều khoáng chất cần thiết cho cơ thể, như: kali, magie, sắt, kẽm,...',100,'Nguyễn Kiên','Quả')");
        db.execSQL("insert into tb_san_pham values (6,2,'Rau cải chíp',10000,'img_cai_chip_trang_chu','Cải chíp là một loại rau có giá trị dinh dưỡng cao, chứa nhiều chất dinh dưỡng, bao gồm:\n" +
                "\n" +
                "Vitamin C: Có tác dụng tăng cường hệ miễn dịch, chống oxy hóa.\n" +
                "Vitamin A: Có tác dụng tốt cho mắt, da, và tóc.\n" +
                "Vitamin K: Có tác dụng đông máu, giúp ngăn ngừa chảy máu.\n" +
                "Sắt: Có tác dụng tạo máu, giúp phòng ngừa thiếu máu.\n" +
                "Chất xơ: Có tác dụng hỗ trợ tiêu hóa, giảm cholesterol.',100,'Nguyễn Nam','Rau')");
        db.execSQL("insert into tb_san_pham values (7,2,'Bắp cải',150000,'img_bap_cai_trang_chu','Rau bắp cải là một loại rau có giá trị dinh dưỡng cao, chứa nhiều chất dinh dưỡng, bao gồm:\n" +
                "\n" +
                "Vitamin C: Có tác dụng tăng cường hệ miễn dịch, chống oxy hóa.\n" +
                "Vitamin K: Có tác dụng đông máu, giúp ngăn ngừa chảy máu.\n" +
                "Sắt: Có tác dụng tạo máu, giúp phòng ngừa thiếu máu.\n" +
                "Chất xơ: Có tác dụng hỗ trợ tiêu hóa, giảm cholesterol.\n" +
                "Khoáng chất: Bắp cải có chứa nhiều khoáng chất cần thiết cho cơ thể, như: kali, magie, canxi, kẽm,...',20,'Nguyễn Cường','Rau')");
        db.execSQL("insert into tb_san_pham values (8,2,'Cà chua',10000,'img_ca_chua','Cà chua là một loại quả có giá trị dinh dưỡng cao, chứa nhiều chất dinh dưỡng, bao gồm:\n" +
                "\n" +
                "Vitamin C: Có tác dụng tăng cường hệ miễn dịch, chống oxy hóa.\n" +
                "Vitamin A: Có tác dụng tốt cho mắt, da, và tóc.\n" +
                "Vitamin K: Có tác dụng đông máu, giúp ngăn ngừa chảy máu.\n" +
                "Lycopene: Là một chất chống oxy hóa mạnh, có tác dụng ngăn ngừa ung thư, tim mạch, và các bệnh mãn tính khác.\n" +
                "Chất xơ: Có tác dụng hỗ trợ tiêu hóa, giảm cholesterol.\n" +
                "Khoáng chất: Cà chua có chứa nhiều khoáng chất cần thiết cho cơ thể, như: kali, magie, canxi, kẽm,...',3,'Nguyễn Huy','Quả')");
        db.execSQL("insert into tb_san_pham values (9,2,'Quả cam',13000,'img_qua_cam','\n" +
                "Cam là một loại quả có múi, có nguồn gốc từ Đông Nam Á. Đây là một loại quả phổ biến trên thế giới, được trồng ở nhiều quốc gia khác nhau, trong đó có Việt Nam.\n" +
                "\n" +
                "Quả cam có hình tròn hoặc hình bầu dục, có kích thước trung bình từ 5-10 cm. Vỏ cam có màu xanh khi còn non và chuyển sang màu vàng cam khi chín. Vỏ cam sần sùi, có nhiều nốt nhỏ.\n" +
                "\n" +
                "Phần thịt cam màu vàng cam, có vị ngọt hoặc chua ngọt tùy theo giống cam. Thịt cam có nhiều múi, mỗi múi có nhiều tép. Tép cam có vị ngọt thanh, có tác dụng giải khát, bổ sung vitamin C và các dưỡng chất khác cho cơ thể.',7,'Nguyễn Anh','Quả')");
        db.execSQL("insert into tb_san_pham values (10,2,'Quả chuối',14000,'img_qua_chuoi','Chuối là một loại quả giàu dinh dưỡng, chứa nhiều vitamin C, vitamin B6, kali, chất xơ,... Đây là một loại quả tốt cho sức khỏe, có tác dụng tăng cường hệ miễn dịch, chống oxy hóa, ngăn ngừa bệnh tim mạch,...',8,'Nguyễn','Quả')");
        db.execSQL("insert into tb_san_pham values (19,2,'Củ đậu',13000,'img_cu_dau','Củ đậu hay còn gọi là sắn nước, có vị ngọt, mát, thường ăn sống hoặc ép lấy nước. Củ này giúp thanh nhiệt, giải độc, tốt cho hệ tiêu hóa.',14,'Nguyễn Anh','Củ')");
        db.execSQL("insert into tb_san_pham values (16,2,'Củ nghệ',22000,'img_cu_nghe','Củ nghệ là loại gia vị phổ biến có màu vàng cam, có tính kháng viêm, chống oxy hóa mạnh. Được dùng trong các món kho, xào và là vị thuốc dân gian trong chữa viêm loét dạ dày.',6,'Nguyễn Anh','Củ')");

        String sqlThongTinKhachHang = "CREATE TABLE tb_thong_tin_khach_hang (\n" +
                "    id_khach_hang  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    ten_khach_hang TEXT    NOT NULL,\n" +
                "    so_dien_thoai  TEXT    NOT NULL,\n" +
                "    gioi_tinh      TEXT    NOT NULL,\n" +
                "    ngay_sinh      TEXT    NOT NULL,\n" +
                "    email          TEXT    NOT NULL,\n" +
                "    dia_chi        TEXT    NOT NULL\n"+
                ");\n";
        db.execSQL(sqlThongTinKhachHang);

        String sqlHoaDon = "CREATE TABLE tb_hoa_don (\n" +
                "    id_hoa_don    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    id_san_pham   INTEGER REFERENCES tb_san_pham (id_san_pham),\n" +
                "    ten_khach_hang TEXT REFERENCES tb_khach_hang (ten_khach_hang)," +
                "    danh_sach_san_pham TEXT," +
                "    so_dien_thoai TEXT    NOT NULL,\n" +
                "    dia_chi       TEXT    NOT NULL,\n" +
                "    ngay_dat      TEXT    NOT NULL,\n" +
                "    trang_thai    TEXT    NOT NULL,\n" +
                "    tong_tien     INTEGER NOT NULL\n" +
                ");\n";
        db.execSQL(sqlHoaDon);


        String sqlTaiKhoan = "CREATE TABLE tb_tai_khoan (\n" +
                "    id_tai_khoan  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    id_khach_hang INTEGER REFERENCES tb_khach_hang (id_khach_hang),\n" +
                "    ten_dang_nhap TEXT    NOT NULL,\n" +
                "    mat_khau      TEXT    NOT NULL ," +
                "tenUser TEXT, " +
                "email TEXT, " +
                "soDienThoai TEXT, " +
                "gioiTinh TEXT," +
                "diaChi TEXT," +
                " ngaySinh TEXT\n" +
                ");\n";
        db.execSQL(sqlTaiKhoan);

        db.execSQL("INSERT INTO tb_tai_khoan VALUES(1, 1, 'admin', '12345', 'Nguyễn Đắc Minh', 'minh@gmail.com', '0253536354', 'Nam','Thái Bình', '27/11/2004')");



        String sqlChiTietHoaDon = "CREATE TABLE tb_chi_tiet_hoa_don (\n" +
                "    id_chi_tiet_hoa_don INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    id_san_pham         INTEGER NOT NULL\n" +
                "                                REFERENCES tb_san_pham (id_san_pham),\n" +
                "    id_hoa_don          INTEGER NOT NULL\n" +
                "                                REFERENCES tb_hoa_don (id_hoa_don),\n" +
                "    ten_san_hoa_pham    TEXT    NOT NULL,\n" +
                "    so_luong            INTEGER NOT NULL,\n" +
                "    tong_tien_hoa_don   TEXT    NOT NULL\n" +
                ");\n";
        db.execSQL(sqlChiTietHoaDon);

        String sqlGioHang = "CREATE TABLE tb_gio_hang (\n" +
                "    id           INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    id_san_pham  INTEGER REFERENCES tb_san_pham (id_san_pham),\n" +
                "    ten_san_pham TEXT,\n" +
                "    gia_san_pham TEXT,\n" +
                "    img_url      TEXT," +
                "    so_luong_sp INTEGER," +
                "    tongTien1Sp INTEGER" +
                ");\n";
        db.execSQL(sqlGioHang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE tb_loai_san_pham");
        db.execSQL("DROP TABLE  tb_san_pham");
        db.execSQL("DROP TABLE tb_thong_tin_khach_hang");
        db.execSQL("DROP TABLE tb_hoa_don");
        db.execSQL("DROP TABLE tb_tai_khoan");
        db.execSQL("DROP TABLE tb_chi_tiet_hoa_don");
        db.execSQL("DROP TABLE tb_gio_hang");

        onCreate(db);
    }
}
