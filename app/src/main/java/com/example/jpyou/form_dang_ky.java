package com.example.jpyou;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jpyou.Admin.AdminListUser;
import com.example.jpyou.Admin.AdminMenu;
import com.example.jpyou.User.UserInterface;

public class form_dang_ky extends AppCompatActivity {

    private Button btnDangKy ;
    private TextView hoTen, ngaySinh, soDT, CCCD, Email, diaChi, matKhau, canhBaoMatKhau;
    private String gioiTinh;
    private RadioButton rd;

    private MyDatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.form_dang_ky);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //click button "Đăng Ký" quay lại login_User
        btnDangKy = findViewById(R.id.btnDangKy);


        hoTen = findViewById(R.id.txtName_DangKy);
        rd = findViewById(R.id.rdNam);
        gioiTinh = rd.isChecked() == true ? "Nam" : "Nữ";
        ngaySinh = findViewById(R.id.txtBirthDay_Dangky);
        soDT = findViewById(R.id.txtPhone_DangKy);
        diaChi = findViewById(R.id.txtAddress_DangKy);
        Email = findViewById(R.id.txtEmail_DangKy);
        CCCD = findViewById(R.id.txtCCCD);
        matKhau = findViewById(R.id.txtMatKhau_DangKy);
        canhBaoMatKhau = findViewById(R.id.txtXacNhanMatKhau);

        convert();
    }

    private void convert() {
        db = new MyDatabaseHelper(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addNguoiDung(
                        soDT.getText().toString(),
                        matKhau.getText().toString(),
                        hoTen.getText().toString(),
                        gioiTinh,
                        ngaySinh.getText().toString(),
                        diaChi.getText().toString(),
                        Email.getText().toString(),
                        soDT.getText().toString(),
                        CCCD.getText().toString(),
                        " ",
                        "Bệnh nhân"
                );

                Intent intent_user = new Intent(form_dang_ky.this, UserInterface.class);
                intent_user.putExtra("TaiKhoanID", db.getTaiKhoanIDByPhoneNumber(Integer.parseInt(soDT.getText().toString())));
                startActivity(intent_user);
            }
        });
    }
}