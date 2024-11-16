package com.example.jpyou.Admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jpyou.MyDatabaseHelper;
import com.example.jpyou.R;

public class AdminMenu extends AppCompatActivity {

    private Button btn_taoNgDung, btn_listNgDung, btn_ListNhanVien, btn_listKHD;
    MyDatabaseHelper db;
    private admininform adm;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_menu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = new MyDatabaseHelper(this);
        btn_taoNgDung = findViewById(R.id.btn_taoNgDung);
        btn_listNgDung = findViewById(R.id.btn_listNgDung);
        btn_ListNhanVien = findViewById(R.id.btn_listNhanVien);
        btn_listKHD = findViewById(R.id.btn_listNgKhongHoatDong);

        adm = new admininform(getIntent().getStringExtra("taikhoan"),
                getIntent().getStringExtra("matkhau"),
                db.getTenChucNang(Integer.parseInt(getIntent().getStringExtra("taikhoanID"))));

        if (adm.getChucNang().equals("Quản lý đăng nhập")) {
            btn_taoNgDung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(AdminMenu.this, AdminCreateUser.class);
                    startActivity(intent);
                }
            });
        }

        btn_listNgDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenu.this, AdminListUser.class);
                intent.putExtra("vaitro", "Bệnh nhân");
                startActivity(intent);
            }
        });

        btn_ListNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenu.this, AdminListUser.class);
                intent.putExtra("vaitro", "Nhân viên");
                startActivity(intent);
            }
        });

        btn_listKHD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminMenu.this, AdminListUser.class);
                intent.putExtra("vaitro", " ");
                startActivity(intent);
            }
        });

    }

}
