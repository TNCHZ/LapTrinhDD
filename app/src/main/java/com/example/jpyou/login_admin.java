package com.example.jpyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class login_admin extends AppCompatActivity {

    private EditText taiKhoan, matKhau;
    private TextView canhBao;
    private Button dangNhap;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        taiKhoan = findViewById(R.id.txtTaiKhoan_Admin);
        matKhau = findViewById(R.id.txtMatKhau_Admin);
        canhBao = findViewById(R.id.txtCanhBao);
        dangNhap = findViewById(R.id.btnLogin_Admin);


        dangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taiKhoan.getText().toString().equals("admin") && matKhau.getText().toString().equals("Admin@123"))
                {
                    Intent intent = new Intent(login_admin.this, menu_admin.class);
                    startActivity(intent);
                }
                else
                {
                    canhBao.setText("Tài khoản hoặc mật khẩu không chính xác");
                }
            }
        });
    }


}