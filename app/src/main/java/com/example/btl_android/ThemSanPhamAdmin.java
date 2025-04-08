package com.example.btl_android;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.btl_android.DAO.TrangChuAdminDAO;

public class ThemSanPhamAdmin extends AppCompatActivity {


    private ImageView imgThemsp,ivBack;
    private TextView captureTxt;
    EditText edtThemtensp, edtThemgiasp,edtNhaCungCap,edtMoTa;
    AppCompatButton btnThemsp;
    TrangChuAdminDAO trangChuAdminDAO;
    Spinner spnCategoryThemSp;


    private ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    int reseltCode = result.getResultCode();
                    if(result.getData() != null){
                        ClipData clipData = result.getData().getClipData();
                        Uri uri;

                        if(clipData != null){
                            int count = clipData.getItemCount();
                            for (int i =0; i<count; i++){
                            uri = clipData.getItemAt(i).getUri();
                            handlerImageView(uri);
                            }
                        }
                        else {
                            uri = result.getData().getData();
                            handlerImageView(uri);
                        }
                    }

                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham_admin);

        imgThemsp = findViewById(R.id.imgThemSp);
        ivBack = findViewById(R.id.ivBackThemSanPham);
        captureTxt = findViewById(R.id.captireTXT);
        edtThemgiasp = findViewById(R.id.edtThemGiaSp);
        edtThemtensp = findViewById(R.id.edtThemTenSp);
        edtNhaCungCap = findViewById(R.id.edtNhaCungCap);
        edtMoTa = findViewById(R.id.edtMoTa);
        btnThemsp = findViewById(R.id.btnThemSp);
        spnCategoryThemSp = findViewById(R.id.spnCategoryThemSp);
        getDataAdmin(spnCategoryThemSp);
        trangChuAdminDAO = new TrangChuAdminDAO(this);

        btnThemsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tensp = edtThemtensp.getText().toString().trim();
                int giasp = Integer.parseInt(edtThemgiasp.getText().toString().trim());
                String tenloai = (String) spnCategoryThemSp.getSelectedItem();
                String nhacungcap = edtNhaCungCap.getText().toString().trim();
                String mota = edtMoTa.getText().toString().trim();
                // Trong phương thức onClick của btnThemsp

                if(tensp.equals("") || String.valueOf(giasp).equals("")){
                    Toast.makeText(ThemSanPhamAdmin.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else if(!isImgaSelected){
                    Toast.makeText(ThemSanPhamAdmin.this, "Vui lòng chọn ảnh", Toast.LENGTH_SHORT).show();

                }else {
                    boolean check = trangChuAdminDAO.ThemSanPham(tensp,giasp,base64,mota,tenloai,nhacungcap);

                    if(check){
                        Toast.makeText(ThemSanPhamAdmin.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }else {
                        Toast.makeText(ThemSanPhamAdmin.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        captureTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
                finish();

            }
        });

        getDataAdmin(spnCategoryThemSp);
    }

    private  void getDataAdmin(Spinner spinnerAdmin){

        ArrayList<String> list = new ArrayList<>();
        list.add(new String("Rau"));
        list.add(new String("Củ"));
        list.add(new String("Quả"));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
        spinnerAdmin.setAdapter(adapter);
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(intent);
    }

    private boolean isImgaSelected = false;

    private void handlerImageView(Uri imgUri){
            try {
                Bitmap bitmap = convertBitmapFromUri(imgUri);
                if(bitmap!= null){
                    imgThemsp.setImageBitmap(bitmap);
                    isImgaSelected = true;
                }

            }catch (Exception e){
                e.printStackTrace();
                isImgaSelected = false;
            }
    }

    private  String base64;
    private Bitmap convertBitmapFromUri(Uri imgUri)throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(imgUri);
        byte[] bytes = getByteFromInputStream(inputStream);

        base64 = Base64.encodeToString(bytes, Base64.NO_WRAP);
        byte[] decodeString = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeString, 0 , decodeString.length);
    }

    private byte[] getByteFromInputStream(InputStream inputStream)throws IOException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] bufferByte = new byte[bufferSize];
        int realSize;
        while ((realSize = inputStream.read(bufferByte))!=-1){
                byteArrayOutputStream.write(bufferByte, 0, realSize);
        }
        return byteArrayOutputStream.toByteArray();
    }


}