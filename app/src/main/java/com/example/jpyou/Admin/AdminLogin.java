package com.example.jpyou.Admin;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jpyou.MyDatabaseHelper;
import com.example.jpyou.R;


public class AdminLogin extends AppCompatActivity {

    private EditText taiKhoan, matKhau;
    private TextView canhBao;
    private Button dangNhap;
    private MyDatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        taiKhoan = findViewById(R.id.txtTaiKhoan_Admin);
        matKhau = findViewById(R.id.txtMatKhau_Admin);
        canhBao = findViewById(R.id.txtCanhBao);
        dangNhap = findViewById(R.id.btnLogin_Admin);
        db = new MyDatabaseHelper(this);

        dangNhap.setOnClickListener(view -> {
            // Lấy kết quả trả về từ phương thức verifyPassword
            int taiKhoanID = Integer.parseInt(db.verifyPassword(taiKhoan.getText().toString(), matKhau.getText().toString(), "Admin"));

            if (taiKhoanID != -1) {
                Intent intent = new Intent(AdminLogin.this, AdminMenu.class);
                intent.putExtra("taikhoan", taiKhoan.getText().toString());
                intent.putExtra("matkhau", matKhau.getText().toString());
                intent.putExtra("taikhoanID", Integer.toString(taiKhoanID));
                startActivity(intent);
            } else {
                canhBao.setText("Tài khoản hoặc mật khẩu không chính xác");
            }
        });
    }


}